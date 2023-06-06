package src.model;

import javax.swing.*;
import java.io.*;


public class FileHandler {


    public static File loadFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();
        return file;
    }

    public static int saveFile(File file, Trainer trainer, int[] offsets) {
        saveTrainerInfo(file, trainer, offsets[0]);
        updateChecksums(file, offsets);
        return 0;
    }

    private static int updateChecksums(File file, int[] offsets) {
        long checksum = 0;
        int upperBits = 0;
        int lowerBits = 0;
        byte[] buffer = new byte[4];
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            for (int i = 0; i < Data.SECTIONS; i++) {
                raf.seek(offsets[i]*Data.SECTION_SIZE);
                for(int j = 0;j<getValidationBytes(i)/4;j++){
                    raf.read(buffer);
                    checksum += byteArrayToInt(buffer,4);
                }
                raf.skipBytes(Data.OFFSET_TO_FOOTER - getValidationBytes(i));
                raf.skipBytes(2);
                upperBits = (int) (checksum >> 16);
                lowerBits = (int) (checksum & 0xFFFF);
                checksum = (upperBits+lowerBits) & 0xFFFF;
                raf.write((int)(checksum & 0xFF));
                raf.write((int)(checksum >> 8));
                raf.close();
                return 0;
            }
        } catch (FileNotFoundException e) {
            return -1;
        } catch (IOException e) {
            return -1;
        } catch (NullPointerException e) {
            return -1;
        }
        return -1;
    }

    private static int saveTrainerInfo(File file, Trainer trainer, int section0Offset) {
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.seek(section0Offset * Data.SECTION_SIZE);
            for (int i = 0; i < trainer.getName().length(); i++) {
                raf.write(TextHandler.convertCharToByte(trainer.getName().charAt(i)));
            }
            for(int i = 0; i<=Data.NAME_MAX_LENGTH - trainer.getName().length();i++){
                raf.write(0xFF);
            }
            raf.write(trainer.getGender());
            raf.skipBytes(1);
            raf.write(trainer.getTrainerId() & 0xFF);
            raf.write(trainer.getTrainerId() >> 8);
            raf.write(trainer.getSecretId() & 0xFF);
            raf.write(trainer.getSecretId() >> 8);
            raf.close();
            return 0;
        } catch (FileNotFoundException e) {
            return -1;
        } catch (IOException e) {
            return -1;
        } catch (NullPointerException e) {
            return -1;
        }
    }



    public static Trainer createTrainer(File file, int offset) {
        Trainer trainer = new Trainer();
        byte[] buffer = new byte[2];
        try {
            FileInputStream stream = new FileInputStream(file);
            try {
                stream.skip(offset * Data.SECTION_SIZE);
                String trainerName = "";
                for (int i = 0; i < 8; i++) {
                    int temp = stream.read();
                    if (temp != 0xFF) {//padding after the string is done
                        trainerName += TextHandler.convertByteToChar(temp);
                    }
                }
                trainer.setName(trainerName);
                trainer.setGender(stream.read());
                stream.skip(1);
                stream.read(buffer, 0, 2);
                trainer.setTrainerId((int)byteArrayToInt(buffer, 2));
                stream.read(buffer, 0, 2);
                trainer.setSecretId((int)byteArrayToInt(buffer, 2));
                stream.close();
                return trainer;
            } catch (IOException e) {
                return null;
            }
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public static int findSectionOffset(File file, int sectionId, int saveBlock) {
        byte[] buffer = new byte[2];
        try {
            FileInputStream stream = new FileInputStream(file);
            if (saveBlock == 1) {
                stream.skip(Data.SECTION_SIZE * Data.SECTIONS);
            }
            for (int i = 0; i < Data.SECTIONS; i++) {
                if (i == 0) {
                    stream.skip(Data.SECTION_SIZE - 12);
                    stream.read(buffer);
                } else {
                    stream.skip(Data.SECTION_SIZE - 2);//-2 so it offsets the stream.read when it reads 2 bytes
                    stream.read(buffer);
                }
                if (byteArrayToInt(buffer, 2) == sectionId) {
                    stream.close();
                    if (saveBlock == 1) {
                        i += Data.SECTIONS;
                    }
                    return i;
                }
            }
            stream.close();
        } catch (FileNotFoundException e) {
            return -1;
        } catch (IOException e) {
            return -1;
        }
        return -1;
    }


    public static int getRecentSaveBlock(File file) {//0 = Recent save in first block, 1 = Recent save in second block
        byte[] buffer = new byte[4];
        int firstSaveValue = 0;
        try {
            FileInputStream stream = new FileInputStream(file);
            stream.skip((Data.SECTION_SIZE * Data.SECTIONS) - 4);
            stream.read(buffer);
            firstSaveValue = (int)byteArrayToInt(buffer, 4);
            stream.skip((Data.SECTION_SIZE * Data.SECTIONS) - 4);
            stream.read(buffer);
            stream.close();
            if (firstSaveValue > byteArrayToInt(buffer, 4)) {
                return 0;
            } else {
                return 1;
            }
        } catch (FileNotFoundException e) {
            return -1;
        } catch (IOException e) {
            return -1;
        }
    }

    private static long byteArrayToInt(byte[] arr, int bytesToRead) {//little endian *btw java uses signed integers, I was overflowing and not realizing. why can't java have unsigned ints?
        long value = 0;
        for (int i = 0; i < bytesToRead; i++) {
            long temp = 0;
            int num = arr[i];
            for (int j = 0; j < 8; j++) {
                temp += (num & 0x01) * Math.pow(2, j + (8 * i));
                num = num >> 1;
            }
            value += temp;
        }
        return value;
    }

    private static int getValidationBytes(int section) {
        switch (section) {
            case 0:
                return 3884;
            case 1:
                return 3968;
            case 2:
                return 3968;
            case 3:
                return 3968;
            case 4:
                return 3848;
            case 5:
                return 3968;
            case 6:
                return 3968;
            case 7:
                return 3968;
            case 8:
                return 3968;
            case 9:
                return 3968;
            case 10:
                return 3968;
            case 11:
                return 3968;
            case 12:
                return 3968;
            case 13:
                return 2000;
        }
        return -1;
    }

}

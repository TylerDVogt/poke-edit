package src.model;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class FileHandler {


    public static void handleFile(File file) {
        populateData(file);
    }

    private static void populateData(File file){
        int saveBlock = getRecentSaveBlock(file);

        if(saveBlock == -1){
            JOptionPane.showMessageDialog(null, "Error parsing file", "Error", JOptionPane.ERROR_MESSAGE);
        }
        App.getData().setSaveBlock(saveBlock);
        populateTrainerInfo(file);
    }

    private static void populateTrainerInfo(File file){
        App.getData().setSection0Offset(findSectionOffset(file,0));
        byte[] buffer = new byte[7];
        try {
            FileInputStream stream = new FileInputStream(file);
            try {
                long i = stream.skip(App.getData().getSection0Offset()*Data.SECTION_SIZE);
                stream.skip(10);
                stream.read(buffer,0,2);
                System.out.println(byteArrayToInt(buffer,4));

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error parsing file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error parsing file", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    private static int findSectionOffset(File file, int sectionId){
        byte[] buffer = new byte[2];
        try {
            FileInputStream stream = new FileInputStream(file);
            if(App.getData().getSaveBlock() == 1){
                stream.skip(Data.SECTION_SIZE*Data.SECTIONS);
            }
            for(int i = 0;i < Data.SECTIONS; i++){
                if(i == 0){
                    stream.skip(Data.SECTION_SIZE-12);
                    stream.read(buffer);
                }else{
                    stream.skip(Data.SECTION_SIZE - 2);//-2 so it offsets the stream.read when it reads 2 bytes
                    stream.read(buffer);
                }
                if(byteArrayToInt(buffer,2) == sectionId) {
                    stream.close();
                    if(App.getData().getSaveBlock() == 1){
                        i += Data.SECTIONS;
                    }
                    return i;
                }
            }
            stream.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error parsing file", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }



    private static int getRecentSaveBlock(File file){//0 = Recent save in first block, 1 = Recent save in second block
        byte[] buffer = new byte[4];
        int firstSaveValue = 0;
        try {
            FileInputStream stream = new FileInputStream(file);
            stream.skip((Data.SECTION_SIZE*Data.SECTIONS)-4);
            stream.read(buffer);
            firstSaveValue = byteArrayToInt(buffer,4);
            stream.skip((Data.SECTION_SIZE*Data.SECTIONS)-4);
            stream.read(buffer);
            stream.close();
            if(firstSaveValue > byteArrayToInt(buffer,4)){
                return 0;
            }else{
                return 1;
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error parsing file", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return -1;
    }

    private static int byteArrayToInt(byte[] arr, int bytesToRead){
        int value = 0;
        for(int i = 0;i<bytesToRead;i++){
            int temp = 0;
            int num = arr[i];
            for(int j = 0;j<8;j++){
                temp += (num & 0x01)*Math.pow(2,j+(8*i));
                num = num >> 1;
            }
            value += temp;
        }
        return value;
    }

}

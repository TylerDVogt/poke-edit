package src.model;

import java.io.File;

public class Data {

    public final static int SECTION_SIZE = 4096;
    public final static int SECTIONS = 14;

    public final static int OFFSET_TO_FOOTER = 4084;

    public final static int NAME_MAX_LENGTH = 7;

    public final static int MAX_TRAINER_ID = 65535;
    private File file;

    private int saveBlock = 0;//0 for save block A, 1 for save block B


    private int section0Offset;

    public int getSection0Offset() {
        return section0Offset;
    }

    public void setSection0Offset(int section0Offset) {
        this.section0Offset = section0Offset;
    }





    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getSaveBlock() {
        return saveBlock;
    }

    public void setSaveBlock(int saveBlock) {
        this.saveBlock = saveBlock;
    }
}

package src.model;

import java.io.File;

public class Data {

    public final static int SECTION_SIZE = 4096;
    public final static int SECTIONS = 14;
    private File file;

    private int saveBlock = 0;
    private Trainer trainer;

    private int section0Offset;

    public int getSection0Offset() {
        return section0Offset;
    }

    public void setSection0Offset(int section0Offset) {
        this.section0Offset = section0Offset;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
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

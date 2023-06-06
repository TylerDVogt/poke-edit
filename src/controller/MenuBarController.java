package src.controller;

import src.model.App;
import src.model.Data;
import src.model.FileHandler;
import src.model.Trainer;
import src.view.MenuBarView;
import src.view.RootView;
import src.view.TrainerInfoView;

import javax.swing.*;
import java.io.File;

public class MenuBarController{

    private MenuBarView menuBarView;
    public MenuBarController(MenuBarView menuBarView){
      this.menuBarView = menuBarView;
    }

    public void loadFile(){
        TrainerInfoView trainerView = menuBarView.getRootView().getTrainerInfoView();
        File file = FileHandler.loadFile();
        if(file == null){
            return;
        }
        int saveBlock = FileHandler.getRecentSaveBlock(file);
        if(saveBlock == -1){
            JOptionPane.showMessageDialog(null, "Error parsing file", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        App.getData().setSaveBlock(saveBlock);

        int section0Offset = FileHandler.findSectionOffset(file,0,saveBlock);
        if(section0Offset == -1){
            JOptionPane.showMessageDialog(null, "Error parsing file", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        App.getData().setSection0Offset(section0Offset);

        Trainer trainer = FileHandler.createTrainer(file,section0Offset);
        if(trainer == null){
            JOptionPane.showMessageDialog(null, "Error parsing file", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        trainerView.getNameTextField().setText(trainer.getName());
        if(trainer.getGender() == 0){
            trainerView.getBoyRadioButton().setSelected(true);
        }else{
            trainerView.getGirlRadioButton().setSelected(true);
        }
        trainerView.getTrainerIdTextField().setText(""+trainer.getTrainerId());
        trainerView.getSecretIdTextField().setText(""+trainer.getSecretId());
        App.getData().setFile(file);
    }

    public void saveFile(){
        RootView rootView = menuBarView.getRootView();
        if(rootView.getTrainerInfoView().getNameTextField().getText().isEmpty() || rootView.getTrainerInfoView().getTrainerIdTextField().getText().isEmpty() ||
                rootView.getTrainerInfoView().getSecretIdTextField().getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        Trainer trainer = new Trainer();
        TrainerInfoView trainerView = rootView.getTrainerInfoView();
        trainer.setName(rootView.getTrainerInfoView().getNameTextField().getText());
        if(rootView.getTrainerInfoView().getBoyRadioButton().isSelected()){
            trainer.setGender(0);
        }else{
            trainer.setGender(1);
        }
        trainer.setTrainerId(Integer.parseInt(trainerView.getTrainerIdTextField().getText()));
        trainer.setSecretId(Integer.parseInt(trainerView.getSecretIdTextField().getText()));

        int[] offsets = new int[Data.SECTIONS];
        offsets[0] = App.getData().getSection0Offset();
        if(FileHandler.saveFile(App.getData().getFile(), trainer, offsets) == -1){
            JOptionPane.showMessageDialog(null, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Save successful", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
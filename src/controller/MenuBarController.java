package src.controller;

import src.model.FileHandler;

import javax.swing.*;
import java.io.File;

public class MenuBarController{

    public static void openFile(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();
        if(file == null){
            return;
        }
        FileHandler.handleFile(file);
    }

}
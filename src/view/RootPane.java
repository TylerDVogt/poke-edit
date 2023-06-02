package src.view;

import javax.swing.*;

public class RootPane{

    private JPanel rootPane;
    private MenuBarPane menuBarPane;
    private TrainerInfoPane trainerInfoPane;

    public RootPane(){
        rootPane = new JPanel();
        menuBarPane = new MenuBarPane();
        trainerInfoPane = new TrainerInfoPane();
        buildRootPane();
    }

    private void buildRootPane(){
        rootPane.add(trainerInfoPane.getTrainerPanel());
    }

    public MenuBarPane getMenuBarPane() {
        return menuBarPane;
    }

    public JPanel getRootPane() {
        return rootPane;
    }

}

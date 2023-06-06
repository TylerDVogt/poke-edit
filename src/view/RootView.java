package src.view;

import javax.swing.*;

public class RootView {

    private JPanel rootPanel;
    private MenuBarView menuBarView;
    private TrainerInfoView trainerInfoView;

    public RootView(Window window){
        rootPanel = new JPanel();
        menuBarView = new MenuBarView(this);
        trainerInfoView = new TrainerInfoView();
        buildView();
    }

    private void buildView(){
        rootPanel.add(trainerInfoView.getTrainerPanel());
    }

    public MenuBarView getMenuBarView() {
        return menuBarView;
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public TrainerInfoView getTrainerInfoView(){
        return trainerInfoView;
    }

}

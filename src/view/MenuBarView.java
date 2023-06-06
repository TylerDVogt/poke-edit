package src.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import src.controller.MenuBarController;

public class MenuBarView {

    private RootView rootView;
    private MenuBarController menuBarController;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem loadMenuItem;

    private JMenuItem saveMenuItem;

    public MenuBarView(RootView rootview){
        this.rootView = rootview;
        menuBarController = new MenuBarController(this);
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        loadMenuItem = new JMenuItem("Load");
        saveMenuItem = new JMenuItem("Save");
        buildMenuBar();
    }

    private void buildMenuBar(){
        buildActionListeners();
        fileMenu.add(loadMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);
    }

    private void buildActionListeners(){
        loadMenuItem.addActionListener(e -> menuBarController.loadFile());
        saveMenuItem.addActionListener(e -> menuBarController.saveFile());
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public RootView getRootView(){
        return rootView;
    }



}

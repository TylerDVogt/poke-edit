package src.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import src.controller.MenuBarController;

public class MenuBarPane{
    
    private JMenuBar menuBar;
    private JMenu menu1;
    private JMenuItem menuItem1;

    public MenuBarPane(){
        menuBar = new JMenuBar();
        menu1 = new JMenu("File");
        menuItem1 = new JMenuItem("Load");
        buildMenuBar();
    }

    private void buildMenuBar(){
        buildActionListeners();
        menu1.add(menuItem1);
        menuBar.add(menu1);
    }

    private void buildActionListeners(){
        menuItem1.addActionListener(e -> MenuBarController.openFile());
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    

}

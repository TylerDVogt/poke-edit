package src.view;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
    
    private JFrame frame;

    public Window(){
        frame = new JFrame();
        buildWindow();
    }

    private void buildWindow(){
        RootPane rootPane = new RootPane();
        frame.add(rootPane.getRootPane());

        frame.setJMenuBar(rootPane.getMenuBarPane().getMenuBar());
        frame.setPreferredSize(new Dimension(800,600));
        frame.setTitle("Poke-Edit");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    
}

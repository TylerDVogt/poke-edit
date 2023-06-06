package src.view;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
    
    private JFrame frame;
    private RootView rootView;

    public Window(){
        frame = new JFrame();
        buildWindow();
    }

    private void buildWindow(){
        rootView = new RootView(this);
        frame.add(rootView.getRootPanel());

        frame.setJMenuBar(rootView.getMenuBarView().getMenuBar());
        frame.setPreferredSize(new Dimension(800,600));
        frame.setTitle("Poke-Edit");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

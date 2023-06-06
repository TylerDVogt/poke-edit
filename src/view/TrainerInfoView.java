package src.view;
import src.model.App;
import src.model.Data;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class TrainerInfoView {

    private JPanel trainerPanel;
    private JTextField nameTextField, trainerIdTextField, secretIdTextField;
    private JRadioButton boyRadioButton;
    private JRadioButton girlRadioButton;
    private ButtonGroup genderButtonGroup;


    public TrainerInfoView(){
        trainerPanel = new JPanel();
        nameTextField = new JTextField();
        boyRadioButton = new JRadioButton("Boy");
        girlRadioButton = new JRadioButton("Girl");
        genderButtonGroup = new ButtonGroup();
        trainerIdTextField = new JTextField();
        secretIdTextField = new JTextField();
        buildView();
    }

    private void buildView(){
        trainerPanel.setLayout(new GridLayout(0,2,0,5));
        trainerPanel.add(new JLabel("<html><u>Trainer Info</u></html>"));
        trainerPanel.add(new JLabel());
        trainerPanel.add(new JLabel("Name:"));
        trainerPanel.add(nameTextField);
        trainerPanel.add(new JLabel("Gender:"));
        JPanel genderPane = new JPanel();
        genderPane.add(boyRadioButton);
        genderPane.add(girlRadioButton);
        trainerPanel.add(genderPane);
        genderButtonGroup.add(boyRadioButton);
        genderButtonGroup.add(girlRadioButton);
        trainerPanel.add(new JLabel("Trainer Id:"));
        trainerPanel.add(trainerIdTextField);
        trainerPanel.add(new JLabel("Secret Id:"));
        trainerPanel.add(secretIdTextField);
        buildListeners();
    }

    public void buildListeners(){
        nameTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(nameTextField.getText().length() > Data.NAME_MAX_LENGTH){
                    nameTextField.setText(nameTextField.getText().substring(0,Data.NAME_MAX_LENGTH));
                }
            }
        });
        trainerIdTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if((e.getKeyChar() < 48 || e.getKeyChar() > 57) && e.getKeyCode() != KeyEvent.VK_BACK_SPACE){
                    trainerIdTextField.setText(trainerIdTextField.getText().substring(0,trainerIdTextField.getText().length()-1));
                }else if(Integer.parseInt(trainerIdTextField.getText()) > Data.MAX_TRAINER_ID){
                    trainerIdTextField.setText("65535");
                }
            }
        });

        secretIdTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if((e.getKeyChar() < 48 || e.getKeyChar() > 57) && e.getKeyCode() != KeyEvent.VK_BACK_SPACE){
                    secretIdTextField.setText(secretIdTextField.getText().substring(0,secretIdTextField.getText().length()-1));
                }else if(Integer.parseInt(secretIdTextField.getText()) > Data.MAX_TRAINER_ID){
                    secretIdTextField.setText("65535");
                }
            }
        });
    }


    public JPanel getTrainerPanel() {
        return trainerPanel;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getTrainerIdTextField() {
        return trainerIdTextField;
    }

    public JTextField getSecretIdTextField() {
        return secretIdTextField;
    }

    public ButtonGroup getGenderButtonGroup() {
        return genderButtonGroup;
    }

    public JRadioButton getBoyRadioButton() {
        return boyRadioButton;
    }

    public JRadioButton getGirlRadioButton() {
        return girlRadioButton;
    }
}
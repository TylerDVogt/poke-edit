package src.view;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class TrainerInfoPane{

    private JPanel trainerPanel;
    private JTextField nameTextField, trainerIdTextField, secretIdTextField;
    private JRadioButton boyRadioButton;
    private JRadioButton girlRadioButton;
    private ButtonGroup genderButtonGroup;


    public TrainerInfoPane(){
        trainerPanel = new JPanel();
        nameTextField = new JTextField();
        boyRadioButton = new JRadioButton("Boy");
        girlRadioButton = new JRadioButton("Girl");
        genderButtonGroup = new ButtonGroup();
        trainerIdTextField = new JTextField();
        secretIdTextField = new JTextField();
        buildPanel();
    }

    private void buildPanel(){
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

    }

    public JPanel getTrainerPanel() {
        return trainerPanel;
    }

}
package Compulsory;

import javax.swing.*;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel label; // we’re drawing regular polygons
    JSpinner sidesField; // number of sides
    JComboBox colorCombo; // the color of the shape
    JSpinner angle;
    JSpinner radius;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    public ConfigPanel(MainFrame frame,int circle) {
        this.frame = frame;
        initCircle();
    }

    private void initCircle(){
        //create the label and the spinner
        sidesField = new JSpinner(new SpinnerNumberModel(300, 300, 300, 1));
        sidesField.setValue(300); //default number of sides
        JLabel radiusLabel = new JLabel("Radius:");
        radius = new JSpinner(new SpinnerNumberModel(100, 1, 1000, 1));
        radius.setValue(30);

        String[] colors={"Black","Random"};
        colorCombo = new JComboBox(colors);
        //create the colorCombo, containing the values: Random and Black
    // ...TODO
        add(radiusLabel);
        add(radius);
        add(colorCombo);
    }

    private void init() {
        //create the label and the spinner
        JLabel sidesLabel = new JLabel("Number of sides:");
        sidesField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        sidesField.setValue(6); //default number of sides

        JLabel angleLabel = new JLabel("Rotation angle:");
        angle = new JSpinner(new SpinnerNumberModel(0,0,360,1));
        angle.setValue(0);

        String[] colors={"Black","Random"};
        colorCombo = new JComboBox(colors);
        //create the colorCombo, containing the values: Random and Black
// ...TODO
        add(sidesLabel); //JPanel uses FlowLayout by default
        add(sidesField);
        add(colorCombo);
        add(angleLabel);
        add(angle);
    }
}
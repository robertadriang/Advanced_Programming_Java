package Compulsory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormSelector extends JPanel{
    final MainFrame frame;
    JComboBox formForm;

    public FormSelector(MainFrame frame) {
        this.frame = frame;
        init(frame);
    }


    private void init(MainFrame main) {

        String[] forms={"Polygon","Circle","Line"};
        formForm = new JComboBox(forms);
        JLabel formLabel = new JLabel("Forms:");
        formForm.setEditable(true);
        formForm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Get the source of the component, which is our combo
                // box.
                JComboBox comboBox = (JComboBox) event.getSource();

                // Print the selected items and the action command.
                Object selected = comboBox.getSelectedItem();
                System.out.println("Selected Item  = " + selected);
                String command = event.getActionCommand();
                System.out.println("Action Command = " + command);
                // Detect whether the action command is "comboBoxChanged"
                 if ("comboBoxChanged".equals(command)) {
                    System.out.println("User has selected an item " +
                            "from the combo box.");
                    if(((String)(selected)).equals("Circle")){ ;
                        main.replaceWithCircle();
                    }
                    else if(((String)(selected)).equals("Polygon")){
                        main.replaceWithPolygon();
                    }
                    else if(((String)(selected)).equals("Line")){
                        main.replaceWithCircle();
                    }
                }
            }
        });
        //create the colorCombo, containing the values: Random and Black
        // ...TODO
        add(formLabel); //JPanel uses FlowLayout by default
        add(formForm);
    }

}

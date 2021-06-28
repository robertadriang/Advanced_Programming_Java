package Compulsory;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;
    FormSelector formSelector;
    boolean circle=false;
    public MainFrame() {
        super("My Drawing Application");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //create the components
        canvas = new DrawingPanel(this);
        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel( this);
        formSelector = new FormSelector(this);
        // ...TODO

        //arrange the components in the container (frame)
        //JFrame uses a BorderLayout by default
         //this is BorderLayout.CENTER
        add(configPanel,BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        add(formSelector,BorderLayout.WEST);
        add(controlPanel,BorderLayout.SOUTH);
        //...TODO

        //invoke the layout manager
        pack();
    }

    public void replaceWithCircle(){
        circle=true;
        this.getContentPane().remove(configPanel);
        pack();
        configPanel = new ConfigPanel(this,1);
        this.getContentPane().add(configPanel,BorderLayout.NORTH);
        pack();
    }

    public void replaceWithPolygon(){
        circle=false;
        this.getContentPane().remove(configPanel);
        pack();
        configPanel=new ConfigPanel(this);
        this.getContentPane().add(configPanel,BorderLayout.NORTH);
        pack();
    }

}
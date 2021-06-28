package Compulsory;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");
    //create all buttons (Load, Reset, Exit)
    // ...TODO

    public ControlPanel(MainFrame frame) {
        this.frame = frame; init();
    }

    private void init() {
        //change the default layout manager (just for fun)
        setLayout(new GridLayout(1, 4));

        this.add(saveBtn);
        this.add(loadBtn);
        this.add(resetBtn);
        this.add(exitBtn);

        //add all buttons ...TODO
        //configure listeners for all buttons
        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(this::load);
        resetBtn.addActionListener(this::reset);
        exitBtn.addActionListener(this::exit);
        //...TODO
    }

    private void save(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("*.png","png"));
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            try
            {
                ImageIO.write(frame.canvas.image, "PNG", file);
            }
            catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        else
        {
            System.out.println("No file chosen.");
        }
    }

    private void load(ActionEvent e){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("*.png","png"));
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            try
            {
                frame.canvas.shapes.clear();
                frame.canvas.clearImage();
                frame.canvas.loadImage(file.getPath());
            }
            catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        else
        {
            System.out.println("No file chosen.");
        }
    }

    private void reset(ActionEvent e){
        frame.canvas.shapes.clear();
        frame.canvas.clearImage();
    }

    private void exit(ActionEvent e){
        System.exit(0);
    }

 //...TODO
}

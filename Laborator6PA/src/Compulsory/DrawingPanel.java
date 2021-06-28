package Compulsory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 1920, H = 880;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the "tools" needed to draw in the image
    ArrayList<Form> shapes;
    Point pressed;
    Point current;
    private Graphics2D g2;
    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init(frame);
    }

    public void clearImage()
    {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);
        repaint();
    }

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        clearImage();
    }

    public void loadImage(String path) throws IOException {
        var image= ImageIO.read(new File(path));
        graphics.drawImage(image,0,0,this);
        repaint();
    }

    private void init(MainFrame main) {
        shapes=new ArrayList<Form>();
        setPreferredSize(new Dimension(W, H)); //don’t use setSize. Why?
        setBorder(BorderFactory.createEtchedBorder()); //for fun
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pressed = new Point(e.getX(),e.getY());
                    if(SwingUtilities.isLeftMouseButton(e)){
                        if(main.circle==false){
                            drawShape(e.getX(), e.getY()); repaint();
                        }
                        else{
                            drawCircle(e.getX(), e.getY()); repaint();
                        }

                    }else if(SwingUtilities.isRightMouseButton(e)){
                        delete(e.getX(),e.getY());
                    }
            } //Can’t use lambdas, JavaFX does a better job in these caseses
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                drawCircle(e.getX(), e.getY()); repaint();
            }
        });

    }

    private boolean onSegment(Point p, Point q, Point r){
        if (q.x <= Math.max(p.x, r.x) &&
                q.x >= Math.min(p.x, r.x) &&
                q.y <= Math.max(p.y, r.y) &&
                q.y >= Math.min(p.y, r.y))
        {
            return true;
        }
        return false;
    }

    private int orientation(Point p, Point q, Point r){
        int val=(q.y-p.y)*(r.x-q.x)-(q.x-p.x)*(r.y-q.y);
        if(val==0){
            return 0;
        }
        return (val>0)? 1:2;
    }

    private int intersects(Point p1,Point q1,Point p2,Point q2){
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        if (o1 != o2 && o3 != o4)
        {
            return 1;
        }

        if (o1 == 0 && onSegment(p1, p2, q1))
        {
            return 1;
        }

        if (o2 == 0 && onSegment(p1, q2, q1))
        {
            return 1;
        }

        if (o3 == 0 && onSegment(p2, p1, q2))
        {
            return 1;
        }

        if (o4 == 0 && onSegment(p2, q1, q2))
        {
            return 1;
        }

        return 0;
    }

    private boolean isInside(Form shape,int x,int y){
        Point click = new Point(x,y);
        Point margin = new Point(W,y);
        int intersectionCount=0,i=0;
        do{
            int nextPoint=(i+1)%shape.sides;
            Point pointA=new Point(shape.form.xpoints[i],shape.form.ypoints[i]);
            Point pointB=new Point(shape.form.xpoints[nextPoint],shape.form.ypoints[nextPoint]);
            if(intersects(click,margin,pointA,pointB)==1){
               if(orientation(pointA,click,pointB)==0){
                   return onSegment(pointA,click,pointB);
               }
               ++intersectionCount;
            }
            i=nextPoint;
        }while(i!=0);
        return (intersectionCount%2==1);
    }

    private void delete(int x,int y){
        try{
            for (Form shape : shapes) {
                if(isInside(shape,x,y)){
                    deleteShape(shape);
                    System.out.println("Inside");
                }else{
                    System.out.println("Outside");
                }
            }
        }
        catch(Exception e){

        }

    }

    private void deleteShape(Form shape) {
        Color color=Color.white;
        graphics.setColor(color);
        RegularPolygon p=new RegularPolygon(shape.xCenter, shape.yCenter, shape.radius, shape.sides);
        p.rotate((Integer) frame.configPanel.angle.getValue());
        shapes.remove(shape);
        graphics.fill(p);
        repaint();
    }

    private void drawCircle(int x,int y){
        int radius=(int) frame.configPanel.radius.getValue();
        int sides=(int) frame.configPanel.sidesField.getValue();
        Color color;
        if(frame.configPanel.colorCombo.getSelectedItem().equals("Black")) {
            color = Color.black;
        }else{
            int r=(int)(Math.random()*255);
            int g=(int)(Math.random()*255);
            int b=(int)(Math.random()*255);
            color = new Color(r,g,b);

        }
        //...TODO; //create a transparent random Color.
        graphics.setColor(color);
        RegularPolygon p=new RegularPolygon(x, y, radius, sides);
        shapes.add(new Form(p,x,y,radius,sides));
        graphics.fill(p);
    }

    private void drawShape(int x, int y) {
        int radius = 1 + (int)(Math.random()*100); //... TODO; //generate a random number
        int sides = (int) frame.configPanel.sidesField.getValue(); //...TODO; //get the value from UI (in ConfigPanel)
        Color color;
        if(frame.configPanel.colorCombo.getSelectedItem().equals("Black")) {
            color = Color.black;
        }else{
            int r=(int)(Math.random()*255);
            int g=(int)(Math.random()*255);
            int b=(int)(Math.random()*255);
            color = new Color(r,g,b);

        }
        //...TODO; //create a transparent random Color.
        graphics.setColor(color);
        RegularPolygon p=new RegularPolygon(x, y, radius, sides);
        p.rotate((Integer) frame.configPanel.angle.getValue());
        shapes.add(new Form(p,x,y,radius,sides));
        graphics.fill(p);
    }

    @Override
    public void update(Graphics g) { } //Why did I do that? R: De unde sa stiu?

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
    //...NEXT SLIDE
}

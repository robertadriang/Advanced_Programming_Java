package Compulsory;

public class Form {
    RegularPolygon form;
    int xCenter;
    int yCenter;
    int radius;
    int sides;

    public Form(RegularPolygon form, int xCenter, int yCenter, int radius, int sides) {
        this.form = form;
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.radius = radius;
        this.sides = sides;
    }
}

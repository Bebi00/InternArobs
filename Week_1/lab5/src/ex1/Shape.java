package ex1;

public abstract class Shape {
    protected String color="red";
    protected boolean filled = true;

    public Shape(){}
    public Shape(String color,boolean filled){
        this.color = color;
        this.filled = filled;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    @Override
    public String toString() {
        String message = "filled";
        if (filled) {
            message = "Not " + message;
        }
        return "A Shape with color of " + color + " and " + message;
    }

    public abstract double getArea();
    public abstract double getPerimeter();
}

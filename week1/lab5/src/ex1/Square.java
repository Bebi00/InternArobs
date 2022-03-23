package ex1;

public class Square extends Rectangle{
    public Square(){}
    public Square(double side){
        super(side,side);
    }


    public void setLength(double side) {
        super.setLength(side);
    }

    public void setWidth(double side) {
        super.setWidth(side);
    }

    public String toString() {
        return "A Square with side="+getLength()+", which is a subclass of "+super.toString();
    }
}

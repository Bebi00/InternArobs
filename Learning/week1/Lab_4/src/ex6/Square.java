package ex6;

public class Square extends Rectangle{
    public Square(){}
    public Square(double side){
        super(side,side);
    }

    @Override
    public void setLength(double side) {
        super.setLength(side);
    }

    @Override
    public void setWidth(double side) {
        super.setWidth(side);
    }

    @Override
    public String toString() {
        return "A Square with side="+getLength()+", which is a subclass of "+super.toString();
    }
}

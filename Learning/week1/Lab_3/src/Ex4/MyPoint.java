package Ex4;

public class MyPoint {
    int x;
    int y;

    MyPoint(){
        this.x = 0;
        this.y = 0;
    }

    MyPoint(int x ,int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setXY(int x,int y){
        setX(x);
        setY(y);
    }

    @Override
    public String toString() {
        return "("+x+","+y+")";
    }

    public double distance(int x,int y){
        return Math.sqrt(Math.pow(this.x-x,2)+Math.pow(this.y-y,2));
    }

    public double distance(MyPoint another){
        return distance(another.getX(),another.getY());
    }

}

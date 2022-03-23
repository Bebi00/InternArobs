package Ex1;

public class Robot {
    int x;

    Robot(){
        this.x=1;
    }

    void change(int k){
        if(k>=1){
            this.x +=k;
        }
    }

    @Override
    public String toString() {
        return "Robot{" +
                "x=" + x +
                '}';
    }
}

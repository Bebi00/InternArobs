package Ex5;

public class Flower{
    static int noObj;
    int petal;
    Flower(){
        System.out.println("Flower has been created!");
        noObj++;
    }

    public static int getNoObj() {
        return noObj;
    }

    public static void main(String[] args) {
        Flower[] garden = new Flower[5];
        for(int i =0;i<5;i++){
            Flower f = new Flower();
            garden[i] = f;
        }

        System.out.println(Flower.noObj);
    }


}

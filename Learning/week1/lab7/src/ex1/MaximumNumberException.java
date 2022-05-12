package ex1;

public class MaximumNumberException extends Exception{
    private int maxObjects;

     MaximumNumberException(int maxObjects,String msg){
         super(msg);
         this.maxObjects=maxObjects;
     }

    public int getMaxObjects() {
        return maxObjects;
    }
}

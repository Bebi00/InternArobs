package ex1;

class CofeeMaker {
    int maxObjects = 10;
    Cofee makeCofee(int coffeeNumber) throws MaximumNumberException {
        System.out.println("Make a coffe");
        int t = (int)(Math.random()*100);
        int c = (int)(Math.random()*100);
        if(coffeeNumber > maxObjects){
            throw new MaximumNumberException(maxObjects,"The maximum number of coffees has been produced.");
        }
        Cofee cofee = new Cofee(t,c);
        return cofee;
    }

}//.class

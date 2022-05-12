package ex4;

import ex3.LightSensor;
import ex3.TemperatureSensor;

public class Controller {
    TemperatureSensor tempSensor=new TemperatureSensor();
    LightSensor lightSensor=new LightSensor();
    static Controller controller;

    private Controller(){}

    public static Controller getController() {
        if(controller == null){
            controller = new Controller();
        }
        return controller;
    }

    public void control() throws InterruptedException {
        for (int i=0;i<20;i++){
            System.out.println("Temp Value= "+tempSensor.readValue());
            System.out.println("Light Value= "+lightSensor.readValue());
            Thread.sleep(1000);
        }
    }
}

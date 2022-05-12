package ex3;

public class Controller {
    TemperatureSensor tempSensor=new TemperatureSensor();
    LightSensor lightSensor=new LightSensor();

    public void control() throws InterruptedException {
        for (int i=0;i<20;i++){
            System.out.println("Temp Value= "+tempSensor.readValue());
            System.out.println("Light Value= "+lightSensor.readValue());
            Thread.sleep(1000);
        }
    }
}

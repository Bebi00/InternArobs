package ex2;

public class ProxyImage implements Image{

    private Image object;
    private String fileName;

    public ProxyImage(String fileName,Image object){
        this.fileName = fileName;
        this.object = object;
    }

    @Override
    public void display() {
        if(object.getClass() == RealImage.class){
            RealImage realImage = (RealImage)object;
            realImage.display();
        }
        else if(object.getClass() == RotatedImage.class){
            RotatedImage rotatedImage = (RotatedImage) object;
            rotatedImage.display();
        }
    }
}


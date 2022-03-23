package ex2;

public class ProxyImage implements Image{

    private Image image;
    private String fileName;

    public ProxyImage(String fileName,Image image){
        this.fileName = fileName;
        this.image = image;
    }

    @Override
    public void display() {
        if(image.getClass() == RealImage.class){
            RealImage realImage = (RealImage)image;
            realImage.display();
        }
        else if(image.getClass() == RotatedImage.class){
            RotatedImage rotatedImage = (RotatedImage) image;
            rotatedImage.display();
        }
    }
}


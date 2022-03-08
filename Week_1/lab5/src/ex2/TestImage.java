package ex2;

public class TestImage {
    public static void main(String[] args) {
        RealImage realImage =new RealImage("real.txt");
        realImage.display();

        RotatedImage rotatedImage = new RotatedImage("rotated.txt");
        rotatedImage.display();

        ProxyImage proxyImage = new ProxyImage("proxy.txt",realImage);
        proxyImage.display();

        ProxyImage proxyImage1 = new ProxyImage("proxy.txt",rotatedImage);
        proxyImage1.display();
    }
}

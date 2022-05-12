package ex2;

public class TestAuthor {
    public static void main(String[] args) {
        Author author = new Author("Paula","paula@gmail.com",'f');
        System.out.println(author.toString());
        System.out.println(author.getName()+", "+author.getGender()+", "+author.getEmail());
    }
}

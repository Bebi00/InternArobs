package ex4;

import ex2.Author;

public class TestBook {
    public static void main(String[] args) {
        Author author = new Author("Paul","paul@arobs.com",'m');
        Author author1 = new Author("Paula","paula@arobs.com",'f');
        Author[] authors = {author};
        Author[] authors1 = {author,author1};

        Book book = new Book("The fellowship of the Ring",authors,70);
        Book book1 = new Book("1984",authors1,30,5);

        System.out.println(book.toString());
        book.printAuthors();
        System.out.println(book1.toString());
        System.out.println(book1.getQtyInStock());
        book1.printAuthors();

    }
}

package ex3;

import ex2.Author;

public class TestBook {
    public static void main(String[] args) {
        Author author = new Author("Paul","paul@arobs.com",'m');
        Book book = new Book("The fellowship of the Ring",author,70);
        Book book1 = new Book("1984",author,30,5);

        System.out.println(book.toString());
        System.out.println(book.getQtyInStock());
        System.out.println(book1.toString());
        System.out.println(book1.getQtyInStock());

    }
}

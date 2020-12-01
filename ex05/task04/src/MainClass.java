import java.util.*;

public class MainClass {
    public static void main(String[] args) {
        List<Book> bookList = new LinkedList<Book>();
        String[] bookName ={"Java", "XML", "JSP", "C++", "J2ME", "OS", "DBMS"};
        double[] bookPrice = {29, 21, 22, 29, 34, 32, 29};
        Book[] book =new Book [bookName.length];
        for (int k = 0; k < book.length; k++) {
            book[k] = new Book();
            book[k].setName(bookName[k]) ;
            book[k].setPrice(bookPrice[k]) ;
            bookList.add(book[k]) ;
        }
        Book newBook = new Book();
        newBook.setPrice(29);
        newBook.setName("Java PATTERN");
        Collections.sort(bookList);
        int m = -1;
        System.out.println("NEW BOOK: " + newBook.getName() + "(" + newBook.getPrice() + ")");
        System.out.println("Other BOOKS: ");
        while ((m = Collections.binarySearch(bookList, newBook, null)) >= 0) {
            Book bk = bookList.get(m);
            System.out.println("\t" + bk.getName() + "(" + bk.getPrice() + ")");
            bookList.remove(m);
        }
        System.out.println("Same Price.");
    }
}

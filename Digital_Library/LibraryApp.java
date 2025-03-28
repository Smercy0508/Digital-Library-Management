package Digital_Library;
import java.util.*;

//Book Model Class
class Book {
 private int bookId;
 private String title;
 private String author;
 private String genre;
 private String status; // "Available" or "Checked Out"

 public Book(int bookId, String title, String author, String genre, String status) {
     this.bookId = bookId;
     this.title = title;
     this.author = author;
     this.genre = genre;
     this.status = status;
 }

 public int getBookId() { return bookId; }
 public String getTitle() { return title; }
 public String getAuthor() { return author; }
 public String getGenre() { return genre; }
 public String getStatus() { return status; }
 
 public void setTitle(String title) { this.title = title; }
 public void setAuthor(String author) { this.author = author; }
 public void setGenre(String genre) { this.genre = genre; }
 public void setStatus(String status) { this.status = status; }

 @Override
 public String toString() {
     return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Genre: " + genre + ", Status: " + status;
 }
}

//Library Service Class
class LibraryService {
 private Map<Integer, Book> books = new HashMap<>();
 
 public void addBook(int bookId, String title, String author, String genre, String status) {
     if (books.containsKey(bookId)) {
         System.out.println("Book ID already exists!");
         return;
     }
     books.put(bookId, new Book(bookId, title, author, genre, status));
     System.out.println("Book added successfully!");
 }

 public void viewAllBooks() {
     if (books.isEmpty()) {
         System.out.println("No books available.");
         return;
     }
     books.values().forEach(System.out::println);
 }

 public void searchBookById(int bookId) {
     if (books.containsKey(bookId)) {
         System.out.println(books.get(bookId));
     } else {
         System.out.println("Book not found.");
     }
 }

 public void searchBookByTitle(String title) {
     books.values().stream()
         .filter(book -> book.getTitle().equalsIgnoreCase(title))
         .forEach(System.out::println);
 }

 public void updateBook(int bookId, String title, String author, String genre, String status) {
     if (!books.containsKey(bookId)) {
         System.out.println("Book not found.");
         return;
     }
     Book book = books.get(bookId);
     book.setTitle(title);
     book.setAuthor(author);
     book.setGenre(genre);
     book.setStatus(status);
     System.out.println("Book updated successfully!");
 }

 public void deleteBook(int bookId) {
     if (books.remove(bookId) != null) {
         System.out.println("Book deleted successfully!");
     } else {
         System.out.println("Book not found.");
     }
 }
}

//Main Application
public class LibraryApp {
 public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);
     LibraryService library = new LibraryService();

     while (true) {
         System.out.println("\n1. Add Book\n2. View All Books\n3. Search Book by ID\n4. Search Book by Title\n5. Update Book\n6. Delete Book\n7. Exit");
         System.out.print("Enter your choice: ");
         int choice = scanner.nextInt();
         scanner.nextLine();

         switch (choice) {
             case 1:
                 System.out.print("Enter Book ID: ");
                 int bookId = scanner.nextInt();
                 scanner.nextLine();
                 System.out.print("Enter Title: ");
                 String title = scanner.nextLine();
                 System.out.print("Enter Author: ");
                 String author = scanner.nextLine();
                 System.out.print("Enter Genre: ");
                 String genre = scanner.nextLine();
                 System.out.print("Enter Status (Available/Checked Out): ");
                 String status = scanner.nextLine();
                 library.addBook(bookId, title, author, genre, status);
                 break;
             case 2:
                 library.viewAllBooks();
                 break;
             case 3:
                 System.out.print("Enter Book ID: ");
                 int searchId = scanner.nextInt();
                 library.searchBookById(searchId);
                 break;
             case 4:
                 System.out.print("Enter Book Title: ");
                 String searchTitle = scanner.nextLine();
                 library.searchBookByTitle(searchTitle);
                 break;
             case 5:
                 System.out.print("Enter Book ID to Update: ");
                 int updateId = scanner.nextInt();
                 scanner.nextLine();
                 System.out.print("Enter New Title: ");
                 String newTitle = scanner.nextLine();
                 System.out.print("Enter New Author: ");
                 String newAuthor = scanner.nextLine();
                 System.out.print("Enter New Genre: ");
                 String newGenre = scanner.nextLine();
                 System.out.print("Enter New Status: ");
                 String newStatus = scanner.nextLine();
                 library.updateBook(updateId, newTitle, newAuthor, newGenre, newStatus);
                 break;
             case 6:
                 System.out.print("Enter Book ID to Delete: ");
                 int deleteId = scanner.nextInt();
                 library.deleteBook(deleteId);
                 break;
             case 7:
                 System.out.println("Exiting system...");
                 scanner.close();
                 return;
             default:
                 System.out.println("Invalid choice, try again.");
         }
     }
 }
}

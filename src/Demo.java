package src;

public class Demo {
    public static void main(String[] args) {
        LibraryManagementSystem libraryManagementSystem = LibraryManagementSystem.getInstance();

        // Add books
        Book book1 = libraryManagementSystem.addBook("The Alchemist", "Paulo Coelho", BookGenre.MYSTERY);
        Book book2 = libraryManagementSystem.addBook("The Great Gatsby", "F. Scott Fitzgerald", BookGenre.MYSTERY);
        Book book3 = libraryManagementSystem.addBook("To Kill a Mockingbird", "Harper Lee", BookGenre.HORROR);
        Book book4 = libraryManagementSystem.addBook("1984", "George Orwell", BookGenre.ACTION);
        Book book5 = libraryManagementSystem.addBook("Pride and Prejudice", "Jane Austen", BookGenre.ROMANCE);
        Book book6 = libraryManagementSystem.addBook("The Catcher in the Rye", "J.D. Salinger", BookGenre.ACTION);

        // Add members
        Member member1 = libraryManagementSystem.addMember("Alice", "1234 Main St", "qwerty1@gmail.com", "1234567890");
        Member member2 = libraryManagementSystem.addMember("Bob", "5678 Elm St", "qwerty2@gmail.com", "0987654321");

        libraryManagementSystem.displayBooks();

        // Reserve books
        Reservation reservation1 = libraryManagementSystem.reserveBook(member1, book1);
        Reservation reservation2 = libraryManagementSystem.reserveBook(member1, book2);
        Reservation reservation3 = libraryManagementSystem.reserveBook(member2, book3);
        Reservation reservation4 = libraryManagementSystem.reserveBook(member2, book2);
        libraryManagementSystem.cancelReservation(member1, reservation2);
        Reservation reservation5 = libraryManagementSystem.reserveBook(member2, book2);

        libraryManagementSystem.displayBooks();

        // Checkout books
        libraryManagementSystem.checkoutBook(member2, reservation1);
        libraryManagementSystem.checkoutBook(member1, reservation1);
        libraryManagementSystem.checkoutBook(member2, reservation3);

        libraryManagementSystem.displayBooks();

        libraryManagementSystem.returnBook(reservation1);
        libraryManagementSystem.returnBook(reservation3);
        libraryManagementSystem.checkoutBook(member2, reservation5);
        libraryManagementSystem.cancelReservation(member2, reservation5);
        libraryManagementSystem.renewBook(member2, reservation5);
        libraryManagementSystem.renewBook(member1, reservation5);

        libraryManagementSystem.displayBooks();
    }
}

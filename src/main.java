package src;

public class main {
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
        Reservation reservation1 = member1.reserveBook(libraryManagementSystem, book1);
        Reservation reservation2 = member1.reserveBook(libraryManagementSystem, book2);
        Reservation reservation3 = member2.reserveBook(libraryManagementSystem, book3);
        Reservation reservation4 = member2.reserveBook(libraryManagementSystem, book2);
        member1.cancelReservation(libraryManagementSystem, reservation2);
        Reservation reservation5 = member2.reserveBook(libraryManagementSystem, book2);

        libraryManagementSystem.displayBooks();

        // Checkout books
        member1.checkoutBook(libraryManagementSystem, reservation1);
        member2.checkoutBook(libraryManagementSystem, reservation3);

        libraryManagementSystem.displayBooks();

        member1.returnBook(libraryManagementSystem, reservation1);
        member2.returnBook(libraryManagementSystem, reservation3);
        member2.checkoutBook(libraryManagementSystem, reservation5);
        member2.renewBook(libraryManagementSystem, reservation5);
        member2.returnBook(libraryManagementSystem, reservation5);

        libraryManagementSystem.displayBooks();
    }
}

package src;

public class Member {
    private final String name;
    private final String address;
    private final String email;
    private final String phoneNumber;
    private final String id;

    public Member(String id, String name, String address, String email, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }

    public Reservation reserveBook(LibraryManagementSystem libraryManagementSystem, Book book) {
        return libraryManagementSystem.reserveBook(this, book);
    }

    public void returnBook(LibraryManagementSystem libraryManagementSystem, Reservation reservation) {
        libraryManagementSystem.returnBook(reservation);

    }

    public void renewBook(LibraryManagementSystem libraryManagementSystem, Reservation reservation) {
        libraryManagementSystem.renewBook(reservation);
    }

    public void checkoutBook(LibraryManagementSystem libraryManagementSystem, Reservation reservation) {
        libraryManagementSystem.checkoutBook(reservation);

    }

    public void cancelReservation(LibraryManagementSystem libraryManagementSystem, Reservation reservation) {
        libraryManagementSystem.cancelReservation(reservation);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getId() {
        return id;
    }
}

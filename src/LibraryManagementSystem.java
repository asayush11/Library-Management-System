package src;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LibraryManagementSystem {
    private static LibraryManagementSystem libraryManagementSystem;
    private final Map<String, Book> books;
    private final Map<String, Member> members;
    private final Map<String, Reservation> reservations;

    private LibraryManagementSystem() {
        this.books = new HashMap<>();
        this.members = new HashMap<>();
        this.reservations = new HashMap<>();
    }

    public synchronized static LibraryManagementSystem getInstance() {
        if (libraryManagementSystem == null) {
            synchronized (LibraryManagementSystem.class) {
                if (libraryManagementSystem == null) {
                    libraryManagementSystem = new LibraryManagementSystem();
                }
            }
        }
        return libraryManagementSystem;
    }

    public Book addBook(String title, String author, BookGenre genre) {
        String bookId = "B" + UUID.randomUUID().toString().substring(0, 8);
        String rackId = "R" + (books.size() + 1)%2;
        String shelfId = "S" + (books.size() + 1)%2;
        Book book = new Book(title, author, rackId, bookId, shelfId, genre);
        books.put(book.getId(), book);
        System.out.println("Book " + book.getTitle() + " added successfully on rack " + rackId + " shelf " + shelfId);
        return book;
    }

    public void removeBook(String bookId) {
        if (books.containsKey(bookId)) {
            if(!books.get(bookId).isAvailable()) {
                System.out.println("Book " + bookId + " is currently checked out and cannot be removed");
                return;
            }
            books.get(bookId).setAvailable(false);
            System.out.println("Book " + bookId + " removed successfully");
        } else {
            System.out.println("Book " + bookId + " not found");
        }
    }

    public Member addMember(String name, String address, String email, String phoneNumber) {
        String memberId = "M" + UUID.randomUUID().toString().substring(0, 8);
        Member member = new Member(memberId, name, address, email, phoneNumber);
        members.put(member.getId(), member);
        System.out.println("Member " + member.getName() + " added successfully");
        return member;
    }

    public void removeMember(String memberId) {
        if (members.containsKey(memberId)) {
            members.remove(memberId);
            System.out.println("Member " + memberId + " removed successfully");
        } else {
            System.out.println("Member " + memberId + " not found");
        }
    }

    public Reservation reserveBook(Member member, Book book) {
        if (!members.containsValue(member)) {
            System.out.println("Member " + member.getId() + " not found");
            return null;
        }

        String bookId = book.getId();
        String memberId = member.getId();

        if (!books.containsKey(bookId)) {
            System.out.println("Book " + bookId + " not found");
            return null;
        }

        if (!book.isAvailable()) {
            System.out.println("Book " + bookId + " is not available");
            return null;
        }
        book.setAvailable(false);
        Reservation reservation = new Reservation("R" + UUID.randomUUID().toString().substring(0,8), member, book);
        reservations.put(reservation.getId(), reservation);
        System.out.println("Book " + bookId + " reserved successfully for member " + memberId + " till " + reservation.getReturnDate());
        return reservation;
    }

    public void cancelReservation(Member member, Reservation reservation) {
        if (reservation.getMember().getId().equals(member.getId())) {
            String reservationId = reservation.getId();

            if (!reservations.containsKey(reservationId)) {
                System.out.println("Reservation " + reservationId + " not found");
                return;
            }
            books.get(reservation.getBook().getBookId()).setAvailable(true);
            reservation.setStatus(BookingStatus.CANCELLED);
            System.out.println("Reservation " + reservationId + " cancelled successfully" + " for member " + reservation.getMember().getName() + " for book " + reservation.getBook().getTitle());
        } else {
            System.out.println("Member " + member.getId() + " not authorized to cancel reservation " + reservation.getId());
        }
    }

    public void checkoutBook(Member member, Reservation reservation) {
        String reservationId = reservation.getId();
        if (!reservations.containsKey(reservationId)) {
            System.out.println("Reservation " + reservationId + " not found");
            return;
        }

        if (reservation.getMember().getId().equals(member.getId())) {
            Book book = reservation.getBook();
            reservation.setStatus(BookingStatus.CHECKED_OUT);
            System.out.println("Book " + book.getTitle() + " checked out successfully by member " + reservation.getMember().getName());
        } else {
            System.out.println("Member " + member.getId() + " not authorized to checkout reservation " + reservation.getId());
        }
    }

    public void returnBook(Reservation reservation) {
        String reservationId = reservation.getId();
        if (!reservations.containsKey(reservationId)) {
            System.out.println("Reservation " + reservationId + " not found");
            return;
        }

        Book book = reservation.getBook();
        book.setAvailable(true);
        reservation.setStatus(BookingStatus.RETURNED);
        System.out.println("Book " + book.getTitle() + " returned successfully by member " + reservation.getMember().getName());
    }

    public void renewBook(Member member, Reservation reservation) {
        String reservationId = reservation.getId();
        if (!reservations.containsKey(reservationId)) {
            System.out.println("Reservation " + reservationId + " not found");
            return;
        }

        if(reservation.getStatus() == BookingStatus.CANCELLED || reservation.getStatus() == BookingStatus.RETURNED) {
            System.out.println("Reservation " + reservationId + " is cancelled or returned");
            return;
        }

        if (reservation.getMember().getId().equals(member.getId())) {
            reservation.setReturnDate(reservation.getReturnDate().plusWeeks(1));
            System.out.println("Book " + reservation.getBook().getTitle() + " renewed successfully for member " + reservation.getMember().getName() + " till " + reservation.getReturnDate());
        } else {
            System.out.println("Member " + member.getId() + " not authorized to renew ");
        }
    }

    public void notifyMembers() {
        System.out.println("Notification sent to members to return book");
        reservations.values().parallelStream().
        forEach(reservation -> {
            if (reservation.getStatus() == BookingStatus.CHECKED_OUT && ChronoUnit.DAYS.between(reservation.getReturnDate(), LocalDate.now()) < 0) {
                reservation.setStatus(BookingStatus.OVERDUE);
                System.out.println("Book " + reservation.getBook().getTitle() + " overdue for member " + reservation.getMember().getName());
            } else if(reservation.getStatus() == BookingStatus.REQUESTED && ChronoUnit.DAYS.between(reservation.getReturnDate(), LocalDate.now()) < 0) {
                Book book = reservation.getBook();
                book.setAvailable(true);
                reservation.setStatus(BookingStatus.CANCELLED);
                System.out.println("Reservation " + reservation.getId() + " cancelled successfully" + " for member " + reservation.getMember().getName() + " for book " + reservation.getBook().getTitle());
            }
        });
    }

    public void displayBooks() {
        System.out.println("Books:");
        books.values().parallelStream()
        .forEach(book -> {
            System.out.println("Book Id: " + book.getId() + " Title: " + book.getTitle() + " Author: " + book.getAuthor() + " Genre: " + book.getGenre() + " Availability: " + book.isAvailable() + " Thread: " + Thread.currentThread().getName());
        });
    }
}

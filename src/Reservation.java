package src;
import java.time.LocalDate;
import java.util.Date;

public class Reservation {
    private final String id;
    private final Member member;
    private final Book book;
    private final LocalDate reservationDate;
    private LocalDate returnDate;
    private BookingStatus status;

    public Reservation(String id, Member member, Book book) {
        this.id = id;
        this.member = member;
        this.book = book;
        this.reservationDate = LocalDate.now();
        this.returnDate = this.reservationDate.plusWeeks(2);
        this.status = BookingStatus.REQUESTED;
    }

    public String getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public Book getBook() {
        return book;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }
}

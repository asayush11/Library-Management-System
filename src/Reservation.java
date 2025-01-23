package src;

import java.sql.Time;
import java.util.Date;

public class Reservation {
    private final String id;
    private final Member member;
    private final Book book;
    private final Date reservationDate;
    private Date returnDate;
    private BookingStatus status;

    public Reservation(String id, Member member, Book book) {
        this.id = id;
        this.member = member;
        this.book = book;
        this.reservationDate = new Date();
        this.returnDate = new Date(this.reservationDate.getTime() + 604800000);
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

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public Date getReturnDate() {
        return returnDate;
    }
}

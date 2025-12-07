package ch.unil.softarch.luxurycarrental.domain.entities;

import ch.unil.softarch.luxurycarrental.domain.enums.BookingStatus;
import ch.unil.softarch.luxurycarrental.domain.enums.PaymentStatus;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID bookingId;  // Booking ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;  // Rented car

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;  // Customer who made the booking

    private LocalDate startDate;  // Rental start date
    private LocalDate endDate;    // Rental end date

    private double totalCost;      // Total cost
    private double depositAmount;  // Deposit amount

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;  // Booking status

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;  // Payment status

    // No-arg constructor with auto-generated UUID
    public Booking() {
        this.bookingId = UUID.randomUUID();
    }

    // Constructor with all fields
    public Booking(UUID bookingId, Car car, Customer customer,
                   LocalDate startDate, LocalDate endDate,
                   double totalCost, double depositAmount,
                   BookingStatus bookingStatus, PaymentStatus paymentStatus) {
        this.bookingId = bookingId;
        this.car = car;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
        this.depositAmount = depositAmount;
        this.bookingStatus = bookingStatus;
        this.paymentStatus = paymentStatus;
    }

    // ---------- Getter & Setter ----------
    public UUID getBookingId() { return bookingId; }
    public void setBookingId(UUID bookingId) { this.bookingId = bookingId; }

    public Car getCar() { return car; }
    public void setCar(Car car) { this.car = car; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }

    public double getDepositAmount() { return depositAmount; }
    public void setDepositAmount(double depositAmount) { this.depositAmount = depositAmount; }

    public BookingStatus getBookingStatus() { return bookingStatus; }
    public void setBookingStatus(BookingStatus bookingStatus) { this.bookingStatus = bookingStatus; }

    public PaymentStatus getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(PaymentStatus paymentStatus) { this.paymentStatus = paymentStatus; }

    // ---------- toString ----------
    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", car=" + car +
                ", customer=" + customer +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalCost=" + totalCost +
                ", depositAmount=" + depositAmount +
                ", bookingStatus=" + bookingStatus +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
}
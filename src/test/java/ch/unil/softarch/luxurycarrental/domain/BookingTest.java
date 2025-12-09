package ch.unil.softarch.luxurycarrental.domain;

import ch.unil.softarch.luxurycarrental.domain.entities.Booking;
import ch.unil.softarch.luxurycarrental.domain.entities.Car;
import ch.unil.softarch.luxurycarrental.domain.entities.Customer;
import ch.unil.softarch.luxurycarrental.domain.enums.BookingStatus;
import ch.unil.softarch.luxurycarrental.domain.enums.PaymentStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Booking} domain entity.
 */
class BookingTest {

    private Booking booking;
    private UUID bookingId;
    private Car car;
    private Customer customer;
    private LocalDate startDate;
    private LocalDate endDate;

    @BeforeEach
    void setUp() {
        bookingId = UUID.randomUUID();

        // Initialize dummy objects for relationships
        car = new Car();
        car.setLicensePlate("TEST-PLATE"); // Set fields used in toString

        customer = new Customer();
        customer.setEmail("test@example.com"); // Set fields used in toString

        startDate = LocalDate.of(2025, 11, 1);
        endDate = LocalDate.of(2025, 11, 5);

        // 1. Initialize Booking using the new constructor (without ID)
        booking = new Booking(
                car,
                customer,
                startDate,
                endDate,
                500.0,
                100.0,
                BookingStatus.PENDING,
                PaymentStatus.SUCCESSFUL
        );

        // 2. Manually set the ID for testing
        // Rationale: @PrePersist is not triggered in unit tests.
        booking.setBookingId(bookingId);
    }

    @Test
    void testGetters() {
        assertEquals(bookingId, booking.getBookingId());
        assertEquals(car, booking.getCar());
        assertEquals(customer, booking.getCustomer());
        assertEquals(startDate, booking.getStartDate());
        assertEquals(endDate, booking.getEndDate());
        assertEquals(500.0, booking.getTotalCost());
        assertEquals(100.0, booking.getDepositAmount());
        assertEquals(BookingStatus.PENDING, booking.getBookingStatus());
        assertEquals(PaymentStatus.SUCCESSFUL, booking.getPaymentStatus());
    }

    @Test
    void testSetters() {
        booking.setTotalCost(600.0);
        assertEquals(600.0, booking.getTotalCost());

        booking.setBookingStatus(BookingStatus.CONFIRMED);
        assertEquals(BookingStatus.CONFIRMED, booking.getBookingStatus());
    }

    @Test
    void testToString() {
        String str = booking.toString();
        // Verify key fields are present
        assertTrue(str.contains(bookingId.toString()));
        assertTrue(str.contains("500.0"));
        assertTrue(str.contains("PENDING"));

        // Verify related entity info (based on updated toString logic)
        assertTrue(str.contains("TEST-PLATE"));
        assertTrue(str.contains("test@example.com"));
    }
}
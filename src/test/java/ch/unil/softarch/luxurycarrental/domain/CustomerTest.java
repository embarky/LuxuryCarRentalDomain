package ch.unil.softarch.luxurycarrental.domain;

import ch.unil.softarch.luxurycarrental.domain.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Customer} domain entity.
 * <p>
 * This class verifies the behavior of the Customer POJO (Plain Old Java Object),
 * specifically focusing on data encapsulation (Getters/Setters) and the toString method.
 * </p>
 */
class CustomerTest {

    private Customer customer;
    private UUID id;
    private Date licenseExpiry;
    private LocalDateTime creationDate;

    /**
     * Sets up the test environment before each test method execution.
     * Initializes a fresh Customer instance with sample data to ensure test isolation.
     */
    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();

        // Create a specific java.util.Date for the license expiry (December 31, 2030)
        Calendar cal = Calendar.getInstance();
        cal.set(2030, Calendar.DECEMBER, 31, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        licenseExpiry = cal.getTime();

        creationDate = LocalDateTime.now();

        // 1. Initialize the Customer object
        // Note: The constructor was modified to remove 'creationDate' as it is now managed by JPA callbacks.
        customer = new Customer(
                id,
                "Alice",
                "Smith",
                "alice.smith@example.com",
                "securePass123",
                "+123456789",
                "DL123456",
                licenseExpiry,
                30,
                true,
                "123 Main St, City",
                500.0
        );

        // 2. Manually set the creationDate for testing purposes
        // Rationale: In a standard JUnit test (without a JPA container), the @PrePersist
        // lifecycle callback is NOT triggered automatically. To verify the getter method,
        // we must manually invoke the setter here.
        customer.setCreationDate(creationDate);
    }

    /**
     * Verifies that all Getter methods return the correct values initialized in the constructor.
     */
    @Test
    void testGetters() {
        assertEquals(id, customer.getId(), "ID should match the initialized value");
        assertEquals("Alice", customer.getFirstName());
        assertEquals("Smith", customer.getLastName());
        assertEquals("alice.smith@example.com", customer.getEmail());
        assertEquals("securePass123", customer.getPassword());
        assertEquals("+123456789", customer.getPhoneNumber());
        assertEquals("DL123456", customer.getDrivingLicenseNumber());
        assertEquals(licenseExpiry, customer.getDrivingLicenseExpiryDate());
        assertEquals(30, customer.getAge());
        assertTrue(customer.isVerifiedIdentity(), "Identity should be verified");
        assertEquals("123 Main St, City", customer.getBillingAddress());
        assertEquals(500.0, customer.getBalance());

        // Validates that the manually set date is retrievable
        assertEquals(creationDate, customer.getCreationDate());
    }

    /**
     * Verifies that all Setter methods correctly update the entity's state.
     */
    @Test
    void testSetters() {
        LocalDateTime now = LocalDateTime.now();

        // Create a new future date for testing updates
        Calendar cal = Calendar.getInstance();
        cal.set(2035, Calendar.JANUARY, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date newExpiry = cal.getTime();

        // Apply changes using setters
        customer.setFirstName("Bob");
        customer.setLastName("Johnson");
        customer.setEmail("bob.johnson@example.com");
        customer.setPassword("newPass456");
        customer.setPhoneNumber("+987654321");
        customer.setDrivingLicenseNumber("DL654321");
        customer.setDrivingLicenseExpiryDate(newExpiry);
        customer.setAge(40);
        customer.setVerifiedIdentity(false);
        customer.setBillingAddress("456 Another St");
        customer.setBalance(1000.0);
        customer.setCreationDate(now);

        // Verify that changes were persisted in the object
        assertEquals("Bob", customer.getFirstName());
        assertEquals("Johnson", customer.getLastName());
        assertEquals("bob.johnson@example.com", customer.getEmail());
        assertEquals("newPass456", customer.getPassword());
        assertEquals("+987654321", customer.getPhoneNumber());
        assertEquals("DL654321", customer.getDrivingLicenseNumber());
        assertEquals(newExpiry, customer.getDrivingLicenseExpiryDate());
        assertEquals(40, customer.getAge());
        assertFalse(customer.isVerifiedIdentity(), "Identity should now be unverified");
        assertEquals("456 Another St", customer.getBillingAddress());
        assertEquals(1000.0, customer.getBalance());
        assertEquals(now, customer.getCreationDate());
    }

    /**
     * Verifies that the toString method generates a string containing key information
     * and that sensitive data (like the password) is masked.
     */
    @Test
    void testToString() {
        String str = customer.toString();

        // Assert that essential fields are present in the string representation
        assertTrue(str.contains(id.toString()), "toString should contain the ID");
        assertTrue(str.contains("Alice"));
        assertTrue(str.contains("Smith"));
        assertTrue(str.contains("alice.smith@example.com"));

        // Assert that the password is NOT shown in plain text
        // (Assuming logic: password != null ? "***" : null)
        assertTrue(str.contains("***"), "Password should be masked in toString output");
    }
}
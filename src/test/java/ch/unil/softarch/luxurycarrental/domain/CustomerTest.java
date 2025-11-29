package ch.unil.softarch.luxurycarrental.domain;

import ch.unil.softarch.luxurycarrental.domain.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private Customer customer;
    private UUID id;
    private Date licenseExpiry;
    private LocalDateTime creationDate;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        // 使用 Calendar 构造 Date
        Calendar cal = Calendar.getInstance();
        cal.set(2030, Calendar.DECEMBER, 31, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        licenseExpiry = cal.getTime();

        creationDate = LocalDateTime.now();

        // 初始化 Customer
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
                500.0,
                creationDate
        );
    }

    @Test
    void testGetters() {
        assertEquals(id, customer.getId());
        assertEquals("Alice", customer.getFirstName());
        assertEquals("Smith", customer.getLastName());
        assertEquals("alice.smith@example.com", customer.getEmail());
        assertEquals("securePass123", customer.getPassword());
        assertEquals("+123456789", customer.getPhoneNumber());
        assertEquals("DL123456", customer.getDrivingLicenseNumber());
        assertEquals(licenseExpiry, customer.getDrivingLicenseExpiryDate());
        assertEquals(30, customer.getAge());
        assertTrue(customer.isVerifiedIdentity());
        assertEquals("123 Main St, City", customer.getBillingAddress());
        assertEquals(500.0, customer.getBalance());
        assertEquals(creationDate, customer.getCreationDate());
    }

    @Test
    void testSetters() {
        LocalDateTime now = LocalDateTime.now();

        Calendar cal = Calendar.getInstance();
        cal.set(2035, Calendar.JANUARY, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date newExpiry = cal.getTime();

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

        assertEquals("Bob", customer.getFirstName());
        assertEquals("Johnson", customer.getLastName());
        assertEquals("bob.johnson@example.com", customer.getEmail());
        assertEquals("newPass456", customer.getPassword());
        assertEquals("+987654321", customer.getPhoneNumber());
        assertEquals("DL654321", customer.getDrivingLicenseNumber());
        assertEquals(newExpiry, customer.getDrivingLicenseExpiryDate());
        assertEquals(40, customer.getAge());
        assertFalse(customer.isVerifiedIdentity());
        assertEquals("456 Another St", customer.getBillingAddress());
        assertEquals(1000.0, customer.getBalance());
        assertEquals(now, customer.getCreationDate());
    }

    @Test
    void testToString() {
        String str = customer.toString();
        assertTrue(str.contains(id.toString()));
        assertTrue(str.contains("Alice"));
        assertTrue(str.contains("Smith"));
        assertTrue(str.contains("alice.smith@example.com"));
        assertTrue(str.contains("***")); // password is masked
    }
}
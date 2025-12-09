package ch.unil.softarch.luxurycarrental.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a Customer in the system.
 * <p>
 * This class is a JPA Entity mapped to the "customer" table.
 * It implements Serializable to support object persistence and transmission.
 * </p>
 */
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    // Unique identifier for serialization interoperability
    private static final long serialVersionUID = 1L;

    /**
     * Unique customer ID.
     * Stored as binary(16) for efficient UUID storage in databases.
     */
    @Id
    @GeneratedValue
    @Column(length = 36)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    /**
     * Customer's email address, used for login and communication.
     * Must be unique across the system.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Encrypted login password.
     */
    @Column(nullable = false)
    private String password;

    private String phoneNumber;

    @Column(unique = true)
    private String drivingLicenseNumber;

    /**
     * Expiry date of the driving license.
     * Using java.util.Date with @Temporal as per legacy JPA patterns,
     * though LocalDate is preferred in newer Java versions.
     */
    @Temporal(TemporalType.DATE)
    private Date drivingLicenseExpiryDate;

    private int age;

    /**
     * Flag indicating if the customer's identity documents have been verified.
     */
    private boolean verifiedIdentity;

    private String billingAddress;

    /**
     * Current account balance used for deposits or payments.
     */
    private double balance;

    /**
     * Timestamp when the customer account was created.
     */
    @Column(nullable = false, updatable = false)
    private LocalDateTime creationDate;

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * No-arg constructor required by JPA specifications.
     */
    public Customer() {
        // ID and creationDate initialization moved to @PrePersist callback
    }

    /**
     * Constructor to initialize a customer with specific details.
     * Note: ID and creationDate are handled automatically by lifecycle callbacks.
     */
    public Customer(UUID id, String firstName, String lastName, String email, String password,
                    String phoneNumber, String drivingLicenseNumber, Date drivingLicenseExpiryDate,
                    int age, boolean verifiedIdentity, String billingAddress, double balance) {
        // We typically ignore the passed ID in favor of auto-generation,
        // or you can set it if you are migrating existing data.
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.drivingLicenseNumber = drivingLicenseNumber;
        this.drivingLicenseExpiryDate = drivingLicenseExpiryDate;
        this.age = age;
        this.verifiedIdentity = verifiedIdentity;
        this.billingAddress = billingAddress;
        this.balance = balance;
        // creationDate is not set here, it will be set on persist
    }

    // -------------------------------------------------------------------------
    // Lifecycle Callbacks
    // -------------------------------------------------------------------------

    /**
     * Executed automatically before the entity is persisted (inserted) into the database.
     * Generates a unique ID if missing and records the creation timestamp.
     */
    @PrePersist
    protected void onCreate() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
        if (this.creationDate == null) {
            this.creationDate = LocalDateTime.now();
        }
    }

    // Note: No @PreUpdate needed as there is no 'updatedAt' field in this class.

    // -------------------------------------------------------------------------
    // Getters and Setters
    // -------------------------------------------------------------------------

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getDrivingLicenseNumber() { return drivingLicenseNumber; }
    public void setDrivingLicenseNumber(String drivingLicenseNumber) { this.drivingLicenseNumber = drivingLicenseNumber; }

    public Date getDrivingLicenseExpiryDate() { return drivingLicenseExpiryDate; }
    public void setDrivingLicenseExpiryDate(Date drivingLicenseExpiryDate) { this.drivingLicenseExpiryDate = drivingLicenseExpiryDate; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public boolean isVerifiedIdentity() { return verifiedIdentity; }
    public void setVerifiedIdentity(boolean verifiedIdentity) { this.verifiedIdentity = verifiedIdentity; }

    public String getBillingAddress() { return billingAddress; }
    public void setBillingAddress(String billingAddress) { this.billingAddress = billingAddress; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public LocalDateTime getCreationDate() { return creationDate; }
    // Setter typically not used for creationDate as it's immutable after creation
    public void setCreationDate(LocalDateTime creationDate) { this.creationDate = creationDate; }

    // -------------------------------------------------------------------------
    // Overrides
    // -------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + (password != null ? "***" : null) + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", drivingLicenseNumber='" + drivingLicenseNumber + '\'' +
                ", drivingLicenseExpiryDate=" + drivingLicenseExpiryDate +
                ", age=" + age +
                ", verifiedIdentity=" + verifiedIdentity +
                ", billingAddress='" + billingAddress + '\'' +
                ", balance=" + balance +
                ", creationDate=" + creationDate +
                '}';
    }
}
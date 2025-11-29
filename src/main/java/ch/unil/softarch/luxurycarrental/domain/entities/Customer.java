package ch.unil.softarch.luxurycarrental.domain.entities;

import java.util.Date;
import java.time.LocalDateTime;
import java.util.UUID;

public class Customer {

    private UUID id;                       // Unique customer ID (UUID)
    private String firstName;              // Customer first name
    private String lastName;               // Customer last name
    private String email;                  // Email address
    private String password;               // Login password
    private String phoneNumber;            // Phone number
    private String drivingLicenseNumber;   // Driving license number
    private Date drivingLicenseExpiryDate; // Driving license expiry date
    private int age;                       // Customer age
    private boolean verifiedIdentity;      // Identity verification status
    private String billingAddress;         // Billing address
    private double balance;                // Balance for deposits and rental payments
    private LocalDateTime creationDate;    // Account creation timestamp


    // Constructor with all fields including password
    public Customer(UUID id, String firstName, String lastName, String email, String password,
                    String phoneNumber, String drivingLicenseNumber, Date drivingLicenseExpiryDate,
                    int age, boolean verifiedIdentity, String billingAddress, double balance,
                    LocalDateTime creationDate) {
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
        this.creationDate = creationDate;
    }

    // No-arg constructor with auto-generated UUID and creationDate
    public Customer() {
        this.id = UUID.randomUUID();
        this.creationDate = LocalDateTime.now();
    }

    // Getter / Setter
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
    public void setCreationDate(LocalDateTime creationDate) { this.creationDate = creationDate; }

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
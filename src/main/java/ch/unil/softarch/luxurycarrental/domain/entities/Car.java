package ch.unil.softarch.luxurycarrental.domain.entities;

import ch.unil.softarch.luxurycarrental.domain.enums.CarStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Represents a physical Car in the fleet.
 * <p>
 * This entity links a specific physical vehicle (identified by VIN/License Plate)
 * to a generic {@link CarType} (Model/Brand).
 * </p>
 */
@Entity
@Table(name = "car")
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(length = 36)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;  // Unique car ID (UUID)

    @Column(nullable = false, unique = true)
    private String licensePlate;

    /**
     * Relationship to the CarType entity.
     * 'fetch = FetchType.LAZY' is recommended for performance.
     * 'name = "car_type_id"' defines the foreign key column in the 'car' table.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_type_id", nullable = false)
    private CarType carType;

    @Column(nullable = false)
    private double dailyRentalPrice;

    @Column(nullable = false)
    private double depositAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarStatus status;

    private String imageUrl;

    private LocalDate registrationDate;

    private LocalDate lastMaintenanceDate;

    @Column(unique = true)
    private String vin;  // Vehicle Identification Number

    private String color;

    private LocalDate insuranceExpiryDate;

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Default no-arg constructor required by JPA.
     */
    public Car() {
        // ID generation handled by @PrePersist
    }

    /**
     * Constructor with fields (excluding ID).
     */
    public Car(String licensePlate, CarType carType, double dailyRentalPrice,
               double depositAmount, CarStatus status, String imageUrl,
               LocalDate registrationDate, LocalDate lastMaintenanceDate,
               String vin, String color, LocalDate insuranceExpiryDate) {
        this.licensePlate = licensePlate;
        this.carType = carType;
        this.dailyRentalPrice = dailyRentalPrice;
        this.depositAmount = depositAmount;
        this.status = status;
        this.imageUrl = imageUrl;
        this.registrationDate = registrationDate;
        this.lastMaintenanceDate = lastMaintenanceDate;
        this.vin = vin;
        this.color = color;
        this.insuranceExpiryDate = insuranceExpiryDate;
    }

    // -------------------------------------------------------------------------
    // Lifecycle Callbacks
    // -------------------------------------------------------------------------

    /**
     * Executed automatically before persisting to the database.
     */
    @PrePersist
    protected void onCreate() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }

    // -------------------------------------------------------------------------
    // Getters and Setters
    // -------------------------------------------------------------------------

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }

    public CarType getCarType() { return carType; }
    public void setCarType(CarType carType) { this.carType = carType; }

    public double getDailyRentalPrice() { return dailyRentalPrice; }
    public void setDailyRentalPrice(double dailyRentalPrice) { this.dailyRentalPrice = dailyRentalPrice; }

    public double getDepositAmount() { return depositAmount; }
    public void setDepositAmount(double depositAmount) { this.depositAmount = depositAmount; }

    public CarStatus getStatus() { return status; }
    public void setStatus(CarStatus status) { this.status = status; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }

    public LocalDate getLastMaintenanceDate() { return lastMaintenanceDate; }
    public void setLastMaintenanceDate(LocalDate lastMaintenanceDate) { this.lastMaintenanceDate = lastMaintenanceDate; }

    public String getVin() { return vin; }
    public void setVin(String vin) { this.vin = vin; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public LocalDate getInsuranceExpiryDate() { return insuranceExpiryDate; }
    public void setInsuranceExpiryDate(LocalDate insuranceExpiryDate) { this.insuranceExpiryDate = insuranceExpiryDate; }

    // -------------------------------------------------------------------------
    // Overrides
    // -------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", licensePlate='" + licensePlate + '\'' +
                ", carType=" + (carType != null ? carType.getModel() : "null") + // Avoid recursion in toString
                ", dailyRentalPrice=" + dailyRentalPrice +
                ", depositAmount=" + depositAmount +
                ", status=" + status +
                ", imageUrl='" + imageUrl + '\'' +
                ", registrationDate=" + registrationDate +
                ", lastMaintenanceDate=" + lastMaintenanceDate +
                ", vin='" + vin + '\'' +
                ", color='" + color + '\'' +
                ", insuranceExpiryDate=" + insuranceExpiryDate +
                '}';
    }
}
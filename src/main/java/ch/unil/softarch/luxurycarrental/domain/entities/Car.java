package ch.unil.softarch.luxurycarrental.domain.entities;

import ch.unil.softarch.luxurycarrental.domain.enums.CarStatus;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;  // Unique car ID (UUID)

    @Column(nullable = false, unique = true)
    private String licensePlate;  // License plate number

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private CarType carType;  // Car type information (references CarType)

    @Column(nullable = false)
    private double dailyRentalPrice;  // Daily rental price

    @Column(nullable = false)
    private double depositAmount;  // Deposit amount

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarStatus status;  // Rental availability status

    private String imageUrl;  // Image URL

    private LocalDate registrationDate;  // Registration / Service start date

    private LocalDate lastMaintenanceDate;  // Last maintenance date

    @Column(unique = true)
    private String vin;  // Vehicle Identification Number (VIN)

    private String color;  // Car color

    private LocalDate insuranceExpiryDate;  // Insurance expiry date

    // No-arg constructor with auto-generated UUID
    public Car() {
        this.id = UUID.randomUUID();
    }

    // Constructor with all fields
    public Car(UUID id, String licensePlate, CarType carType, double dailyRentalPrice,
               double depositAmount, CarStatus status, String imageUrl,
               LocalDate registrationDate, LocalDate lastMaintenanceDate,
               String vin, String color, LocalDate insuranceExpiryDate) {
        this.id = id;
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

    // ---------- Getter & Setter ----------
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

    // ---------- toString ----------
    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", licensePlate='" + licensePlate + '\'' +
                ", carType=" + carType +
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
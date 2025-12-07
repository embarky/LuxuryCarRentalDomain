package ch.unil.softarch.luxurycarrental.domain.entities;

import ch.unil.softarch.luxurycarrental.domain.enums.DriveType;
import ch.unil.softarch.luxurycarrental.domain.enums.Transmission;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "car_type")
public class CarType {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;  // Unique identifier

    @Column(nullable = false)
    private String category;  // Car category

    @Column(nullable = false)
    private String brand;  // Brand

    @Column(nullable = false)
    private String model;  // Model

    private String engine;  // Engine

    private int power;  // Power [hp]

    private int maxSpeed;  // Maximum speed [km/h]

    private double acceleration;  // 0-100 km/h acceleration [s]

    private double weight;  // Weight [kg]

    @Enumerated(EnumType.STRING)
    private DriveType driveType;  // Drive type

    @Enumerated(EnumType.STRING)
    private Transmission transmission;  // Transmission type

    private int seats;  // Number of seats

    @Column(length = 1000)
    private String description;  // Description

    @ElementCollection
    @CollectionTable(name = "car_type_features", joinColumns = @JoinColumn(name = "car_type_id"))
    @Column(name = "feature")
    private List<String> features;  // List of features

    // Constructor with all fields
    public CarType(UUID id, String category, String brand, String model, String engine,
                   int power, int maxSpeed, double acceleration, double weight,
                   DriveType driveType, Transmission transmission,
                   int seats, String description, List<String> features) {
        this.id = id;
        this.category = category;
        this.brand = brand;
        this.model = model;
        this.engine = engine;
        this.power = power;
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.weight = weight;
        this.driveType = driveType;
        this.transmission = transmission;
        this.seats = seats;
        this.description = description;
        this.features = features;
    }

    // No-arg constructor with auto-generated UUID
    public CarType() {
        this.id = UUID.randomUUID();
    }

    // Getter / Setter
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getEngine() { return engine; }
    public void setEngine(String engine) { this.engine = engine; }

    public int getPower() { return power; }
    public void setPower(int power) { this.power = power; }

    public int getMaxSpeed() { return maxSpeed; }
    public void setMaxSpeed(int maxSpeed) { this.maxSpeed = maxSpeed; }

    public double getAcceleration() { return acceleration; }
    public void setAcceleration(double acceleration) { this.acceleration = acceleration; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public DriveType getDriveType() { return driveType; }
    public void setDriveType(DriveType driveType) { this.driveType = driveType; }

    public Transmission getTransmission() { return transmission; }
    public void setTransmission(Transmission transmission) { this.transmission = transmission; }

    public int getSeats() { return seats; }
    public void setSeats(int seats) { this.seats = seats; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<String> getFeatures() { return features; }
    public void setFeatures(List<String> features) { this.features = features; }

    @Override
    public String toString() {
        return "CarType{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", engine='" + engine + '\'' +
                ", power=" + power +
                ", maxSpeed=" + maxSpeed +
                ", acceleration=" + acceleration +
                ", weight=" + weight +
                ", driveType=" + driveType +
                ", transmission=" + transmission +
                ", seats=" + seats +
                ", description='" + description + '\'' +
                ", features=" + features +
                '}';
    }
}
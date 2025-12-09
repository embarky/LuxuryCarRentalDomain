package ch.unil.softarch.luxurycarrental.domain;

import ch.unil.softarch.luxurycarrental.domain.entities.Car;
import ch.unil.softarch.luxurycarrental.domain.entities.CarType;
import ch.unil.softarch.luxurycarrental.domain.enums.CarStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Car} domain entity.
 */
class CarTest {

    private Car car;
    private CarType carType;
    private UUID carId;

    @BeforeEach
    void setUp() {
        carId = UUID.randomUUID();

        // 1. Prepare dependencies (CarType)
        // Note: Using the CarType constructor without ID as per previous modifications
        carType = new CarType();
        carType.setBrand("Tesla");
        carType.setModel("Model S");
        carType.setSeats(5);
        // We might want to set an ID for CarType manually if we were testing deeper relations
        carType.setId(UUID.randomUUID());

        // 2. Initialize Car
        // We use the no-arg constructor and setters here, which is a common pattern
        // for entities with many fields.
        car = new Car();

        // 3. Manually set ID
        // Essential step: @PrePersist is not triggered in unit tests.
        car.setId(carId);

        car.setLicensePlate("ABC-1234");
        car.setCarType(carType);
        car.setDailyRentalPrice(100.0);
        car.setDepositAmount(500.0);
        car.setStatus(CarStatus.AVAILABLE);
        car.setImageUrl("https://example.com/car.jpg");
        car.setRegistrationDate(LocalDate.of(2022, 1, 10));
        car.setLastMaintenanceDate(LocalDate.of(2023, 5, 15));
        car.setVin("1HGCM82633A004352");
        car.setColor("Red");
        car.setInsuranceExpiryDate(LocalDate.of(2024, 1, 10));
    }

    @Test
    void testCarConstructorAndGetters() {
        assertEquals(carId, car.getId(), "ID should match the manually set value");
        assertEquals("ABC-1234", car.getLicensePlate());
        assertEquals(carType, car.getCarType());
        assertEquals(100.0, car.getDailyRentalPrice());
        assertEquals(500.0, car.getDepositAmount());
        assertEquals(CarStatus.AVAILABLE, car.getStatus());
        assertEquals("https://example.com/car.jpg", car.getImageUrl());
        assertEquals(LocalDate.of(2022, 1, 10), car.getRegistrationDate());
        assertEquals(LocalDate.of(2023, 5, 15), car.getLastMaintenanceDate());
        assertEquals("1HGCM82633A004352", car.getVin());
        assertEquals("Red", car.getColor());
        assertEquals(LocalDate.of(2024, 1, 10), car.getInsuranceExpiryDate());
    }

    @Test
    void testCarStatusChange() {
        car.setStatus(CarStatus.AVAILABLE);
        assertEquals(CarStatus.AVAILABLE, car.getStatus());

        car.setStatus(CarStatus.UNAVAILABLE);
        assertEquals(CarStatus.UNAVAILABLE, car.getStatus());
    }

    @Test
    void testLicensePlateUpdate() {
        car.setLicensePlate("XYZ-5678");
        assertEquals("XYZ-5678", car.getLicensePlate());
    }

    @Test
    void testToString() {
        String str = car.toString();
        // Verify key information is present
        assertTrue(str.contains(carId.toString()));
        assertTrue(str.contains("ABC-1234"));
        assertTrue(str.contains("Model S")); // From the linked CarType
    }
}
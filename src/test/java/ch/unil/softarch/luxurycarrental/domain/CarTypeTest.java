package ch.unil.softarch.luxurycarrental.domain;

import ch.unil.softarch.luxurycarrental.domain.entities.CarType;
import ch.unil.softarch.luxurycarrental.domain.enums.DriveType;
import ch.unil.softarch.luxurycarrental.domain.enums.Transmission;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link CarType} domain entity.
 */
class CarTypeTest {

    private CarType carType;
    private UUID id;
    private List<String> features;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        features = Arrays.asList("Air Conditioning", "GPS", "Bluetooth");

        // 1. Updated constructor call: No ID passed here
        carType = new CarType(
                "SUV",
                "Toyota",
                "RAV4",
                "2.5L Hybrid",
                219,
                180,
                8.0,
                1750.0,
                DriveType.FOUR_WHEEL_DRIVE,
                Transmission.AUTOMATIC,
                5,
                "Reliable compact SUV",
                features
        );

        // 2. Manual ID setting
        // Since @PrePersist is not triggered in unit tests, we set the ID manually
        // to verify getters and toString behavior.
        carType.setId(id);
    }

    @Test
    void testGetters() {
        assertEquals(id, carType.getId());
        assertEquals("SUV", carType.getCategory());
        assertEquals("Toyota", carType.getBrand());
        assertEquals("RAV4", carType.getModel());
        assertEquals("2.5L Hybrid", carType.getEngine());
        assertEquals(219, carType.getPower());
        assertEquals(180, carType.getMaxSpeed());
        assertEquals(8.0, carType.getAcceleration());
        assertEquals(1750.0, carType.getWeight());
        assertEquals(DriveType.FOUR_WHEEL_DRIVE, carType.getDriveType());
        assertEquals(Transmission.AUTOMATIC, carType.getTransmission());
        assertEquals(5, carType.getSeats());
        assertEquals("Reliable compact SUV", carType.getDescription());
        assertEquals(features, carType.getFeatures());
    }

    @Test
    void testSetters() {
        carType.setCategory("Convertible");
        assertEquals("Convertible", carType.getCategory());

        carType.setPower(250);
        assertEquals(250, carType.getPower());
    }

    @Test
    void testToString() {
        String str = carType.toString();
        // Verify key information including ID and fields
        assertTrue(str.contains(id.toString()));
        assertTrue(str.contains("Toyota"));
        assertTrue(str.contains("RAV4"));
        assertTrue(str.contains("SUV"));
    }
}
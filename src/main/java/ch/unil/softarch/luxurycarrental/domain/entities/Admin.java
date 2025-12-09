package ch.unil.softarch.luxurycarrental.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import jakarta.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.json.bind.annotation.JsonbTransient;

/**
 * Represents an Administrator in the system.
 * <p>
 * This class is a JPA Entity mapped to the "admin" table in the database.
 * It implements Serializable as per Jakarta EE standards for persistent objects.
 * </p>
 */
@Entity
@Table(name = "admin")
public class Admin implements Serializable {

    // Recommended for Serializable classes to ensure version compatibility during deserialization
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for the administrator.
     * Stored as a 16-byte binary value for efficiency.
     */
    @Id
    @GeneratedValue
    @Column(length = 36)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    /**
     * The unique username used for logging in.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * The administrator's password.
     * Note: In a production environment, this should store a hash, not plain text.
     */
    @Column(nullable = false)
    private String password;

    /**
     * The full name of the administrator.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The email address, must be unique.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Timestamp of when the account was created.
     * 'updatable = false' ensures this column is never modified after the initial insert.
     */
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Timestamp of the last update to the account.
     */
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Default no-argument constructor.
     * Required by the JPA specification for entity instantiation.
     */
    public Admin() {
        // The ID generation and timestamp initialization are handled by the @PrePersist callback
    }

    /**
     * Constructor to initialize a new Administrator with specific details.
     *
     * @param username The login username
     * @param password The login password
     * @param name     The full name
     * @param email    The email address
     */
    public Admin(String username, String password, String name, String email) {
        // We do not generate the ID here; it is safer to let the Lifecycle Callback handle it
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    // -------------------------------------------------------------------------
    // Lifecycle Callbacks (Automatic Timestamp Management)
    // -------------------------------------------------------------------------

    /**
     * Executed automatically before the entity is persisted (inserted) into the database.
     * Initializes the UUID if null and sets the creation/update timestamps.
     */
    @PrePersist
    protected void onCreate() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Executed automatically before the entity is updated in the database.
     * Refreshes the 'updatedAt' timestamp to the current time.
     */
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // -------------------------------------------------------------------------
    // Getters and Setters
    // -------------------------------------------------------------------------

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Setter for createdAt is usually not needed as it is managed automatically

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // Setter for updatedAt is usually not needed as it is managed automatically

    // -------------------------------------------------------------------------
    // Overrides (equals, hashCode, toString)
    // -------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin admin)) return false;
        // Equality is based on the unique ID (Primary Key)
        return Objects.equals(id, admin.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
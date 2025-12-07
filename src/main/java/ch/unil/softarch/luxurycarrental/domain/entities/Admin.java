package ch.unil.softarch.luxurycarrental.domain.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;  // Administrator ID

    @Column(nullable = false, unique = true)
    private String username;  // Login username

    @Column(nullable = false)
    private String password;  // Login password (can be encrypted later)

    @Column(nullable = false)
    private String name;  // Administrator full name

    @Column(nullable = false, unique = true)
    private String email;  // Email address

    @Column(nullable = false)
    private LocalDateTime createdAt;  // Account creation timestamp

    @Column(nullable = false)
    private LocalDateTime updatedAt;  // Last update timestamp

    // No-arg constructor with auto-generated UUID and timestamps
    public Admin() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Constructor with main fields
    public Admin(UUID id, String username, String password, String name, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // ---------- Getter & Setter ----------
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    // ---------- equals & hashCode ----------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin admin)) return false;
        return Objects.equals(id, admin.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // ---------- toString ----------
    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
package kz.kbtu.tsis4.domain;

import jakarta.persistence.*;
import kz.kbtu.tsis4.enums.Theme;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="events")
@Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name="name")
    String name;

    @Column(name="email", unique = true,  nullable = false)
    String email;

    @Column(name="date_of_start", nullable = false)
    LocalDate dateOfStart;

    @Column(name="duration", nullable = false)
    Integer duration;

    @Column(name="description")
    String description;

    @Column(name="theme", nullable = false)
    @Enumerated(EnumType.STRING)
    Theme theme;

    @Column(name="created_at", nullable = false)
    LocalDateTime createdAt;

    @Column(name="updated_at", nullable = false)
    LocalDateTime updatedAt;


    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}

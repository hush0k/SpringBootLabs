package kz.kbtu.tsis5v2.domain;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import kz.kbtu.tsis5v2.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Hidden
@Table(name="teachers")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teachers_seq")
    @SequenceGenerator(
            name = "teachers_seq",
            sequenceName = "teachers_seq",
            allocationSize = 1
    )
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "teacher_subjects",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjects;

    @Column(name="email" , nullable = false, unique = true)
    String email;

    @Column(name="first_name" , nullable = false)
    String firstName;

    @Column(name="last_name" , nullable = false)
    String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name="gender" , nullable = false)
    Gender gender;

    @Column(name="phone_number" , nullable = false)
    String phoneNumber;

    @Column(name="is_active" , nullable = false)
    Boolean isActive;

    @Column(name="created_at" , nullable = false)
    LocalDateTime createdAt;

    @Column(name="updated_at" , nullable = false)
    LocalDateTime updatedAt;


    @PrePersist
    public void prePersist() {
        this.createdAt = java.time.LocalDateTime.now();
        this.updatedAt = java.time.LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = java.time.LocalDateTime.now();
    }
}

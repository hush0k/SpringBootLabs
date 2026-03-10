package kz.kbtu.tsis5v2.domain;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import kz.kbtu.tsis5v2.enums.Gender;
import kz.kbtu.tsis5v2.enums.School;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="students")
@Hidden
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "students_seq")
    @SequenceGenerator(name="students_seq", sequenceName = "students_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Long id;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Enrollment> enrollments;

    @Column(name="email" , nullable = false, unique = true)
    private String email;

    @Column(name="first_name" , nullable = false)
    private String firstName;

    @Column(name="last_name" , nullable = false)
    private String lastName;

    @Column(name="date_of_birth" , nullable = false)
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name="gender" , nullable= false)
    private Gender gender;

    @Column(name="phone_number" , nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name="school" , nullable = false)
    private School school;

    @Column(name="year_of_study" , nullable = false)
    private Integer yearOfStudy;

    @Column(name="gpa")
    private Float gpa;

    @Column(name="is_active" , nullable = false)
    private Boolean isActive;

    @Column(name="created_at" , nullable = false)
    private LocalDateTime createdAt;

    @Column(name="updated_at" , nullable = false)
    private LocalDateTime updatedAt;


    @PrePersist
    public void prePersist() {
        if (gpa == null) {
            gpa = 0.0f;
        }
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}

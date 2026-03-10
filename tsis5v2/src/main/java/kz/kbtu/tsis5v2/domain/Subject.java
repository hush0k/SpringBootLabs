package kz.kbtu.tsis5v2.domain;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import kz.kbtu.tsis5v2.enums.School;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Hidden
@Table(name="subjects")
@Builder
public class Subject {
    @Id
    @EqualsAndHashCode.Include
    private String id;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Enrollment> enrollments;

    @ManyToMany(mappedBy = "subjects")
    private Set<Teacher> teachers;

    @Column(name="name" , nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name="school" , nullable = false)
    private School school;

    @Column(name="credits" , nullable = false)
    private Integer credits;

    @Column(name="description")
    private String description;

    @ElementCollection
    @CollectionTable(
            name = "subject_prerequisites",
            joinColumns = @JoinColumn(name = "subject_id")
    )
    @Column(name = "prerequisite")
    private Set<String> prerequisites;

    @Column(name="max_students" , nullable = false)
    private Integer maxStudents;

    @Column(name="is_active" , nullable = false)
    private Boolean isActive;

    @Column(name="created_at" , nullable = false)
    private LocalDateTime createdAt;

    @Column(name="updated_at" , nullable = false)
    private LocalDateTime updatedAt;

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

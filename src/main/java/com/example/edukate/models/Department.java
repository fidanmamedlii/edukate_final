package com.example.edukate.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "departments")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    @OneToMany(mappedBy = "department")
    List<Instructor> instructors;

    @OneToMany(mappedBy = "department")
    List<Course> courses;

    @Column(name = "is_deleted")
    boolean isDeleted = false;

}


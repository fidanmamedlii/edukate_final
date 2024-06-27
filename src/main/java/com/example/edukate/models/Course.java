package com.example.edukate.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="courses")
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CourseName", nullable = false)
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Image")
    private String image;

    @Column(name = "DurationTime")
    private String durationTime;

    @Column(name = "LectureCount")
    private int lectureCount;

    @Column(name = "SkillLevel")
    private String skillLevel;

    @Column(name = "Language")
    private String language;

    @Column(name = "Price", nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    private Instructor instructor;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
    private List<Student> student;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @Column(name = "Rating")
    private double rating;
}
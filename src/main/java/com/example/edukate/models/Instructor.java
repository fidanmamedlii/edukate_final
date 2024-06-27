package com.example.edukate.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "instructors")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;

    @OneToMany(mappedBy = "instructor", fetch = FetchType.EAGER)
    private List<Course> courses;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="departmentId",referencedColumnName = "id")
    private Department department;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;
}


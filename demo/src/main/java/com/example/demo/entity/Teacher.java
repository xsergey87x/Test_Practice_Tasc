package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Teacher {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String firstName;
    String lastName;
    String gender;
    int age;

    @OneToMany(mappedBy = "teacher")
    @JsonManagedReference
    private List<Group> groups = new ArrayList<>();

}
package com.example.test.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Adjuster {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String fname;
    private String lname;
    private Long grade;
    @OneToOne
    private Brigade numberBrigades;
    private String phone;

    @Override
    public String toString() {
        String text;
            text = "<tr>" +
                    "<td>" + id + "</td>" +
                    "<td>" + fname + "</td>" +
                    "<td>" + lname + "</td>" +
                    "<td>" + grade + "</td>" +
                    "<td>" + numberBrigades + "</td>" +
                    "<td>" + phone + "</td>" +
                    "</td>" +
                    "</tr>";
        return text;
    }
}

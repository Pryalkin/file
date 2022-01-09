package com.example.test.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne
    private Plan plan;
    @OneToOne
    private Warehouse material;

    @Override
    public String toString() {
        return "<tr>" +
                "<td>" + id + "</td>" +
                "<td>" + plan + "</td>" +
                "<td>" + material + "</td>" +
                "</tr>";
    }
}

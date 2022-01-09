package com.example.test.entity;

import com.example.test.controller.Flag;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class TypeOfWork {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String typeOfWork;
    private Long days;
    private Long price;

    @Override
    public String toString() {
        String text;
        String startOption = "<option>";
        String endOption = "</option>";
        if (Flag.isTypeOfWorkToString()) {
            text = "<tr>" +
                    "<td>" + id + "</td>" +
                    "<td>" + typeOfWork + "</td>" +
                    "<td>" + days + "</td>" +
                    "<td>" + price + "</td>" +
                    "</td>" +
                    "</tr>";
        } else {
            if (!Flag.isPlanToString()) {
                startOption = "";
                endOption = "";
            }
            text = startOption + id + " " + typeOfWork + " " + days + " " + price + endOption;
        }
        return text;
    }
}

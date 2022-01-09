package com.example.test.entity;

import com.example.test.controller.Flag;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Brigade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long number;
    private Boolean availability;

    @Override
    public String toString() {
        String text;
        String startOption = "<option>";
        String endOption = "</option>";
        if (Flag.isBrigadeToString()) {
            text = "<tr>" +
                    "<td>" + id + "</td>" +
                    "<td>" + number + "</td>" +
                    "<td>" + availability + "</td>" +
                    "</td>" +
                    "</tr>";
        } else {
            if (!Flag.isPlanToString()) {
                startOption = "";
                endOption = "";
            }
            text = startOption + id + " " + number + " " + availability + endOption;
        }
        return text;
    }

}

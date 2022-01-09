package com.example.test.entity;

import com.example.test.controller.Flag;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String place;
    private Long price;

    @Override
    public String toString() {
        String text;
        String startOption = "<option>";
        String endOption = "</option>";
        if (Flag.isPlaceToString()){
            text = "<tr>" +
                        "<td>" + id + "</td>" +
                        "<td>" + place + "</td>" +
                        "<td>" + price + "</td>" +
                    "</tr>";
        } else {
            if (!Flag.isReportToString()) {
                startOption = "";
                endOption = "";
            }
            text = startOption + id + " " + place + " " + price + " рублей" + endOption;
        }
        return text;
    }
}

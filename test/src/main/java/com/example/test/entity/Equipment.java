package com.example.test.entity;

import com.example.test.controller.Flag;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
    public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nameEq;
    private String modelEq;

    @Override
    public String toString() {
        String text;
        String startOption = "<option>";
        String endOption = "</option>";
        if (Flag.isEquipmentToString()){
            text = "<tr>" +
                    "<td>" + id + "</td>" +
                    "<td>" + nameEq + "</td>" +
                    "<td>" + modelEq + "</td>" +
                    "</tr>";
        } else {
            if (!Flag.isReportToString()) {
                startOption = "";
                endOption = "";
            }
            text = startOption + id + " " + nameEq + " " + modelEq + endOption;
        }
        return text;
    }
}

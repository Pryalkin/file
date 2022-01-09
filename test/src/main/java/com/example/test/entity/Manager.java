package com.example.test.entity;

import com.example.test.controller.Flag;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String fname;
    private String lname;
    private String email;
    private String phone;

    @Override
    public String toString() {
        String text;
        String startOption = "<option>";
        String endOption = "</option>";
        if (Flag.isManagerToString()){
            text = "<tr>" +
                    "<td>" + id + "</td>" +
                    "<td>" + fname + "</td>" +
                    "<td>" + lname + "</td>" +
                    "<td>" + email + "</td>" +
                    "<td>" + phone + "</td>" +
                    "</tr>";
        } else {
            if (!Flag.isReportToString()) {
                startOption = "";
                endOption = "";
            }
            text = startOption + id + " " + fname + " " + lname + " " + email + " " + phone + endOption;
        }
        return text;
    }
}

package com.example.test.entity;

import com.example.test.controller.Flag;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne
    private Application application;
    @OneToOne
    private Brigade brigade;
    @OneToOne
    private TypeOfWork typeOfWork;
    private Date startDate;
    private Date endDate;

    @Override
    public String toString() {
        String text;
        String startOption = "<option>";
        String endOption = "</option>";
        if (Flag.isPlanToString()){
            text = "<tr>" +
                    "<td>" + id + "</td>" +
                    "<td>" + application + "</td>" +
                    "<td>" + brigade + "</td>" +
                    "<td>" + typeOfWork + "</td>" +
                    "<td>" + startDate + "</td>" +
                    "<td>" + endDate + "</td>" +
                    "</tr>";
        } else {
            if (Flag.isReportToString()) {
                startOption = "";
                endOption = "";
            }
            text = startOption + id + " " + application + " " + brigade + " " + typeOfWork + " " + startDate + " " + endDate + endOption;
        }
        return text;
    }
}

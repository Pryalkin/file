package com.example.test.entity;

import com.example.test.controller.Flag;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne
    private Customer customer;
    @OneToOne
    private Equipment equipment;
    @OneToOne
    private Place place;
    @OneToOne
    private Manager manager;
    private Date date;
    private Boolean accepted;

    @Override
    public String toString() {
        String text;
        String startOption = "<option>";
        String endOption = "</option>";
        if (Flag.isApplicationToString()) {
            text = "<tr>" +
                    "<td>" + id + "</td>" +
                    "<td>" + customer + "</td>" +
                    "<td>" + equipment + "</td>" +
                    "<td>" + place + "</td>" +
                    "<td>" + manager + "</td>" +
                    "<td>" + date + "</td>" +
                    "<td>" +
                    "<form action='http://localhost:8080/manager/addManager'>" +
                    "<input name='id' type='submit' value='" + id + "'>" +
                    "</form>" +
                    "</td>" +
                    "</tr>";
        } else {
            if (!Flag.isPlanToString()) {
                startOption = "";
                endOption = "";
            }
            text = startOption + id + " " + customer + " " + equipment + " " + place + " " + manager + " " + date + endOption;
        }
        return text;
    }

}

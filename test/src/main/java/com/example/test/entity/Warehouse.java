package com.example.test.entity;

import com.example.test.controller.Flag;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne
    private AppForMat material;
    private Long price;
    private Boolean availability;
    private Date date;

    @Override
    public String toString() {
        String text;
        String startOption = "<option>";
        String endOption = "</option>";
        if (Flag.isWarehouseToString()){
            text = "<tr>" +
                    "<td>" + id + "</td>" +
                    "<td>" + material + "</td>" +
                    "<td>" + price + "</td>" +
                    "<td>" + availability + "</td>" +
                    "<td>" + date + "</td>" +
                    "</tr>";
        } else {
            if (Flag.isReportToString()){
                startOption = "";
                endOption = "";
            }
            text = startOption + id + " " + material + " " + price + " " + availability + " " + date + endOption;
        }
        return text;
    }

}

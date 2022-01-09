package com.example.test.entity;

import com.example.test.controller.Flag;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class AppForMat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nameMat;
    private String modelName;
    private Date date;
    @OneToOne
    private Manager manager;
    private Boolean accepted;

    @Override
    public String toString() {
        String text;
        String startOption = "<option>";
        String endOption = "</option>";
        if (Flag.isAppForMatToString()){
            text = "<tr>" +
                    "<td>" + id + "</td>" +
                    "<td>" + nameMat + "</td>" +
                    "<td>" + modelName + "</td>" +
                    "<td>" + date + "</td>" +
                    "<td>" + manager + "</td>" +
                    "</tr>";
        } else {
            if (!Flag.isWarehouseToString()){
                startOption = "";
                endOption = "";
            }
            text = startOption + id + " " + nameMat + " " + modelName + " " + date + " " + manager + endOption;
        }
        return text;
    }


}

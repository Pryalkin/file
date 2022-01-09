package com.example.test.entity;

import com.example.test.controller.Flag;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Customer {

    @Id // анатация которая говорит, что поле является ID
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id; // id сущности Customer
    private String nameOrg; // название организации
    private String street; // название улицы
    private String house; // название дома

    @Override
    public String toString() {
        String text;
        String startOption = "<option>";
        String endOption = "</option>";
       if (Flag.isCustomerToString()){
            text = "<tr>" +
                    "<td>" + id + "</td>" +
                    "<td>" + nameOrg + "</td>" +
                    "<td>" + street + "</td>" +
                    "<td>" + house + "</td>" +
                    "</tr>";
        } else {
           if (!Flag.isReportToString()){
               startOption = "";
               endOption = "";
           }
            text = startOption + id + " " + nameOrg + " " + street + " " + house + endOption;
        }
        return text;
    }
}

package com.example.test.controller;

import com.example.test.service.ApplicationService;
import com.example.test.service.CustomerService;
import com.example.test.service.EquipmentService;
import com.example.test.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;
    private final CustomerService customerService;
    private final EquipmentService equipmentService;
    private final PlaceService placeService;

    @GetMapping()
    public String application(){
        String text = "";
        if (!Flag.manager || Flag.admin) {
            Flag.isAll();
            Flag.reportToString = true;
            text = "<div align=center>" +
                    "<form action='http://localhost:8080/application/addApplication'>" +
                    "<p><strong>Выберите предприятие</strong></p>" +
                    "<p><select name='customer' type='text'>" +
                    customerService.getAll().toString() +
                    "</select></p>" +
                    "<p><strong>Выберите оборудование</strong></p>" +
                    "<p><select name='equipment' type='text'>" +
                    equipmentService.getAll().toString() +
                    "</select></p>" +
                    "<p><strong>Выберите начальные условия</strong></p>" +
                    "<p><select name='place' type='text'>" +
                    placeService.getAll().toString() +
                    "</select></p>" +
                    "<input type='date' name='date' placeholder='Дата регистрации'/></br>" +
                    "<input type='submit' value='Отправить'></p>" +
                    "</form>" +
                    "</div>";
        }
        return text;
    }

    @GetMapping("/addApplication")
    public String addApplication(@RequestParam String customer,
                          @RequestParam String equipment,
                          @RequestParam String place,
                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date)
    {
        String text = "";
        if (!Flag.manager || Flag.admin) {
            int i;

            StringBuffer sbCustomer = new StringBuffer(customer);
            i = sbCustomer.indexOf(" ");
            sbCustomer.setLength(i);
            Long cust = Long.parseLong(sbCustomer.toString());

            StringBuffer sbEquipment = new StringBuffer(equipment);
            i = sbEquipment.indexOf(" ");
            sbEquipment.setLength(i);
            Long equ = Long.parseLong(sbEquipment.toString());

            StringBuffer sbPlace = new StringBuffer(place);
            i = sbPlace.indexOf(" ");
            sbPlace.setLength(i);
            Long pl = Long.parseLong(sbPlace.toString());

            applicationService.add(cust, equ, pl, date);

            text = "<div align=center>" +
                    "<p>Заявка оформлена!</p>" +
                    "<form action='http://localhost:8080'>" +
                    "<button type='submit'>Выйти с учетной записи</button>" +
                    "</form>" +
                    "</div>";

        }
        return text;
    }
//////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/addApplicationManager")
    public String addApplicationManager(@RequestParam Long id,
                                        @RequestParam String manager) {
        String text = "";
        if (Flag.manager && !Flag.storekeeper || Flag.admin) {
            int i;

            StringBuffer sbCustomer = new StringBuffer(manager);
            i = sbCustomer.indexOf(" ");
            sbCustomer.setLength(i);
            Long man = Long.parseLong(sbCustomer.toString());

            applicationService.addManager(id, man);
            text = "<div align=center>" +
                    "<p>Заявка принята!</p>" +
                    "<a href='http://localhost:8080/home/manager'>Вернуться в кабинет</a>" +
                    "</div>";
        }
        return text;
    }

    @GetMapping("/getAll")
    public String getAll(){
        String text = "";
        if (Flag.manager && !Flag.storekeeper || Flag.admin) {
            Flag.applicationToString = true;
            Flag.isAll();
            text = "<div align=center>" +
                    "<table border=1 align=center>" +
                    "<tr><th>id</th><th>customer</th><th>equipment</th><th>place</th><th>manager</th><th>date</th><th>Принять</th></tr>"+
                    applicationService.getAllExceptManager().toString() +
                    "</table></br>" +
                    "<a href='http://localhost:8080/home/manager'>В кабинет</a>" +
                    "</div>";
        }
        return text;

    }
}

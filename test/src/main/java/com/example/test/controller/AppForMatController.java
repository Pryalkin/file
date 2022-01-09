package com.example.test.controller;

import com.example.test.service.AppForMatService;
import com.example.test.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RequestMapping("appForMat")
@RestController
@RequiredArgsConstructor
public class AppForMatController {

    private final AppForMatService appForMatService;
    private final ManagerService managerService;

    @GetMapping("/formAppForMat")
    public String formAppForMat(){
        String text = "";
        if (Flag.manager && !Flag.storekeeper || Flag.admin) {
            Flag.reportToString = true;
            Flag.managerToString = false;
        text = "<div align=center>" +
                "<form action='http://localhost:8080/appForMat/addAppForMat'>" +
                "<input type='text' name='nameMat' placeholder='Название материала'/></br>" +
                "<input type='text' name='modelName' placeholder='Модель материала'/></br>" +
                "<input type='date' name='date' placeholder='Дата оформления заявки'/></br>" +
                "<p><strong>Выберите менеджера</strong></p>" +
                "<p><select name='manager' type='text'>" +
                managerService.getAll().toString() +
                "</select></p>" +
                "<button type='submit'>Создать</button>" +
                "</form>" +
                "</div>";
            }
        return text;
    }

    @RequestMapping("/addAppForMat")
    public String addAppForMat(@RequestParam String nameMat,
                          @RequestParam String modelName,
                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                          @RequestParam String manager){
        String text = "";
        if (Flag.manager && !Flag.storekeeper || Flag.admin) {
            int i;

            StringBuffer sbManager = new StringBuffer(manager);
            i = sbManager.indexOf(" ");
            sbManager.setLength(i);
            Long man = Long.parseLong(sbManager.toString());

            appForMatService.add(nameMat, modelName, date, man);

            text = "<div align=center>" +
                    "<p>Успешно добавлено!</p>" +
                    "<a href='http://localhost:8080/home/manager'>Назад</a>" +
                    "</div>";
        }
        return text;
    }
}

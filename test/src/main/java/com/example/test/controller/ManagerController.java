package com.example.test.controller;

import com.example.test.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/manager")
@RestController
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @GetMapping("/addMan")
    public String addMan(){
        String text = "";
        if (Flag.admin) {
                text = "<div align=center>" +
                        "<form action='http://localhost:8080/manager/add'>" +
                        "<input type='text' name='fname' placeholder='Имя'></br>" +
                        "<input type='text' name='lname' placeholder='Фамилия'/></br>" +
                        "<input type='text' name='email' placeholder='email'/></br>" +
                        "<input type='text' name='phone' placeholder='телефон'/></br>" +
                        "<button type='submit'>Создать</button>" +
                        "</form>" +
                        "</div>";
        }
        return text;
    }

    @GetMapping("/add")
    public String getInfo(@RequestParam String fname,
                          @RequestParam String lname,
                          @RequestParam String email,
                          @RequestParam String phone){
        String text = "";
        if (Flag.admin) {
            text = "<div align=center>" +
                    "<p>Успешно добавлен!</p>" +
                    "<a href='http://localhost:8080/home/admin'>В кабинет админа</a>" +
                    "</div>";
            managerService.add(fname, lname, email, phone);
        }
        return text;
    }
//////////////////////////////////////////////////////////////////////
    @GetMapping("/addManager")
    public String addManager(@RequestParam Long id){
        String text = "";
        if (Flag.manager && !Flag.storekeeper || Flag.admin) {
            Flag.managerToString = false;
            Flag.reportToString = true;
            Long idApp = id;
            text = "<div align=center>" +
                    "<form action='http://localhost:8080/application/addApplicationManager'>" +
                    "<p><strong>Выбрать менеджера</strong></p>" +
                    "<p><select name='manager'>" +
                    managerService.getAll().toString() +
                    "</select></p>" +
                    "<p><select name='id'>" +
                    "<option>" + idApp + "</option>" +
                    "</select></p>" +
                    "<input type='submit' value='Отправить'></p>" +
                    "</form>" +
                    "</div>";
        }
    return text;
    }
}

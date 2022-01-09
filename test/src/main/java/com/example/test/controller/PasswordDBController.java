package com.example.test.controller;

import com.example.test.service.PasswordDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
@RequiredArgsConstructor
public class PasswordDBController {

    private final PasswordDBService passwordDBService;

    @GetMapping()
    public String authorization(){
        Flag.customer = false;
        Flag.equipment = false;
        Flag.place = false;
        Flag.admin = false;
        Flag.manager = false;
        Flag.storekeeper = false;
        return "<div align=center>" +
                "<p>Войдите в свою учетную запись</p>" +
                "<p>или если вы простой пользователь,</p> " +
                "<p>то просто нажмите на Войти.</p>" +
                "<form action='http://localhost:8080/check'>" +
                "<input type='text' name='login' placeholder='Логин'/></br>" +
                "<input type='text' name='pass' placeholder='Пароль'/></br>" +
                "<button type='submit'>Войти</button>" +
                "</form>" +
                "</div>";
    }

    @GetMapping("/check")
    public String check(@RequestParam String login,
                        @RequestParam String pass) {
        String text = "";
        boolean flagManager = passwordDBService.checkDB(login, pass);
        boolean flagAdmin = false;
        boolean flagStorekeeper = false;

        if (flagManager){
            Flag.manager = true;
            flagStorekeeper = passwordDBService.checkDBStorekeeper(login, pass);
            if (flagStorekeeper){
                Flag.storekeeper = true;
            }
            flagAdmin = passwordDBService.checkDBAdmin(login, pass);
            if (flagAdmin) Flag.admin = true;
        }
        if (!flagManager) {
            text = "<div align=center><a href='http://localhost:8080/home/user'>Оформление заявки</a></div";
        }
        if (flagManager && !flagAdmin && !flagStorekeeper){
            text = "<div align=center><a href='http://localhost:8080/home/manager'>В кабинет менеджера</a></div";
        }
        if (flagManager && flagAdmin){
            text = "<div align=center>" +
                    "<p><a href='http://localhost:8080/home/user'>Оформление заявки</a</p>" +
                    "<p><a href='http://localhost:8080/home/manager'>В кабинет менеджера</a></p>" +
                    "<p><a href='http://localhost:8080/home/storekeeper'>В кабинет кладовщика</a></p>" +
                    "<p><a href='http://localhost:8080/home/admin'>В кабинет администратора</a></p>" +
                    "</div>";
        }
        if (flagManager && flagStorekeeper){
            text = "<div align=center><a href='http://localhost:8080/home/storekeeper'>В кабинет кладовщика</a></div";
        }
        return text;
    }

    @GetMapping("/check/addManagerForm")
    public String addManagerForm(){
        String text = "";
        if (Flag.admin) {
            text = "<div align=center>" +
                    "<form action='http://localhost:8080/check/addManagerForm/addManager'>" +
                    "<input type='text' name='login' placeholder='Логин'/></br>" +
                    "<input type='text' name='pass' placeholder='Пароль'/></br>" +
                    "<button type='submit'>Создать</button>" +
                    "</form>" +
                    "</div>";
        }
        return text;
    }

    @GetMapping("/check/addManagerForm/addManager")
    public String addManager(@RequestParam String login,
                             @RequestParam String pass){
        String text = "";
        if (Flag.admin) {
            passwordDBService.add(login, pass);
            text = "<div align=center>" +
                    "<p>Менеджер успешно добавлен в базу данных!</p>" +
                    "<a href='http://localhost:8080/home/admin'>В кабинет админа</a>" +
                    "</div>";
        }
        return text;
    }
}

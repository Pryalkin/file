package com.example.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/home")
@RestController
    public class HomeController {

    @GetMapping("/user")
    public String user(){
        String error = "";
        if (!Flag.manager || Flag.admin){
        String checkMrkCustomer = "";
        String checkMrkEquipment = "";
        String checkMrkPlace = "";
        String text = "";
        if (Flag.customer) {
            checkMrkCustomer = "&#10004";
        }
        if (Flag.equipment) {
            checkMrkEquipment = "&#10004";
        }
        if (Flag.place) {
            checkMrkPlace = "&#10004";
        }
        if (Flag.customer && Flag.equipment && Flag.place){
            text = "<p>Второй этап. Подтверждение.</p>" +
                    "<p><a href='http://localhost:8080/application'>Подтвердить заявку</a></p>";
        }

            error = "<div style='text-align:center; width:100%'>" +
                    "<p>Первый этап. Регистрация.</p>" +
                    "<p><a href='http://localhost:8080/customer'>Предприятие</a>" + checkMrkCustomer + "</p>" +
                    "<p><a href='http://localhost:8080/equipment'>Оборудование</a>" + checkMrkEquipment + "</p>" +
                    "<p><a href='http://localhost:8080/place'>Место провождение</a>" + checkMrkPlace + "</p>" +
                    text +
                    "<form action='http://localhost:8080'>" +
                    "<button type='submit'>Выйти с учетной записи</button>" +
                    "</form>" +
                    "</div>";
        }
        return error;
    }

    @GetMapping("/manager")
    public String manager(){
        String text = "";
        if (Flag.manager && !Flag.storekeeper || Flag.admin){
            text = "<div align=center>" +
                    "<p><a href='http://localhost:8080/place/formPlaceForManager'>Добавить место</a></p>" +
                    "<p><a href='http://localhost:8080/application/getAll'>Принять заявку</a></p>" +
                    "<p><a href='http://localhost:8080/plan'>Оформить плановую работу</a></p>" +
                    "<p><a href='http://localhost:8080/appForMat/formAppForMat'>Сделать заявку на материалы</a></p>" +
                    "<p><a href='http://localhost:8080/report'>Отчет по проделанным работам</a></p>" +
                    "<form action='http://localhost:8080'>" +
                    "<button type='submit'>Выйти с учетной записи</button>" +
                    "</form>" +
                    "</div>";
        }
        return text;
    }

    @GetMapping("/storekeeper")
    public String storekeeper(){
        String text = "";
        if (Flag.storekeeper || Flag.admin) {
            text = "<div align=center>" +
                    "<p><a href='http://localhost:8080/warehouse/getAll'>Просмотреть склад</a></p>" +
                    "<p><a href='http://localhost:8080/warehouse/formWarehouse'>Поставить на приход</a></p>" +
                    "<form action='http://localhost:8080'>" +
                    "<button type='submit'>Выйти с учетной записи</button>" +
                    "</form>" +
                    "</div>";
        }
        return text;
    }

    @GetMapping("/admin")
    public String admin(){
        String text = "";
        if (Flag.admin) {
            text = "<div align=center>" +
                    "<p><a href='http://localhost:8080/check/addManagerForm'>Создать аккаунт для менеджера или кладовщика</a></p>" +
                    "<p><a href='http://localhost:8080/manager/addMan'>Добавить менеджера в базу дынных</a></p>" +
                    "<p><a href='http://localhost:8080/adjuster/addAdjusterFrom'>Добавить наладчика в базу дынных</a></p>" +
                    "<form action='http://localhost:8080'>" +
                    "<button type='submit'>Выйти с учетной записи</button>" +
                    "</form>" +
                    "</div>";
        }
        return text;
    }


}

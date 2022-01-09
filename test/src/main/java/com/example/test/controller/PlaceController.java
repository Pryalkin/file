package com.example.test.controller;

import com.example.test.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/place")
@RestController
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping
    public String place(){
        String text = "";
        if (!Flag.manager || Flag.admin) {
            if (Flag.place) {
                text = "<div align=center>" +
                        "<p>Вы уже добавили место</a></p>" +
                        "<a href='http://localhost:8080/home/user'>Вернуться к оформлению заявки</a>" +
                        "</div>";
            } else
                text = "<div align=center>" +
                        "<p><a href='http://localhost:8080/place/formPlace'>Подтвердить место</a></p>" +
                        "<p><a href='http://localhost:8080/place/getAll'>Вывести весь список</a></p>" +
                        "<a href='http://localhost:8080/home/user'>Назад</a>" +
                        "</div>";
        }
        return text;
    }

    @GetMapping("/formPlace")
    public String formPlace(){
        String text = "";
        if (!Flag.manager || Flag.admin) {
            Flag.placeToString = false;
            Flag.reportToString = true;
            text = "<div align=center>" +
                    "<form action='http://localhost:8080/place/addPlace'>" +
                    "<p><strong>Выберите место</strong></p>" +
                    "<p><select name='place'>" +
                    placeService.getAll().toString() +
                    "</select></p>" +
                    "<input type='submit' value='Отправить'></p>" +
                    "</form>" +
                    "</div>";
        }
        return text;
    }

    @GetMapping("/formPlaceForManager")
    public String formPlaceForManager(){
        String text = "";
        if (Flag.manager && !Flag.storekeeper || Flag.admin){
            text = "<div align=center>" +
                    "<form action='http://localhost:8080/place/addPlaceForManager'>" +
                    "<input type='text' name='place' placeholder='Место обслуживания'/></br>" +
                    "<input type='text' name='price' placeholder='Цена'/></br>" +
                    "<button type='submit'>Создать</button>" +
                    "</form>" +
                    "</div>";
        }
        return text;
    }

    @GetMapping("/addPlace")
    public String addPlace(){
        String text = "";
        if (!Flag.manager || Flag.admin) {
            Flag.place = true;
            text = "<div align=center>" +
                    "<p>Успешно добавлено!</p>" +
                    "<a href='http://localhost:8080/home/user'>Вернуться к оформлению заявки</a>" +
                    "</div>";
        }
        return text;
    }

    @GetMapping("/addPlaceForManager")
    public String addPlaceForManager(@RequestParam String place,
                                     @RequestParam Long price){
        String text = "";
        if (Flag.manager && !Flag.storekeeper || Flag.admin){
            placeService.add(place, price);
            text = "<div align=center>" +
                    "<p>Успешно добавлено!</p>" +
                    "<a href='http://localhost:8080/home/manager'>Вернуться в кабинет</a>" +
                    "</div>";
        }
        return text;
    }

    @GetMapping("/getAll")
    public String getAll(){
        String text = "";
        if (!Flag.manager || Flag.admin) {
            Flag.placeToString = true;
            text = "<div align=center>" +
                    "<table border=1 align=center>" +
                    "<tr><th>id</th><th>Место</th><th>Цена</th></tr>"+
                    placeService.getAll().toString() +
                    "</table></br>" +
                    "<a href='http://localhost:8080/home/user'>Вернуться к оформлению заявки</a>" +
                    "</div>";
        }
        return text;
    }
}

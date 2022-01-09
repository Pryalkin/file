package com.example.test.controller;

import com.example.test.service.TypeOfWorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/typeOfWork")
@RestController
@RequiredArgsConstructor
    public class TypeOfWorkController {

    private final TypeOfWorkService typeOfWorkService;

    @GetMapping("/fromTypeOfWork")
    public String fromTypeOfWork(){
        String text = "";
        if (Flag.manager && !Flag.storekeeper || Flag.admin){
            text = "<div align=center>" +
                    "<form action='http://localhost:8080/typeOfWork/addTypeOfWork'>" +
                    "<input type='text' name='typeOfWork' placeholder='Тип работы'></br>" +
                    "<input type='number' name='days' placeholder='Количество дней'></br>" +
                    "<input type='number' name='price' placeholder='Цена'></br>" +
                    "<button type='submit'>Создать</button>" +
                    "</form>" +
                    "</div>";
        }
        return text;
    }

    @GetMapping("/addTypeOfWork")
    public String addTypeOfWork(@RequestParam String typeOfWork,
                          @RequestParam Long days,
                          @RequestParam Long price){
        String text = "";
        if (Flag.manager && !Flag.storekeeper || Flag.admin){
            text = "<div align=center>" +
                    "<p>Успешно добавлено!</p>" +
                    "<a href='http://localhost:8080/plan'>Назад</a>" +
                    "</div>";
        }
        typeOfWorkService.add(typeOfWork, days, price);
        return text;
    }
}

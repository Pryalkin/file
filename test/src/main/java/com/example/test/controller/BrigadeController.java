package com.example.test.controller;

import com.example.test.service.AdjusterService;
import com.example.test.service.BrigadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/brigade")
@RestController
@RequiredArgsConstructor
public class BrigadeController {

    public final BrigadeService brigadeService;
    public final AdjusterService adjusterService;

    @RequestMapping()
    public String addBrigade(){
        String text = "";
        if (Flag.manager && !Flag.storekeeper || Flag.admin) {
            text = "<div align=center>" +
                    "<p><a href='http://localhost:8080/brigade/fromBrigade'>Добавить информацию в сущность бригады</a></p>" +
                    "<p><a href='http://localhost:8080/brigade/getAll'>Показать таблицу всех бригад</a></p>" +
                    "<p><a href='http://localhost:8080/adjuster/getAll'>Показать таблицу всех наладчиков</a></p>" +
                    "<p><a href='http://localhost:8080/plan'>Назад</a></p>" +
                    "</div>";
        }
        return text;
    }

    @RequestMapping("/addBrigade")
    public String addBrigade(@RequestParam Long number){
        String text = "";
        if (Flag.manager && !Flag.storekeeper || Flag.admin) {
            text = "<div align=center>" +
                    "<p>Успешно добавлено!</p>" +
                    "<a href='http://localhost:8080/brigade'>Назад</a>" +
                    "</div>";
        }
        brigadeService.add(number);
        return text;
    }

    @GetMapping("/fromBrigade")
    public String fromBrigade(){
        String text = "";
        if (Flag.manager && !Flag.storekeeper || Flag.admin) {
            text = "<div align=center>" +
                    "<form action='http://localhost:8080/brigade/addBrigade'>" +
                    "<input type='text' name='number' placeholder='Номер бригады'></br>" +
                    "<button type='submit'>Создать</button>" +
                    "</form>" +
                    "</div>";
        }
        return text;
    }

    @GetMapping("/getAll")
    public String getAll(){
        String text = "";
        if (Flag.manager && !Flag.storekeeper || Flag.admin) {
            Flag.brigadeToString = true;
            text = "<div align=center>" +
                    "<table border=1 align=center>" +
                    "<tr><th>id</th><th>number</th><th>availability</th></tr>"+
                    brigadeService.getAll().toString() +
                    "</table></br>" +
                    "<a href='http://localhost:8080/brigade'>Назад</a>" +
                    "</div>";
        }
        return text;
    }
}

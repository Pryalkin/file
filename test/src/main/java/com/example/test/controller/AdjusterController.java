package com.example.test.controller;

import com.example.test.service.AdjusterService;
import com.example.test.service.BrigadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/adjuster")
@RestController
@RequiredArgsConstructor
public class AdjusterController {

    private final AdjusterService adjusterService;
    private final BrigadeService brigadeService;

    @GetMapping("/addAdjuster")
    public String getInfo(@RequestParam String fname,
                          @RequestParam String lname,
                          @RequestParam Long grade,
                          @RequestParam String numberBrigades,
                          @RequestParam String phone){
        String text = "";
        if (Flag.admin) {
            int i;

            StringBuffer sbNumberBrigades = new StringBuffer(numberBrigades);
            i = sbNumberBrigades.indexOf(" ");
            sbNumberBrigades.setLength(i);
            Long number = Long.parseLong(sbNumberBrigades.toString());

            adjusterService.add(fname, lname, grade, number, phone);

            text = "<div align=center>" +
                    "<p>Успешно добавлен!</p>" +
                    "<a href='http://localhost:8080/home/admin'>В кабинет админа</a>" +
                    "</div>";
        }
        return text;
    }

    @GetMapping("/addAdjusterFrom")
    public String addMan(){
        String text = "";
        if (Flag.admin) {
        Flag.brigadeToString = false;
        Flag.planToString = true;
            text = "<div align=center>" +
                    "<form action='http://localhost:8080/adjuster/addAdjuster'>" +
                    "<input type='text' name='fname' placeholder='Имя'></br>" +
                    "<input type='text' name='lname' placeholder='Фамилия'/></br>" +
                    "<input type='number' name='grade' placeholder='Разряд'/></br>" +
                    "<p><select name='numberBrigades' type='number'>" +
                    brigadeService.getAll().toString() +
                    "</select></p>" +
                    "<input type='text' name='phone' placeholder='Телефон'/></br>" +
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
            Flag.brigadeToString = false;
            text =  "<div align=center>" +
                    "<table border=1 align=center>" +
                    "<tr><th>id</th><th>fname</th><th>lname</th><th>grade</th><th>numberBrigades</th><th>phone</th></tr>"+
                    adjusterService.getAll().toString() +
                    "</table></br>" +
                    "<a href='http://localhost:8080/brigade'>Назад</a>" +
                    "</div>";
        }
        return text;
    }


}

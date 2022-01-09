package com.example.test.controller;

import com.example.test.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/equipment")
@RestController
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;

    @GetMapping
    public String equipment(){
        String text = "";
        if (!Flag.manager || Flag.admin) {
            if (Flag.equipment) {
                text = "<div align=center>" +
                        "<p>Вы уже добавили оборудование</a></p>" +
                        "<a href='http://localhost:8080/home/user'>Вернуться к оформлению заявки</a>" +
                        "</div>";
            } else
                text = "<div align=center>" +
                        "<p><a href='http://localhost:8080/equipment/formEquipment'>Добавить оборудование</a></p>" +
                        "<p><a href='http://localhost:8080/equipment/getAll'>Вывести весь список</a></p>" +
                        "<a href='http://localhost:8080/home/user'>Назад</a>" +
                        "</div>";
        }
        return text;
    }

    @GetMapping("/formEquipment")
    public String formEquipment(){
        String text = "";
        if (!Flag.manager || Flag.admin) {
            text = "<div align=center>" +
                    "<form action='http://localhost:8080/equipment/addEquipment'>" +
                    "<input type='text' name='nameEq' placeholder='Название оборудования'/></br>" +
                    "<input type='text' name='modelEq' placeholder='Название модели'/></br>" +
                    "<button type='submit'>Создать</button>" +
                    "</form>" +
                    "</div>";
        }
        return text;
    }

    @GetMapping("/addEquipment")
    public String addEquipment(@RequestParam String nameEq,
                          @RequestParam String modelEq){
        String text = "";
        if (!Flag.manager || Flag.admin) {
            Flag.equipment = true;
            equipmentService.add(nameEq, modelEq);
            text = "<div align=center>" +
                    "<p>Успешно добавлено!</p>" +
                    "<a href='http://localhost:8080/home/user'>Вернуться к оформлению заявки</a>" +
                    "</div>";
        }
        return text;
    }

    @GetMapping("/getAll")
    public String getAll(){
        String text = "";
        if (!Flag.manager || Flag.admin) {
            Flag.equipmentToString = true;
            text = "<div align=center>" +
                    "<table border=1 align=center>" +
                    "<tr><th>id</th><th>nameEq</th><th>modelEq</th></tr>"+
                    equipmentService.getAll().toString() +
                    "</table></br>" +
                    "<a href='http://localhost:8080/home/user'>Вернуться к оформлению заявки</a>" +
                    "</div>";
        }
        return text;
    }
}

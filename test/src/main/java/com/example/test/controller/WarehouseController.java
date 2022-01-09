package com.example.test.controller;

import com.example.test.service.AppForMatService;
import com.example.test.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

@RequestMapping("/warehouse")
@RestController
@RequiredArgsConstructor
public class WarehouseController {

    public final WarehouseService warehouseService;
    public final AppForMatService appForMatService;


    @GetMapping("/formWarehouse")
    public String formWarehouse(){
        String text = "";
        if (Flag.storekeeper || Flag.admin) {
            Flag.appForMatToString = false;
            Flag.reportToString = false;
            Flag.managerToString = false;
            Flag.warehouseToString = true;
            text = "<div align=center>" +
                    "<form action='http://localhost:8080/warehouse/addWarehouse'>" +
                    "<p><strong>Выберите заявку</strong></p>" +
                    "<p><select name='material' type='text'>" +
                    appForMatService.getAll().toString() +
                    "</select></p>" +
                    "<input type='number' name='price' placeholder='Цена'></br>" +
                    "<input type='date' name='date' placeholder='Дата прихода'/></br>" +
                    "<button type='submit'>Создать</button>" +
                    "</form>" +
                    "</div>";
        }
        return text;
    }

    @RequestMapping("/addWarehouse")
    public String gerInfo(@RequestParam String material,
                          @RequestParam Long price,
                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        String text = "";
        if (Flag.storekeeper || Flag.admin) {
            int i;

            StringBuffer sbMaterial = new StringBuffer(material);
            i = sbMaterial.indexOf(" ");
            sbMaterial.setLength(i);
            Long mat = Long.parseLong(sbMaterial.toString());

            warehouseService.add(mat, price, date);

            text = "<div align=center>" +
                    "<p>Успешно добавлено!</p>" +
                    "<a href='http://localhost:8080/home/storekeeper'>Назад</a>" +
                    "</div>";
        }
        return text;
    }

    @GetMapping("/getAll")
    public String getAll(){
        String text = "";
        if (Flag.storekeeper || Flag.admin) {
            Flag.warehouseToString = true;
            text =  "<div align=center>" +
                    "<table border=1 align=center>" +
                    "<tr><th>id</th><th>material</th><th>price</th><th>availability</th><th>date</th></tr>"+
                    warehouseService.getAll().toString() +
                    "</table></br>" +
                    "<a href='http://localhost:8080/home/storekeeper'>Назад</a>" +
                    "</div>";
        }
        return text;

    }
}

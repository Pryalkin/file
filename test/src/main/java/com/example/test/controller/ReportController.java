package com.example.test.controller;

import com.example.test.service.PlanService;
import com.example.test.service.ReportService;
import com.example.test.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/report")
@RestController
@RequiredArgsConstructor
public class ReportController {

    public final ReportService reportService;
    public final PlanService planService;
    public final WarehouseService warehouseService;

    @GetMapping()
    public String report(){
        String text = "";
        if (Flag.manager && !Flag.storekeeper || Flag.admin){
            text = "<div align=center>" +
                    "<p><a href='http://localhost:8080/report/formReport'>Оформить отчет</a></p>" +
                    "<p><a href='http://localhost:8080/report/getAll'>Посмотреть таблицу отчетов</a></p>" +
                    "<p><a href='http://localhost:8080/home/manager'>Назад</a></p>" +
                    "</div>";
        }
        return text;
    }

    @GetMapping("/formReport")
    public String formReport(){
        String text = "";
        if (Flag.manager && !Flag.storekeeper || Flag.admin) {
            Flag.planToString = false;
            Flag.reportToString = false;
            Flag.applicationToString = false;
            Flag.brigadeToString = false;
            Flag.typeOfWorkToString = false;
            Flag.isAll();
            Flag.warehouseToString = false;
            Flag.appForMatToString = false;
            Flag.managerToString = false;
            text = "<div align=center>" +
                    "<form action='http://localhost:8080/report/add'>" +
                    "<p><strong>Выберите плановую работу</strong></p>" +
                    "<p><select name='plan' type='text'>" +
                    planService.getAll().toString() +
                    "</select></p>" +
                    "<p><strong>Выберите расходный материал</strong></p>" +
                    "<p><select name='material' value='0' type='text'>" +
                    warehouseService.getAll().toString() +
                    "</select></p>" +
                    "<button type='submit'>Создать</button>" +
                    "</form>" +
                    "</div>";
        }
        return text;
    }

    @RequestMapping("/add")
    public String getInfo(@RequestParam String plan,
                          @RequestParam(required = false) String material){
        String text = "";
        if (Flag.manager && !Flag.storekeeper || Flag.admin) {
            int i;

            StringBuffer sbPlan = new StringBuffer(plan);
            i = sbPlan.indexOf(" ");
            sbPlan.setLength(i);
            Long pl = Long.parseLong(sbPlan.toString());

            Long mat;
            if (material != null) {
                StringBuffer sbMaterial = new StringBuffer(material);
                i = sbMaterial.indexOf(" ");
                sbMaterial.setLength(i);
                mat = Long.parseLong(sbMaterial.toString());
            } else mat = 0L;

            reportService.add(pl, mat);

            text = "<div align=center>" +
                    "<p>Отчет успешно оформлен</p>" +
                    "<a href='http://localhost:8080/home/manager'>Вернуться в кабинет</a>" +
                    "</div>";
        }
        return text;
    }

    @GetMapping("/getAll")
    public String getAll(){
        String text = "";
        if (Flag.manager && !Flag.storekeeper || Flag.admin) {
            Flag.isAll();
            Flag.reportToString = false;
            Flag.managerToString = false;
            Flag.applicationToString = false;
            Flag.typeOfWorkToString = false;
            Flag.brigadeToString = false;
            Flag.planToString = false;
            Flag.appForMatToString = false;
            Flag.warehouseToString = false;
            text = "<div align=center>" +
                    "<table border=1 align=center>" +
                    "<tr><th>id</th><th>plan</th><th>material</th></tr>"+
                    reportService.getAll().toString() +
                    "</table></br>" +
                    "<a href='http://localhost:8080/report'>Назад</a>" +
                    "</div>";
        }
        return text;

    }
}

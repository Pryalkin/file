package com.example.test.controller;

import com.example.test.service.ApplicationService;
import com.example.test.service.BrigadeService;
import com.example.test.service.PlanService;
import com.example.test.service.TypeOfWorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RequestMapping("/plan")
@RestController
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;
    private final ApplicationService applicationService;
    private final BrigadeService brigadeService;
    private final TypeOfWorkService typeOfWorkService;

    @GetMapping()
    public String plan(){
        String text = "";
        if (Flag.manager && !Flag.storekeeper || Flag.admin){
            text = "<div align=center>" +
                    "<p><a href='http://localhost:8080/brigade'>Информация о бригаде</a></p>" +
                    "<p><a href='http://localhost:8080/typeOfWork/fromTypeOfWork'>Добавить информацию о регламентных работах</a></p>" +
                    "<p><a href='http://localhost:8080/plan/formPlan'>К оформлению</a></p>" +
                    "<p><a href='http://localhost:8080/home/manager'>Вернуться в кабинет</a></p>" +
                    "</div>";
        }
        return text;
    }

    @RequestMapping("/addPlan")
    public String addPlan(@RequestParam String application,
                          @RequestParam String brigade,
                          @RequestParam String typeOfWork,
                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
        String text = "";
        if (Flag.manager && !Flag.storekeeper || Flag.admin) {
            int i;

            StringBuffer sbApplication = new StringBuffer(application);
            i = sbApplication.indexOf(" ");
            sbApplication.setLength(i);
            Long app = Long.parseLong(sbApplication.toString());

            StringBuffer sbBrigade = new StringBuffer(brigade);
            i = sbBrigade.indexOf(" ");
            sbBrigade.setLength(i);
            Long br = Long.parseLong(sbBrigade.toString());

            StringBuffer sbTypeOfWork = new StringBuffer(typeOfWork);
            i = sbTypeOfWork.indexOf(" ");
            sbTypeOfWork.setLength(i);
            Long type = Long.parseLong(sbTypeOfWork.toString());

            planService.add(app, br, type, startDate, endDate);

            text =  "<div align=center>" +
                    "<p>Успешно добавлено!</p>" +
                    "<a href='http://localhost:8080/plan'>Назад</a>" +
                    "</div>";
        }
        return text;

    }

    @GetMapping("/formPlan")
    public String formPlan(){
        String text = "";
        if (Flag.manager && !Flag.storekeeper || Flag.admin) {
            Flag.brigadeToString = false;
            Flag.typeOfWorkToString = false;
            Flag.applicationToString = false;
            Flag.isAll();
            Flag.reportToString = false;
            Flag.planToString = true;
            text = "<div align=center>" +
                    "<form action='http://localhost:8080/plan/addPlan'>" +
                    "<p><strong>Выберите заявку</strong></p>" +
                    "<p><select name='application' type='text'>" +
                    applicationService.getAllExceptNullManager().toString() +
                    "</select></p>" +
                    "<p><strong>Выберите бригаду</strong></p>" +
                    "<p><select name='brigade' type='text'>" +
                    brigadeService.getAllExceptAvailable().toString() +
                    "</select></p>" +
                    "<p><strong>Выберите регламентную работу</strong></p>" +
                    "<p><select name='typeOfWork' type='text'>" +
                    typeOfWorkService.getAll().toString() +
                    "</select></p>" +
                    "<input type='date' name='startDate' placeholder='Дата начала работ(dd.MM.yyyy)'/></br>" +
                    "<input type='date' name='endDate' placeholder='Дата окончания работ(dd.MM.yyyy)'/></br>" +
                    "<input type='submit' value='Отправить'></p>" +
                    "</form>" +
                    "</div>";
        }
        return text;
    }
}

package com.example.test.controller;
import com.example.test.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/customer")
@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public String customer(){
        String text = "";
        if (!Flag.manager || Flag.admin) {
            if (Flag.customer) {
                text = "<div align=center>" +
                        "<p>Вы уже добавили предприятие</a></p>" +
                        "<a href='http://localhost:8080/home/user'>Вернуться к оформлению заявки</a>" +
                        "</div>";
            } else
                text = "<div align=center>" +
                        "<p><a href='http://localhost:8080/customer/formCustomer'>Добавить предприятие</a></p>" +
                        "<p><a href='http://localhost:8080/customer/getAll'>Вывести весь список</a></p>" +
                        "<a href='http://localhost:8080/home/user'>Назад</a>" +
                        "</div>";
        }
        return text;
    }

    @GetMapping("/formCustomer")
    public String formCustomer(){
        String text = "";
        if (!Flag.manager || Flag.admin) {
            text = "<div align=center>" +
                    "<form action='http://localhost:8080/customer/addCustomer'>" +
                    "<input type='text' name='nameOrg' placeholder='Название предприятия'/></br>" +
                    "<input type='text' name='street' placeholder='Улица'/></br>" +
                    "<input type='text' name='house' placeholder='Дом'/></br>" +
                    "<button type='submit'>Создать</button>" +
                    "</form>" +
                    "</div>";
        }
        return text;
    }

    @GetMapping("/addCustomer") // анатация для метода
    public String addCustomer(@RequestParam String nameOrg, // поле типа стринг которое
                          @RequestParam String street,      // считываюся с браузера
                          @RequestParam String house) {
        String text = "";
        if (!Flag.manager || Flag.admin) { // проверка на право доступа
            Flag.customer = true;
            customerService.add(nameOrg, street, house); // у объекта Service вызывается метод в который
            text = "<div align=center>" +                // передается информация полученная с браузера
                    "<p>Успешно добавлено!</p>" +
                    "<a href='http://localhost:8080/home/user'>Вернуться к оформлению заявки</a>" +
                    "</div>";
        }
        return text; // тест который отобразится в браузере
    }

    @GetMapping("/getAll")
    public String getAll(){
        String text = "";
        if (!Flag.manager || Flag.admin) {
            Flag.customerToString = true;
            text = "<div align=center>" +
                    "<table border=1 align=center>" +
                    "<tr><th>id</th><th>nameOrg</th><th>street</th><th>house</th></tr>"+
                    customerService.getAll().toString() +
                    "</table></br>" +
                    "<a href='http://localhost:8080/home/user'>Вернуться к оформлению заявки</a>" +
                    "</div>";
        }
        return text;
    }
}
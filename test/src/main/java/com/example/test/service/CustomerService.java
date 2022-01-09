package com.example.test.service;

import com.example.test.entity.Customer;
import com.example.test.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public void add(String name, String street, String house) {
        Customer customer = customerRepository. // создается объект класса(сущности) Customer
                findByNameOrgAndStreetAndHouse(name, street, house). // ищется в БД аналогичная строка
                orElse(new Customer()); // и если есть, то возвращает, а если нет создает новый объект
        customer.setNameOrg(name); // сохраняет или пересохраняет переданное значение из контролера
        customer.setStreet(street); // сохраняет или пересохраняет переданное значение из контролера
        customer.setHouse(house); // сохраняет или пересохраняет переданное значение из контролера
        customerRepository.save(customer); // у объекта Repository вызывается метод, который сохраняет объект в БД
    }

    public Customer getByNameOrg(String name){
        return customerRepository.findByNameOrg(name);
    }

    public List<Customer> getAll(){
        return customerRepository.findAll();
    }
}

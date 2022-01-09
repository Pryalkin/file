package com.example.test.service;

import com.example.test.entity.PasswordDB;
import com.example.test.repository.PasswordDBRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequiredArgsConstructor
public class PasswordDBService {

    private final PasswordDBRepository passwordDBRepository;

    public boolean checkDB(String login, String pass) {
        boolean passwordDB = passwordDBRepository.findByLoginAndPass(login, pass).isPresent();
        return passwordDB;
    }

    public boolean checkDBAdmin(String login, String pass) {
        boolean flagAdmin = false;
        PasswordDB passwordDB = passwordDBRepository.findByLoginAndPass(login, pass).orElse(new PasswordDB());
        if (passwordDB.getLogin().equals("AdminLogin")){
            if (passwordDB.getPass().equals("AdminPassword")){
                flagAdmin = true;
            }
        }
        return flagAdmin;
    }

    public boolean checkDBStorekeeper(String login, String pass) {
        boolean flagStorekeeper = false;
        PasswordDB passwordDB = passwordDBRepository.findByLoginAndPass(login, pass).orElse(new PasswordDB());
        if (passwordDB.getLogin().equals("Storekeeper")){
            if (passwordDB.getPass().equals("Storekeeper")){
                flagStorekeeper = true;
            }
        }
        return flagStorekeeper;
    }



    public void add(String login, String pass) {
        PasswordDB passwordDB = new PasswordDB();
        passwordDB.setLogin(login);
        passwordDB.setPass(pass);
        passwordDBRepository.save(passwordDB);
    }
}

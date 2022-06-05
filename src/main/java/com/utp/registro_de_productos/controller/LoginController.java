package com.utp.registro_de_productos.controller;

import com.utp.registro_de_productos.model.UserModel;
import com.utp.registro_de_productos.provider.LoginProvider;

public class LoginController {
    
    public void onLoginClick(String username, String password) {
        UserModel user = new LoginProvider().getUserIfPasswordValid(username, password);
        if (user != null) {
            System.out.println("ga");
        } else {
            System.out.println("gaaaaa");
        }
    }
    
}

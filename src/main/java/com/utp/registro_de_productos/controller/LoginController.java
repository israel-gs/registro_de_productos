package com.utp.registro_de_productos.controller;

import com.utp.registro_de_productos.model.UserModel;
import com.utp.registro_de_productos.provider.LoginProvider;
import io.vavr.control.Either;

public class LoginController {

    public Either<String, UserModel>  onLoginClick(String username, String password) {
        Either<String, UserModel> user = new LoginProvider().getUserIfPasswordValid(username, password);
        return user;
    }

}

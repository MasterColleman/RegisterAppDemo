package com.sergio.RegisterApp.run;

import com.sergio.RegisterApp.controller.ControlManager;
import com.sergio.RegisterApp.exceptions.CustomerIDAlreadyExistException;

import java.io.IOException;

public class Runner {

    public static void main(String[] args) throws IOException, CustomerIDAlreadyExistException {
        new ControlManager();
    }
}

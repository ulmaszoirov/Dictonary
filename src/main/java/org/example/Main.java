package org.example;

import org.example.config.Config;
import org.example.controller.MainController;
import org.example.db.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class Main {

    public static void main(String[] args) {

        Database.getConnection();
        Database.createTable();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
            MainController mainController = (MainController) applicationContext.getBean("mainController");
        mainController.start();

        //here i am
    }
}
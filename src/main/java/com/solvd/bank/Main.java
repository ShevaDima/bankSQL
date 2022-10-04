package com.solvd.bank;

import com.solvd.bank.models.UserModel;
import com.solvd.bank.services.BankApp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
    private final static Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        BankApp app = new BankApp();
        app.setup();
    }
}

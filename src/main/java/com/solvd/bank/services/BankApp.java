package com.solvd.bank.services;

import com.solvd.bank.Main;
import com.solvd.bank.dao.jdbc.mysql.ContactDao;
import com.solvd.bank.dao.jdbc.mysql.LoginDao;
import com.solvd.bank.dao.jdbc.mysql.UserDao;
import com.solvd.bank.models.ContactModel;
import com.solvd.bank.models.LoginModel;
import com.solvd.bank.models.UserModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class BankApp {
    private final static Logger log = LogManager.getLogger(BankApp.class);
    private final Scanner scanner = new Scanner(System.in);

    public UserModel setup() {
        log.info("Welcome to BankApp. Do you want to sign in or create a new account?");
        log.info("1. Sign In");
        log.info("2. Sign Up");

        int ans;
        while (true) {
            try {
                ans = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch (NumberFormatException e) {
                log.error(e.getMessage());
                log.info("Try one more time. Use only dedicated digits");
            }
        }

        if (ans == 1) {
            return signIn();
        }
        return signUp();
    }

    public UserModel signIn() {
        log.info("Sign In");
        log.info("Enter your login");
        boolean loginStatus;
        LoginModel loginModel;
        do {
            String login = scanner.nextLine();
            LoginDao getLogin = new LoginDao();
            loginModel = getLogin.getByLogin(login);
            if (loginModel != null && login.equals(loginModel.getLogin())) {
                    loginStatus = true;
            }
            else {
                loginStatus = false;
                log.info("Login does not exists. Try again");
            }
        }
        while (!loginStatus);

       log.info("Enter your password:");
        boolean passwordStatus;
        do {
            String password = scanner.nextLine();
            LoginDao getLogin = new LoginDao();
            if (password.equals(loginModel.getPassword())) {
                passwordStatus = true;
            }
            else {
                passwordStatus = false;
                log.info("Wrong password. Try again");
            }
        }
        while (!passwordStatus);

        log.info("Your account:\nLogin: " + loginModel.getLogin() + "\nPassword: " + loginModel.getPassword());

        UserDao userDao = new UserDao();
        UserModel user = userDao.getByLoginId(loginModel.getId());
        return user;
    }

    public UserModel signUp() {
        log.info("Sign Up");
        log.info("Enter your login");
        boolean loginStatus;
        LoginModel loginModel;
        String login;
        do {
            login = scanner.nextLine();
            LoginDao getLogin = new LoginDao();
            loginModel = getLogin.getByLogin(login);
            if (loginModel != null && login.equals(loginModel.getLogin())) {
                loginStatus = true;
                log.info("This login exists. Try another one");
            }
            else {
                loginStatus = false;
            }
        }
        while (loginStatus);

        boolean passStatus = false;
        String pass1;
        do {
            log.info("Enter password:");
            pass1 = scanner.nextLine();
            log.info("Repeat password:");
            String pass2 = scanner.nextLine();
            if (pass1.equals(pass2)) {
                passStatus = true;
            }
            else {
                log.info("Passwords doesn't match");
            }
        }
        while (!passStatus);

        LoginDao loginDao = new LoginDao();
        loginDao.create(new LoginModel(999, login, pass1));

        log.info("Save your account details\nLogin: " + login + "\nPassword: " + pass1 + "\n\n");
        log.info("Take a minute and fill your profile, please");
        log.info("Enter your name: ");
        String name = scanner.nextLine();
        log.info("Enter your surname: ");
        String surname = scanner.nextLine();
        log.info("Enter your phone: ");
        String phone1 = scanner.nextLine();
        log.info("Enter your email: ");
        String email;
        boolean check = false;
        do {
            email = scanner.nextLine();
            if (!email.matches("^(.+)@(.+)$")) {
                log.info("Wrong email format. Try again");
            }
            else {
                check = true;
            }
        }
        while (!check);

        ContactModel contact = new ContactModel();
        contact.setPhone1(phone1);
        contact.setEmail(email);
        ContactDao contactDao = new ContactDao();
        contactDao.create(contact);

        log.info("Do you want to add one more phone?");
        log.info("1. Yes");
        log.info("2. No");
        int ans = Integer.parseInt(scanner.nextLine());

        if (ans == 1) {
            log.info("Enter your second phone: ");
            String phone2 = scanner.nextLine();
            contact = contactDao.getByEmail(email);
            contact.setPhone2(phone2);
            log.info(contact.toString());
            contactDao.update(contact);
        }

        UserModel user = new UserModel();
        user.setName(name);
        user.setSurname(surname);
        user.setContactId(contactDao.getByEmail(email).getId());
        user.setLoginId(loginDao.getByLogin(login).getId());
        UserDao userDao = new UserDao();
        userDao.create(user);

        return user;
    }
}

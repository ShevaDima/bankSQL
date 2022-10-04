package com.solvd.bank.services;

import com.solvd.bank.Main;
import com.solvd.bank.dao.jdbc.mysql.*;
import com.solvd.bank.models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

public class BankApp {
    private final static Logger log = LogManager.getLogger(BankApp.class);
    private final Scanner scanner = new Scanner(System.in);
    private UserModel activeUser;
    private BankModel activeBank;

    public void setup() {
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
            activeUser = signIn();
        }
        else {
            activeUser = signUp();
        }
        mainMenu();
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
        return userDao.getByLoginId(loginModel.getId());
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

        log.info("Save your account details\nLogin: " + login + "\nPassword: " + pass1 + "\n");
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
            contactDao.update(contact);
        }

        UserModel user = new UserModel();
        user.setName(name);
        user.setSurname(surname);
        user.setContactId(contactDao.getByEmail(email).getId());
        user.setLoginId(loginDao.getByLogin(login).getId());
        UserDao userDao = new UserDao();
        userDao.create(user);
        user = userDao.getByLoginId(loginDao.getByLogin(login).getId());

        BankDao bankDao = new BankDao();
        log.info("Choose your bank:");
        List<BankModel> bankModels = bankDao.getAllBanks();
        AdressDao adressDao = new AdressDao();
        CityDao cityDao = new CityDao();
        CountryDao countryDao = new CountryDao();
        for (BankModel bank : bankModels) {
            log.info(bank.getId() + ". " + bank.getName() + ". Location: " +
                    countryDao.getById(cityDao.getById(adressDao.getById(bank.getAdressId()).getCityId()).getCountryId()).getCountry()
                    + ", " + cityDao.getById(adressDao.getById(bank.getAdressId()).getCityId()).getCity() + ", "
                    + adressDao.getById(bank.getAdressId()).getStreet()
                    + ((adressDao.getById(bank.getAdressId()).getBuilding() != null) ?
                    (", " + adressDao.getById(bank.getAdressId()).getBuilding()) : "") + "");
        }

        while (true) {
            try {
                ans = Integer.parseInt(scanner.nextLine());
                if (ans <= bankModels.size()) {
                    break;
                }
                else {
                    log.info("Wrong bank number");
                }
            }
            catch (NumberFormatException e) {
                log.error(e.getMessage());
                log.info("Try one more time. Use only dedicated digits");
            }
        }

        activeBank = bankDao.getById(ans);

        AccountDao accountDao = new AccountDao();
        accountDao.create(new AccountModel(999, activeBank.getId(), user.getId()));
        return user;
    }

    public void mainMenu() {
        log.info("Main Menu\n1. My Account\n2. My Transactions\n3. My Cards\n4. My Bank\n0. Quit App");

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

        switch (ans) {
            case 1:
                accountMenu();
                break;
            case 2:
                transactionMenu();
                break;
            case 3:
                cardMenu();
                break;
            case 4:
                bankMenu();
                break;
            case 0:
                log.info("See you soon!");
                System.exit(0);
                break;
        }
    }

    public void accountMenu() {
        ContactDao contactDao = new ContactDao();
        log.info("My Account\nName: " + activeUser.getName() + "\nSurname: " + activeUser.getSurname() + "\nPhone 1: " +
                contactDao.getById(activeUser.getContactId()).getPhone1() + "\nPhone 2: " +
                contactDao.getById(activeUser.getContactId()).getPhone2() + "\nEmail: " +
                contactDao.getById(activeUser.getContactId()).getEmail() + "\n\n1. Delete account\n0. Back to menu");
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
            LoginDao loginDao = new LoginDao();
            loginDao.remove(activeUser.getLoginId());
            contactDao.remove(activeUser.getContactId());
            log.info("Account deleted successfully");
            setup();
        }
        else {
            mainMenu();
        }
    }

    public void bankMenu() {
        BankDao bankDao = new BankDao();
        AccountDao accountDao = new AccountDao();
        CountryDao countryDao = new CountryDao();
        CityDao cityDao = new CityDao();
        AdressDao adressDao = new AdressDao();
        activeBank = bankDao.getById(accountDao.getByUserId(activeUser.getId()).getBankId());
        log.info("My Bank\nName: " + activeBank.getName() +
                "\nLocation: " + countryDao.getById(cityDao.getById(adressDao.getById(activeBank.getAdressId()).getCityId()).getCountryId()).getCountry()
                + ", " + cityDao.getById(adressDao.getById(activeBank.getAdressId()).getCityId()).getCity() + ", "
                + adressDao.getById(activeBank.getAdressId()).getStreet()
                + ((adressDao.getById(activeBank.getAdressId()).getBuilding() != null) ?
                (", " + adressDao.getById(activeBank.getAdressId()).getBuilding()) : "") + "\n\n1. Change bank\n0. Back to menu");
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
            bankDao = new BankDao();
            log.info("Choose your bank:");
            List<BankModel> bankModels = bankDao.getAllBanks();
            adressDao = new AdressDao();
            cityDao = new CityDao();
            countryDao = new CountryDao();
            for (BankModel ibank : bankModels) {
                log.info(ibank.getId() + ". " + ibank.getName() + ". Location: " +
                        countryDao.getById(cityDao.getById(adressDao.getById(ibank.getAdressId()).getCityId()).getCountryId()).getCountry()
                        + ", " + cityDao.getById(adressDao.getById(ibank.getAdressId()).getCityId()).getCity() + ", "
                        + adressDao.getById(ibank.getAdressId()).getStreet()
                        + ((adressDao.getById(ibank.getAdressId()).getBuilding() != null) ?
                        (", " + adressDao.getById(ibank.getAdressId()).getBuilding()) : "") + "");
            }

            while (true) {
                try {
                    ans = Integer.parseInt(scanner.nextLine());
                    if (ans <= bankModels.size()) {
                        break;
                    }
                    else {
                        log.info("Wrong bank number");
                    }
                }
                catch (NumberFormatException e) {
                    log.error(e.getMessage());
                    log.info("Try one more time. Use only dedicated digits");
                }
            }

            activeBank = bankDao.getById(ans);
            accountDao.update(new AccountModel(accountDao.getByUserId(activeUser.getId()).getId(), activeBank.getId(), activeUser.getId()));
            bankMenu();
        }
        else {
            mainMenu();
        }
    }

    public void cardMenu() {
        CardDao cardDao = new CardDao();
        List<CardModel> cards = cardDao.getAllCards();
        CardTypeDao cardTypeDao = new CardTypeDao();
        AccountDao accountDao = new AccountDao();
        log.info("My Cards\n");

        int count = 0;
        for (CardModel card : cards) {
            if (card.getAccountId() == accountDao.getByUserId(activeUser.getId()).getId()) {
                count++;
            }
        }

        if (count <= 0) {
            log.info("You have no cards!\n\n1. Open a card\n0. Back to Menu");
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
                addCard();
            }
            else {
                mainMenu();
            }
        }
        else {
            int c = 0;
            for (CardModel card : cards) {
                if (card.getAccountId() == accountDao.getByUserId(activeUser.getId()).getId()) {
                    c++;
                    log.info(c + ". " + cardTypeDao.getById(card.getTypeId()).getType() + "");
                }
            }
            log.info("\n1. Add a card\n\n0. Back to Menu");

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
                addCard();
            }
            else {
                mainMenu();
            }
        }
    }

    public void addCard() {
        CardDao cardDao = new CardDao();
        CardTypeDao cardTypeDao = new CardTypeDao();
        AccountDao accountDao = new AccountDao();

        log.info("New card. Select card type");
        List<CardTypeModel> cardTypeModels = cardTypeDao.getAllCardTypes();
        for(CardTypeModel card : cardTypeModels) {
            log.info(card.getId() + ". " + card.getType());
        }

        int ans;
        while (true) {
            try {
                ans = Integer.parseInt(scanner.nextLine());
                if (ans <= cardTypeModels.size()) {
                    break;
                }
            }
            catch (NumberFormatException e) {
                log.error(e.getMessage());
                log.info("Try one more time. Use only dedicated digits");
            }
        }

        cardDao.create(new CardModel(999, accountDao.getByUserId(activeUser.getId()).getId(), ans));
        cardMenu();
    }

    public void transactionMenu() {
        TransactionDao transactionDao = new TransactionDao();
        TransactionStatusDao transactionStatusDao = new TransactionStatusDao();
        AccountDao accountDao = new AccountDao();
        List<TransactionModel> transactionModels = transactionDao.getAllTransactions();
        log.info("My Transactions\n");
        int count = 0;
        for (TransactionModel trans : transactionModels) {
            if (accountDao.getByUserId(activeUser.getId()).getId() == trans.getAccountId()) {
                count++;
            }
        }
        if (count > 0) {
            for (TransactionModel trans : transactionModels) {
                if (accountDao.getByUserId(activeUser.getId()).getId() == trans.getAccountId()) {
                    log.info(trans.getId() + ". Date: " + trans.getDate() + ". Amount: " + trans.getAmount() + ". Status: " +
                            transactionStatusDao.getById(trans.getStatusId()).getStatus());
                }
            }
            log.info("\n1. Create transaction\n\n0. Back to Menu");

        }
        else {
            log.info("You have no transactions yet!\n\n1. Create transaction\n0. Back to Menu");
        }
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
            createTransaction();
        }
        else {
            mainMenu();
        }
    }

    public void createTransaction() {
        TransactionDao transactionDao = new TransactionDao();
        Date date = new Date(System.currentTimeMillis());
        Time time = new Time(System.currentTimeMillis());
        String dateTime = date.toString() + " " + time.toString();

        log.info("Input amount to make a transaction");
        String amount = "";
        while (true) {
            amount = scanner.nextLine();
            if (amount.matches("^[1-9]\\d*$")) {
                break;
            }
            log.info("Amount should be > 0");
        }

        AccountDao accountDao = new AccountDao();

        transactionDao.create(new TransactionModel(999, dateTime, amount, accountDao.getByUserId(activeUser.getId()).getId(), 3));
        transactionMenu();
    }
}

USE `bank`;


INSERT INTO countries(country)
VALUES ('Ukraine'), ('USA'), ('Canada'), ('France'), ('Great Britain');

INSERT INTO cities(city, country_id)
VALUES ('Kyiv', 1), ('New York', 2), ('Toronto', 3), ('Paris', 4), ('London', 5), ('Dnipro', 1);

INSERT INTO adresses(street, building, city_id)
VALUES ('Vasylkivska', '105', 1), ('34th St', '20', 2), ('Dundas St', '317', 3), ('5 Av. Anatole France', null, 4), ('Newington Causeway', '42', 5), ('Trinity Square', '5a', 1);

INSERT INTO banks(name, adress_id)
VALUES ('Kilo Bank', 1), ('Mega Bank', 2), ('Giga Bank', 3), ('Tera Bank', 4), ('Nano Bank', 5), ('Femto Bank', 6);

INSERT INTO positions(position)
VALUES ('Cashier'), ('Accountant'), ('Manager');

INSERT INTO employees(name, surname, position_id, bank_id)
VALUES ('Dan', 'Smith', 1, 1), ('Mike', 'Johnson', 2, 1), ('Dana', 'Black', 3, 1), 
('Galeazzo', 'Rainier', 1, 2), ('Xia', 'Drakon', 2, 2), ('Hadubert', 'Shinobu', 3, 2), 
('Emelrich', 'Hilma', 1, 3), ('Müjde', 'Minna', 2, 3), ('Arnfinnr', 'Eigyr', 3, 3), 
('Mahammad', 'Cerys', 1, 4), ('Ailill', 'Mathis', 2, 4), ('Arevik', 'Larunda', 3, 4), 
('Fabrice', 'Alanna', 1, 5), ('Mitul', 'Hamo', 2, 5), ('Sócrates', 'Naomi', 3, 5),
('Diodato', 'Culhwch', 1, 6), ('Tabitha', 'Otto', 2, 6), ('Wendelin', 'Pocahontas', 3, 6);

INSERT INTO employee_info(employee_id, phone, salary)
VALUES (1, '89421648', 2000), (3, '90421648', 2500), (4, '12421648', 1000),
(7, '92146648', 3000), (8, '1320648', 7000), (9, '84197248', 6000),
(11, '10471648', 1200), (15, '011154758', 2700), (17, '8974121648', 3600);

INSERT INTO licenses(number, bank_id)
VALUES ('AB123', 1), ('CD456', 2), ('EF789', 3), ('HD489', 4), ('NM450', 5), ('MG777', 6);

INSERT INTO contacts(phone1, phone2, email)
VALUES ('1235689', '981205', 'Taha@email.com'), (null, '981205', 'Josefina@email.com'), ('9452004', '555546670', null),
(null, '99600155', null), (null, null, 'NamrataS@email.com');

INSERT INTO users(name, surname, contact_id)
VALUES ('Taha', 'Grimwald', 1), ('Josefina', 'Turnus', 2), ('Dileep', 'Margaretha', 3), ('Lelisa', 'Tomislav', 4), ('Namrata', 'Sadbh', 5);

INSERT INTO accounts(user_id, bank_id)
VALUES (1, 6), (2, 5), (3, 4), (4, 3), (5, 1);

INSERT INTO card_types(type)
VALUES ('Credit'), ('Virtual'), ('Installment');

INSERT INTO cards(type_id, account_id)
VALUES (1, 1), (2, 2), (3, 3), (1, 4), (2, 5);

INSERT INTO transaction_statuses(status)
VALUES ('Success'), ('Abort'), ('In progress');

INSERT INTO transactions(account_id, date, amount, status_id)
VALUES (1, '2022-01-02 12:05', 2000, 1), (2, '2021-07-13 18:09', 3000, 2), (3, '2022-05-27 14:55', 1000, 3), 
(4, '2022-08-24 12:00', 70000, 1), (5, '2022-03-08 09:27', 10000, 2);



UPDATE users
SET surname = 'Gilbert'
WHERE id = 1;

UPDATE transactions
SET status_id = 3
WHERE id = 5;

UPDATE contacts
SET phone1 = '777511'
WHERE id = 5;

UPDATE licenses
SET number = 'AI145'
WHERE id = 5;

UPDATE employee_info
SET salary = 5000
WHERE id = 1;



DELETE FROM users
WHERE id = 1;

DELETE FROM employees
WHERE id = 3;

DELETE FROM banks
WHERE id = 6;

DELETE FROM cards
WHERE id = 4;

DELETE FROM licenses
WHERE id = 2;



SELECT * FROM users
WHERE id > 3;

SELECT * FROM employees
WHERE bank_id BETWEEN 2 AND 4;

SELECT * FROM banks
WHERE name LIKE '%o Bank';

SELECT * FROM transactions
WHERE amount > 5000;

SELECT position_id, COUNT(id) AS '# of employees' FROM employees
GROUP BY position_id
ORDER BY position_id;



SELECT t1.id, t1.name, t1.surname, t2.phone1, t2.phone2, t2.email FROM users t1
JOIN contacts t2
ON t1.contact_id = t2.id;

SELECT t1.name AS 'Bank name', t2.name AS 'Employee name', t2.surname AS 'Employee surname', t3.position FROM banks t1
JOIN employees t2
ON t1.id = t2.bank_id
JOIN positions t3
ON t2.position_id = t3.id;

SELECT t1.name, t1.surname, t2.phone, t2.salary FROM employees t1
JOIN employee_info t2
ON t1.id = t2.employee_id

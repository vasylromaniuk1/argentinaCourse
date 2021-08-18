SELECT * FROM mydb.users;
INSERT INTO mydb.users(first_name,last_name,age)values('Ivan','Ivanov',40);
INSERT INTO mydb.users(first_name,last_name,age)values('Petr','Petrov',22);
INSERT INTO mydb.users(first_name,last_name,age)values('Bill','John',45);
INSERT INTO mydb.users(first_name,last_name,age)values('Dana','Devis',30);
INSERT INTO mydb.users(first_name,last_name,age)values('Alina','Volkov',33);
INSERT INTO mydb.users(first_name,last_name,age)values('Sarah','Jonson',25);
INSERT INTO mydb.users(first_name,last_name,age)values('Nick','Portnov',55);
INSERT INTO mydb.users(first_name,last_name,age)values('Alex','Smirnov',35);
INSERT INTO mydb.airplans(model,carrying,user_id)values('Boing737',3000.50,2);
INSERT INTO mydb.airplans(model,carrying,user_id)values('Airbass320',2550.50,1);
INSERT INTO mydb.cars(model,carrying,user_id)values('Van',250.70,3);
INSERT INTO mydb.cars(model,carrying,user_id)values('Truck',1250.50,4);
INSERT INTO mydb.ships(model,carrying,user_id)values('Cargo250',10550.20,5);
INSERT INTO mydb.ships(model,carrying,user_id)values('Cargo500',20250.70,6);
INSERT INTO mydb.airports(location)values('Terninal A Los Angeles');
INSERT INTO mydb.airports(location)values('Terninal B Sacramento');

UPDATE mydb.users SET first_name = 'Ivan',age = 55 WHERE last_name = 'Petrov';
UPDATE mydb.users SET first_name = 'Bill',age = 25 WHERE last_name = 'Smirnov';
UPDATE mydb.users SET first_name = 'Inna',age = 20 WHERE last_name = 'Jonson';
UPDATE mydb.users SET first_name = 'Irvin',age = 30 WHERE last_name = 'John';
UPDATE mydb.users SET first_name = 'Dmytro' WHERE last_name = 'Devis';
UPDATE mydb.users SET age = 30 WHERE last_name = 'Petrov';
UPDATE mydb.users SET age = 50 WHERE last_name = 'Ivanov';
UPDATE mydb.airplans SET carrying = 3500.20 WHERE model ='Boing737';
UPDATE mydb.airplans SET carrying = 3770.20 WHERE model ='Airbass320';
UPDATE mydb.cars SET carrying = 500.20 WHERE model ='Van';
UPDATE mydb.cars SET carrying = 2500.50 WHERE model ='Truck';
UPDATE mydb.ships SET carrying = 20500.20 WHERE model ='Cargo250';
UPDATE mydb.ships SET carrying = 50500.50 WHERE model ='Cargo500';
UPDATE mydb.airports SET location = 'Terminal B Boston' WHERE location = 'Terninal A Los Angeles';
UPDATE mydb.airports SET location = 'Terminal A Sacramento' WHERE location = 'Terninal B Sacramento';

DELETE FROM mydb.users  WHERE last_name = 'Smirnov';
DELETE FROM mydb.users  WHERE last_name = 'Petrov';
DELETE FROM mydb.users  WHERE last_name = 'Ivanov';
DELETE FROM mydb.airports  WHERE location = 'Terminal B Sacramento';
DELETE FROM mydb.ships  WHERE model = 'Cargo250';

SELECT * FROM employees e
    LEFT JOIN cliens c ON e.id = c.client_id
    LEFT JOIN car_delivery cd ON l.locker_id = cd.id
    LEFT JOIN sea_delivery sd ON p.port_id = sd.id
    LEFT JOIN air_delivery. ad ON a.airport_id = ad.id
    RIGHT JOIN orders.o ON o.id = c.client_id
    LEFT JOIN cars c ON cd.car_delivery_id = c.id
    RIGHT JOIN ships s ON s.id = sd.sea_delivery_id;

SELECT AVG(age) FROM mydb.users ;
SELECT MIN(age) FROM mydb.users ;
SELECT AVG(age) FROM mydb.clients ;
SELECT MAX(age) FROM mydb.clients ;
SELECT id,AVG(carrying) FROM mydb.cars;

SELECT id,AVG(age) FROM mydb.users GROUP BY last_name;
SELECT id,AVG(age) FROM mydb.users WHERE age>25 GROUP BY id;
SELECT last_name,AVG(age) FROM mydb.users GROUP BY first_name;
SELECT id,AVG(carrying) FROM mydb.airports GROUP BY model;

SELECT id, COUNT(*) FROM mydb.employees GROUP BY id HAVING COUNT(*) > 1;
SELECT id, COUNT(cost) FROM mydb.orders GROUP BY id HAVING MIN(cost) > 1000;
SELECT id,last_name,AVG(age) FROM mydb.clients GROUP BY id HAVING AVG(age)<25;
SELECT id, carrying FROM mydb.airplans GROUP BY id HAVING carrying > 3000;
SELECT id,capasity FROM mydb.lockers GROUP BY capasity HAVING capasity >= 500;




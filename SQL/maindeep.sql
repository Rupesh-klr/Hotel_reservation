use world;
drop table users;
drop table item;
drop table cart;
drop table cart_item;
drop table bills;

truncate table users;
truncate table item;
truncate table cart;
truncate table cart_item;
truncate table billsbills;


 CREATE TABLE users (
    user_id int PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(255),
    wallet_amount decimal(8,2)
);

CREATE TABLE item (
    item_id INT PRIMARY KEY AUTO_INCREMENT,
    item_name VARCHAR(255) not null,
    item_price DECIMAL(10, 2) default 0,
    item_discount DECIMAL(10, 2) default 0,
    tax_percentage DECIMAL(5, 2) default 0
);
CREATE TABLE cart (
    cart_id INT PRIMARY KEY AUTO_INCREMENT,
    no_of_items INT default 0,
    total_items_cost DECIMAL(10, 2) default 0,
    total_tax DECIMAL(10, 2) default 0,
    total_amount_to_pay DECIMAL(10, 2) default 0
);
CREATE TABLE cart_item (
	cart_item_id INT PRIMARY KEY AUTO_INCREMENT,
    cart_id INT not null,
    item_id INT not null,
    FOREIGN KEY (cart_id) REFERENCES cart(cart_id),
    FOREIGN KEY (item_id) REFERENCES item(item_id)
);
 CREATE TABLE bills (
    bill_id int PRIMARY KEY AUTO_INCREMENT,
    user_id int not null,
    cart_id int default 0,
    payment_status  ENUM('paided','billed') default 'billed',
    FOREIGN KEY (user_id) REFERENCES users(user_id),
	FOREIGN KEY (cart_id) REFERENCES cart(cart_id)
);

ALTER TABLE users AUTO_INCREMENT=1001;
ALTER TABLE bills AUTO_INCREMENT=557001;


select * from users;
select * from item;
select * from cart;
select * from cart_item;
select * from bills;

INSERT INTO `world`.`users`
(`user_name`,`wallet_amount`)
VALUES ('ramu', 5000.00),('raju',2000),('kiran',10000),('rahul',1000);


INSERT INTO `world`.`item`
(`item_name`,`item_price`,`item_discount`,`tax_percentage`)
VALUES 
('milk packet', 41.02, 0 , 0),
('redmi a1-phone',17499,5,18),
('iphone 15 pro',125000,25,18),
('carry box',250,5,0),
('laptop', 50000, 10, 18),
('headphones', 2000, 15, 18),
('book', 500, 5, 5),
('pen', 20, 0, 5),
('notebook', 100, 0, 5),
('backpack', 1500, 10, 18),
('water bottle', 300, 0, 5),
('sneakers', 3000, 20, 18),
('t-shirt', 1000, 10, 5),
('jeans', 2000, 15, 5),
('jacket', 3000, 20, 18),
('watch', 10000, 25, 18),
('sunglasses', 1500, 15, 18),
('cap', 500, 10, 5),
('scarf', 700, 10, 5),
('gloves', 500, 10, 5),
('socks', 200, 0, 5),
('belt', 1000, 10, 18),
('wallet', 1500, 15, 18),
('purse', 2000, 20, 18),
('umbrella', 800, 0, 5),
('raincoat', 2000, 10, 18),
('boots', 4000, 20, 18),
('sandals', 1500, 10, 5),
('flip flops', 500, 0, 5),
('slippers', 500, 0, 5),
('bedsheet', 1000, 10, 5),
('pillow', 500, 0, 5),
('blanket', 2000, 15, 5),
('towel', 500, 0, 5),
('soap', 100, 0, 5),
('shampoo', 500, 5, 18),
('toothpaste', 100, 0, 5),
('toothbrush', 50, 0, 5);

insert into cart values(),(),(),(),();

insert into cart_item (cart_id,item_id) values 
(1,1),(1,4),(1,5),(1,7),(1,8)
,(2,1),(2,7),(2,11),(2,15),(2,17)
,(3,2)
,(4,11),(4,21),(4,22),(4,31),(4,37)
,(5,11),(5,8),(5,13),(5,18);





use world;
select ci.cart_id,count(item_price) no_of_items,sum((item_price*(100-item_discount))/100) total_items_cost,
 sum((item_price*(100-item_discount))/100*(tax_percentage/100)) total_tax from cart_item ci join item i on ci.item_id = i.item_id
group by ci.cart_id;


use world;


UPDATE `world`.`cart`
JOIN (
    SELECT 
        ci.cart_id,
        COUNT(item_price) AS no_of_items,
        SUM((item_price*(100-item_discount))/100) AS total_items_cost,
        SUM((item_price*(100-item_discount))/100*(tax_percentage/100)) AS total_tax
    FROM 
        cart_item ci 
    JOIN 
        item i ON ci.item_id = i.item_id
    GROUP BY 
        ci.cart_id
) AS items
ON `world`.`cart`.cart_id = items.cart_id
SET
    `world`.`cart`.`no_of_items` = items.no_of_items,
    `world`.`cart`.`total_items_cost` = items.total_items_cost,
    `world`.`cart`.`total_tax` = items.total_tax,
    `world`.`cart`.`total_amount_to_pay` = items.total_items_cost + items.total_tax
WHERE
    `world`.`cart`.cart_id = `world`.`cart`.cart_id;
    

INSERT INTO `world`.`bills`
(`user_id`,`cart_id`)
VALUES (1001,1),(1002,2),(1003,3),(1004,4),(1001,5);




UPDATE `world`.`users` SET wallet_amount =wallet_amount+200 WHERE `user_id` = 1001;

UPDATE `world`.`users` SET wallet_amount = wallet_amount - 200 WHERE `user_id` = 1001;
UPDATE `world`.`bills` set payment_status = 'paided' where bill_id =1;


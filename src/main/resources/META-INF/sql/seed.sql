insert ignore into sec_group(groupname, groupdesc) values('BUYER','This group contains buyers.');
insert ignore into sec_group(groupname, groupdesc) values('SELLER','This group contains selleres.');
insert ignore into sec_group(groupname, groupdesc) values('ADMINS','This group contains admins.');

insert ignore into sec_user(username, password, enabled) values('buyer1', SHA2('buyer1', 256), true); 
insert ignore into sec_user(username, password, enabled) values('buyer2', SHA2('buyer2', 256), true); 
insert ignore into sec_user(username, password, enabled) values('seller1', SHA2('seller1', 256), true); 
insert ignore into sec_user(username, password, enabled) values('seller2', SHA2('seller2', 256), true);
insert ignore into sec_user(username, password, enabled) values('milad', SHA2('milad', 256), true); 
insert ignore into sec_user(username, password, enabled) values('admin', SHA2('admin', 256), true); 

insert ignore into sec_user_groups(username, groupname) values('buyer1','BUYER');
insert ignore into sec_user_groups(username, groupname) values('buyer2','BUYER');
insert ignore into sec_user_groups(username, groupname) values('milad','BUYER');
insert ignore into sec_user_groups(username, groupname) values('seller1','SELLER');
insert ignore into sec_user_groups(username, groupname) values('seller2','SELLER');
insert ignore into sec_user_groups(username, groupname) values('milad','SELLER');
insert ignore into sec_user_groups(username, groupname) values('admin','ADMINS');

insert into BUYER(buyerid, birthday, firstname, lastname, gender, username, email) values(1, '1970-09-01', 'Alex', 'Wooden', 'male', 'buyer1', 'alex.wooden@example.com');
insert into BUYER(buyerid, birthday, firstname, lastname, gender, username, email) values(2, '1980-09-01', 'Jack', 'Smith', 'male', 'buyer2', 'jack.smith@example.com');
insert into SELLER(sellerid, birthday, firstname, lastname, gender, username, email) values(1, '1970-09-01', 'Chris', 'Hamilton', 'male', 'seller1', 'chris.hamilton@example.com');
insert into SELLER(sellerid, birthday, firstname, lastname, gender, username, email) values(2, '1980-09-01', 'Richard', 'Rex', 'male', 'seller2', 'rich.rex@example.com');

insert into FOOD(id, date_cooked, description, name, seller_id) values(1, '2018-11-01', 'includes meat', 'Meat Ball', 1);
insert into FOOD(id, date_cooked, description, name, seller_id) values(2, '2018-11-02', 'includes Vegi', 'Vegi Burger', 1);

insert into OFFER(offer_id, createddate, description, quantity, title, unitprice, seller_id, recipe, image_file) values(1, '2018-11-01', 'includes meat', 10, 'Barh Kabab',  10, 1, "meat, potato", "steak.jpg");
insert into OFFER(offer_id, createddate, description, quantity, title, unitprice, seller_id, recipe, image_file) values(2, '2018-11-02', 'includes vegi', 14, 'Vegi Taco',  5, 1, "spinach, spring", "salad.jpeg");
insert into OFFER(offer_id, createddate, description, quantity, title, unitprice, seller_id, recipe, image_file) values(3, '2018-11-03', 'includes rice', 8, 'Zafran Rice',  3, 1, "rice, egg, zafran", "tahchin.jpg");

insert into food_offer(offer_id, food_id) values(1, 1);
insert into food_offer(offer_id, food_id) values(2, 2);

insert into ORDER_FOOD(order_id, order_date, price, quantity, buyer_id, offer_id) values(1, '2018-11-01', 20, 2, 1,  1);
insert into ORDER_FOOD(order_id, order_date, price, quantity, buyer_id, offer_id) values(2, '2018-11-02', 30, 6, 1,  2);
insert into ORDER_FOOD(order_id, order_date, price, quantity, buyer_id, offer_id) values(3, '2018-11-03', 9, 3, 1,  3);

insert into recipe(food_id, recipes) values(1, 'beef');
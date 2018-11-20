insert ignore into sec_group(groupname, groupdesc) values('BUYER','This group contains buyers.');
insert ignore into sec_group(groupname, groupdesc) values('SELLER','This group contains selleres.');

insert ignore into sec_user(username, password, enabled) values('buyer1', SHA2('buyer1', 256), true); 
insert ignore into sec_user(username, password, enabled) values('buyer2', SHA2('buyer2', 256), true); 
insert ignore into sec_user(username, password, enabled) values('seller1', SHA2('seller1', 256), true); 
insert ignore into sec_user(username, password, enabled) values('seller2', SHA2('seller2', 256), true); 
insert ignore into sec_user(username, password, enabled) values('milad', SHA2('milad', 256), true); 

insert ignore into sec_user_groups(username, groupname) values('buyer1','BUYER');
insert ignore into sec_user_groups(username, groupname) values('buyer2','BUYER');
insert ignore into sec_user_groups(username, groupname) values('milad','BUYER');
insert ignore into sec_user_groups(username, groupname) values('seller1','SELLER');
insert ignore into sec_user_groups(username, groupname) values('seller2','SELLER');
insert ignore into sec_user_groups(username, groupname) values('milad','SELLER');

insert into buyer(buyerid, birthday, firstname, lastname, gender, username) values(1, '1970-09-01', 'Alex', 'Wooden', 'male', 'buyer1');
insert into seller(sellerid, birthday, firstname, lastname, gender, username) values(1, '1970-09-01', 'Chris', 'Hamilton', 'male', 'seller1');

insert into food(id, date_cooked, description, name, seller_id) values(1, '2018-11-01', 'includes meat', 'Meat Ball', 1);
insert into food(id, date_cooked, description, name, seller_id) values(2, '2018-11-02', 'includes Vegi', 'Vegi Burger', 1);

insert into offer(offer_id, createddate, description, quantity, title, unitprice) values(1, '2018-11-01', 'includes meat', 1, 'Meat Offer',  10);
insert into offer(offer_id, createddate, description, quantity, title, unitprice) values(2, '2018-11-02', 'includes vegi', 1, 'Vegi Offer',  5);
insert into offer(offer_id, createddate, description, quantity, title, unitprice) values(3, '2018-11-03', 'includes egg', 1, 'Egg Offer',  3);

insert into food_offer(offer_id, food_id) values(1, 1);
insert into food_offer(offer_id, food_id) values(2, 2);

insert into order_food(order_id, order_date, price, quantity, buyer_id, offer_id) values(1, '2018-11-01', 50, 2, 1,  1);
insert into order_food(order_id, order_date, price, quantity, buyer_id, offer_id) values(2, '2018-11-02', 75, 6, 1,  2);
insert into order_food(order_id, order_date, price, quantity, buyer_id, offer_id) values(3, '2018-11-03', 80, 3, 1,  3);

insert into recipe(food_id, recipes) values(1, 'beef');
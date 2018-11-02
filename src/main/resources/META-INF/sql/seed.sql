insert ignore into sec_group(groupname, groupdesc) values('BUYER','This group contains buyers.');
insert ignore into sec_group(groupname, groupdesc) values('SELLER','This group contains selleres.');

insert ignore into sec_user(username, password, enabled) values('buyer1', 'buyer1', true); 
insert ignore into sec_user(username, password, enabled) values('buyer2', 'buyer2', true); 
insert ignore into sec_user(username, password, enabled) values('seller1', 'seller1', true); 
insert ignore into sec_user(username, password, enabled) values('seller2', 'seller2', true); 

insert ignore into sec_user_groups(username, groupname) values('buyer1','BUYER');
insert ignore into sec_user_groups(username, groupname) values('buyer2','BUYER');
insert ignore into sec_user_groups(username, groupname) values('seller1','SELLER');
insert ignore into sec_user_groups(username, groupname) values('seller2','SELLER');

insert into buyer(buyerid, birthday, firstname, lastname, gender) values(1, '1970-09-01', 'Alex', 'Wooden', 'mail');
insert into seller(sellerid, birthday, firstname, lastname, gender) values(1, '1970-09-01', 'Chris', 'Hamilton', 'mail');

insert into food(id, date_cooked, description, name, seller_id) values(1, '2018-11-01', 'includes meat', 'Meat Ball', 1);

insert into offer(offer_id, createddate, description, quantity, title, unitprice) values(1, '2018-11-01', 'includes meat', 1, 'Meat Offer',  100);
insert into food_offer(offer_id, food_id) values(1, 1);
insert into order_food(order_id, order_date, price, quantity, buyer_id, offer_id) values(1, '2018-11-01', 50, 2, 1,  1);

insert into recipe(food_id, recipes) values(1, 'beef');
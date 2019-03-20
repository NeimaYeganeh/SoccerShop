CREATE TABLE IF NOT EXISTS Items (
	itemID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30),
    price DOUBLE,
    stock INT,
    description VARCHAR(120)
);

CREATE TABLE IF NOT EXISTS Tags (
	tagID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30),
    isSelected BOOL
);
CREATE TABLE IF NOT EXISTS ItemTags (
	itemID INT,
    tagID INT,
    PRIMARY KEY (itemID, tagID),
    FOREIGN KEY (itemID) REFERENCES Items (itemID),
    FOREIGN KEY (tagID) REFERENCES Tags (tagID)
);

/*jersy*/

insert into Items (name, price, stock, description) values("Messi Jersey", 120, 99, "Messi FC Barcelona Jersey"); /*1*/
insert into Items (name, price, stock, description) values("Paul Pogba Jersey", 145, 3, "Paul Pogba Manchester United Jersey"); /*2*/
insert into Items (name, price, stock, description) values("Christian Ronaldo Jersey", 150, 34, "Christian Ronaldo Home Champions League Jersey"); /*3*/
insert into Items (name, price, stock, description) values("Mohammed Saleh Jersey", 25, 12, "Mohammed Saleh Home Jersey by New Balance"); /*4*/
insert into Items (name, price, stock, description) values("Kevin De Bruyne Jersey", 75, 19, "Kevin De Bruyne Home Jersey by Nike");/*5*/

/*jersy tags*/
insert into Tags (name, isSelected) values("Jersey", 0); /*1*/
insert into Tags (name, isSelected) values("Nike", 0);  /*2*/
insert into Tags (name, isSelected) values("FC Barcelona", 0); /*3*/
insert into Tags (name, isSelected) values("New Balance", 0); /*4*/

/*jersy ItemTags*/
insert into ItemTags (itemID, tagID) values(1, 1);
insert into ItemTags (itemID, tagID) values(2, 1);
insert into ItemTags (itemID, tagID) values(3, 1);
insert into ItemTags (itemID, tagID) values(4, 1);
insert into ItemTags (itemID, tagID) values(5, 1);
insert into ItemTags (itemID, tagID) values(5, 2);
insert into ItemTags (itemID, tagID) values(1, 3);
insert into ItemTags (itemID, tagID) values(4, 4);


/*shoes*/
insert into Items (name, price, stock, description) values("Adidas Nemeziz 18.1", 139, 30, "adidas Nemeziz 18.1 FG Soccer Cleats"); /*6*/
insert into Items (name, price, stock, description) values("Nike Hypervenom Phantom III", 179, 15, "Nike Hypervenom Phantom III Elite DF FG Soccer Cleat"); /*7*/
insert into Items (name, price, stock, description) values("Puma Future", 120, 31, "Puma Future a 360-degree Flyknit upper"); /*8*/
insert into Items (name, price, stock, description) values("Nike Tiempo", 229, 30, "Nike Tiempo Legend VII Elite FG Soccer Cleat"); /*9*/
insert into Items (name, price, stock, description) values("Adidas Predator", 175, 15, "Adidas Predator Accelerator FG ZZ Soccer Cleats"); /*10*/

/*shoe tags*/
insert into Tags (name, isSelected) values("Adidas", 0);  /*5*/
insert into Tags (name, isSelected) values("Cleat", 0); /*6*/
insert into Tags (name, isSelected) values("Puma", 0); /*7*/

/*jersy ItemTags*/
insert into ItemTags (itemID, tagID) values(6, 6);
insert into ItemTags (itemID, tagID) values(7, 6);
insert into ItemTags (itemID, tagID) values(8, 6);
insert into ItemTags (itemID, tagID) values(9, 6);
insert into ItemTags (itemID, tagID) values(10, 6);
insert into ItemTags (itemID, tagID) values(6, 5);
insert into ItemTags (itemID, tagID) values(10, 5);
insert into ItemTags (itemID, tagID) values(7, 2);
insert into ItemTags (itemID, tagID) values(9, 2);
insert into ItemTags (itemID, tagID) values(8, 7);



/*Accessories*/
insert into Items (name, price, stock, description) values("Kwik Goal Net Fastener", 20, 25, "Kwik Goal Net Fastener Make sure your soccer net is securely attached to the goal with the Kwik Goal® Net Fastener."); /*11*/
insert into Items (name, price, stock, description) values("Freekick Man Set of 3", 159, 25, "Freekick Man Set of 3 Each is made of heavy-duty PVC. Spikes keep them securely in the ground. Each is 72"); /*12*/
insert into Items (name, price, stock, description) values("Pugg Pop-up Goals 4in", 69, 18, "Pugg Pop-up Goals 4in great for small-sided training or backyard fun."); /*13*/
insert into Items (name, price, stock, description) values("Kwik Goal 6' Bench w/ Back", 494, 30, "Kwik Goal 6' Bench w/ Back 10in wide aluminum bench"); /*14*/
insert into Items (name, price, stock, description) values("Pevo StayNet Fasteners", 79, 25, "Pevo StayNet Fasteners Bag of 100 clips."); /*15*/

/*Accessories tags*/
insert into Tags (name, isSelected) values("Kwik", 0); /*8*/
insert into Tags (name, isSelected) values("Pugg", 0); /*9*/
insert into Tags (name, isSelected) values("Pevo", 0); /*10*/
insert into Tags (name, isSelected) values("Accessories", 0); /*11*/
insert into Tags (name, isSelected) values("Goal", 0); /*12*/
insert into Tags (name, isSelected) values("Fastener", 0); /*13*/
insert into Tags (name, isSelected) values("Bench", 0); /*14*/

/*Accessories ItemTags*/
insert into ItemTags (itemID, tagID) values(11, 11);
insert into ItemTags (itemID, tagID) values(12, 11);
insert into ItemTags (itemID, tagID) values(13, 11);
insert into ItemTags (itemID, tagID) values(14, 11);
insert into ItemTags (itemID, tagID) values(15, 11);
insert into ItemTags (itemID, tagID) values(11, 8);
insert into ItemTags (itemID, tagID) values(11, 13);
insert into ItemTags (itemID, tagID) values(13, 9);
insert into ItemTags (itemID, tagID) values(13, 12);
insert into ItemTags (itemID, tagID) values(14, 8);
insert into ItemTags (itemID, tagID) values(14, 14);
insert into ItemTags (itemID, tagID) values(15, 10);
insert into ItemTags (itemID, tagID) values(15, 13);


/*Equipment*/
insert into Items (name, price, stock, description) values("Nike HyperWarm Gloves", 120, 15, "Nike HyperWarm Gloves  palms help you grip the ball during throw-ins, while the flexible cuffs offer a secure fit."); /*16*/
insert into Items (name, price, stock, description) values("Adidas X Lesto Shin Guards", 9, 14, "adidas X Lesto Shin Guards Lightweight, low-profile shin guards"); /*17*/
insert into Items (name, price, stock, description) values("Puma Sock Stoppers", 3, 23, "Puma Sock Stoppers Keep your socks and shin guards in place."); /*18*/
insert into Items (name, price, stock, description) values("Nike Youth Shin Guard Socks", 9, 8, "Nike Youth Soccer Shin Socks"); /*19*/
insert into Items (name, price, stock, description) values("Adidas X Pro Shin Guards", 17, 23, "adidas X Pro Soccer Shin Guards Built for flexibility and speed"); /*20*/

/*Equipment tags*/
insert into Tags (name, isSelected) values("equipment", 0); /*15*/
insert into Tags (name, isSelected) values("Glove", 0); /*16*/
insert into Tags (name, isSelected) values("Shin Guard", 0); /*17*/
insert into Tags (name, isSelected) values("Sock", 0); /*18*/
insert into Tags (name, isSelected) values("Sock Stopper", 0); /*19*/

/*Equipment ItemTags*/
insert into ItemTags (itemID, tagID) values(16, 15);
insert into ItemTags (itemID, tagID) values(17, 15);
insert into ItemTags (itemID, tagID) values(18, 15);
insert into ItemTags (itemID, tagID) values(19, 15);
insert into ItemTags (itemID, tagID) values(20, 15);
insert into ItemTags (itemID, tagID) values(16, 16);
insert into ItemTags (itemID, tagID) values(16, 2);
insert into ItemTags (itemID, tagID) values(17, 17);
insert into ItemTags (itemID, tagID) values(17, 5);
insert into ItemTags (itemID, tagID) values(18, 7);
insert into ItemTags (itemID, tagID) values(18, 19);
insert into ItemTags (itemID, tagID) values(19, 2);
insert into ItemTags (itemID, tagID) values(19, 17);
insert into ItemTags (itemID, tagID) values(20, 5);
insert into ItemTags (itemID, tagID) values(20, 17);

/*Ball*/
insert into Items (name, price, stock, description) values("Nike Strike Team Soccer Ball", 14, 25, "Nike Strike Team Soccer Ball Made to stand out. Bring some flair to your practice with the Nike Strike Team Ball."); /*21*/
insert into Items (name, price, stock, description) values("Nike Merlin Hi-Vis Premier", 159, 13, "Nike's new replacement of the beloved Nike Ordem"); /*22*/
insert into Items (name, price, stock, description) values("New Balance Furon Dispatch", 21, 16, "The New Balance Furon ball delivers consistent performance with accuracy and power."); /*23*/
insert into Items (name, price, stock, description) values("Under Armour Desafio 595", 49, 13, "Under Armour Desafio 595 Foam layer backing for softness"); /*24*/
insert into Items (name, price, stock, description) values("Senda Amador Club", 21, 10, "Senda Amador Club High-Quality and Polyvinyl Chloride (PVC)"); /*25*/

/*Ball tags*/
insert into Tags (name, isSelected) values("Ball", 0); /*20*/
insert into Tags (name, isSelected) values("Under Armour", 0); /*21*/
insert into Tags (name, isSelected) values("Senda", 0); /*22*/

/*Ball ItemTags*/
insert into ItemTags (itemID, tagID) values(21, 20);
insert into ItemTags (itemID, tagID) values(22, 20);
insert into ItemTags (itemID, tagID) values(23, 20);
insert into ItemTags (itemID, tagID) values(24, 20);
insert into ItemTags (itemID, tagID) values(25, 20);
insert into ItemTags (itemID, tagID) values(21, 2);
insert into ItemTags (itemID, tagID) values(22, 2);
insert into ItemTags (itemID, tagID) values(23, 4);
insert into ItemTags (itemID, tagID) values(24, 21);
insert into ItemTags (itemID, tagID) values(25, 22);



/* all the tags
 *	1. Jersey
 *	2. Nike
 *	3. FC Barcelona
 *	4. New Balance
 *	5. Adidas
 *	6. Cleat
 *	7. Puma
 *	8. Kwik
 *	9. Pugg
 *10. Pevo
 *11. Accessories
 *12. Goal
 *13. Fastener
 *14. Bench
 *15. equipment
 *16. Glove
 *17. Shin Guard
 *18. Sock
 *19. Sock Stopper
 *20. Ball
 *21. Under Armour
 *22. Senda
 **/
 
/*select tag n
 * where n is the tagid */
update Tags 
set isSelected =1
where tagID = 2;

update Tags 
set isSelected =1
where tagID = 6;


/* search n selectd*/
/* where n is equal to the number of unique selected tags 
 * in this case it is 2 seen in the last select statment
 */
create view selectedtags as
select i.itemID, i.name, i.price, i.stock, i.description, t.tagID
from Items i, ItemTags it, Tags t
where i.itemID = it. itemID and t.tagID = it. tagID and t.isSelected = 1;

create view numtags as
select itemID, count(tagID) as  numtags
from selectedtags
group by itemID;

select distinct s.itemID, s.name, s.price, s.stock, s.description
from selectedtags s, numtags n
where n.numtags = 2 and n.itemID = s.itemID;

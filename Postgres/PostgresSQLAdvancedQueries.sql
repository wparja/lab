
-- FILM TABLE
CREATE TABLE public.film
(
  film_id serial,
  title text,
  description text,
  release_year integer,
  rental_duration smallint,
  lenght smallint,
  rental_rate numeric(4,2),
  CONSTRAINT film_pkey PRIMARY KEY (film_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.film
  OWNER TO postgres;
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght, rental_rate)
	VALUES ( 'Test1', 'Test1', 2001, 1, 160, 3.40);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght,rental_rate)
	VALUES ( 'Test2', 'Test2', 2002, 6, 90, 4.30);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght, rental_rate)
	VALUES ( 'Test3', 'Test3', 2003, 7, 160, 6.0);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght, rental_rate)
	VALUES ( 'Test4', 'Test4', 2005, 8, 100, 7.0);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght, rental_rate)
	VALUES ( 'Test5', 'Test5', 2006, 9, 90, 4.9);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght, rental_rate)
	VALUES ( 'Test6', 'Test6', 2006, 10, 160, 4.4);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght, rental_rate)
	VALUES ( 'Test7', 'Test7', 2006, 6, 160, 4.2);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght, rental_rate)
	VALUES ( 'Test8', 'Test8', 2006, 6, 140, 7.1);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght, rental_rate)
	VALUES ( 'Test9', 'Test9', 2006, 5, 180, 8.9);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght, rental_rate)
	VALUES ( 'Test10', 'Test10', 2006, 7, 160, 8.2);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght, rental_rate)
	VALUES ( 'Test11', 'Test11', 2006, 7, 170, 4.7);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght, rental_rate)
	VALUES ( 'Test12', 'Test12', 2006, 8, 160, 9.1);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght, rental_rate)
	VALUES ( 'Test13', 'Test13', 2006, 6, 160, 4.0);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght, rental_rate)
	VALUES ( 'Test14', 'Test14', 2006, 7, 130, 8.9);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght, rental_rate)
	VALUES ( 'Test15', 'Test15', 2006, 6, 130, 5.6);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght, rental_rate)
	VALUES ( 'Test16', 'Test15', 2006, 6, 30, 0.99);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght, rental_rate)
	VALUES ( 'Test17', 'Test15', 2006, 6, 70, 2.99);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght, rental_rate)
	VALUES ( 'Test18', 'Test15', 2006, 6, 40, 4.8);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght, rental_rate)
	VALUES ( 'Test19', 'Test15', 2006, 6, 48,4.99);


SELECT * FROM film WHERE  release_year = 2006 AND rental_duration = 6

SELECT * FROM film WHERE  release_year = 2006 OR rental_duration = 6

SELECT * FROM film WHERE  release_year = 2006 AND NOT rental_duration = 6

SELECT * FROM film WHERE  rental_duration >= 2 AND rental_duration <= 6

SELECT * FROM film WHERE  lenght = 160 OR (rental_duration >= 2 AND rental_duration <= 6)


-- while converting length of movie from minute to hour
SELECT film_id, title, lenght, (lenght/60.0) length_in_hour,
	round((lenght/60.0), 2) lenght_in_hour_round FROM film

-- where the length of the movie is over 2 hours
SELECT film_id, title, lenght, (lenght/60.0) length_in_hour,
	round((lenght/60.0), 2) lenght_in_hour_round FROM film
	WHERE (lenght/60.0) > 2;
-- while converting rental_rate to nearest higher integer
SELECT film_id, title, rental_rate, ceiling(rental_rate) rental_rate_new FROM film;
-- ########################################################################
-- ACTOR TABLE
CREATE TABLE public.actor
(
  actor_id integer NOT NULL DEFAULT nextval('actor_actor_id_seq'::regclass),
  first_name character varying(45),
  last_name character varying(45),
  CONSTRAINT actor_pkey PRIMARY KEY (actor_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.actor
  OWNER TO postgres;


	INSERT INTO actor(first_name, last_name) VALUES('Calixta', 'Teobaldo');
	INSERT INTO actor(first_name, last_name) VALUES('Odilon', 'Jovita');
	INSERT INTO actor(first_name, last_name) VALUES('Ernesto', 'Aurora');
	INSERT INTO actor(first_name, last_name) VALUES('Cristiano', 'Maristela');
	INSERT INTO actor(first_name, last_name) VALUES('Teresinha', 'Gertrudes');
	INSERT INTO actor(first_name, last_name) VALUES('Faustino', 'Sérgio');
	INSERT INTO actor(first_name, last_name) VALUES('Lula', 'Rafinha');
	INSERT INTO actor(first_name, last_name) VALUES('Lara', 'Ricardo');
	INSERT INTO actor(first_name, last_name) VALUES('Igor', 'Domingos');
	INSERT INTO actor(first_name, last_name) VALUES('Ciríaco', 'Albina');
	INSERT INTO actor(first_name, last_name) VALUES('Judy', 'Cage');
	INSERT INTO actor(first_name, last_name) VALUES('Gary', 'Penn');

-- while combining first_name and last_name in single column
	SELECT first_name, last_name, (first_name || ' ' || last_name) full_name FROM actor;
	SELECT first_name, last_name, CONCAT(first_name, ' ', last_name) full_name FROM actor;
-- with extra column which will list the intials of the actor
SELECT first_name, last_name, CONCAT(LEFT(first_name, 1), LEFT(last_name, 1)) initials FROM actor;
-- where the length of the name is of 4 characters
	SELECT first_name, last_name FROM actor
	WHERE LENGTH(first_name) = 4 AND LENGTH(last_name) = 4;
-- while converting all the last name in the upper case
	SELECT first_name, last_name, UPPER(last_name) UpperLastName FROM actor;
-- while replacing character a with character @ in column first_name
SELECT first_name, REPLACE(first_name, 'a', '@') AS new_first_name FROM actor

	

-- ########################################################################
CREATE TABLE public.rental
(
  rental_id integer NOT NULL DEFAULT nextval('rental_rental_id_seq'::regclass),
  rental_date date,
  inventory_id integer,
  customer_id smallint,
  return_date time with time zone,
  staff_id smallint,
  last_update time with time zone,
  CONSTRAINT rental_pkey PRIMARY KEY (rental_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.rental
  OWNER TO postgres;
INSERT INTO rental (rental_date, inventory_id, customer_id, return_date, staff_id, last_update)
    VALUES ('11/09/2021 23:49:21',39,28, '15/09/2021 23:49:21',2,'15/09/2021 23:49:21');   
INSERT INTO rental (rental_date, inventory_id, customer_id, return_date, staff_id, last_update)
    VALUES ('14/08/2021 23:19:53',25,24, '19/08/2021 23:19:53',2,'19/08/2021 23:19:53');   
INSERT INTO rental (rental_date, inventory_id, customer_id, return_date, staff_id, last_update)
    VALUES ('18/03/2021 14:58:36',33,25, '22/03/2021 14:58:36',2,'22/03/2021 14:58:36');   
INSERT INTO rental (rental_date, inventory_id, customer_id, return_date, staff_id, last_update)
    VALUES ('22/01/2021 09:28:35',26,31, '25/01/2021 09:28:35',4,'25/01/2021 09:28:35');   
INSERT INTO rental (rental_date, inventory_id, customer_id, return_date, staff_id, last_update)
    VALUES ('27/06/2021 06:13:02',42,16, '02/07/2021 06:13:02',1,'02/07/2021 06:13:02');   
INSERT INTO rental (rental_date, inventory_id, customer_id, return_date, staff_id, last_update)
    VALUES ('04/07/2021 14:40:31',50,45, '10/07/2021 14:40:31',4,'10/07/2021 14:40:31');   
INSERT INTO rental (rental_date, inventory_id, customer_id, return_date, staff_id, last_update)
    VALUES ('07/04/2021 06:38:40',14,47, '12/04/2021 06:38:40',4,'12/04/2021 06:38:40');   
INSERT INTO rental (rental_date, inventory_id, customer_id, return_date, staff_id, last_update)
    VALUES ('20/03/2021 05:45:57',28,48, '25/03/2021 05:45:57',1,'25/03/2021 05:45:57');   
INSERT INTO rental (rental_date, inventory_id, customer_id, return_date, staff_id, last_update)
    VALUES ('24/07/2021 13:04:09',36,8, '30/07/2021 13:04:09' ,3,'30/07/2021 13:04:09');   
INSERT INTO rental (rental_date, inventory_id, customer_id, return_date, staff_id, last_update)
    VALUES ('25/04/2021 06:26:05',48,1, '30/04/2021 06:26:05' ,2,'30/04/2021 06:26:05');   
INSERT INTO rental (rental_date, inventory_id, customer_id, return_date, staff_id, last_update)
    VALUES ('23/11/2021 21:52:54',20,13, '28/11/2021 21:52:54',2,'28/11/2021 21:52:54');   
INSERT INTO rental (rental_date, inventory_id, customer_id, return_date, staff_id, last_update)
    VALUES ('11/03/2021 11:13:25',34,21, '18/03/2021 11:13:25',1,'18/03/2021 11:13:25');   
INSERT INTO rental (rental_date, inventory_id, customer_id, return_date, staff_id, last_update)
    VALUES ('08/11/2021 04:02:23',8, 2, '12/11/2021 04:02:23' ,4,'12/11/2021 04:02:23');  
INSERT INTO rental (rental_date, inventory_id, customer_id, return_date, staff_id, last_update)
    VALUES ('05/06/2021 22:28:51',4, 46, '10/06/2021 22:28:51',1,'10/06/2021 22:28:51');  
INSERT INTO rental (rental_date, inventory_id, customer_id, return_date, staff_id, last_update)
    VALUES ('17/05/2021 03:54:56',16,7, '23/05/2021 03:54:56' ,4,'23/05/2021 03:54:56');   
INSERT INTO rental (rental_date, inventory_id, customer_id, return_date, staff_id, last_update)
    VALUES ('07/02/2021 15:47:46',6, 41, '12/02/2021 15:47:46',1,'12/02/2021 15:47:46');  
INSERT INTO rental (rental_date, inventory_id, customer_id, return_date, staff_id, last_update)
    VALUES ('19/07/2021 06:29:10',31,39, '23/07/2021 06:29:10',2,'23/07/2021 06:29:10');   
INSERT INTO rental (rental_date, inventory_id, customer_id, return_date, staff_id, last_update)
    VALUES ('10/05/2021 12:41:03',12,26, '15/05/2021 12:41:03',3,'15/05/2021 12:41:03');   
INSERT INTO rental (rental_date, inventory_id, customer_id, return_date, staff_id, last_update)
    VALUES ('10/06/2021 06:56:54',40,37, '15/06/2021 06:56:54',4,'15/06/2021 06:56:54');   
INSERT INTO rental (rental_date, inventory_id, customer_id, return_date, staff_id, last_update)
    VALUES ('09/10/2021 08:36:43',10,12, '13/10/2021 08:36:43',3,'13/10/2021 08:36:43');   
  
-- RENTAL TABLE

-- Retrieve all the rows
-- While displaying rental duration in days and hours
-- Where rental duration of movie is over 3 days
-- Where rental duration of movie is over 100 hours
-- While adding an extra colum with fix return_date as 7 days from rental
-- ########################################################################




-- Find area of the store with diameter 500 feet
/*
A = PI * (radius)^2;
A = PI * (diameter/2)^2;
*/

SELECT PI()*(500/2)^2 AS AreaofCircle;





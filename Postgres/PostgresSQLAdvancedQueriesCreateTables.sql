
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


-- ACTOR TABLE
CREATE TABLE public.actor
(
  actor_id integer ,
  first_name character varying(45),
  last_name character varying(45),
  CONSTRAINT actor_pkey PRIMARY KEY (actor_id)
)
WITH (
  OIDS=FALSE
);

-- ########################################################################
-- RENTAL TABLE
CREATE TABLE public.rental
(
  rental_id serial,
  rental_date timestamp without time zone,
  inventory_id integer,
  customer_id smallint,
  return_date timestamp without time zone,
  staff_id smallint,
  last_update timestamp without time zone,
  CONSTRAINT rental_pkey PRIMARY KEY (rental_id)
)
WITH (
  OIDS=FALSE
);

-- Find out total rental payment collected from customers
-- ########################################################################
-- PAYMENT TABLE
CREATE TABLE public.payment
(
  payment_id integer NOT NULL DEFAULT nextval('payment_payment_id_seq'::regclass),
  customer_id smallint,
  staff_id smallint,
  rental_id integer,
  amount numeric(5,2),
  payment_date timestamp without time zone,
  CONSTRAINT payment_pkey PRIMARY KEY (payment_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.payment
  OWNER TO postgres;
ALTER TABLE public.rental
  OWNER TO postgres;
INSERT INTO rental (customer_id,inventory_id,  return_date, staff_id, last_update)
    VALUES (28, '15/09/2021 23:49:21',2,'15/09/2021 23:49:21');   
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
INSERT INTO rental (rental_date, inventory_id, customer_id, return_date, staff_id, last_update)
    VALUES ('09/10/2021 08:36:43',10,12, '13/10/2021 08:36:43',3,'13/10/2021 09:36:43');  
INSERT INTO rental (rental_date, inventory_id, customer_id, return_date, staff_id, last_update)
    VALUES ('09/10/2021 08:36:43',10,12, '13/10/2021 09:36:43',3,'13/10/2021 09:36:43');  



-- Find area of the store with diameter 500 feet
/*
A = PI * (radius)^2;
A = PI * (diameter/2)^2;
*/

SELECT PI()*(500/2)^2 AS AreaofCircle;





	CREATE TABLE public.film
(
  film_id integer NOT NULL DEFAULT nextval('film_film_id_seq'::regclass),
  title text,
  description text,
  release_year integer,
  rental_duration smallint,
  lenght smallint,
  CONSTRAINT film_pkey PRIMARY KEY (film_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.film
  OWNER TO postgres;
	
	
	
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght)
	VALUES ( 'Test1', 'Test1', 2001, 1, 160);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght)
	VALUES ( 'Test2', 'Test2', 2002, 6, 90);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght)
	VALUES ( 'Test3', 'Test3', 2003, 7, 160);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght)
	VALUES ( 'Test4', 'Test4', 2005, 8, 100);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght)
	VALUES ( 'Test5', 'Test5', 2006, 9, 90);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght)
	VALUES ( 'Test6', 'Test6', 2006, 10, 160);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght)
	VALUES ( 'Test7', 'Test7', 2006, 6, 160);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght)
	VALUES ( 'Test8', 'Test8', 2006, 6, 140);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght)
	VALUES ( 'Test9', 'Test9', 2006, 5, 180);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght)
	VALUES ( 'Test10', 'Test10', 2006, 7, 160);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght)
	VALUES ( 'Test11', 'Test11', 2006, 7, 170);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght)
	VALUES ( 'Test12', 'Test12', 2006, 8, 160);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght)
	VALUES ( 'Test13', 'Test13', 2006, 6, 160);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght)
	VALUES ( 'Test14', 'Test14', 2006, 7, 130);
	INSERT INTO public.film( title, description, release_year, rental_duration, lenght)
	VALUES ( 'Test15', 'Test15', 2006, 6, 130);

-- ########################################################################
SELECT * FROM film WHERE  release_year = 2006 AND rental_duration = 6

SELECT * FROM film WHERE  release_year = 2006 OR rental_duration = 6

SELECT * FROM film WHERE  release_year = 2006 AND NOT rental_duration = 6

SELECT * FROM film WHERE  rental_duration >= 2 AND rental_duration <= 6

SELECT * FROM film WHERE  lenght = 160 OR (rental_duration >= 2 AND rental_duration <= 6)

update film set lenght = 160

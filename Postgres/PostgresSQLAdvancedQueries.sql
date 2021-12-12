
-- ########################## FILM
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


-- ############################### ACTOR 
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


-- ################################ RENTAL
-- Retrieve all the rows
	SELECT * FROM rental
-- While displaying rental duration in days and hours
	SELECT return_date - rental_date FROM rental
-- Where rental duration of movie is over 4 days
	SELECT (return_date - rental_date) FROM rental
	WHERE EXTRACT(days FROM return_date - rental_date) > 4
	
-- Where rental duration of movie is over 100 hours
	SELECT (return_date - rental_date)  rented_days,
	extract(epoch from return_date - rental_date)/3600
	FROM rental
	WHERE EXTRACT(epoch from return_date - rental_date)/3600 > 100
-- While adding an extra colum with fix return_date as 7 days from rental
	SELECT rental_date, rental_date + interval '7 day' AS new_return_date from rental;

-- Display the number of total rentals in history
SELECT COUNT(*) FROM rental

-- Display the first ever rental and the latest rental
SELECT min(rental_date) FirstRental, max(rental_date) LastRental FROM rental
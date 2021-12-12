CREATE OR REPLACE FUNCTION getFirstName() RETURNS TEXT AS $$
DECLARE
first_names TEXT[];
first_name TEXT;
_index INTEGER;
    BEGIN
     first_names := ARRAY[
	    'Adam','Bill','Bob','Calvin','Donald','Dwight','Frank','Fred','George','Howard',
	    'James','John','Jacob','Jack','Martin','Matthew','Max','Michael',
	    'Paul','Peter','Phil','Roland','Ronald','Samuel','Steve','Theo','Warren','William',
	    'Abigail','Alice','Allison','Amanda','Anne','Barbara','Betty','Carol','Cleo','Donna',
	    'Jane','Jennifer','Julie','Martha','Mary','Melissa','Patty','Sarah','Simone','Susan'
	    ];
	    _index := random() * ARRAY_LENGTH(first_names,1);
	    IF _index = 0 THEN
	      _index := 1;
	    END IF;
	    first_name := first_names[_index];
	    raise notice 'index first name % - size % name %', _index, ARRAY_LENGTH(first_names,1), first_name;
        RETURN first_name;
    END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION getLastName() RETURNS TEXT AS $$
DECLARE
last_names TEXT[];
last_name TEXT;
_index INTEGER;
    BEGIN
    last_names := ARRAY[
		'Matthews','Smith','Jones','Davis','Jacobson','Williams','Donaldson','Maxwell','Peterson','Stevens',
		'Franklin','Washington','Jefferson','Adams','Jackson','Johnson','Lincoln','Grant','Fillmore','Harding','Taft',
		'Truman','Nixon','Ford','Carter','Reagan','Bush','Clinton','Hancock'
	    ];
	    _index := random() * ARRAY_LENGTH(last_names,1);
	    IF _index = 0 THEN
	      _index := 1;
	    END IF;
	    last_name := last_names[_index];
	    raise notice 'index last name % - size % name %', _index, ARRAY_LENGTH(last_names,1), last_name;

        RETURN last_name;
    END;
$$ LANGUAGE plpgsql;
	
-- fetch the actor table
do $$
Declare 
 firstName TEXT;
 lastName TEXT;
begin	    
    for i IN 1..100 loop
          firstName := getFirstName();
          lastName := getLastName();                    
	 INSERT INTO actor(actor_id, first_name, last_name) values 
	 (	i, 
		firstName, 
		lastName
	);
    end loop;
end;
$$


CREATE OR REPLACE FUNCTION getFilmTitle() RETURNS TEXT AS $$
DECLARE
film_titles TEXT[];
_index INTEGER;
BEGIN
	film_titles := ARRAY['The Shawshank Redemption', 'The Godfather', 'The Godfather: Part II', 'The Dark Knight', '12 Angry Men', 'Schindler''s List','Pulp Fiction', 'The Good, the Bad and the Ugly', 
	'The Lord of the Rings: The Return of the King', 'Fight Club', 'The Lord of the Rings: The Fellowship of the Ring', 'Star Wars: Episode V - The Empire Strikes Back', 'Forrest Gump', 'Inception', 
	'One Flew Over the Cuckoo''s Nest', 'The Lord of the Rings: The Two Towers', 'Goodfellas', 'The Matrix', 'Star Wars', 'Seven Samurai', 'City of God', 'Se7en', 'The Silence of the Lambs', 'The Usual Suspects', 
	'It''s a Wonderful Life', 'Life Is Beautiful', 'Léon: The Professional', 'Once Upon a Time in the West', 'Interstellar', 'Saving Private Ryan', 'American History X', 'Spirited Away', 'Casablanca', 
	'Raiders of the Lost Ark', 'Psycho', 'City Lights', 'Rear Window', 'The Intouchables', 'Modern Times', 'Terminator 2: Judgment Day', 'Whiplash', 'The Green Mile', 'The Pianist', 'Memento', 'The Departed', 
	'Gladiator', 'Apocalypse Now', 'Back to the Future', 'Sunset Blvd.', 'Dr. Strangelove or: How I Learned to Stop Worrying and Love the Bomb', 'The Prestige', 'Alien', 'The Lion King', 'The Lives of Others', 
	'The Great Dictator', 'Inside Out', 'Cinema Paradiso', 'The Shining', 'Paths of Glory', 'Django Unchained', 'The Dark Knight Rises', 'WALL·E', 'American Beauty', 'Grave of the Fireflies', 'Aliens', 'Citizen Kane', 
	'North by Northwest', 'Princess Mononoke', 'Vertigo', 'Oldeuboi', 'Das Boot', 'M', 'Star Wars: Episode VI - Return of the Jedi', 'Once Upon a Time in America', 'Amélie', 'Witness for the Prosecution', 'Reservoir Dogs', 
	'Braveheart', 'Toy Story 3', 'A Clockwork Orange', 'Double Indemnity', 'Taxi Driver', 'Requiem for a Dream', 'To Kill a Mockingbird', 'Lawrence of Arabia', 'Eternal Sunshine of the Spotless Mind', 'Full Metal Jacket', 
	'The Sting', 'Amadeus', 'Bicycle Thieves', 'Singin'' in the Rain', 'Monty Python and the Holy Grail', 'Snatch.', '2001: A Space Odyssey', 'The Kid', 'L.A. Confidential', 'Rashômon', 'For a Few Dollars More', 'Toy Story', 
	'The Apartment', 'Inglourious Basterds', 'All About Eve', 'The Treasure of the Sierra Madre', 'Jodaeiye Nader az Simin', 'Indiana Jones and the Last Crusade', 'Metropolis', 'Yojimbo', 'The Third Man', 'Batman Begins', 
	'Scarface', 'Some Like It Hot', 'Unforgiven', '3 Idiots', 'Up', 'Raging Bull', 'Downfall', 'Mad Max: Fury Road', 'Jagten', 'Chinatown', 'The Great Escape', 'Die Hard', 'Good Will Hunting', 'Heat', 'On the Waterfront'];
	_index := random() * ARRAY_LENGTH(film_titles, 1);
	IF _index = 0 THEN 
	   _index := 1;
	END IF;
	RETURN film_titles[_index];
END;
$$ LANGUAGE plpgsql;

-- fetch the film table
do $$
Declare
	filmTitle TEXT;
BEGIN
	FOR i IN 1..10 LOOP
	filmTitle := getFilmTitle();                    
	 INSERT INTO 
		film( title, description, release_year, rental_duration, lenght, rental_rate) 
	 VALUES 
		(filmTitle, filmTitle,(SELECT floor(random() * 20 + 1)::int + 2000), (SELECT random() * 7), ((SELECT random() * 100) + 100), (SELECT round((random()* 9)::NUMERIC, 2)));
	END LOOP;
END;
$$

-- fetch rental table

do $$
Declare
	_rental_date TIMESTAMP;
BEGIN
	FOR i IN 1..10 LOOP
	_rental_date := (select timestamp '2014-01-10 08:00:00' + random() * (timestamp '2021-01-20 18:00:00' - timestamp '2014-01-10 08:00:00'));                    
	 INSERT INTO 
		rental (rental_date, inventory_id, customer_id, return_date, staff_id, last_update)
	VALUES 
		(_rental_date, (SELECT random() * 100), (SELECT random() * 500), (SELECT _rental_date + interval '4 day'), (SELECT random() * 4), (SELECT _rental_date + interval '4 day'));
	END LOOP;
END;
$$

SELECT (random() * 20.0)

SELECT floor(random() * 20)::int + 2000

SELECT round((random()* 9)::NUMERIC, 2)

SELECT to_char((random() * 9.0),'FM999999990.00')

select timestamp '2014-01-10 08:00:00' +
       random() * (timestamp '2021-01-20 18:00:00' -
                   timestamp '2014-01-10 08:00:00')
select actor_id from actor order by random() limit 1
SELECT generate_series(date'2010-01-20',date'2010-02-04','1 day');
   


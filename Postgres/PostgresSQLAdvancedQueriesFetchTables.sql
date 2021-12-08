
do $$
DECLARE
first_names TEXT[];
last_names TEXT[];
begin
first_names := ARRAY[
	    'Adam','Bill','Bob','Calvin','Donald','Dwight','Frank','Fred','George','Howard',
	    'James','John','Jacob','Jack','Martin','Matthew','Max','Michael',
	    'Paul','Peter','Phil','Roland','Ronald','Samuel','Steve','Theo','Warren','William',
	    'Abigail','Alice','Allison','Amanda','Anne','Barbara','Betty','Carol','Cleo','Donna',
	    'Jane','Jennifer','Julie','Martha','Mary','Melissa','Patty','Sarah','Simone','Susan'
	    ];
    last_names := ARRAY[
		'Matthews','Smith','Jones','Davis','Jacobson','Williams','Donaldson','Maxwell','Peterson','Stevens',
		'Franklin','Washington','Jefferson','Adams','Jackson','Johnson','Lincoln','Grant','Fillmore','Harding','Taft',
		'Truman','Nixon','Ford','Carter','Reagan','Bush','Clinton','Hancock'
	    ];
	    
    for i IN 1..10 loop
	 INSERT INTO actor(actor_id, first_name, last_name) values 
	 (i, 
	 first_names[random() * ARRAY_LENGTH(first_names,1)], 
	first_names[random() * ARRAY_LENGTH(first_names,1)]);
    end loop;
end;
$$

-- select actor_id from actor order by random() limit 1

   

FOR i IN 1..10 LOOP
 -- Fetch actor table
 INSERT INTO actor(actor_id, first_name, last_name) values (i, 
  arrays.firstnames[s.a % ARRAY_LENGTH(arrays.firstnames,1) + 1], 
 (SELECT array_to_string(ARRAY(SELECT chr((65 + round(random() * 25)) :: integer) FROM generate_series(1,15)), '')));
END LOOP;

SELECT
    arrays.firstnames[s.a % ARRAY_LENGTH(arrays.firstnames,1) + 1] AS firstname,
    substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ' from s.a%26+1 for 1) AS middlename,
    arrays.lastnames[s.a % ARRAY_LENGTH(arrays.lastnames,1) + 1] AS lastname
FROM     generate_series(1,300) AS s(a) -- number of names to generate
CROSS JOIN(
    SELECT ARRAY[
    'Adam','Bill','Bob','Calvin','Donald','Dwight','Frank','Fred','George','Howard',
    'James','John','Jacob','Jack','Martin','Matthew','Max','Michael',
    'Paul','Peter','Phil','Roland','Ronald','Samuel','Steve','Theo','Warren','William',
    'Abigail','Alice','Allison','Amanda','Anne','Barbara','Betty','Carol','Cleo','Donna',
    'Jane','Jennifer','Julie','Martha','Mary','Melissa','Patty','Sarah','Simone','Susan'
    ] AS firstnames,
    ARRAY[
        'Matthews','Smith','Jones','Davis','Jacobson','Williams','Donaldson','Maxwell','Peterson','Stevens',
        'Franklin','Washington','Jefferson','Adams','Jackson','Johnson','Lincoln','Grant','Fillmore','Harding','Taft',
        'Truman','Nixon','Ford','Carter','Reagan','Bush','Clinton','Hancock'
    ] AS lastnames
) AS arrays
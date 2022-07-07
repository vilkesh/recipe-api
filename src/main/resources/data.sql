/**
 * CREATE Script for init of DB
 */

 -- create recipes

insert into recipes(NAME,SERVINGS,USERNAME,TYPE,DATE_CREATED) values('Pasta with bacon and tomato sauce',3,'user01','VEG',now());
insert into recipes(NAME,SERVINGS,USERNAME,TYPE,DATE_CREATED) values('Spanish Rice',4,'user02','VEG',now());
insert into recipes(NAME,SERVINGS,USERNAME,TYPE,DATE_CREATED) values('Githeri',5,'user03','NONVEG',now());
insert into recipes(NAME,SERVINGS,USERNAME,TYPE,DATE_CREATED) values('Tweed Kettle',3,'user06','VEG',now());
insert into recipes(NAME,SERVINGS,USERNAME,TYPE,DATE_CREATED) values('Haggis',4,'user07','NONVEG',now());
insert into recipes(NAME,SERVINGS,USERNAME,TYPE,DATE_CREATED) values('Scotch Pancakes',12,'user08','VEG',now());

-- create Ingredients
insert into ingredients(RECIPE_ID,INGREDIENT) values(1,'1 red onion.');
insert into ingredients(RECIPE_ID,INGREDIENT) values(1,'2 red peppers.');
insert into ingredients(RECIPE_ID,INGREDIENT) values(1,'1 can (450g) tomatoes.');
insert into ingredients(RECIPE_ID,INGREDIENT) values(1,'1 cup water.');
insert into ingredients(RECIPE_ID,INGREDIENT) values(1,'olive oil');
insert into ingredients(RECIPE_ID,INGREDIENT) values(2,'1 cup parboiled Rice (uncooked)');
insert into ingredients(RECIPE_ID,INGREDIENT) values(2,'Nyanya');
insert into ingredients(RECIPE_ID,INGREDIENT) values(2,'Royco');
insert into ingredients(RECIPE_ID,INGREDIENT) values(3,'Maize.');
insert into ingredients(RECIPE_ID,INGREDIENT) values(3,'beans');
insert into ingredients(RECIPE_ID,INGREDIENT) values(3,'potatoes onions');
insert into ingredients(RECIPE_ID,INGREDIENT) values(3,'tomatoes cooking oil.');
insert into ingredients(RECIPE_ID,INGREDIENT) values(4,'Beef');
insert into ingredients(RECIPE_ID,INGREDIENT) values(4,'Tomatoes');
insert into ingredients(RECIPE_ID,INGREDIENT) values(5,'Cock or Fowl');
insert into ingredients(RECIPE_ID,INGREDIENT) values(5,'Leek');
insert into ingredients(RECIPE_ID,INGREDIENT) values(5,'Prunes');
insert into ingredients(RECIPE_ID,INGREDIENT) values(6,'Scotch Salmon');
insert into ingredients(RECIPE_ID,INGREDIENT) values(6,'Shallots');
insert into ingredients(RECIPE_ID,INGREDIENT) values(6,'Gridle Scones (aside)');

insert into test(NAME) values('test1');


-- create instructions

insert into instructions(RECIPE_ID,INSTRUCTION) values(1,'Cut the onion');
insert into instructions(RECIPE_ID,INSTRUCTION) values(1,'red peppers and bacon into small pieces');
insert into instructions(RECIPE_ID,INSTRUCTION) values(1,'Heat some olive oil in a pan and fry the onion red pepper and bacon.');
insert into instructions(RECIPE_ID,INSTRUCTION) values(1,'Add oregano, garlic, tomatoes and water and cook for 20 minutes.');
insert into instructions(RECIPE_ID,INSTRUCTION) values(1,'Cook the pasta in a big pot of boiling water.');
insert into instructions(RECIPE_ID,INSTRUCTION) values(1,'Serve the pasta with the sauce.');
insert into instructions(RECIPE_ID,INSTRUCTION) values(2,'Brown hamburger.');
insert into instructions(RECIPE_ID,INSTRUCTION) values(2,'Stir all other ingredients.');
insert into instructions(RECIPE_ID,INSTRUCTION) values(2,'Bring to a boil.');
insert into instructions(RECIPE_ID,INSTRUCTION) values(2,'Stir.');
insert into instructions(RECIPE_ID,INSTRUCTION) values(2,'Lower-to simmer.');
insert into instructions(RECIPE_ID,INSTRUCTION) values(2,'Cover and cook for 20 minutes or until all liquid is absorbed.');
insert into instructions(RECIPE_ID,INSTRUCTION) values(3,'Boil maize and beans together until cooked.');
insert into instructions(RECIPE_ID,INSTRUCTION) values(3,'Fry onions and tomatoes.');
insert into instructions(RECIPE_ID,INSTRUCTION) values(3,'add potatoes until almost cooked.');
insert into instructions(RECIPE_ID,INSTRUCTION) values(3,'add the maize and beans plus salt to taste.');
insert into instructions(RECIPE_ID,INSTRUCTION) values(3,'let the mixture come to a boil until potatoes are well cooked.');
insert into instructions(RECIPE_ID,INSTRUCTION) values(3,'Serve while hot.');
insert into instructions(RECIPE_ID,INSTRUCTION) values(4,'Koroga hadi ii-ve.');
insert into instructions(RECIPE_ID,INSTRUCTION) values(5,'Simmer a cock/fowl for 2 hrs with leeks.');
insert into instructions(RECIPE_ID,INSTRUCTION) values(5,'Cut the bird into pieces.');
insert into instructions(RECIPE_ID,INSTRUCTION) values(5,'put in tureen and pour over the broth.');
insert into instructions(RECIPE_ID,INSTRUCTION) values(5,'Add Prunes to sweeten.');
insert into instructions(RECIPE_ID,INSTRUCTION) values(6,'Poach the fish with shallots and white wine.');
insert into instructions(RECIPE_ID,INSTRUCTION) values(6,'Serve hot with fresh girdle scones.');















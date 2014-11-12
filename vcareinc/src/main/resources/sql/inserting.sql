INSERT INTO price (optionType, priceType, amount, active, description, detail)
SELECT 0, 0, 0.00, 1, '', '' FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM price WHERE optionType = 0 AND priceType = 0
) LIMIT 1;


INSERT INTO price (optionType, priceType, amount, active, description, detail)
SELECT 0, 1, 19.97, 1, 'Silver level listings .', 'Title, Address, Phone, Web Link, E-mail Link, E-mail to Friend, Add to Favorites, Ratings and Reviews.' FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM price WHERE optionType = 0 AND priceType = 1
) LIMIT 1;

INSERT INTO price (optionType, priceType, amount, active, description, detail)
SELECT 0, 2, 29.97, 1, 'Gold level listings appear in search results above Silver listings.', 'Title, Address, Phone, Web Link, E-mail Link, E-mail to Friend, Add to Favorites, Ratings and Reviews.' FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM price WHERE optionType = 0 AND priceType = 2
) LIMIT 1;

INSERT INTO price (optionType, priceType, amount, active, description, detail)
SELECT 0, 3, 39.97, 1, 'Diamond level listings appear in search results above Gold, Silver and Bronze listings.', 'Contains Detail View and Summary View.
Title, Logo Image, Address, Phone, Fax, Web Link, E-Mail Link, Summary Description, Detail Description, E-mail to Friend, Add to Favorites, Print, Map, Badges, Deal, Ratings and Reviews, Photo Gallery, Contact Form, Video Snippet, Attachment File, Hours of work, Location, Mobile Check in, Facebook Like and Send button, Google +1 button, Pinterest button, Send to Phone, Click to Call.' FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM price WHERE optionType = 0 AND priceType = 3
) LIMIT 1;

INSERT INTO price (optionType, priceType, amount, active, description, detail)
SELECT 1, 0, 0.00, 1, '', 'Title, Date, Address, Phone, E-mail to Friend, Add to Favorites, Recurring option.' FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM price WHERE optionType = 1 AND priceType = 0
) LIMIT 1;

INSERT INTO price (optionType, priceType, amount, active, description, detail)
SELECT 1, 2, 25.00, 1, 'Gold Events appear in search results above the Silver Events.', 'Title, Date, Time, Logo image, Address, Phone, Summary Description, E-mail to Friend, Add to Favorites, Recurring option.' FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM price WHERE optionType = 1 AND priceType = 2
) LIMIT 1;

INSERT INTO price (optionType, priceType, amount, active, description, detail)
SELECT 1, 3, 50.00, 1, 'Diamond Events appear in search results above Gold and Silver Events.', 'Contains Summary View and Detail view.
Title, Date, Time, Logo Image, Address, Summary Description, Driving Directions, Phone, Web Link, E-mail, Contact Name, Photo Gallery, Detail Description, E-mail to Friend, Add to Favorites, Print, Facebook Like and Send button, Google +1 button, Pinterest button, Recurring option.' FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM price WHERE optionType = 1 AND priceType = 3
) LIMIT 1;

INSERT INTO price (optionType, priceType, amount, active, description, detail)
SELECT 2, 0, 0.00, 1, '', 'Title, Address, Photo, Phone, E-mail, Price, Short Description, E-mail to Friend, Add to Favorites.' FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM price WHERE optionType = 2 AND priceType = 0
) LIMIT 1;

INSERT INTO price (optionType, priceType, amount, active, description, detail)
SELECT 2, 2, 25.00, 1, 'Gold Classifieds appear in search results above Silver Classifieds.', 'Contains Summary View and Detail View.
Title, Address, Photo, Contact Name, Phone, E-mail, Price, Short Description, Detail Description, Photo Gallery, E-mail to Friend, Add to Favorites, Print, Facebook Like and Send button, Google +1 button, Pinterest button.' FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM price WHERE optionType = 2 AND priceType = 2
) LIMIT 1;

INSERT INTO price (optionType, priceType, amount, active, description, detail)
SELECT 2, 3, 50.00, 1, 'Diamond Classifieds appear in search results above Gold and Silver Classifieds.', 'Contains Summary View and Detail View.
Title, Address, Photo, Contact Name, Phone, Fax, E-mail, Web Link, Price, Short Description, Detail Description, Photo Gallery, E-mail to Friend, Add to Favorites, Print, Facebook Like and Send button, Google +1 button, Pinterest button.' FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM price WHERE optionType = 2 AND priceType = 3
) LIMIT 1;

INSERT INTO price (optionType, priceType, amount, active, description, detail)
SELECT 3, 1, 30.00, 1, '', 'Contains Summary View and Detail View.
Title, Publication Date, Author, Author URL, Abstract, Image, Content, Photo Gallery, E-mail to Friend, Add to Favorites, Print, Facebook Like and Send button, Google +1 button, Pinterest button.' FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM price WHERE optionType = 3 AND priceType = 1
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Beauty and Fitness', 0, 'Beauty and Fitness', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Beauty and Fitness'
) LIMIT 1;

SELECT @CatBAFID := ID FROM category WHERE name = 'Beauty and Fitness';

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Beauty Salons', 0, 'Beauty Salons', @CatBAFID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Beauty Salons'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Gyms', 0, 'Gyms', @CatBAFID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Gyms'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Spas', 0, 'Spas', @CatBAFID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Spas'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Massage', 0, 'Massage', @CatBAFID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Massage'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Beauty Distributor', 0, 'Beauty Distributor', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Beauty Distributor'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Beauty Schools', 0, 'Beauty Schools', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Beauty Schools'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Beauty Students/ Apprentice', 0, 'Beauty Students/ Apprentice', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Beauty Students/ Apprentice'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Beauty Supply', 0, 'Beauty Supply', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Beauty Supply'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Animals', 1, 'Animals', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Animals'
) LIMIT 1;

SELECT @CatAnimID := ID FROM category WHERE name = 'Animals';

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Exotics', 1, 'Exotics', @CatAnimID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Exotics'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Presentations', 1, 'Presentations', @CatAnimID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Presentations'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Arts', 1, 'Arts', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Arts'
) LIMIT 1;

SELECT @CatArtsID := ID FROM category WHERE name = 'Arts';

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Galleries', 1, 'Galleries', @CatArtsID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Galleries'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Shows', 1, 'Shows', @CatArtsID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Shows'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Beauty Conventions / Shows', 1, 'Beauty Conventions / Shows', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Beauty Conventions / Shows'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Entertainment', 1, 'Entertainment', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Entertainment'
) LIMIT 1;

SELECT @CatEntID := ID FROM category WHERE name = 'Entertainment';

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Championship', 1, 'Championship', @CatEntID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Championship'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Cinema', 1, 'Cinema', @CatEntID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Cinema'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Concerts', 1, 'Concerts', @CatEntID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Concerts'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Night Clubs', 1, 'Night Clubs', @CatEntID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Night Clubs'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Presentations', 1, 'Presentations', @CatEntID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Presentations'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Fair', 1, 'Fair', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Fair'
) LIMIT 1;

SELECT @CatFairID := ID FROM category WHERE name = 'Fair';

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Antiquities', 1, 'Antiquities', @CatFairID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Antiquities'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Beauty', 1, 'Beauty', @CatFairID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Beauty'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Cars', 1, 'Cars', @CatFairID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Cars'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Technology', 1, 'Technology', @CatFairID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Technology'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Food and Dining', 1, 'Food and Dining', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Food and Dining'
) LIMIT 1;

SELECT @CatFODID := ID FROM category WHERE name = 'Food and Dining';

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Drinks', 1, 'Drinks', @CatFODID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Drinks'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Festivals', 1, 'Festivals', @CatFODID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Festivals'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Leisure', 1, 'Leisure', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Leisure'
) LIMIT 1;

SELECT @CatLeisID := ID FROM category WHERE name = 'Leisure';

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Sports', 1, 'Sports', @CatLeisID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Sports'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Jobs', 2, 'Jobs', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Jobs'
) LIMIT 1;

SELECT @CatJobsID := ID FROM category WHERE name = 'Jobs';

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'General', 2, 'General', @CatJobsID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'General'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Office', 2, 'Office', @CatJobsID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Office'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Sales', 2, 'Sales', @CatJobsID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Sales'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Merchandise', 2, 'Merchandise', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Merchandise'
) LIMIT 1;

SELECT @CatMerchID := ID FROM category WHERE name = 'Merchandise';

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Clothing', 2, 'Clothing', @CatMerchID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Clothing'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Health & Beauty', 2, 'Health & Beauty', @CatMerchID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Health & Beauty'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Notices', 2, 'Notices', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Notices'
) LIMIT 1;

SELECT @CatNoticesID := ID FROM category WHERE name = 'Notices';

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Announcements', 2, 'Announcements', @CatNoticesID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Announcements'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Lost & Found', 2, 'Lost & Found', @CatNoticesID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Lost & Found'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Obituaries', 2, 'Obituaries', @CatNoticesID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Obituaries'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Personals', 2, 'Personals', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Personals'
) LIMIT 1;

SELECT @CatPersoID := ID FROM category WHERE name = 'Personals';

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Agency Services', 2, 'Agency Services', @CatPersoID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Agency Services'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Friends', 2, 'Friends', @CatPersoID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Friends'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Pets', 2, 'Pets', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Pets'
) LIMIT 1;

SELECT @CatPetsID := ID FROM category WHERE name = 'Pets';

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Exotic Animals', 2, 'Exotic Animals', @CatPetsID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Exotic Animals'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Pet Supplies', 2, 'Pet Supplies', @CatPetsID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Pet Supplies'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Business', 3, 'Business', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Business'
) LIMIT 1;

SELECT @CatBussID := ID FROM category WHERE name = 'Business';

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Marketing', 3, 'Marketing', @CatBussID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Marketing'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Small Business', 3, 'Small Business', @CatBussID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Small Business'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Strategy', 3, 'Strategy', @CatBussID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Strategy'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Finance', 3, 'Finance', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Finance'
) LIMIT 1;

SELECT @CatFinaID := ID FROM category WHERE name = 'Finance';

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Consulting Finance', 3, 'Consulting Finance', @CatFinaID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Consulting Finance'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Personal Finance', 3, 'Personal Finance', @CatFinaID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Personal Finance'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Fitness', 3, 'Fitness', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Fitness'
) LIMIT 1;

SELECT @CatFitnID := ID FROM category WHERE name = 'Fitness';

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Exercise', 3, 'Exercise', @CatFitnID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Exercise'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Nutrition', 3, 'Nutrition', @CatFitnID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Nutrition'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Life Style', 3, 'Life Style', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Life Style'
) LIMIT 1;

SELECT @CatListyID := ID FROM category WHERE name = 'Life Style';

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Career', 3, 'Career', @CatListyID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Career'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Relationship', 3, 'Relationship', @CatListyID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Relationship'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Technology', 3, 'Technology', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Technology'
) LIMIT 1;

SELECT @CatTechID := ID FROM category WHERE name = 'Technology';

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Equipment', 3, 'Equipment', @CatTechID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Equipment'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Games', 3, 'Games', @CatTechID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Games'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Internet', 3, 'Internet', @CatTechID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Internet'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Volunteer', 3, 'Volunteer', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Volunteer'
) LIMIT 1;

SELECT @CatVolunID := ID FROM category WHERE name = 'Volunteer';

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Organizations', 3, 'Organizations', @CatVolunID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Organizations'
) LIMIT 1;

INSERT INTO category(created, description, enable, name, optionType, pageTitle, parentCategory_id)
SELECT CURDATE(), NULL, 1, 'Volunteer Opportunities', 3, 'Volunteer Opportunities', @CatVolunID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM category WHERE name = 'Volunteer Opportunities'
) LIMIT 1;

INSERT INTO country(name, code)
SELECT 'United States', 'US' FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM country WHERE code = 'US'
) LIMIT 1;

SELECT @CountryUS := ID FROM country WHERE code = 'US';

INSERT INTO state(name, code, country_id)
SELECT 'Alabama','AL',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'AL'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Alaska','AK',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'AK'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Arizona','AZ',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'AZ'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Arkansas','AR',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'AR'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'California','CA',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'CA'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Colorado','CO',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'CO'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Connecticut','CT',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'CT'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Delaware','DE',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'DE'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Florida','FL',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'FL'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Georgia','GA',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'GA'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Hawaii','HI',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'HI'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Idaho','ID',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'ID'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Illinois','IL',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'IL'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Indiana','IN',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'IN'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Iowa','IA',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'IA'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Kansas','KS',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'KS'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Kentucky','KY',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'KY'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Louisiana','LA',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'LA'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Maine','ME',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'ME'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Maryland','MD',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'MD'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Massachusetts','MA',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'MA'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Michigan','MI',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'MI'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Minnesota','MN',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'MN'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Mississippi','MS',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'MS'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Missouri','MO',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'MO'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Montana','MT',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'MT'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Nebraska','NE',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'NE'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Nevada','NV',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'NV'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'New Hampshire','NH',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'NH'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'New Jersey','NJ',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'NJ'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'New Mexico','NM',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'NM'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'New York','NY',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'NY'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'North Carolina','NC',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'NC'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'North Dakota','ND',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'ND'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Ohio','OH',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'OH'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Oklahoma','OK',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'OK'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Oregon','OR',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'OR'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Pennsylvania','PA',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'PA'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Rhode Island','RI',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'RI'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'South Carolina','SC',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'SC'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'South Dakota','SD',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'SD'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Tennessee','TN',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'TN'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Texas','TX',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'TX'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Utah','UT',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'UT'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Vermont','VT',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'VT'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Virginia','VA',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'VA'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Washington','WA',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'WA'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'West Virginia','WV',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'WV'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Wisconsin','WI',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'WI'
) LIMIT 1;

INSERT INTO state(name, code, country_id)
SELECT 'Wyoming','WY',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM state WHERE code = 'WY'
) LIMIT 1;

INSERT INTO city(name)
SELECT 'Atlanta' FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM city WHERE name = 'Atlanta'
)LIMIT 1;

INSERT INTO city(name)
SELECT 'Austin' FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM city WHERE name = 'Austin'
)LIMIT 1;

INSERT INTO city(name)
SELECT 'Brooklyn' FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM city WHERE name = 'Brooklyn'
)LIMIT 1;

INSERT INTO city(name)
SELECT 'Chicago' FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM city WHERE name = 'Chicago'
)LIMIT 1;

INSERT INTO city(name)
SELECT 'Cleveland' FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM city WHERE name = 'Cleveland'
)LIMIT 1;

INSERT INTO city(name)
SELECT 'Dallas' FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM city WHERE name = 'Dallas'
)LIMIT 1;

INSERT INTO city(name)
SELECT 'Denver' FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM city WHERE name = 'Denver'
)LIMIT 1;

INSERT INTO city(name)
SELECT 'Houston' FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM city WHERE name = 'Houston'
)LIMIT 1;

INSERT INTO city(name)
SELECT 'Las Vegas' FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM city WHERE name = 'Las Vegas'
)LIMIT 1;

INSERT INTO city(name)
SELECT 'Los Angeles' FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM city WHERE name = 'Los Angeles'
)LIMIT 1;

INSERT INTO city(name)
SELECT 'Minneapolis' FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM city WHERE name = 'Minneapolis'
)LIMIT 1;

INSERT INTO city(name)
SELECT 'New York' FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM city WHERE name = 'New York'
)LIMIT 1;

INSERT INTO city(name)
SELECT 'Philadelphia' FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM city WHERE name = 'Philadelphia'
)LIMIT 1;

INSERT INTO city(name)
SELECT 'Phoenix' FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM city WHERE name = 'Phoenix'
)LIMIT 1;

INSERT INTO city(name)
SELECT 'Pittsburgh' FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM city WHERE name = 'Pittsburgh'
)LIMIT 1;

INSERT INTO city(name)
SELECT 'Portland' FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM city WHERE name = 'Portland'
)LIMIT 1;

INSERT INTO city(name)
SELECT 'Saint Louis' FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM city WHERE name = 'Saint Louis'
)LIMIT 1;

INSERT INTO city(name)
SELECT 'San Antonio' FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM city WHERE name = 'San Antonio'
)LIMIT 1;

INSERT INTO city(name)
SELECT 'San Diego' FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM city WHERE name = 'San Diego'
)LIMIT 1;

INSERT INTO city(name)
SELECT 'San Francisco' FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM city WHERE name = 'San Francisco'
)LIMIT 1;

INSERT INTO city(name)
SELECT 'Seattle' FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM city WHERE name = 'Seattle'
)LIMIT 1;

INSERT INTO city(name)
SELECT 'Tampa' FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM city WHERE name = 'Tampa'
)LIMIT 1;

INSERT INTO city(name)
SELECT 'Tucson' FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM city WHERE name = 'Tucson'
)LIMIT 1;

INSERT INTO city(name)
SELECT 'Washington' FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM city WHERE name = 'Washington'
)LIMIT 1;
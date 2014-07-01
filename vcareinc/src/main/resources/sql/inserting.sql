INSERT INTO PRICE (OPTIONTYPE, PRICETYPE, AMOUNT, ACTIVE, DESCRIPTION, DETAIL)
SELECT 0, 0, 0.00, 1, '', '' FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM PRICE WHERE OPTIONTYPE = 0 AND PRICETYPE = 0
) LIMIT 1;


INSERT INTO PRICE (OPTIONTYPE, PRICETYPE, AMOUNT, ACTIVE, DESCRIPTION, DETAIL)
SELECT 0, 1, 19.97, 1, 'Silver level listings .', 'Title, Address, Phone, Web Link, E-mail Link, E-mail to Friend, Add to Favorites, Ratings and Reviews.' FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM PRICE WHERE OPTIONTYPE = 0 AND PRICETYPE = 1
) LIMIT 1;

INSERT INTO PRICE (OPTIONTYPE, PRICETYPE, AMOUNT, ACTIVE, DESCRIPTION, DETAIL)
SELECT 0, 2, 29.97, 1, 'Gold level listings appear in search results above Silver listings.', 'Title, Address, Phone, Web Link, E-mail Link, E-mail to Friend, Add to Favorites, Ratings and Reviews.' FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM PRICE WHERE OPTIONTYPE = 0 AND PRICETYPE = 2
) LIMIT 1;

INSERT INTO PRICE (OPTIONTYPE, PRICETYPE, AMOUNT, ACTIVE, DESCRIPTION, DETAIL)
SELECT 0, 3, 39.97, 1, 'Diamond level listings appear in search results above Gold, Silver and Bronze listings.', 'Contains Detail View and Summary View.
Title, Logo Image, Address, Phone, Fax, Web Link, E-Mail Link, Summary Description, Detail Description, E-mail to Friend, Add to Favorites, Print, Map, Badges, Deal, Ratings and Reviews, Photo Gallery, Contact Form, Video Snippet, Attachment File, Hours of work, Location, Mobile Check in, Facebook Like and Send button, Google +1 button, Pinterest button, Send to Phone, Click to Call.' FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM PRICE WHERE OPTIONTYPE = 0 AND PRICETYPE = 3
) LIMIT 1;

INSERT INTO PRICE (OPTIONTYPE, PRICETYPE, AMOUNT, ACTIVE, DESCRIPTION, DETAIL)
SELECT 1, 0, 0.00, 1, '', 'Title, Date, Address, Phone, E-mail to Friend, Add to Favorites, Recurring option.' FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM PRICE WHERE OPTIONTYPE = 1 AND PRICETYPE = 0
) LIMIT 1;

INSERT INTO PRICE (OPTIONTYPE, PRICETYPE, AMOUNT, ACTIVE, DESCRIPTION, DETAIL)
SELECT 1, 2, 25.00, 1, 'Gold Events appear in search results above the Silver Events.', 'Title, Date, Time, Logo image, Address, Phone, Summary Description, E-mail to Friend, Add to Favorites, Recurring option.' FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM PRICE WHERE OPTIONTYPE = 1 AND PRICETYPE = 2
) LIMIT 1;

INSERT INTO PRICE (OPTIONTYPE, PRICETYPE, AMOUNT, ACTIVE, DESCRIPTION, DETAIL)
SELECT 1, 3, 50.00, 1, 'Diamond Events appear in search results above Gold and Silver Events.', 'Contains Summary View and Detail view.
Title, Date, Time, Logo Image, Address, Summary Description, Driving Directions, Phone, Web Link, E-mail, Contact Name, Photo Gallery, Detail Description, E-mail to Friend, Add to Favorites, Print, Facebook Like and Send button, Google +1 button, Pinterest button, Recurring option.' FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM PRICE WHERE OPTIONTYPE = 1 AND PRICETYPE = 3
) LIMIT 1;

INSERT INTO PRICE (OPTIONTYPE, PRICETYPE, AMOUNT, ACTIVE, DESCRIPTION, DETAIL)
SELECT 2, 0, 0.00, 1, '', 'Title, Address, Photo, Phone, E-mail, Price, Short Description, E-mail to Friend, Add to Favorites.' FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM PRICE WHERE OPTIONTYPE = 2 AND PRICETYPE = 0
) LIMIT 1;

INSERT INTO PRICE (OPTIONTYPE, PRICETYPE, AMOUNT, ACTIVE, DESCRIPTION, DETAIL)
SELECT 2, 2, 25.00, 1, 'Gold Classifieds appear in search results above Silver Classifieds.', 'Contains Summary View and Detail View.
Title, Address, Photo, Contact Name, Phone, E-mail, Price, Short Description, Detail Description, Photo Gallery, E-mail to Friend, Add to Favorites, Print, Facebook Like and Send button, Google +1 button, Pinterest button.' FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM PRICE WHERE OPTIONTYPE = 2 AND PRICETYPE = 2
) LIMIT 1;

INSERT INTO PRICE (OPTIONTYPE, PRICETYPE, AMOUNT, ACTIVE, DESCRIPTION, DETAIL)
SELECT 2, 3, 50.00, 1, 'Diamond Classifieds appear in search results above Gold and Silver Classifieds.', 'Contains Summary View and Detail View.
Title, Address, Photo, Contact Name, Phone, Fax, E-mail, Web Link, Price, Short Description, Detail Description, Photo Gallery, E-mail to Friend, Add to Favorites, Print, Facebook Like and Send button, Google +1 button, Pinterest button.' FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM PRICE WHERE OPTIONTYPE = 2 AND PRICETYPE = 3
) LIMIT 1;

INSERT INTO PRICE (OPTIONTYPE, PRICETYPE, AMOUNT, ACTIVE, DESCRIPTION, DETAIL)
SELECT 3, 1, 30.00, 1, '', 'Contains Summary View and Detail View.
Title, Publication Date, Author, Author URL, Abstract, Image, Content, Photo Gallery, E-mail to Friend, Add to Favorites, Print, Facebook Like and Send button, Google +1 button, Pinterest button.' FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM PRICE WHERE OPTIONTYPE = 3 AND PRICETYPE = 1
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Beauty and Fitness', 0, 'Beauty and Fitness', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Beauty and Fitness'
) LIMIT 1;

SELECT @CatBAFID := ID FROM CATEGORY WHERE NAME = 'Beauty and Fitness';

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Beauty Salons', 0, 'Beauty Salons', @CatBAFID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Beauty Salons'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Gyms', 0, 'Gyms', @CatBAFID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Gyms'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Spas', 0, 'Spas', @CatBAFID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Spas'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Massage', 0, 'Massage', @CatBAFID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Massage'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Beauty Distributor', 0, 'Beauty Distributor', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Beauty Distributor'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Beauty Schools', 0, 'Beauty Schools', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Beauty Schools'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Beauty Students/ Apprentice', 0, 'Beauty Students/ Apprentice', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Beauty Students/ Apprentice'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Beauty Supply', 0, 'Beauty Supply', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Beauty Supply'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Animals', 1, 'Animals', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Animals'
) LIMIT 1;

SELECT @CatAnimID := ID FROM CATEGORY WHERE NAME = 'Animals';

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Exotics', 1, 'Exotics', @CatAnimID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Exotics'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Presentations', 1, 'Presentations', @CatAnimID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Presentations'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Arts', 1, 'Arts', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Arts'
) LIMIT 1;

SELECT @CatArtsID := ID FROM CATEGORY WHERE NAME = 'Arts';

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Galleries', 1, 'Galleries', @CatArtsID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Galleries'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Shows', 1, 'Shows', @CatArtsID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Shows'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Beauty Conventions / Shows', 1, 'Beauty Conventions / Shows', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Beauty Conventions / Shows'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Entertainment', 1, 'Entertainment', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Entertainment'
) LIMIT 1;

SELECT @CatEntID := ID FROM CATEGORY WHERE NAME = 'Entertainment';

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Championship', 1, 'Championship', @CatEntID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Championship'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Cinema', 1, 'Cinema', @CatEntID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Cinema'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Concerts', 1, 'Concerts', @CatEntID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Concerts'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Night Clubs', 1, 'Night Clubs', @CatEntID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Night Clubs'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Presentations', 1, 'Presentations', @CatEntID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Presentations'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Fair', 1, 'Fair', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Fair'
) LIMIT 1;

SELECT @CatFairID := ID FROM CATEGORY WHERE NAME = 'Fair';

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Antiquities', 1, 'Antiquities', @CatFairID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Antiquities'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Beauty', 1, 'Beauty', @CatFairID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Beauty'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Cars', 1, 'Cars', @CatFairID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Cars'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Technology', 1, 'Technology', @CatFairID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Technology'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Food and Dining', 1, 'Food and Dining', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Food and Dining'
) LIMIT 1;

SELECT @CatFODID := ID FROM CATEGORY WHERE NAME = 'Food and Dining';

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Drinks', 1, 'Drinks', @CatFODID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Drinks'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Festivals', 1, 'Festivals', @CatFODID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Festivals'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Leisure', 1, 'Leisure', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Leisure'
) LIMIT 1;

SELECT @CatLeisID := ID FROM CATEGORY WHERE NAME = 'Leisure';

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Sports', 1, 'Sports', @CatLeisID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Sports'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Jobs', 2, 'Jobs', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Jobs'
) LIMIT 1;

SELECT @CatJobsID := ID FROM CATEGORY WHERE NAME = 'Jobs';

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'General', 2, 'General', @CatJobsID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'General'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Office', 2, 'Office', @CatJobsID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Office'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Sales', 2, 'Sales', @CatJobsID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Sales'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Merchandise', 2, 'Merchandise', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Merchandise'
) LIMIT 1;

SELECT @CatMerchID := ID FROM CATEGORY WHERE NAME = 'Merchandise';

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Clothing', 2, 'Clothing', @CatMerchID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Clothing'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Health & Beauty', 2, 'Health & Beauty', @CatMerchID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Health & Beauty'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Notices', 2, 'Notices', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Notices'
) LIMIT 1;

SELECT @CatNoticesID := ID FROM CATEGORY WHERE NAME = 'Notices';

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Announcements', 2, 'Announcements', @CatNoticesID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Announcements'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Lost & Found', 2, 'Lost & Found', @CatNoticesID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Lost & Found'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Obituaries', 2, 'Obituaries', @CatNoticesID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Obituaries'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Personals', 2, 'Personals', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Personals'
) LIMIT 1;

SELECT @CatPersoID := ID FROM CATEGORY WHERE NAME = 'Personals';

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Agency Services', 2, 'Agency Services', @CatPersoID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Agency Services'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Friends', 2, 'Friends', @CatPersoID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Friends'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Pets', 2, 'Pets', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Pets'
) LIMIT 1;

SELECT @CatPetsID := ID FROM CATEGORY WHERE NAME = 'Pets';

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Exotic Animals', 2, 'Exotic Animals', @CatPetsID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Exotic Animals'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Pet Supplies', 2, 'Pet Supplies', @CatPetsID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Pet Supplies'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Business', 3, 'Business', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Business'
) LIMIT 1;

SELECT @CatBussID := ID FROM CATEGORY WHERE NAME = 'Business';

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Marketing', 3, 'Marketing', @CatBussID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Marketing'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Small Business', 3, 'Small Business', @CatBussID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Small Business'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Strategy', 3, 'Strategy', @CatBussID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Strategy'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Finance', 3, 'Finance', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Finance'
) LIMIT 1;

SELECT @CatFinaID := ID FROM CATEGORY WHERE NAME = 'Finance';

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Consulting Finance', 3, 'Consulting Finance', @CatFinaID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Consulting Finance'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Personal Finance', 3, 'Personal Finance', @CatFinaID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Personal Finance'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Fitness', 3, 'Fitness', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Fitness'
) LIMIT 1;

SELECT @CatFitnID := ID FROM CATEGORY WHERE NAME = 'Fitness';

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Exercise', 3, 'Exercise', @CatFitnID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Exercise'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Nutrition', 3, 'Nutrition', @CatFitnID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Nutrition'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Life Style', 3, 'Life Style', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Life Style'
) LIMIT 1;

SELECT @CatListyID := ID FROM CATEGORY WHERE NAME = 'Life Style';

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Career', 3, 'Career', @CatListyID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Career'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Relationship', 3, 'Relationship', @CatListyID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Relationship'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Technology', 3, 'Technology', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Technology'
) LIMIT 1;

SELECT @CatTechID := ID FROM CATEGORY WHERE NAME = 'Technology';

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Equipment', 3, 'Equipment', @CatTechID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Equipment'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Games', 3, 'Games', @CatTechID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Games'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Internet', 3, 'Internet', @CatTechID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Internet'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Volunteer', 3, 'Volunteer', NULL FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Volunteer'
) LIMIT 1;

SELECT @CatVolunID := ID FROM CATEGORY WHERE NAME = 'Volunteer';

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Organizations', 3, 'Organizations', @CatVolunID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Organizations'
) LIMIT 1;

INSERT INTO CATEGORY(CREATED, DESCRIPTION, ENABLE, NAME, OPTIONTYPE, PAGETITLE, PARENTCATEGORY_ID)
SELECT CURDATE(), NULL, 1, 'Volunteer Opportunities', 3, 'Volunteer Opportunities', @CatVolunID FROM DUAL
WHERE NOT EXISTS(
  SELECT * FROM CATEGORY WHERE NAME = 'Volunteer Opportunities'
) LIMIT 1;

INSERT INTO COUNTRY(NAME, CODE)
SELECT 'United States', 'US' FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM COUNTRY WHERE CODE = 'US'
) LIMIT 1;

SELECT @CountryUS := ID FROM COUNTRY WHERE CODE = 'US';

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Alabama','AL',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'AL'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Alaska','AK',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'AK'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Arizona','AZ',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'AZ'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Arkansas','AR',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'AR'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'California','CA',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'CA'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Colorado','CO',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'CO'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Connecticut','CT',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'CT'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Delaware','DE',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'DE'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Florida','FL',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'FL'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Georgia','GA',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'GA'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Hawaii','HI',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'HI'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Idaho','ID',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'ID'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Illinois','IL',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'IL'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Indiana','IN',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'IN'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Iowa','IA',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'IA'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Kansas','KS',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'KS'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Kentucky','KY',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'KY'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Louisiana','LA',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'LA'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Maine','ME',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'ME'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Maryland','MD',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'MD'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Massachusetts','MA',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'MA'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Michigan','MI',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'MI'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Minnesota','MN',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'MN'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Mississippi','MS',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'MS'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Missouri','MO',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'MO'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Montana','MT',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'MT'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Nebraska','NE',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'NE'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Nevada','NV',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'NV'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'New Hampshire','NH',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'NH'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'New Jersey','NJ',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'NJ'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'New Mexico','NM',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'NM'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'New York','NY',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'NY'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'North Carolina','NC',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'NC'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'North Dakota','ND',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'ND'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Ohio','OH',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'OH'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Oklahoma','OK',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'OK'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Oregon','OR',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'OR'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Pennsylvania','PA',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'PA'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Rhode Island','RI',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'RI'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'South Carolina','SC',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'SC'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'South Dakota','SD',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'SD'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Tennessee','TN',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'TN'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Texas','TX',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'TX'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Utah','UT',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'UT'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Vermont','VT',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'VT'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Virginia','VA',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'VA'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Washington','WA',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'WA'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'West Virginia','WV',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'WV'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Wisconsin','WI',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'WI'
) LIMIT 1;

INSERT INTO STATE(NAME, CODE, COUNTRY_ID)
SELECT 'Wyoming','WY',@CountryUS FROM DUAL
WHERE NOT EXISTS(
	SELECT * FROM STATE WHERE CODE = 'WY'
) LIMIT 1;
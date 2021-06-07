INSERT INTO title_categories (id, category_name) VALUES
    (1, 'Movie'),
    (2, 'Serie');

INSERT INTO filmworker_categories (id, category_name) VALUES
    (1, 'Director'),
    (2, 'Writer'),
    (3, 'Actor');

INSERT INTO titles (id, original_title, is_an_adult_film, start_year, end_year, runtime_minutes, title_category_id) VALUES
    (1, 'Titanic', true, 1997, null, 200, 1),
    (2, 'Star Wars', false, 1977, null, 260, 1),
    (3, 'Indiana Jones', false, 1981, null, 180, 1),
    (4, 'Aladin', false, 1992, null, 90, 1),
    (5, 'Naruto', false, 1999, 2014, null, 2),
    (6, 'Dragon Ball Z', false, 1989, 1996, null, 2),
    (7, 'Fullmetal alchemist Brotherhood', false, 2009, 2010, null, 2),
    (8, 'Atack on Titan', true, 2009, 2021, null, 2),
    (9, 'Death Note', true, 2003, 2007, null, 2),
    (10, 'Tierra de Osos', false, 2003, null, 100, 1);

INSERT INTO filmworkers (id, name, surname, birth_year, death_year, filmworker_category_id) VALUES
    (1, 'James', 'Cameron', 1954, null, 1),
    (2, 'George', 'Lucas', 1944, null, 1),
    (3, 'Steven', 'Spielberg', 1946, null, 1),
    (4, 'Ron', 'Clements', 1953, null, 1),
    (5, 'Masashi', 'Kishimoto', 1974, null, 2),
    (6, 'Daisuke', 'Nishio', 1959, null, 1),
    (7, 'Takao', 'Koyama', 1948, null, 2),
    (8, 'Yasuhiro', 'Irie', 1971, null, 1),
    (9, 'Leonardo', 'DiCaprio', 1974, null, 3),
    (10, 'Harrison', 'Ford', 1942, null, 3);

INSERT INTO titles_title_filmworkers (title_id, title_filmworkers_id) VALUES
    (1, 1),
    (2, 2),
    (3, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6),
    (6, 7),
    (7, 8),
    (1, 9),
    (2, 10),
    (3, 10);


INSERT INTO reviews(dtype, id, date, extended_description, language, nmbr_dislike, nmbr_like, platform_writerid, rating, source_platform,
summary_description, geographic_position, nick_name, spoiler_alert,type) VALUES
    ('PremiumReview', 1, TO_DATE('16/5/2018', 'DD/MM/YYYY'), 'description', 'French', 2, 3, 'Net1', 5, 'Netflix',
     'desc', 'Francia', null, null,'premReview'),
    ('PublicReview', 2, TO_DATE('26/9/2021', 'DD/MM/YYYY'), 'description', 'Spanish', 0, 0, 'Net2', 3, 'Netflix',
     'desc', 'Argentina', 'Fran', false, 'pubReview'),
    ('PremiumReview', 3, TO_DATE('7/8/2010', 'DD/MM/YYYY'), 'description', 'Germany', 0, 0, 'Amz1', 5, 'AmazonPrime',
     'desc', 'Alemania', null, true, 'premReview'),
    ('PublicReview', 4, TO_DATE('16/12/2021', 'DD/MM/YYYY'), 'description', 'Spanish', 0, 0, 'Net2', 1, 'Netflix',
     'desc', 'Argentina', 'Fran', false, 'pubReview'),
    ('PremiumReview', 5, TO_DATE('10/4/2014', 'DD/MM/YYYY'), 'description', 'Spanish', 0, 0, 'Dis1', 3, 'Disney+',
     'desc', null, null, true, 'premReview'),
    ('PublicReview', 6, TO_DATE('22/7/2019', 'DD/MM/YYYY'), 'description', 'Italian', 0, 0, 'Par2', 1, 'Paramount+',
     'desc', 'Italia', 'Fran', true, 'pubReview'),
    ('PremiumReview', 7, TO_DATE('16/11/2015', 'DD/MM/YYYY'), 'description', 'Spanish', 0, 0, 'Net1', 1, 'Netflix',
     'desc', null, null, null, 'premReview'),
    ('PublicReview', 8, TO_DATE('30/4/1962', 'DD/MM/YYYY'), 'description', 'English', 0, 0, 'Net2', 2, 'Netflix',
     'desc', 'USA', 'Fran', false, 'pubReview'),
    ('PremiumReview', 9, TO_DATE('21/11/1992', 'DD/MM/YYYY'), 'description', 'French', 0, 0, 'Net1', 5, 'Netflix',
     'desc', 'Francia', null, null, 'premReview'),
    ('PublicReview', 10, TO_DATE('16/5/1995', 'DD/MM/YYYY'), 'description', 'Spanish', 0, 0, 'Net2', 4, 'Netflix',
     'desc', 'Argentina', 'Fran', false, 'pubReview'),
    ('PremiumReview', 11, TO_DATE('6/3/1964', 'DD/MM/YYYY'), 'description', 'Spanish', 0, 0, 'Net1', 3, 'Netflix',
     'desc', null, null, null, 'premReview'),
    ('PublicReview', 12, TO_DATE('30/7/2014', 'DD/MM/YYYY'), 'description', 'French', 0, 0, 'Net2', 2, 'Netflix',
     'desc', 'Francia', 'Fran', false, 'pubReview'),
    ('PremiumReview', 13, TO_DATE('12/9/2020', 'DD/MM/YYYY'), 'description', 'Spanish', 0, 0, 'Net1', 4, 'Netflix',
     'desc', null, null, null, 'premReview'),
    ('PublicReview', 14, TO_DATE('21/12/2014', 'DD/MM/YYYY'), 'description', 'Spanish', 0, 0, 'Net2', 2, 'Netflix',
     'desc', 'Argentina', 'Fran', false, 'pubReview'),
    ('PremiumReview', 15, TO_DATE('6/5/2021', 'DD/MM/YYYY'), 'description', 'Spanish', 0, 0, 'Net1', 3, 'Netflix',
     'desc', null, null, null, 'premReview'),
    ('PublicReview', 16, TO_DATE('30/1/1990', 'DD/MM/YYYY'), 'description', 'Spanish', 0, 0, 'Net2', 2, 'DysneyPlus',
     'desc', 'Argentina', 'Fran', false, 'pubReview'),
    ('PremiumReview', 17, TO_DATE('28/2/1970', 'DD/MM/YYYY'), 'description', 'Spanish', 0, 0, 'Net1', 1, 'Netflix',
     'desc', null, null, null, 'premReview'),
    ('PublicReview', 18, TO_DATE('14/1/1974', 'DD/MM/YYYY'), 'description', 'Spanish', 0, 0, 'Net2', 1, 'Netflix',
     'desc', 'Argentina', 'Fran', false, 'pubReview'),
    ('PremiumReview', 19, TO_DATE('3/6/1999', 'DD/MM/YYYY'), 'description', 'Spanish', 0, 0, 'Net1', 3, 'Netflix',
     'desc', null, null, null, 'premReview'),
    ('PublicReview', 20, TO_DATE('20/3/1994', 'DD/MM/YYYY'), 'description', 'Spanish', 0, 0, 'Net2', 2, 'Netflix',
     'desc', 'Argentina', 'Fran', false, 'pubReview');

INSERT INTO titles_title_reviews (title_id, title_reviews_id) values
    (1,1),
    (1,2),
    (2,3),
    (2,4),
    (3,5),
    (3,6),
    (4,7),
    (4,8),
    (5,9),
    (5,10),
    (6,11),
    (6,12),
    (7,13),
    (7,14),
    (8,15),
    (8,16),
    (9,17),
    (9,18),
    (10,19),
    (10,20);
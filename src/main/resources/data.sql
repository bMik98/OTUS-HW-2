insert into AUTHORS (id, name) values (1, 'Maria');
insert into AUTHORS (id, name) values (2, 'Vasya');
insert into AUTHORS (id, name) values (3, 'Kostya');

insert into GENRES (id, name) values (1, 'SciFi');
insert into GENRES (id, name) values (2, 'Documentary');
insert into GENRES (id, name) values (3, 'Love Story');
insert into GENRES (id, name) values (4, 'Roman');
insert into GENRES (id, name) values (5, 'Detective');

insert into BOOKS (id, name) values (1, 'Interesting Book');
insert into BOOKS (id, name) values (2, 'Not Interesting Book');
insert into BOOKS (id, name) values (3, 'Just Modern Story');
insert into BOOKS (id, name) values (4, 'Yet Another Narrative');
insert into BOOKS (id, name) values (5, 'Study him to fly');
insert into BOOKS (id, name) values (6, 'Hidden story');

insert into BOOK_GENRES(genre_id, book_id) values (1, 1);
insert into BOOK_GENRES(genre_id, book_id) values (2, 2);
insert into BOOK_GENRES(genre_id, book_id) values (3, 3);
insert into BOOK_GENRES(genre_id, book_id) values (4, 4);
insert into BOOK_GENRES(genre_id, book_id) values (5, 5);
insert into BOOK_GENRES(genre_id, book_id) values (3, 5);

insert into BOOK_AUTHORS(author_id, book_id) values (1, 1);
insert into BOOK_AUTHORS(author_id, book_id) values (2, 2);
insert into BOOK_AUTHORS(author_id, book_id) values (3, 3);
insert into BOOK_AUTHORS(author_id, book_id) values (1, 4);
insert into BOOK_AUTHORS(author_id, book_id) values (2, 4);
insert into BOOK_AUTHORS(author_id, book_id) values (1, 5);
insert into BOOK_AUTHORS(author_id, book_id) values (2, 5);
insert into BOOK_AUTHORS(author_id, book_id) values (3, 5);
CREATE TABLE movies (
    id int primary key,
    title varchar(255),
    release_date date,
    duration int,
    score int
);

CREATE TABLE genres (
    id int primary key,
    name varchar(255)
);

CREATE TABLE movies_genres(
    movie_id int not null,
    genre_id int not null,
    constaint fk_movies foreign key (movie_id) references movies(id),
    constaint fk_genres foreign key (genre_id) references genres(id),
);
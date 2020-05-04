SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS sightings (
    id int PRIMARY KEY auto_increment,
    category VARCHAR,
    species VARCHAR,
    location VARCHAR,
    health VARCHAR,
    age VARCHAR,
    ranger VARCHAR
);
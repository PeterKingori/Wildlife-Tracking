CREATE DATABASE wildlife_tracker;
\c wildlife_tracker;

CREATE TABLE sightings (
    id SERIAL PRIMARY KEY,
    category VARCHAR,
    species VARCHAR,
    location VARCHAR,
    health VARCHAR,
    age VARCHAR,
    ranger VARCHAR,
    date_sighted VARCHAR
);

CREATE TABLE animals (
id SERIAL PRIMARY KEY,
species VARCHAR
);

CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;
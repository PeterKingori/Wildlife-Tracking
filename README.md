# Wildlife Tracker
## Description
This is an app that allows Rangers to track wildlife sightings in a certain area and record
 details about them.
## Technologies used
1. Java
2. Spark
3. Handlebars
4. Gradle
5. Postgresql
6. CSS & Bootstrap

## Setup/Installation
1. Install Postgres SQL to be able to create a local database on your machine.
2. Fork this repository.
3. Clone the repository to your machine.
4. Open the folder in your IDE of choice.
5. Run the command `postgres` in a terminal.
6. Create two `sql` files named `create.sql` and `drop.sql`. The `create.sql` file should contain
 the following commands
        ``` CREATE DATABASE wildlife_tracker;
            \c wildlife_tracker;
            
            CREATE TABLE IF NOT EXISTS sightings (
            id SERIAL PRIMARY KEY,
            category VARCHAR,
            species VARCHAR,
            location VARCHAR,
            health VARCHAR,
            age VARCHAR,
            ranger VARCHAR,
            date_sighted VARCHAR
            );
            
            CREATE TABLE IF NOT EXISTS animals (
            id SERIAL PRIMARY KEY,
            species VARCHAR
            );
            
            CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;```
            
 The `drop.sql` file should have the following commands
         ```DROP DATABASE wildlife_tracker_test;
            DROP DATABASE wildlife_tracker; ``` 
 
 7. Run the command `psql < create.sql` in a separate terminal window.
 
 8.Go to the IDE and navigate to the folder with the main file App.java, then compile and run the
  program on the
  terminal. Alternatively, run the program in your IDE.
  
 ## Support  and Contacts
 Peter Kingori Ndegwa. <br/> If you run into any issues or would like to make a contribution to the
  work kindly email me at kpndegwa@gmail.com
 
 ## License
 This project is free to use under the **GNU General Public License**. See the full [LICENSE](https://choosealicense.com/licenses/gpl-3.0/) for details.
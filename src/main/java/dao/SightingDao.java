package dao;

import models.Sighting;

import java.util.List;

public interface SightingDao {

    //LIST all sightings
    List<Sighting> getAll();

    //CREATE
    void add(Sighting sighting);

    //READ
    Sighting findById(int id);

    //UPDATE
    void update(int id, String category, String species, String location, String health,
                String age, String ranger);

    //DELETE
    void deleteById(int id);
    void clearAllSightings();
}

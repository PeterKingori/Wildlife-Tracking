package dao;

import models.Animal;
import models.Sighting;

import java.util.List;

public interface AnimalDao {

    //LIST
    List<Animal> getAll();

    //CREATE
    void add (Animal category);

    //READ
    Animal findById(int id);

    //UPDATE
    void update(int id, String species);

    //DELETE
    void deleteById(int id);
}
package dao;

import models.Animal;

import java.util.List;

public interface AnimalDao {

    //LIST
    List<Animal> getAll();

    //CREATE
    void add(Animal animal);

    //READ
    Animal findById(int id);

    //UPDATE
    void update(int id, String species);

    //DELETE
    void deleteById(int id);
    void clearAllAnimals();
}
package dao;

import models.Animal;
import models.Sighting;
import org.sql2o.*;
import java.util.List;

public class Sql2oAnimalDao implements AnimalDao {
    private final Sql2o sql2o;
    public Sql2oAnimalDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Animal animal) {
        String sql = "INSERT INTO animals (species) VALUES (:species)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(animal)
                    .executeUpdate()
                    .getKey();
            animal.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Animal> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM animals")
                    .executeAndFetch(Animal.class);
        }
    }

    @Override
    public Animal findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM animals WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Animal.class);
        }
    }

    @Override
    public void update(int id, String newSpecies){
        String sql = "UPDATE animals SET species = :species WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("species", newSpecies)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from animals WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllAnimals() {
        String sql = "DELETE FROM animals";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

}

package dao;

import models.Sighting;
import org.sql2o.*;
import java.util.List;
import java.sql.Timestamp;

public class Sql2oSightingDao implements SightingDao {

    private final Sql2o sql2o;

    public Sql2oSightingDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Sighting sighting) {
        String sql = "INSERT INTO sightings (category, species, location, health, age, ranger, " +
                "date_sighted) " +
                "VALUES (:category, :species, :location, :health, :age, :ranger, now())";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(sighting)
                    .executeUpdate()
                    .getKey();
            sighting.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Sighting> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM sightings")
                    .executeAndFetch(Sighting.class);
        }
    }

    @Override
    public Sighting findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM sightings WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
        }
    }

    @Override
    public void update(int id, String newCategory, String newSpecies, String newLocation,
                       String newHealth, String newAge, String newRanger){
        String sql = "UPDATE sightings SET (category, species, location, health, age, ranger) = (:category, :species, :location, :health, :age, :ranger) " +
                "WHERE " +
                "id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("category", newCategory)
                    .addParameter("species", newSpecies)
                    .addParameter("location", newLocation)
                    .addParameter("health", newHealth)
                    .addParameter("age", newAge)
                    .addParameter("ranger", newRanger)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM sightings WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllSightings() {
        String sql = "DELETE FROM sightings";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}

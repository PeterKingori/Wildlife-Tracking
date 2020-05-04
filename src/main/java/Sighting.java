import org.sql2o.*;

import java.util.ArrayList;
import java.util.List;

public class Sighting {
    private String category;
    private String species;
    private String location;
    private String health;
    private String age;
    private String ranger;
    private int id;
    private static ArrayList<Sighting> instances = new ArrayList<>();


    public Sighting(String category, String species, String location, String health, String age,
                    String ranger) {
        this.category = category;
        this.species = species;
        this.location = location;
        this.health = health;
        this.age = age;
        this.ranger = ranger;
        instances.add(this);
        this.id = instances.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sighting sighting = (Sighting) o;

        if (id != sighting.id) return false;
        if (!category.equals(sighting.category)) return false;
        if (!species.equals(sighting.species)) return false;
        if (!location.equals(sighting.location)) return false;
        if (!health.equals(sighting.health)) return false;
        if (!age.equals(sighting.age)) return false;
        return ranger.equals(sighting.ranger);
    }

    @Override
    public int hashCode() {
        int result = category.hashCode();
        result = 31 * result + species.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + health.hashCode();
        result = 31 * result + age.hashCode();
        result = 31 * result + ranger.hashCode();
        result = 31 * result + id;
        return result;
    }

    public String getCategory() {
        return category;
    }

    public String getSpecies() {
        return species;
    }

    public String getLocation() {
        return location;
    }

    public String getRanger() {
        return ranger;
    }

    public int getId() {
        return id;
    }

    public String getHealth() {
        return health;
    }

    public String getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static ArrayList<Sighting> getAll() {
        return instances;
    }

    public static void clearAll() {
        instances.clear();
    }

    public static Sighting findById(int id) {
        return instances.get(id - 1);
    }

    public void update(String category, String species, String location, String health, String age,
                       String ranger) {
        this.category = category;
        this.species = species;
        this.location = location;
        this.health = health;
        this.age = age;
        this.ranger = ranger;
    }

    public void deleteSighting() {
        instances.remove(id - 1);
    }

}

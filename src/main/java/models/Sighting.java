package models;
import java.sql.Timestamp;

public class Sighting {
    private String category;
    private String species;
    private String location;
    private String health;
    private String age;
    private String ranger;
    private String date_sighted;
    private int id;

    public Sighting(String category, String species, String location, String health, String age,
                    String ranger) {
        this.category = category;
        this.species = species;
        this.location = location;
        this.health = health;
        this.age = age;
        this.ranger = ranger;
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

    public String getDate_sighted() {
        return date_sighted;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setRanger(String ranger) {
        this.ranger = ranger;
    }

}

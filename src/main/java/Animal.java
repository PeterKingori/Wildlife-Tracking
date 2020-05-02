import org.sql2o.*;
import java.util.List;

public class Animal {
    private String category;
    private String name;
    private int sightingId;
    private int id;

    public Animal(String category, String name, int sightingId) {
        this.category = category;
        this.name = name;
        this.sightingId = sightingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Animal animal = (Animal) o;

        if (sightingId != animal.sightingId) return false;
        if (id != animal.id) return false;
        if (!category.equals(animal.category)) return false;
        return name.equals(animal.name);
    }

    @Override
    public int hashCode() {
        int result = category.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + sightingId;
        result = 31 * result + id;
        return result;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getSightingId() {
        return sightingId;
    }

    public int getId() {
        return id;
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (category, name, sightingId) VALUES (:category, " +
                    ":name, :sightingId)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("category", this.category)
                    .addParameter("name", this.name)
                    .addParameter("sightingId", this.sightingId)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Animal> all() {
        String sql = "SELECT * FROM animals";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Animal.class);
        }
    }
}


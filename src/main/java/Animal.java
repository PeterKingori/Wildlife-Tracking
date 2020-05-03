import org.sql2o.*;
import java.util.List;

public class Animal {
    private String category;
    private String name;
    private String health;
    private String age;
    private int id;

    public Animal(String category, String name, String health, String age) {
        this.category = category;
        this.name = name;
        this.health = health;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Animal animal = (Animal) o;

        if (id != animal.id) return false;
        if (!category.equals(animal.category)) return false;
        if (!name.equals(animal.name)) return false;
        if (!health.equals(animal.health)) return false;
        return age.equals(animal.age);
    }

    @Override
    public int hashCode() {
        int result = category.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + health.hashCode();
        result = 31 * result + age.hashCode();
        result = 31 * result + id;
        return result;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }


    public int getId() {
        return id;
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (category, name, health, age) VALUES " +
                    "(:category, " +
                    ":name, :health, :age)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("category", this.category)
                    .addParameter("name", this.name)
                    .addParameter("health", this.health)
                    .addParameter("age", this.age)
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

    public static Animal find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            Animal animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Animal.class);
            return animal;
        }
    }
}


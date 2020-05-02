import org.sql2o.*;
import java.util.List;

public class Sighting {
    private String category;
    private String typeOfAnimal;
    private String location;
    private String rangername;
    private int id;

    public Sighting(String category, String typeOfAnimal, String location, String rangername) {
        this.category = category;
        this.typeOfAnimal = typeOfAnimal;
        this.location = location;
        this.rangername = rangername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sighting sighting = (Sighting) o;

        if (id != sighting.id) return false;
        if (!category.equals(sighting.category)) return false;
        if (!typeOfAnimal.equals(sighting.typeOfAnimal)) return false;
        if (!location.equals(sighting.location)) return false;
        return rangername.equals(sighting.rangername);
    }

    @Override
    public int hashCode() {
        int result = category.hashCode();
        result = 31 * result + typeOfAnimal.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + rangername.hashCode();
        result = 31 * result + id;
        return result;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRangerName() {
        return rangername;
    }

    public void setRangerName(String rangername) {
        this.rangername = rangername;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeOfAnimal() {
        return typeOfAnimal;
    }

    public void setTypeOfAnimal(String typeOfAnimal) {
        this.typeOfAnimal = typeOfAnimal;
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (category, typeofanimal, location, rangername) " +
                    "VALUES " +
                    "(:category, :typeofanimal, :location, :rangername)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("category", this.category)
                    .addParameter("typeofanimal", this.typeOfAnimal)
                    .addParameter("location", this.location)
                    .addParameter("rangername", this.rangername)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Sighting> all() {
        String sql = "SELECT * FROM sightings";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Sighting.class);
        }
    }

    public static Sighting find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings where id=:id";
            Sighting sighting = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
            return sighting;
        }
    }



}

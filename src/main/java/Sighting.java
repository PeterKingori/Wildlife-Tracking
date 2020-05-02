import org.sql2o.*;
import java.util.List;

public class Sighting {
    private String animalId;
    private String location;
    private String rangername;
    private int id;

    public Sighting(String animalId, String location, String rangername) {
        this.animalId = animalId;
        this.location = location;
        this.rangername = rangername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sighting sighting = (Sighting) o;

        if (!animalId.equals(sighting.animalId)) return false;
        if (!location.equals(sighting.location)) return false;
        return rangername.equals(sighting.rangername);
    }

    @Override
    public int hashCode() {
        int result = animalId.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + rangername.hashCode();
        return result;
    }

    public String getAnimalId() {
        return animalId;
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
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

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (animalId, location, rangername) VALUES " +
                    "(:animalId, :location, :rangername)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("animalId", this.animalId)
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

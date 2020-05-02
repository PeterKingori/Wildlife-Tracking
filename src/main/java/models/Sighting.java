package models;

public class Sighting {
    private int trackingId;
    private String location;
    private String rangerName;

    public Sighting(int trackingId, String location, String rangerName) {
        this.trackingId = trackingId;
        this.location = location;
        this.rangerName = rangerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sighting sighting = (Sighting) o;

        if (trackingId != sighting.trackingId) return false;
        if (!location.equals(sighting.location)) return false;
        return rangerName.equals(sighting.rangerName);
    }

    @Override
    public int hashCode() {
        int result = trackingId;
        result = 31 * result + location.hashCode();
        result = 31 * result + rangerName.hashCode();
        return result;
    }

    public int getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(int trackingId) {
        this.trackingId = trackingId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRangerName() {
        return rangerName;
    }

    public void setRangerName(String rangerName) {
        this.rangerName = rangerName;
    }
}

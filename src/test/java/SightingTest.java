import models.Sighting;
import org.junit.*;

import java.security.PublicKey;

import static org.junit.Assert.*;

public class SightingTest {
    @Test
    public void ranger_InstantiatesCorrectly_true() {
        Sighting testSighting = setUpNewSighting();
        assertEquals(true, testSighting instanceof Sighting);
    }

    @Test
    public void getTrackingId_sightingInstantiatesWithTrackingId_1() {
        Sighting testSighting = setUpNewSighting();
        assertEquals(1, testSighting.getTrackingId());
    }

    @Test
    public void getLocation_sightingInstantiatesWithLocation_ZoneA() {
        Sighting testSighting = setUpNewSighting();
        assertEquals("Zone A", testSighting.getLocation());
    }

    @Test
    public void getRangerName_sightingInstantiatesWithRangerName_Bob() {
        Sighting testSighting = setUpNewSighting();
        assertEquals("Bob", testSighting.getRangerName());
    }

    // helper methods
    public Sighting setUpNewSighting() {
        return new Sighting(1, "Zone A", "Bob");
    }
}

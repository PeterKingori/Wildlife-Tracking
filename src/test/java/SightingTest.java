import org.junit.*;
import static org.junit.Assert.*;

public class SightingTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void sighting_InstantiatesCorrectly_true() {
        Sighting testSighting = setUpNewSighting();
        assertEquals(true, testSighting instanceof Sighting);
    }

    @Test
    public void getTrackingId_sightingInstantiatesWithAnimalId_1() {
        Sighting testSighting = setUpNewSighting();
        assertEquals("Animal", testSighting.getCategory());
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

    @Test
    public void equals_returnsTrueIfPropertiesAreTheSame_true() {
        Sighting testSighting = setUpNewSighting();
        Sighting secondSighting = setUpNewSighting();
        assertTrue(testSighting.equals(secondSighting));
    }

    @Test
    public void save_insertsObjectIntoDatabase_Sighting() {
        Sighting testSighting = setUpNewSighting();
        testSighting.save();
        assertTrue(Sighting.all().get(0).equals(testSighting));
    }

    @Test
    public void all_returnsAllInstancesOfSightings_true() {
        Sighting testSighting = setUpNewSighting();
        testSighting.save();
        Sighting secondSighting = new Sighting("Animal", "Cheetah","River side", "Jane");
        secondSighting.save();
        assertEquals(true, Sighting.all().get(0).equals(testSighting));
        assertEquals(true, Sighting.all().get(1).equals(secondSighting));
    }

    @Test
    public void save_assignsIdToObject() {
        Sighting testSighting = setUpNewSighting();
        testSighting.save();
        Sighting savedSighting = Sighting.all().get(0);
        assertEquals(testSighting.getId(), savedSighting.getId());
    }

    @Test
    public void find_returnsAnimalWithSameId_secondSighting() {
        Sighting firstSighting = setUpNewSighting();
        firstSighting.save();
        Sighting secondSighting = new Sighting("Animal", "Lion", "River side", "Bob");
        secondSighting.save();
        assertEquals(Sighting.find(secondSighting.getId()), secondSighting);
    }

    // helper methods
    public Sighting setUpNewSighting() {
        return new Sighting("Animal", "Elephant","Zone A", "Bob");
    }
}

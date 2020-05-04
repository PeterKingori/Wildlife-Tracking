import org.junit.*;
import static org.junit.Assert.*;

public class SightingTest {

    @Test
    public void sighting_InstantiatesCorrectly_true() throws Exception {
        Sighting testSighting = setUpNewSighting();
        assertEquals(true, testSighting instanceof Sighting);
        System.out.println(Sighting.getAll().size());
    }

    @Test
    public void equals_returnsTrueIfPropertiesAreTheSame_true() throws Exception {
        Sighting testSighting = setUpNewSighting();
        Sighting secondSighting = new Sighting("Animal", "Elephant","Zone A", "Ill", "Adult",
                "Bob");
        assertTrue(testSighting.equals(secondSighting));
    }

    @Test
    public void AllSightingsAreCorrectlyReturned_true() throws Exception {
        Sighting sighting = setUpNewSighting();
        Sighting secondSighting = new Sighting("Endangered", "Panda", "Riverside",
                "Healthy", "Young", "James");
        assertEquals(2, Sighting.getAll().size());
    }

    @Test
    public void findReturnsCorrectSightingWhenMoreThanOneExists() throws Exception {
        Sighting sighting = setUpNewSighting();
        Sighting secondSighting = new Sighting("Endangered", "Panda", "Riverside",
                "Healthy", "Young", "James");
        assertEquals(2, Sighting.findById(secondSighting.getId()).getId());
    }

    @Test
    public void updateChangesToSighting() throws Exception {
        Sighting sighting = setUpNewSighting();
        String formerCategory = sighting.getCategory();
        String formerSpecies = sighting.getSpecies();
        String formerLocation = sighting.getLocation();
        String formerHealth = sighting.getHealth();
        String formerAge = sighting.getAge();
        String formerRanger = sighting.getRanger();
        int formerId = sighting.getId();
        sighting.update("Animal", "Elephant", "Zone C", "Ill", "Young", "Bob");
        assertEquals(formerId, sighting.getId());
        assertEquals(formerCategory, sighting.getCategory());
        assertEquals(formerSpecies, sighting.getSpecies());
        assertNotEquals(formerLocation, sighting.getLocation());
        assertEquals(formerHealth, sighting.getHealth());
        assertNotEquals(formerAge, sighting.getAge());
        assertEquals(formerRanger, sighting.getRanger());
    }

    @Test
    public void deleteDeletesASighting() throws Exception {
        Sighting sighting = setUpNewSighting();
        Sighting secondSighting = new Sighting("Endangered", "Panda", "Riverside",
                "Healthy", "Young", "James");
        sighting.deleteSighting();
        assertEquals(1, Sighting.getAll().size());
        assertEquals(Sighting.getAll().get(0).getId(), 2);
    }

    // helper methods
    public Sighting setUpNewSighting() {
        return new Sighting("Animal", "Elephant","Zone A", "Ill", "Adult", "Bob");
    }
}

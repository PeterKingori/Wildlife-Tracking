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

    // helper methods
    public Sighting setUpNewSighting() {
        return new Sighting("Animal", "Elephant","Zone A", "Ill", "Adult", "Bob");
    }
}

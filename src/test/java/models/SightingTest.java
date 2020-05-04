package models;

import models.Sighting;
import org.junit.*;
import static org.junit.Assert.*;

public class SightingTest {

    @Test
    public void sighting_InstantiatesCorrectly_true() throws Exception {
        Sighting testSighting = setUpNewSighting();
        assertEquals(true, testSighting instanceof Sighting);
    }

    @Test
    public void equals_returnsTrueIfPropertiesAreTheSame_true() throws Exception {
        Sighting testSighting = setUpNewSighting();
        Sighting secondSighting = new Sighting("Animal", "Elephant","Zone A", "Ill", "Adult",
                "Bob");
        assertTrue(testSighting.equals(secondSighting));
    }


    // helper methods
    public Sighting setUpNewSighting() {
        return new Sighting("Animal", "Elephant","Zone A", "Ill", "Adult", "Bob");
    }
}

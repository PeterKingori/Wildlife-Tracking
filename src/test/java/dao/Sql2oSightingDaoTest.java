package dao;

import models.Sighting;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Sql2oSightingDaoTest {
    private Sql2oSightingDao sightingDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        sightingDao = new Sql2oSightingDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingSightingSetsId() throws Exception {
        Sighting sighting = setUpNewSighting();
        int originalSightingId = sighting.getId();
        sightingDao.add(sighting);
        assertNotEquals(originalSightingId, sighting.getId());
    }

    @Test
    public void existingSightingsCanBeFoundById() throws Exception {
        Sighting sighting = setUpNewSighting();
        sightingDao.add(sighting);
        Sighting foundSighting = sightingDao.findById(sighting.getId());
        assertEquals(sighting, foundSighting);
    }

    @Test
    public void AllSightingsAreCorrectlyReturned_true() throws Exception {
        Sighting sighting = setUpNewSighting();
        Sighting secondSighting = new Sighting("Endangered", "Panda", "Riverside",
                "Healthy", "Young", "James");
        sightingDao.add(sighting);
        sightingDao.add(secondSighting);
        assertEquals(2, sightingDao.getAll().size());
    }

    @Test
    public void findReturnsCorrectSightingWhenMoreThanOneExists() throws Exception {
        Sighting sighting = setUpNewSighting();
        Sighting secondSighting = new Sighting("Endangered", "Panda", "Riverside",
                "Healthy", "Young", "James");
        sightingDao.add(sighting);
        sightingDao.add(secondSighting);
        assertEquals(2, sightingDao.findById(secondSighting.getId()).getId());
    }

    @Test
    public void noSightingsReturnsEmptyList() throws Exception {
        assertEquals(0, sightingDao.getAll().size());
    }

    @Test
    public void updateChangesToSighting() throws Exception {
        String initialCategory = "Animal";
        String initialSpecies = "Elephant";
        String initialLocation = "Tree line";
        String initialHealth = "Ill";
        String initialAge = "Adult";
        String initialRanger = "James";
        Sighting sighting = new Sighting(initialCategory, initialSpecies, initialLocation,
                initialHealth, initialAge, initialRanger);
        sightingDao.add(sighting);
        sightingDao.update(sighting.getId(),"Endangered Animal", "Cheetah", "Zone C", "Healthy",
                "Young",
                "Bob");
        Sighting updatedSighting = sightingDao.findById(sighting.getId());
        assertNotEquals(initialCategory, updatedSighting.getCategory());
        assertNotEquals(initialSpecies, updatedSighting.getSpecies());
        assertNotEquals(initialLocation, updatedSighting.getLocation());
        assertNotEquals(initialHealth, updatedSighting.getHealth());
        assertNotEquals(initialAge, updatedSighting.getAge());
        assertNotEquals(initialRanger, updatedSighting.getRanger());
    }

    @Test
    public void deleteDeletesASighting() throws Exception {
        Sighting sighting = setUpNewSighting();
        sightingDao.add(sighting);
        Sighting secondSighting = new Sighting("Endangered", "Panda", "Riverside",
                "Healthy", "Young", "James");
        sightingDao.add(secondSighting);
        sightingDao.deleteById(sighting.getId());
        assertEquals(1, sightingDao.getAll().size());
        assertEquals(sightingDao.getAll().get(0).getId(), 2);
    }



    //helper methods
    public Sighting setUpNewSighting() {
        return new Sighting("Animal", "Elephant","Zone A", "Ill", "Adult", "Bob");
    }
}
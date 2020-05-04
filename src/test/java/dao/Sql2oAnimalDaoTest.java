package dao;

import models.Animal;
import org.junit.Test;

import models.Sighting;
import models.Animal;
import org.junit.After;
import org.junit.Before;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Sql2oAnimalDaoTest {
    private static Sql2oAnimalDao animalDao;
    private static Sql2oSightingDao sightingDao;
    private static Connection conn;

    @BeforeClass
    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/wildlife_tracker_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        animalDao = new Sql2oAnimalDao(sql2o);
        sightingDao = new Sql2oSightingDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        animalDao.clearAllAnimals();
        sightingDao.clearAllSightings();
    }

    @AfterClass
    public static void shutDown() throws Exception {
        conn.close(); // close connection once after this entire test file is finished
        System.out.println("connection closed");
    }

    @Test
    public void addingAnimalSetsId() throws Exception {
        Animal animal = setUpNewAnimal();
        int originalAnimalId = animal.getId();
        animalDao.add(animal);
        assertNotEquals(originalAnimalId, animal.getId());
    }

    @Test
    public void existingAnimalsCanBeFoundById() throws Exception {
        Animal animal = setUpNewAnimal();
        animalDao.add(animal);
        Animal foundAnimal = animalDao.findById(animal.getId());
        assertEquals(animal, foundAnimal);
    }

    @Test
    public void AllAnimalsAreCorrectlyReturned_true() {
        Animal testAnimal = setUpNewAnimal();
        Animal anotherAnimal = new Animal("Rhino");
        animalDao.add(testAnimal);
        animalDao.add(anotherAnimal);
        assertEquals(2, animalDao.getAll().size());
    }

    @Test
    public void noAnimalsReturnsEmptyList() throws Exception {
        assertEquals(0, animalDao.getAll().size());
    }

    @Test
    public void updateChangesAnimalContent() throws Exception {
        String initialSpecies = "Elephant";
        Animal animal = new Animal (initialSpecies);
        animalDao.add(animal);
        animalDao.update(animal.getId(),"Cheetah");
        Animal updatedAnimal = animalDao.findById(animal.getId());
        assertNotEquals(initialSpecies, updatedAnimal.getSpecies());
    }

    @Test
    public void deleteByIdDeletesCorrectAnimal() throws Exception {
        Animal animal = setUpNewAnimal();
        animalDao.add(animal);
        animalDao.deleteById(animal.getId());
        assertEquals(0, animalDao.getAll().size());
    }

    @Test
    public void find_returnsNullWhenNoAnimalFound_null() {
        assertTrue(animalDao.findById(999) == null);
    }

    //helper methods
    public Animal setUpNewAnimal() {
        return new Animal("Elephant");
    }
}

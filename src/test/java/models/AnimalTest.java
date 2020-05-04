package models;

import models.Animal;
import org.junit.*;
import static org.junit.Assert.*;

public class AnimalTest {

    @Test
    public void animal_InstantiatesCorrectly_true() throws  Exception{
        Animal testAnimal = setUpNewAnimal();
        assertEquals(true, testAnimal instanceof Animal);
    }

    @Test
    public void getName_animalInstantiatesWithSpecies_Elephant() throws  Exception {
        Animal testAnimal = setUpNewAnimal();
        assertEquals("Elephant", testAnimal.getSpecies());
    }

    @Test
    public void equals_returnsTrueIfPropertiesAreEqualAreSame_true() throws  Exception {
        Animal testAnimal = setUpNewAnimal();
        Animal anotherAnimal = new Animal("Elephant");
        assertTrue(testAnimal.equals(anotherAnimal));
    }


    //helper methods
    public Animal setUpNewAnimal() {
        return new Animal("Elephant");
    }
}

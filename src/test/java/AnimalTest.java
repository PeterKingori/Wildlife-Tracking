import org.junit.*;
import static org.junit.Assert.*;

public class AnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void animal_InstantiatesCorrectly_true() {
        Animal testAnimal = setUpNewAnimal();
        assertEquals(true, testAnimal instanceof Animal);
    }

    @Test
    public void getName_animalInstantiatesWithName_Elephant() {
        Animal testAnimal = setUpNewAnimal();
        assertEquals("Elephant", testAnimal.getName());
    }

    @Test
    public void getSightingId_animalInstantiatesWithCategoryAndSightingId_1() {
        Animal testAnimal = setUpNewAnimal();
        assertEquals("Animal", testAnimal.getCategory());
        assertEquals(1, testAnimal.getSightingId());
    }

    @Test
    public void equals_returnsTrueIfCategoryAndNameAndSightingIdAreSame_true() {
        Animal testAnimal = setUpNewAnimal();
        Animal anotherAnimal = new Animal("Animal","Elephant", 1);
        assertTrue(testAnimal.equals(anotherAnimal));
    }

    @Test
    public void save_returnsTrueIfDescriptionsAretheSame() {
        Animal testAnimal = setUpNewAnimal();
        testAnimal.save();
        assertTrue(Animal.all().get(0).equals(testAnimal));
    }

    @Test
    public void save_assignsIdToAnimal() {
        Animal testAnimal = setUpNewAnimal();
        testAnimal.save();
        Animal savedAnimal = Animal.all().get(0);
        assertEquals(savedAnimal.getId(), testAnimal.getId());
    }

    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        Animal firstAnimal = setUpNewAnimal();
        firstAnimal.save();
        Animal secondAnimal = new Animal("Animal", "Cheetah", 1);
        secondAnimal.save();
        assertEquals(true, Animal.all().get(0).equals(firstAnimal));
        assertEquals(true, Animal.all().get(1).equals(secondAnimal));
    }
    @Test
    public void find_returnsAnimalWithSameId_secondAnimal() {
        Animal firstAnimal = setUpNewAnimal();
        firstAnimal.save();
        Animal secondAnimal = new Animal("Animal", "Cheetah", 1);
        secondAnimal.save();
        assertEquals(Animal.find(secondAnimal.getId()), secondAnimal);
    }

    @Test
    public void save_savesSightingIdIntoDB_true() {
        Sighting firstSighting = new Sighting("Animal", "Cheetah", "Zone A", "Bob");
        firstSighting.save();
        Animal testAnimal = new Animal("Animal", "Cheetah", firstSighting.getId());
        testAnimal.save();
        Animal savedAnimal = Animal.find(testAnimal.getId());
        assertEquals(savedAnimal.getSightingId(), firstSighting.getId());
    }


    //helper methods
    public Animal setUpNewAnimal() {
        return new Animal("Animal","Elephant", 1);
    }
}

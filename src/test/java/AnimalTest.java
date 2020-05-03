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
    public void equals_returnsTrueIfCategoryAndNameAndSightingIdAreSame_true() {
        Animal testAnimal = setUpNewAnimal();
        Animal anotherAnimal = new Animal("Animal","Elephant", "healthy", "young");
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
        Animal secondAnimal = new Animal("Animal","Cheetah", "healthy", "young");
        secondAnimal.save();
        assertEquals(true, Animal.all().get(0).equals(firstAnimal));
        assertEquals(true, Animal.all().get(1).equals(secondAnimal));
    }
    @Test
    public void find_returnsAnimalWithSameId_secondAnimal() {
        Animal firstAnimal = setUpNewAnimal();
        firstAnimal.save();
        Animal secondAnimal = new Animal("Animal","Cheetah", "healthy", "young");
        secondAnimal.save();
        assertEquals(Animal.find(secondAnimal.getId()), secondAnimal);
    }



    //helper methods
    public Animal setUpNewAnimal() {
        return new Animal("Animal","Elephant", "healthy", "young");
    }
}

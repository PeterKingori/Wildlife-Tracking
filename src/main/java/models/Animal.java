package models;

public class Animal {
    private String species;
    private int id;

    public Animal(String species) {
        this.species = species;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Animal animal = (Animal) o;

        if (id != animal.id) return false;
        return species.equals(animal.species);
    }

    @Override
    public int hashCode() {
        int result = species.hashCode();
        result = 31 * result + id;
        return result;
    }

    public String getSpecies() {
        return species;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


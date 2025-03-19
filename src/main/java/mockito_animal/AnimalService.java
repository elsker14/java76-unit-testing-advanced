package mockito_animal;

// utilizati dependinte externe de mapping intre DTO <-> Model/Entity (ModelMapper, MapStruct)

import java.util.List;

public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public Animal createAnimal(Animal animal) {
        if(animal.getName() == null || animal.getType() == null) {
            throw new IllegalArgumentException("Name and type are required");
        }

        return animalRepository.save(animal);
    }

    public Animal retrieveAnimalByIdAndType(int id, String type) {
        Animal animal = animalRepository.findByIdAndType(id, type);
        if(animal == null) {
            throw new AnimalNotFoundException("Animal not found with id " + id + " and type " + type);
        }

        return animal;
    }

    public List<Animal> findAllAnimals() {
        return animalRepository.findAll();
    }
}

package mockito_animal;

import java.util.List;

public interface AnimalRepository {

    Animal save(Animal animal); // poate fi void/int

    Animal findByIdAndType(int id, String type);    // poate fi Optional<Animal> -> pt mock Optional.ofNullable

    List<Animal> findAll(); // daca lista e goala, va avea size 0, iar in contextul unui request de GET va intoarce

    // CRUD - updateById, updateBy<Attribute>, deleteById, patch (partial update), ...

}

package mockito_animal;

// TDD / BDD

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/***
 * TDD = test driven development
 * BDD = behavioral driven development
 *
 * BDD in Unit Testing - Mockito = propune un approach de tipul given-when-then (Cucumber)
 * Who? What? Why?
 * User/Test data; Action; Actual Result;
 *
 * Testele cu mockuri se scriu pe baza unei documentatii business in urma unor criterii de acceptanta SAU a unor API contracts.
 */

public class AnimalRepositoryTest {

    AnimalRepository mockAnimalRepository;
    AnimalService mockAnimalService;

    @BeforeEach
    public void setup() {
        mockAnimalRepository = Mockito.mock(AnimalRepository.class);
        mockAnimalService = new AnimalService(mockAnimalRepository);
    }

    @Test
    public void testCreateAnimal() {
        // Given - mock the repository - se creeaza o copie care permite asocierea unui comportament dat la nivel de metoda
        /** setup method **/

        // When - stubbing behaviour
        Animal expectedResult = new Animal(1, "cat", "Tom");
        when(mockAnimalRepository.save(expectedResult))
                .thenReturn(expectedResult);
        Animal actualResult = mockAnimalRepository.save(new Animal(1, "cat", "Tom"));

        // Then - verify the result
        assertThat(actualResult)
                .isNotNull()
                .isEqualTo(expectedResult);
    }

    @Test
    public void testGetAnimalByIdAndType() {
        Animal expectedResult = new Animal(2, "dog", "Jerry");
        when(mockAnimalRepository.findByIdAndType(2, "dog"))
                .thenReturn(expectedResult);

        Animal actualResult = mockAnimalRepository.findByIdAndType(2, "dog");

        assertThat(actualResult)
                .isNotNull()
                .isEqualTo(expectedResult);
    }

    @Test
    public void testGetAllAnimals() {
        List<Animal> expectedResult = new ArrayList<>(
                List.of(
                        new Animal(1, "cat", "Tom"),
                        new Animal(2, "dog", "Jerry"),
                        new Animal(3, "parrot", "Chicharito")
                )
        );
        when(mockAnimalRepository.findAll()).thenReturn(expectedResult);

        List<Animal> actualResult = mockAnimalRepository.findAll();
        assertThat(actualResult)
                .isNotNull()
                .isNotEmpty()
                .hasSameSizeAs(expectedResult)
                .hasSameElementsAs(expectedResult)
                .isEqualTo(expectedResult);
    }
}

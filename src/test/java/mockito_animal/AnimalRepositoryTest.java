package mockito_animal;

// TDD / BDD

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    public void testCreateAnimal() {
        // Given - mock the repository - se creeaza o copie care permite asocierea unui comportament dat la nivel de metoda
        AnimalRepository mockAnimalRepository = Mockito.mock(AnimalRepository.class);
        AnimalService mockAnimalService = new AnimalService(mockAnimalRepository);

        // When - stubbing behaviour
        Animal expectedResult = new Animal(1, "cat", "Tom");
        Mockito.when(mockAnimalRepository.save(expectedResult))
                .thenReturn(expectedResult);
        Animal actualResult = mockAnimalRepository.save(new Animal(1, "cat", "Tom"));

        // Then - verify the result
        assertThat(actualResult)
                .isNotNull()
                .isEqualTo(expectedResult);
    }
}

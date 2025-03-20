package mockito_animal;

/*
    testGetAnimalByIdAndType_AnimalExists:
        Tests that the service retrieves an existing animal by ID and type correctly.

    testGetAnimalByIdAndType_AnimalDoesNotExist:
        Tests that the service throws a NotFoundException when the animal does not exist.

    testCreateAnimal:
        Tests that the service correctly creates a new animal.

    testGetAnimalByIdAndType_ArgumentCaptor:
        Uses ArgumentCaptor to capture and assert the arguments passed to the repository.

    testCreateAnimal_ArgumentCaptor:
        Uses ArgumentCaptor to capture and assert the animal object passed to the repository.

    testFindAllAnimals:
        Tests that the service retrieves all animals correctly.

    testCreateAnimal_NullName:
        Tests that the service throws an IllegalArgumentException when trying to create an animal with a null name.

    testGetAnimalByIdAndType_EmptyType:
        Tests that the service retrieves an animal when the type is an empty string.

    testGetAnimalByIdAndType_VerifyNoMoreInteractions:
        Tests that no further interactions occur with the repository beyond the expected method call.

    testCreateAnimal_EmptyType:
        Tests that the service correctly creates an animal with an empty type.
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class AnimalServiceTest {

    private AnimalRepository mockAnimalRepo;
    private AnimalService mockAnimalService;

    @BeforeEach
    public void setUp() {
        this.mockAnimalRepo = mock(AnimalRepository.class);
        this.mockAnimalService = new AnimalService(this.mockAnimalRepo);
    }

    @Test
    @Order(1)
    public void testGetAnimalByIdAndType_AnimalExists() {
        Animal expectedResult = new Animal(1, "cat", "Buddy");
        when(this.mockAnimalRepo.findByIdAndType(1, "cat")).thenReturn(expectedResult);

        Animal actualResult = this.mockAnimalService.retrieveAnimalByIdAndType(1, "cat");

        assertThat(actualResult).isEqualTo(expectedResult);
        verify(this.mockAnimalRepo, times(1)).findByIdAndType(1, "cat");    // exista scenarii in zona de FE cand anumite call-uri HTTTP sunt dublate
    }

    @Test
    public void testGetAnimalByIdAndType_AnimalDoesNotExist() {
        // behavior stubbing & verifications are done only to mocked objects
        when(this.mockAnimalRepo.findByIdAndType(1, "cat")).thenReturn(null);

        assertThatThrownBy(() -> this.mockAnimalService.retrieveAnimalByIdAndType(1, "cat"))
                .isInstanceOf(AnimalNotFoundException.class)
                .hasMessage("Animal not found with id 1 and type cat");

        verify(mockAnimalRepo, times(1)).findByIdAndType(1, "cat");
        verify(mockAnimalRepo, never()).findByIdAndType(2, "cat");
    }

    @Test
    public void testGetAnimalByIdAndType_ArgumentCaptor() {
        Animal expectedResult = new Animal(3, "parrot", "Titi");
        when(this.mockAnimalRepo.findByIdAndType(anyInt(), anyString()))
                .thenReturn(expectedResult);

        this.mockAnimalService.retrieveAnimalByIdAndType(3, "parrot");

        ArgumentCaptor<Integer> idCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> typeCaptor = ArgumentCaptor.forClass(String.class);
        verify(this.mockAnimalRepo).findByIdAndType(idCaptor.capture(), typeCaptor.capture());

        assertThat(idCaptor.getValue()).isEqualTo(3);
        assertThat(typeCaptor.getValue()).isEqualTo("parrot");
    }

    @Test
    public void testCreateAnimal_ArgumentCaptor() {
        Animal expectedResult = new Animal(4, "fish", "Nemo");
        when(this.mockAnimalRepo.save(any(Animal.class))).thenReturn(expectedResult);

        this.mockAnimalService.createAnimal(expectedResult);    // cand folositi ArgumentCaptor, nu mai e necesar sa creati o instanta noua de obiect pt actualResult

        ArgumentCaptor<Animal> animalCaptor = ArgumentCaptor.forClass(Animal.class);
        verify(this.mockAnimalRepo, times(1)).save(animalCaptor.capture());

        assertThat(animalCaptor.getValue()).isEqualTo(expectedResult);
    }

    @Test
    public void testCreateAnimal_NullName() {
        Animal expectedResult = new Animal(7, "rabbit", null);

        assertThatThrownBy(() -> this.mockAnimalService.createAnimal(expectedResult))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Name and type are required");

        verify(this.mockAnimalRepo, times(0)).save(any(Animal.class));
    }

    @ParameterizedTest
    @MethodSource("provideAnimalTestCases")
    public void testCreateAnimal_NullNameOrTypeAndParams(Animal testObject) {
        assertThatThrownBy(() -> this.mockAnimalService.createAnimal(testObject))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Name and type are required");

        verify(this.mockAnimalRepo, times(0)).save(any(Animal.class));
    }

    // default / private pt data providers
    private static Stream<Arguments> provideAnimalTestCases() {
        return Stream.of(
                Arguments.of(new Animal(7, "rabbit", null)),
                Arguments.of(new Animal(7, null, "RobiBobi")),
                Arguments.of(new Animal(7, null, null))
        );
    }

    @Test
    public void testGetAnimalByIdAndType_EmptyType() {
        Animal expectedResult = new Animal(8, "rhino", "Rinocel");
        when(this.mockAnimalRepo.findByIdAndType(eq(8), eq(""))).thenReturn(expectedResult);

        Animal actualResult = this.mockAnimalService.retrieveAnimalByIdAndType(8, "");

        assertThat(actualResult).isEqualTo(expectedResult);
        verify(this.mockAnimalRepo, times(1)).findByIdAndType(eq(8), eq(""));
    }

    @Test
    public void testGetAnimalByIdAndType_VerifyNoMoreInteractions() {
        when(this.mockAnimalRepo.findByIdAndType(anyInt(), anyString())).thenReturn(new Animal(8, "horse", "Horsy"));

        this.mockAnimalService.retrieveAnimalByIdAndType(8, "horse");

        // nu se mai intampla nimic

        verify(this.mockAnimalRepo, times(1)).findByIdAndType(anyInt(), anyString());
        verifyNoMoreInteractions(this.mockAnimalRepo);
    }
}

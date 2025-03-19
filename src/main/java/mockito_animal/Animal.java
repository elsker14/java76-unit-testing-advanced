package mockito_animal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data vs Getter/Setter?
 * la implementarea cu baza de date NU FOLOSITI @AllArgsConstructor, recomand @RequiredArgsConstrutor si sa puneti @NonNull
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

    private int id;
    private String type;
    private String name;
}

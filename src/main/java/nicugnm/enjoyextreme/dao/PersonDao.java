package nicugnm.enjoyextreme.dao;

import nicugnm.enjoyextreme.model.Person;
import nicugnm.enjoyextreme.model.Places;
import nicugnm.enjoyextreme.model.Valability;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    int insertPerson(UUID id, Person person);

    default int insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    int insertPlaces(UUID id, Places places);

    default int insertPlaces(Person person, Places places) {
        UUID id = person.getId();
        return insertPlaces(id, places);
    }

    int insertValability(UUID id, Valability valability);

    default int insertValability(Person person, Valability valability) {
        UUID id = person.getId();
        return insertValability(id, valability);
    }

    List<Object> selectAllPeople();

    Optional<Object> selectPersonById(UUID id);

    int deletePersonById(UUID id);

    int deletePlacesById(UUID id);

    int deletePlaces(UUID id, Places places);

    int deleteValabilityById(UUID id);

    int deleteValability(UUID id, Valability valability);

    int updatePersonById(UUID id, Person person);

    int updatePlacesById(UUID id, Places places);

    int updateValabilityById(UUID id, Valability valability);
}

package nicugnm.enjoyextreme.service;

import nicugnm.enjoyextreme.dao.PersonDao;
import nicugnm.enjoyextreme.model.Person;
import nicugnm.enjoyextreme.model.Places;
import nicugnm.enjoyextreme.model.Valability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("postgres") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person) {
        return personDao.insertPerson(person);
    }

    public int addPlaces(UUID id, Places places) {
        return personDao.insertPlaces(id, places);
    }

    public int addPlaces(Person person, Places places) {
        return addPlaces(person.getId(), places);
    }

    public int addValability(UUID id, Valability valability) {
        return personDao.insertValability(id, valability);
    }

    public int addValability(Person person, Valability valability) {
        return addValability(person.getId(), valability);
    }

    public List<Person> getAllPeople() {
        return (List<Person>) (Object) personDao.selectAllPeople();
    }

    public Optional<Person> getPersonById(UUID id) {
        return (Optional<Person>) (Object) personDao.selectPersonById(id);
    }

    public int deletePerson(UUID id) {
        return personDao.deletePersonById(id);
    }

    public int updatePerson(UUID id, Person newPerson) {
        return personDao.updatePersonById(id, newPerson);
    }

    public int updatePlaces(UUID id, Places newPlaces) {
        return personDao.updatePlacesById(id, newPlaces);
    }

    public int updateValability(UUID id, Valability newValability) {
        return personDao.updateValabilityById(id, newValability);
    }

    public int deletePersonById(UUID id) {
        return personDao.deletePersonById(id);
    }

    public int deletePlacesById(UUID id) {
        return personDao.deletePlacesById(id);
    }

    public int deletePlaces(UUID id, Places places) {
        return personDao.deletePlaces(id, places);
    }

    public int deleteValabilityById(UUID id) {
        return personDao.deleteValabilityById(id);
    }

    public int deleteValability(UUID id, Valability valability) {
        return personDao.deleteValability(id, valability);
    }
}

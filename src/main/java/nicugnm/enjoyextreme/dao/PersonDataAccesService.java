package nicugnm.enjoyextreme.dao;

import nicugnm.enjoyextreme.model.Person;
import nicugnm.enjoyextreme.model.Places;
import nicugnm.enjoyextreme.model.Valability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("postgres")
public class PersonDataAccesService implements PersonDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccesService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        String sql = "" +
                "INSERT INTO person (" +
                " id, " +
                " name, " +
                " costPerDay" +
                "VALUES (?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                id,
                person.getName(),
                person.getCostPerDay()
        );
    }

    @Override
    public int insertPerson(Person person) {
        String sql = "" +
                "INSERT INTO person (" +
                " id, " +
                " name, " +
                " costPerDay" +
                "VALUES (?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                person.getId(),
                person.getName(),
                person.getCostPerDay()
        );
    }

    @Override
    public int insertPlaces(UUID id, Places places) {
        String sql = "" +
                "INSERT INTO place (" +
                " id, " +
                " country, " +
                " region, " +
                " city" +
                "VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                id,
                places.getCountry(),
                places.getRegion(),
                places.getCity()
        );
    }

    @Override
    public int insertPlaces(Person person, Places places) {
        String sql = "" +
                "INSERT INTO place (" +
                " id, " +
                " country, " +
                " region, " +
                " city" +
                "VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                person.getId(),
                places.getCountry(),
                places.getRegion(),
                places.getCity()
        );
    }

    @Override
    public int insertValability(UUID id, Valability valability) {
        String sql = "" +
                "INSERT INTO valability (" +
                " id, " +
                " sport, " +
                " period, " +
                " costSport " +
                "VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                id,
                valability.getSport(),
                valability.getPeriod(),
                valability.getCostSport()
        );
    }

    @Override
    public int insertValability(Person person, Valability valability) {
        String sql = "" +
                "INSERT INTO valability (" +
                " id, " +
                " sport, " +
                " period, " +
                " costSport " +
                "VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                person.getId(),
                valability.getSport(),
                valability.getPeriod(),
                valability.getCostSport()
        );
    }

    @Override
    public List<Object> selectAllPeople() {
        final String sql = "SELECT id, name, costPerDay FROM person";
        final String sqlPlaces = "SELECT id, country, region, city FROM place";
        final String sqlValability = "SELECT id, sport, period, costSport FROM valability";
        return jdbcTemplate.query(sql, (resultSet, index) ->
                jdbcTemplate.query(sqlPlaces, (resultSetPlaces, indexPlaces) ->
                                jdbcTemplate.query(sqlValability, (resultSetValability, indexValability) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));

            String name = resultSet.getString("name");

            double costPerDay = resultSet.getDouble("costPerDay");

            List<Places> places = new ArrayList<>();
            while (resultSetPlaces.next()) {
                String country = resultSetPlaces.getString("country");
                String region = resultSetPlaces.getString("region");
                String city = resultSetPlaces.getString("city");
                places.add(new Places(country, region, city));
            }

            List<Valability> valability = new ArrayList<>();
            while (resultSetValability.next()) {
                String sport = resultSetValability.getString("sport");
                Date period = resultSetValability.getDate("period");
                double costSport = resultSetValability.getDouble("costSport");
                valability.add(new Valability(sport, period, places, costSport));
            }
            return new Person(id, name, valability, costPerDay);
        })));
    }

    @Override
    public Optional<Object> selectPersonById(UUID id) {
        final String sql = "SELECT id, name, costPerDay FROM person WHERE id = ?";
        final String sqlPlaces = "SELECT id, country, region, city FROM place";
        final String sqlValability = "SELECT id, sport, period, costSport FROM valability";
        Object person = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, index) ->
                jdbcTemplate.query(sqlPlaces, (resultSetPlaces, indexPlaces) ->
                        jdbcTemplate.query(sqlValability, (resultSetValability, indexValability) -> {
            String name = resultSet.getString("name");

            double costPerDay = resultSet.getDouble("costPerDay");

            List<Places> places = new ArrayList<>();
            while (resultSetPlaces.next()) {
                String country = resultSetPlaces.getString("country");
                String region = resultSetPlaces.getString("region");
                String city = resultSetPlaces.getString("city");
                places.add(new Places(country, region, city));
            }

            List<Valability> valability = new ArrayList<>();
            while (resultSetValability.next()) {
                String sport = resultSetValability.getString("sport");
                Date period = resultSetValability.getDate("period");
                double costSport = resultSetValability.getDouble("costSport");
                valability.add(new Valability(sport, period, places, costSport));
            }

            return new Person(id, name, valability, costPerDay);
        })));
        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {
        String sql = "" +
                "DELETE FROM person " +
                "WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int deletePlacesById(UUID id) {
        String sql = "" +
                "DELETE FROM place " +
                "WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int deletePlaces(UUID id, Places places) {
        String sql = "" +
                "DELETE FROM place " +
                "WHERE id = ?, country = ?, region = ?, city = ?";
        return jdbcTemplate.update(sql, id, places.getCountry(), places.getRegion(), places.getCity());
    }

    @Override
    public int deleteValabilityById(UUID id) {
        String sql = "" +
                "DELETE FROM valability " +
                "WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int deleteValability(UUID id, Valability valability) {
        String sql = "" +
                "DELETE FROM valability " +
                "WHERE id = ?, sport = ?, period = ?, costSport = ?";
        return jdbcTemplate.update(sql, id, valability.getSport(), valability.getPeriod(), valability.getCostSport());
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return selectPersonById(id)
                .map(p -> {
                    boolean existPerson = selectPersonById(id).isEmpty();
                    if (!existPerson) {
                        String sql = "UPDATE person name = ?, sport = ?, period = ?, costPerDay = ? WHERE id = ?";
                        while (person.getValability().iterator().hasNext()) {
                            String sport = person.getValability().iterator().next().getSport();
                            Date period = person.getValability().iterator().next().getPeriod();
                            jdbcTemplate.update(sql,
                                    person.getName(),
                                    sport,
                                    period,
                                    person.getCostPerDay(),
                                    id);
                        }
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

    @Override
    public int updatePlacesById(UUID id, Places places) {
        return selectPersonById(id)
                .map(p -> {
                    boolean existPerson = selectPersonById(id).isEmpty();
                    if (!existPerson) {
                        String sql = "UPDATE place country = ?, region = ?, city = ? WHERE id = ?";
                        return jdbcTemplate.update(sql,
                                places.getCountry(),
                                places.getRegion(),
                                places.getCity(),
                                id);
                    }
                    return 0;
                })
                .orElse(0);
    }

    @Override
    public int updateValabilityById(UUID id, Valability valability) {
        return selectPersonById(id)
                .map(p -> {
                    boolean existPerson = selectPersonById(id).isEmpty();
                    if (!existPerson) {
                        String sql = "UPDATE valability sport = ?, period = ?, costSport = ? WHERE id = ?";
                        return jdbcTemplate.update(sql,
                                valability.getSport(),
                                valability.getPeriod(),
                                valability.getCostSport(),
                                id);
                    }
                    return 0;
                })
                .orElse(0);
    }
}

package nicugnm.enjoyextreme.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

public class Person {

    @Getter
    private final UUID id;

    @Getter
    private final String name;

    @Getter
    @Setter
    private final List<Valability> valability;

    @Getter
    @Setter
    private final double costPerDay;

    public Person(@JsonProperty("id") UUID id,
                  @JsonProperty("name") String name,
                  List<Valability> valability,
                  @JsonProperty("cost") double costPerDay) {
        this.id = id;
        this.name = name;
        this.valability = valability;
        this.costPerDay = costPerDay;
    }
}

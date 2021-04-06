package nicugnm.enjoyextreme.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

public class Valability {

    @Getter
    @Setter
    private final String sport;

    @Getter
    @Setter
    private final Date period;

    @Getter
    @Setter
    private final List<Places> places;

    @Getter
    @Setter
    private final double costSport;

    public Valability(@JsonProperty("sport") String sport,
                      @JsonProperty("period") Date period,
                      List<Places> places,
                      @JsonProperty("costSport") double costSport) {
        this.sport = sport;
        this.period = period;
        this.places = places;
        this.costSport = costSport;
    }
}

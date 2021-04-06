package nicugnm.enjoyextreme.model;

import lombok.Getter;
import lombok.Setter;

public class Places {

    @Getter
    @Setter
    private final String country;

    @Getter
    @Setter
    private final String region;

    @Getter
    @Setter
    private final String city;

    public Places(String country, String region, String city) {
        this.country = country;
        this.region = region;
        this.city = city;
    }
}

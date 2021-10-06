package fr.abes.referentiels.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="COUNTRY_URI")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Geoname {
    @Id
    private String code;
    private String uri;
}

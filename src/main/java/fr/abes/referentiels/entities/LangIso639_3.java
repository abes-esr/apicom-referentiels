package fr.abes.referentiels.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="LANG_ISO_639_3")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LangIso639_3 {
    @Id
    private String code;
    private String label;
}

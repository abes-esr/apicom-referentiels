package fr.abes.referentiels.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="LANG_LABEL")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Langue {
    @Id
    private String code;
    private String label;
    private String label_EN;
}

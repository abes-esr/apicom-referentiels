package fr.abes.referentiels.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="CODE_ECRITURE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CodeEcriture {
    @Id
    private String code;
    private String label;
}

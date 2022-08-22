package fr.abes.referentiels.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="FNCT_MARC21")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    private String code;
    private String fnct;
    @Column(name = "relationship_fr", nullable = false)
    private String relationshipFr;
    @Column(name = "relationship_en", nullable = false)
    private String relationshipEn;
}

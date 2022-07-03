package piattaformeweb.project.progettoclinica.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "MEDICO", schema = "clinicadb")
public class Medico {

    @Id
    @Column(name = "MATRICOLA", nullable = false, length = 6)
    private String matricola;

    @Basic
    @Column(name = "NOME", length = 30)
    private String nome;

    @Basic
    @Column(name = "COGNOME", length = 30)
    private String cognome;

    /*
    @OneToMany(targetEntity = Prenotazione.class, mappedBy = "medico", cascade = CascadeType.MERGE)
    private Set<Prenotazione> prenotazioni;*/

}

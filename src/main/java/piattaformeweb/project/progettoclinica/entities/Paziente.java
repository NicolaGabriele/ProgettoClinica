package piattaformeweb.project.progettoclinica.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "PAZIENTE", schema = "clinicadb")
public class Paziente {

    @Id
    @Column(name = "CF", nullable = true, length = 16)
    private String codiceFiscale;

    @Basic
    @Column(name = "NOME", nullable = true, length = 30)
    String nome;

    @Basic
    @Column(name = "COGNOME", nullable = true, length = 30)
    String cognome;

    @Basic
    @Column(name = "DATA_NASCITA", nullable = true)
    Date dataNascita;

    /*
    @OneToMany(targetEntity = Prenotazione.class, mappedBy = "paziente", cascade = CascadeType.ALL)
    private Set<Prenotazione> prenotazioni;*/
}

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
@Table(name = "PRESTAZIONE", schema = "clinicadb")
public class Prestazione {

    @Id
    @Column(name = "ID", nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "NPOSTI")
    private int nPosti;

    @Basic
    @Column(name = "DESCRIZIONE", length = 100)
    private String descrizione;

    @Basic
    @Column(name = "IMPORTO")
    private double importo;

    @OneToMany(targetEntity = Prenotazione.class, mappedBy = "prestazione",cascade = CascadeType.ALL)
    private Set<Prenotazione> prenotazioni;

}

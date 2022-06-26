package piattaformeweb.project.progettoclinica.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "PRENOTAZIONE", schema = "clinicadb")
public class Prenotazione {

    @Id
    @Column(name = "ID", insertable = false, updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "DATA")
    private Date data;

    @ManyToOne
    @JoinColumn(name = "paziente")
    private Paziente paziente;

    @Basic
    @Column(name = "ORA")
    private Time ora;

    @ManyToOne
    @JoinColumn(name = "medico")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "prestazione")
    private Prestazione prestazione;

}

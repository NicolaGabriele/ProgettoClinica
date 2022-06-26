package piattaformeweb.project.progettoclinica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import piattaformeweb.project.progettoclinica.entities.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Repository
public interface RepositoryPrenotazioni extends JpaRepository<Prenotazione, Integer> {

    List<Prenotazione> findById(int id);
    List<Prenotazione> findByPrestazione(Prestazione prestazione);
    List<Prenotazione> findByMedico(Medico medico);
    List<Prenotazione> findByPaziente(Paziente paziente);
    List<Prenotazione> findByData(Date data);
    List<Prenotazione> findByDataAndOra(Date data, Time ora);

    @Query("select p from Prenotazione p where (p.paziente = ?1 OR p.paziente is null) and (p.prestazione = ?2 or p.prestazione is null)" +
            "and (p.medico = ?3 or p.medico is null) and (p.data = ?4 or p.data is null)")
    List<Prenotazione> advacedSearch(Paziente p, Prestazione pr, Medico m, Date d);
}

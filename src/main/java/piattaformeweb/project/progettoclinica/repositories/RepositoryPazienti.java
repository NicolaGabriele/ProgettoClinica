package piattaformeweb.project.progettoclinica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import piattaformeweb.project.progettoclinica.entities.Paziente;

import java.sql.Date;
import java.util.List;

@Repository
public interface RepositoryPazienti extends JpaRepository<Paziente,String> {

    List<Paziente> findByCodiceFiscale(String codice_fiscale);
    List<Paziente> findByNome(String nome);
    List<Paziente> findByCognome(String cognome);
    List<Paziente> findByDataNascita(Date data_nascita);

    List<Paziente> findByNomeAndCognome(String nome, String cognome);

    @Query("select p from Paziente p where (p.nome like :nome or :nome is null) and" +
            "(p.cognome like :cognome or :cognome is null) and (p.dataNascita = :data or :data is null)")
    List<Paziente> advancedSearch(String nome, String cognome, Date data);

}

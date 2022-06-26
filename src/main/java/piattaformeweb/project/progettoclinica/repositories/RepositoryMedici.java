package piattaformeweb.project.progettoclinica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import piattaformeweb.project.progettoclinica.entities.Medico;

import java.util.List;

@Repository
public interface RepositoryMedici extends JpaRepository<Medico,String> {

    List<Medico> findByMatricola(String matricola);
    List<Medico> findByNome(String nome);
    List<Medico> findByCognome(String cognome);

    List<Medico> findByNomeAndCognome(String nome, String cognome);

    @Query("select m from Medico m where (m.nome like :nome or :nome is null) and (m.cognome like :cognome or :cognome is null)")
    List<Medico> advacedSearch(String nome, String cognome);
}

package piattaformeweb.project.progettoclinica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import piattaformeweb.project.progettoclinica.entities.Prestazione;

import java.util.List;

@Repository
public interface RepositoryPrestazioni extends JpaRepository<Prestazione, Integer> {

    List<Prestazione> findById(int id);
    List<Prestazione> findByDescrizioneLike(String descrizione);

    List<Prestazione> findByImporto(double importo);

    @Query("select p from Prestazione p where (p.descrizione like :descrizione or :descrizione is null) " +
            "and (p.importo = :importo or :importo is null)")
    List<Prestazione> advacedSearch(String descrizione, Double importo);
}

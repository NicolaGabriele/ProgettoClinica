package piattaformeweb.project.progettoclinica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import piattaformeweb.project.progettoclinica.entities.Prestazione;
import piattaformeweb.project.progettoclinica.exceptions.PrestazioneNonValidaException;
import piattaformeweb.project.progettoclinica.repositories.RepositoryPrestazioni;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrestazioniService {

    @Autowired
    private RepositoryPrestazioni repositoryPrestazioni;

    @Transactional
    public Prestazione inserisciPrestazione(int nPosti, String descrizione, double importo){
        if(nPosti<0 || importo < 0)
            throw new PrestazioneNonValidaException();
        Prestazione p = new Prestazione();
        p.setDescrizione(descrizione);
        p.setImporto(importo);
        p.setNPosti(nPosti);
        repositoryPrestazioni.save(p);
        return p;
    }

    @Transactional(readOnly = true)
    public List<Prestazione> ricercaPerId(int id){
        return repositoryPrestazioni.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Prestazione> ricercaPerDescrizione(String descrizione){
        return repositoryPrestazioni.findByDescrizioneLike(descrizione);
    }

    @Transactional(readOnly = true)
    public List<Prestazione> showAll(){
        return repositoryPrestazioni.findAll();
    }

    @Transactional(readOnly = true)
    public List<Prestazione> showAllByPage(int pageNumber, int pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNumber,pageSize,Sort.by(sortBy));
        Page<Prestazione> page = repositoryPrestazioni.findAll(paging);
        if(!page.hasContent())
            return new ArrayList<Prestazione>();
        return page.getContent();
    }

    @Transactional(readOnly = true)
    public List<Prestazione> ricercaAvanzata(String descrizione, Double importo){
        return repositoryPrestazioni.advacedSearch(descrizione,importo);
    }
}

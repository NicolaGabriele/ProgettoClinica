package piattaformeweb.project.progettoclinica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import piattaformeweb.project.progettoclinica.entities.Medico;
import piattaformeweb.project.progettoclinica.exceptions.MatricolaNonValidaException;
import piattaformeweb.project.progettoclinica.exceptions.MedicoGiaEsistenteException;
import piattaformeweb.project.progettoclinica.repositories.RepositoryMedici;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private RepositoryMedici repositoryMedici;

    @Transactional
    public Medico inserisciMedico(String matricola, String nome, String cognome){
        if(!matricola.matches("\\d{6}"))
            throw new MatricolaNonValidaException();
        if(repositoryMedici.findByMatricola(matricola).size() > 0)
            throw new MedicoGiaEsistenteException();
        Medico m = new Medico();
        m.setMatricola(matricola);
        m.setNome(nome);
        m.setCognome(cognome);
        repositoryMedici.save(m);
        return m;
    }

    @Transactional(readOnly = true)
    public List<Medico> ricercaPerNome(String nome){
        return repositoryMedici.findByNome(nome);
    }

    @Transactional(readOnly = true)
    public List<Medico> ricercaPerCognome(String cognome){
        return repositoryMedici.findByCognome(cognome);
    }

    @Transactional(readOnly = true)
    public List<Medico> ricercaPerMatricola(String matricola){
        return repositoryMedici.findByMatricola(matricola);
    }

    @Transactional(readOnly = true)
    public List<Medico> ricercaAvanzata(String nome, String cognome){
        return repositoryMedici.advacedSearch(nome,cognome);
    }
}

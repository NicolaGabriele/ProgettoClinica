package piattaformeweb.project.progettoclinica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import piattaformeweb.project.progettoclinica.entities.Paziente;
import piattaformeweb.project.progettoclinica.exceptions.PazienteGiaEsistenteException;
import piattaformeweb.project.progettoclinica.repositories.RepositoryPazienti;

import java.sql.Date;
import java.util.List;

@Service
public class PazienteService {

    @Autowired
    private RepositoryPazienti repositoryPazienti;

    @Transactional
    public Paziente inserisciPaziente(String codiceFiscale, String nome, String cognome, Date dataNascita){
        if(repositoryPazienti.findByCodiceFiscale(codiceFiscale).size() > 0)
            throw new PazienteGiaEsistenteException();
        Paziente p = new Paziente();
        p.setCodiceFiscale(codiceFiscale);
        p.setNome(nome);
        p.setCognome(cognome);
        p.setDataNascita(dataNascita);
        repositoryPazienti.save(p);
        return  p;
    }

    @Transactional(readOnly = true)
    public List<Paziente> getPaziente(String codiceFiscale){
        return repositoryPazienti.findByCodiceFiscale(codiceFiscale);
    }

    @Transactional(readOnly = true)
    public List<Paziente> showAll(){
        return repositoryPazienti.findAll();
    }

    @Transactional(readOnly = true)
    public List<Paziente> ricercaPerNome(String nome){
        return repositoryPazienti.findByNome(nome);
    }

    @Transactional(readOnly = true)
    public List<Paziente> ricercaPerCognome(String cognome){
        return repositoryPazienti.findByCognome(cognome);
    }

    @Transactional(readOnly = true)
    public List<Paziente> ricercaPerDataDiNascita(Date dataNascita){
        return repositoryPazienti.findByDataNascita(dataNascita);
    }

    @Transactional(readOnly = true)
    public List<Paziente> ricercaPerNomeECognome(String nome, String cognome){
        return repositoryPazienti.findByNomeAndCognome(nome,cognome);
    }

    @Transactional(readOnly = true)
    public List<Paziente> ricercaAvanzata(@Nullable String nome, @Nullable String cognome, @Nullable Date data){
        return repositoryPazienti.advancedSearch(nome,cognome,data);
    }

}

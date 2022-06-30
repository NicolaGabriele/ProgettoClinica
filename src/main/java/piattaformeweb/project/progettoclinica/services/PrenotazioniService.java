package piattaformeweb.project.progettoclinica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import piattaformeweb.project.progettoclinica.entities.Medico;
import piattaformeweb.project.progettoclinica.entities.Paziente;
import piattaformeweb.project.progettoclinica.entities.Prenotazione;
import piattaformeweb.project.progettoclinica.entities.Prestazione;
import piattaformeweb.project.progettoclinica.exceptions.*;
import piattaformeweb.project.progettoclinica.repositories.RepositoryMedici;
import piattaformeweb.project.progettoclinica.repositories.RepositoryPrenotazioni;
import piattaformeweb.project.progettoclinica.repositories.RepositoryPrestazioni;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Service
public class PrenotazioniService {

    @Autowired
    private RepositoryPrenotazioni repositoryPrenotazioni;

    @Autowired
    private RepositoryPrestazioni repositoryPrestazioni;

    @Autowired
    private RepositoryMedici repositoryMedici;

    @Transactional
    public Prenotazione prenota(Paziente paziente, Medico medico, Prestazione prestazione, Date data, Time ora){
        //controllo se la data è valida
        Date now = new Date(System.currentTimeMillis());
        if(now.compareTo(data)>0)
            throw new DataNonValidaException();

        //controllo se la prestazione è valida
        if(repositoryPrestazioni.findById(prestazione.getId()).size() == 0)
            throw new PrestazioneNonErogabileException();

        //Controllo se il medico è valido
        if(repositoryMedici.findByMatricola(medico.getMatricola()).size() == 0)
            throw new MedicoNonValidoException();

        //Controllo che la prenotazione non sia gia stata effettuata
        if(repositoryPrenotazioni.advacedSearch(paziente,prestazione,medico,data).size()>0)
            throw new PrenotazioneGiaEsistenteException();

        if(repositoryPrenotazioni.advacedSearch(null,prestazione,null,data).size() == repositoryPrestazioni.getReferenceById(prestazione.getId()).getNPosti() )
            throw new PostiFinitiException();
        Prenotazione p = new Prenotazione();
        p.setPaziente(paziente);
        p.setData(data);
        p.setMedico(medico);
        p.setPrestazione(prestazione);
        p.setOra(ora);
        repositoryPrenotazioni.save(p);
        return p;
    }

    @Transactional(readOnly = true)
    public List<Prenotazione> ricercaPerPaziente(Paziente p){
        return repositoryPrenotazioni.findByPaziente(p);
    }

    @Transactional(readOnly = true)
    public List<Prenotazione> ricercaPerMedico(Medico m){
        return repositoryPrenotazioni.findByMedico(m);
    }

    @Transactional(readOnly = true)
    public List<Prenotazione> ricercaPerPrestazione(Prestazione p){
        return repositoryPrenotazioni.findByPrestazione(p);
    }

    @Transactional(readOnly = true)
    public List<Prenotazione> ricercaPerData(Date d){
        return repositoryPrenotazioni.findByData(d);
    }

    @Transactional(readOnly = true)
    public List<Prenotazione> ricercaPerDataEOra(Date d,Time o){
        return repositoryPrenotazioni.findByDataAndOra(d,o);
    }

    @Transactional(readOnly = true)
    public List<Prenotazione> ricercaPerId(int id){
        return repositoryPrenotazioni.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Prenotazione> ricercaAvanzata(Paziente p, Prestazione pr, Medico m, Date d){
        return repositoryPrenotazioni.advacedSearch(p,pr,m,d);
    }
}

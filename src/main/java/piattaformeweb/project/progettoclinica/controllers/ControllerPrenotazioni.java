package piattaformeweb.project.progettoclinica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import piattaformeweb.project.progettoclinica.entities.Medico;
import piattaformeweb.project.progettoclinica.entities.Paziente;
import piattaformeweb.project.progettoclinica.entities.Prenotazione;
import piattaformeweb.project.progettoclinica.entities.Prestazione;
import piattaformeweb.project.progettoclinica.exceptions.*;
import piattaformeweb.project.progettoclinica.services.PrenotazioniService;

import java.sql.Date;
import java.util.List;

@RestController
public class ControllerPrenotazioni {

    @Autowired
    private PrenotazioniService prenotazioniService;

    @PreAuthorize("hasAuthority('user')")
    @PostMapping("/addPrenotazione")
    public @ResponseBody ResponseEntity addPrenotazione(@RequestBody  Prenotazione p){
        Prenotazione nuova;
        try{
            nuova = prenotazioniService.prenota(p.getPaziente(),p.getMedico(),p.getPrestazione(),p.getData(),p.getOra());
        }catch(DataNonValidaException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"data non valida");
        }catch(PrestazioneNonErogabileException e){
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST,"La prestazione non è erogabile");
        }catch(MedicoNonValidoException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "il medico non è presente in organico");
        }catch(PrenotazioneGiaEsistenteException e){
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST,"prenotazione già presente");
        }catch(PostiFinitiException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"posti terminati per la data inserita");
        }
        return new ResponseEntity<>(nuova, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/prenotazione")
    public @ResponseBody ResponseEntity getById(@RequestParam int id){
        List<Prenotazione> l = prenotazioniService.ricercaPerId(id);
        return (l.isEmpty())?new ResponseEntity<>("nessun risultatp",HttpStatus.OK):
                new ResponseEntity<>(l.get(0),HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/prenotazioni")
    public @ResponseBody ResponseEntity advancedSearch(@Nullable @RequestBody Prenotazione p){
        List<Prenotazione> l = prenotazioniService.ricercaAvanzata(p.getPaziente(),p.getPrestazione(), p.getMedico()
                ,p.getData());
        return (l.isEmpty())?new ResponseEntity<>("nessun risultato",HttpStatus.OK):
                new ResponseEntity<>(l,HttpStatus.OK);
    }
}

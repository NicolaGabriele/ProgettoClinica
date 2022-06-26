package piattaformeweb.project.progettoclinica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import piattaformeweb.project.progettoclinica.entities.Paziente;
import piattaformeweb.project.progettoclinica.exceptions.PazienteGiaEsistenteException;
import piattaformeweb.project.progettoclinica.services.PazienteService;

import java.sql.Date;
import java.util.List;

@RestController
public class ControllerPazienti {

    @Autowired
    private PazienteService pazienteService;

    @PostMapping("/addPaziente")
    public @ResponseBody ResponseEntity addPaziente( @RequestBody Paziente p){
        //Paziente p;
        try{
            p = pazienteService.inserisciPaziente(p.getCodiceFiscale(),p.getNome(),p.getCognome(),p.getDataNascita());
        }catch(PazienteGiaEsistenteException e){
            return new ResponseEntity<>("Paziente già esistente", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(p, HttpStatus.OK);
    }


   @GetMapping("/paziente")
   public @ResponseBody ResponseEntity getByCodiceFiscale(@RequestParam String cf){
        List<Paziente> l = pazienteService.getPaziente(cf);
        if(l.isEmpty())
            return new ResponseEntity<>("nessun risultato",HttpStatus.OK);
        else
            return new ResponseEntity<>(l.get(0),HttpStatus.OK);
   }

   @GetMapping("/pazienti")
   public @ResponseBody ResponseEntity search(@RequestParam @Nullable String nome, @RequestParam @Nullable String cognome,
                                              @RequestParam @Nullable Date data){
    List<Paziente> l = pazienteService.ricercaAvanzata(nome,cognome,data);
    if(l.isEmpty())
        return new ResponseEntity<>("nessun risultato",HttpStatus.OK);
    else
        return new ResponseEntity<>(l,HttpStatus.OK);
    }
}

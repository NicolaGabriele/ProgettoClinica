package piattaformeweb.project.progettoclinica.controllers;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import piattaformeweb.project.progettoclinica.entities.Prestazione;
import piattaformeweb.project.progettoclinica.exceptions.PrestazioneNonValidaException;
import piattaformeweb.project.progettoclinica.services.PrestazioniService;

import java.util.List;

@RestController
public class ControllerPrestazioni {

    @Autowired
    private PrestazioniService prestazioniService;

    @PostMapping("/addPrestazione")
    public @ResponseBody ResponseEntity addPrestazione(@RequestBody Prestazione p){
        Prestazione p1;
        try{
            p1 = prestazioniService.inserisciPrestazione(p.getNPosti(),p.getDescrizione(),p.getImporto());
        }catch(PrestazioneNonValidaException e){
            return new ResponseEntity("prestazione non valida", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(p1, HttpStatus.OK);
    }

    @GetMapping("/prestazioni")
    public @ResponseBody ResponseEntity ricerca(@Nullable @RequestParam String descrizione, @Nullable @RequestParam Double importo){
        List<Prestazione> l = prestazioniService.ricercaAvanzata(descrizione,importo);
        return (l.isEmpty())?new ResponseEntity<>("nessun risultato",HttpStatus.OK):
                new ResponseEntity<>(l,HttpStatus.OK);
    }

    @GetMapping("/prestazione")
    public @ResponseBody ResponseEntity getById(@RequestParam @NotNull int id){
        List<Prestazione> l = prestazioniService.ricercaPerId(id);
        return (l.isEmpty())?new ResponseEntity<>("nessun risultato",HttpStatus.OK):
                new ResponseEntity<>(l.get(0),HttpStatus.OK);
    }
}

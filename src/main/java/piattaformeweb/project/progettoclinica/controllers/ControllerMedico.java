package piattaformeweb.project.progettoclinica.controllers;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import piattaformeweb.project.progettoclinica.entities.Medico;
import piattaformeweb.project.progettoclinica.exceptions.MatricolaNonValidaException;
import piattaformeweb.project.progettoclinica.exceptions.MedicoGiaEsistenteException;
import piattaformeweb.project.progettoclinica.services.MedicoService;

import java.util.List;

@RestController
public class ControllerMedico {

    @Autowired
    private MedicoService medicoService;

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/addMedico")
    public @ResponseBody ResponseEntity addMedico(@RequestBody Medico m){
        Medico res;
        try{
            res = medicoService.inserisciMedico(m.getMatricola(),m.getNome(),m.getCognome());
        }catch(MatricolaNonValidaException e){
            return new ResponseEntity<>("matricola non valida", HttpStatus.BAD_REQUEST);
        }catch(MedicoGiaEsistenteException e){
            return new ResponseEntity<>("medico già esistente", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/medici")
    public @ResponseBody ResponseEntity getMedici(@Nullable @RequestParam String nome, @Nullable @RequestParam String cognome){
        List<Medico> l = medicoService.ricercaAvanzata(nome,cognome);
        return ( l.isEmpty())?new ResponseEntity<>("nessun risultato",HttpStatus.OK):
                new ResponseEntity<>(l,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/medico")
    public @ResponseBody ResponseEntity getByMatricola(@NotNull @RequestParam String matricola){
        List<Medico> l = medicoService.ricercaPerMatricola(matricola);
        return (l.isEmpty())?new ResponseEntity<>("nessun riultato",HttpStatus.OK):
                new ResponseEntity<>(l.get(0),HttpStatus.OK);
    }
}

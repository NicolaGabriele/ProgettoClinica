package piattaformeweb.project.progettoclinica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import piattaformeweb.project.progettoclinica.entities.Paziente;
import piattaformeweb.project.progettoclinica.services.RegistrazioneUtente;

@RestController
public class ControllerRegistrazione {

    @Autowired
    RegistrazioneUtente serviceRegistrazione;

    @PostMapping("/registrazione")
    public @ResponseBody ResponseEntity registrazione(@RequestBody User u){
        String pass[] = {u.password};
        String users[] = {u.username};
        String mails[] = {u.email};
        try {
            serviceRegistrazione.registra(pass, users, mails, u.paziente);
        }catch(Exception e){
            return new ResponseEntity("errore di registrazione", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("registrazione completata", HttpStatus.OK);
    }


    private static class User{
        Paziente paziente;
        String email;
        String username;
        String password;

        public void setPaziente(Paziente paziente){
            this.paziente = paziente;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}

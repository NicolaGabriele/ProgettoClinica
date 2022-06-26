package piattaformeweb.project.progettoclinica.exceptions;

public class PazienteGiaEsistenteException extends RuntimeException{

    public PazienteGiaEsistenteException(){
        super("il paziente esiste già");
    }
}

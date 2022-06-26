package piattaformeweb.project.progettoclinica.exceptions;

public class PrenotazioneGiaEsistenteException extends RuntimeException{

    public PrenotazioneGiaEsistenteException(){
        super("la prenotazione risulta già presente");
    }
}

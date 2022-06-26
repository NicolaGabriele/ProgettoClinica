package piattaformeweb.project.progettoclinica.exceptions;

public class PrestazioneNonValidaException extends RuntimeException{

    public PrestazioneNonValidaException(){
        super("importo e/o numero di posti negativo");
    }
}

package piattaformeweb.project.progettoclinica.exceptions;


public class PrestazioneNonErogabileException extends RuntimeException{

    public PrestazioneNonErogabileException(){
        super("la prestazione inserita non è erogata");
    }
}

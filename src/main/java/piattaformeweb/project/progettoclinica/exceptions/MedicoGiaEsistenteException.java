package piattaformeweb.project.progettoclinica.exceptions;

public class MedicoGiaEsistenteException extends RuntimeException{

    public MedicoGiaEsistenteException(){
        super("medico già presente");
    }
}

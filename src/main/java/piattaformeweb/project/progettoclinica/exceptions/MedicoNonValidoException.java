package piattaformeweb.project.progettoclinica.exceptions;

public class MedicoNonValidoException extends RuntimeException{

    public MedicoNonValidoException(){
        super("medico inserito non esistente");
    }
}

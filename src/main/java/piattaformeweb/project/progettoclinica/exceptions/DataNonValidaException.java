package piattaformeweb.project.progettoclinica.exceptions;

public class DataNonValidaException extends RuntimeException{

    public DataNonValidaException(){
        super("la data inserita non è valida");
    }
}

package piattaformeweb.project.progettoclinica.exceptions;

public class MatricolaNonValidaException extends RuntimeException{

    public MatricolaNonValidaException(){
        super("matricola non numerica e/o superiore alle 6 cifre");
    }
}

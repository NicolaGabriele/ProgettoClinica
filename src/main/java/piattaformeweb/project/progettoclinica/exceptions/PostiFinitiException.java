package piattaformeweb.project.progettoclinica.exceptions;

public class PostiFinitiException extends RuntimeException{

    public PostiFinitiException(){
        super("posti finiti per la prestazione richiesta nella data indicata");
    }
}

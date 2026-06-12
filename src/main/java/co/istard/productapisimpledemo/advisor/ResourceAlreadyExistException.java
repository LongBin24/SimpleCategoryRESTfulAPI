package co.istard.productapisimpledemo.advisor;

//Exception use whet resource is already exists
public class ResourceAlreadyExistException  extends  RuntimeException{
    public  ResourceAlreadyExistException(String messag){
        super(messag);
    }
}

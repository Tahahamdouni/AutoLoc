package tn.esprit.tpautoloc.exception;

public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String resourceName, Long id) {
        super(String.format("%s non trouvé avec l'ID : %d", resourceName, id));
    }
    
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

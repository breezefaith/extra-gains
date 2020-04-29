
public class NonExistentKeyException extends RuntimeException {

    public NonExistentKeyException(String key) {
        super("Key " + key + " no exists.");
    }
}

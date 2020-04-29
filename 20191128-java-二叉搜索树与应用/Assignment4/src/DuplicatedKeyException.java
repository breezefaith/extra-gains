
public class DuplicatedKeyException extends Exception{
    String key;
    public DuplicatedKeyException(String key) {
        this.key = key;
    }
    
    public String toString() {
        return "Key " + key + " had exists.";
    }
}

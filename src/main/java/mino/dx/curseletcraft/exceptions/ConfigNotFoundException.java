package mino.dx.curseletcraft.exceptions;

public class ConfigNotFoundException extends RuntimeException {
    public ConfigNotFoundException(String key) {
        super("Config key not found or invalid: " + key);
    }
}

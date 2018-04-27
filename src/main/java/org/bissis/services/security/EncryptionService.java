package org.bissis.services.security;

/**
 * @author Markus Ullrich
 */
public interface EncryptionService {
    String encryptString(String input);
    boolean checkPassword(String plainPassword, String encryptedPassword);
}

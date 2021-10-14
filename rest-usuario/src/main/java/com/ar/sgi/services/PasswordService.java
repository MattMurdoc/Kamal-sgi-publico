package com.ar.sgi.services;

import com.ar.sgi.exceptions.ServiceLayerException;
import org.apache.commons.codec.binary.Base64;
import org.jboss.logmanager.Level;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.enterprise.context.ApplicationScoped;
import java.security.spec.KeySpec;
import java.util.logging.Logger;

@ApplicationScoped
public class PasswordService {

    private static final Logger LOGGER = Logger.getLogger(PasswordService.class.getName());
    private static final String UNICODE_FORMAT = "UTF-8";
    private static final String DESEDE_ENCRYPTION_SCHEME = "DESede";

    public String encriptar(String password) throws ServiceLayerException {
        String encryptedPassword = "";
        if(LOGGER.isLoggable(Level.DEBUG))
            LOGGER.log(Level.DEBUG,"Iniciando metodo <<encriptar>>");
        try {
            Cipher cipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
            cipher.init(Cipher.ENCRYPT_MODE, generateSecretKey());
            encryptedPassword = new String(Base64.encodeBase64(cipher.doFinal(password.getBytes(UNICODE_FORMAT))));
        } catch(Exception e) {
            LOGGER.log(Level.SEVERE,e.getLocalizedMessage(), e);
            throw new ServiceLayerException("Ha ocurrido un error al leer su contrase\u00F1a");
        }
        if(LOGGER.isLoggable(Level.DEBUG))
            LOGGER.log(Level.DEBUG,"Finalizando metodo <<encriptar>>");
        return encryptedPassword;
    }

    private SecretKey generateSecretKey() throws Exception {
        String myEncryptionKey = "KAMAL_SISTEMA_GESTION$1/";
        byte[] arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        KeySpec ks = new DESedeKeySpec(arrayBytes);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(DESEDE_ENCRYPTION_SCHEME);
        return skf.generateSecret(ks);
    }

    public static void main(String args[]) throws Exception {
        String password = "aetoigo";
        PasswordService passwordService = new PasswordService();
        System.out.println(passwordService.encriptar(password));
    }

}

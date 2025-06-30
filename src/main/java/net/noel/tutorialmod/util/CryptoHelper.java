package net.noel.tutorialmod.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class CryptoHelper {
    private static final String AES = "AES";

    public static byte[] generateKey() throws Exception  {
        KeyGenerator generator = KeyGenerator.getInstance(AES);
        generator.init(128);
        SecretKey secretKey = generator.generateKey();
        return secretKey.getEncoded();
    }

    public static void encryptFile(String path, byte[] key) throws Exception{
        byte[] content = Files.readAllBytes(Paths.get(path));
        SecretKeySpec secretKey = new SecretKeySpec(key, AES);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(content);
        Files.write(Paths.get(path + ".encrypted"), encrypted); //output to new file
    }
}

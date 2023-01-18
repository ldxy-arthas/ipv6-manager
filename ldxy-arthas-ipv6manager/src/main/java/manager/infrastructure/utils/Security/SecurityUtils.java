package manager.infrastructure.utils.Security;

import manager.infrastructure.utils.AESEncryption.AESEncryption;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-18  19:34
 * @Description: TODO
 */

public class SecurityUtils {

    public static String encrypt(String content) throws IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {

        //dd90fcd003fb4a2da600e15cff5ea3a2
        byte[] bytes = AESEncryption.encryptText(content);
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String decrypt(String content) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        byte[] bytes = Base64.getDecoder().decode(content);
        return AESEncryption.decryptText(bytes);
    }

}

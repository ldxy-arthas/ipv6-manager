package manager.system.AESUtils;

import cn.hutool.core.lang.Assert;
import manager.infrastructure.utils.AESEncryption.AES;
import manager.infrastructure.utils.AESEncryption.AESEncryption;
import manager.infrastructure.utils.UuidUtil;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-12  12:07
 * @Description: TODO
 */

public class AESTest {

    private static final SecretKey secretEncryptionKey;

    static {
        try {
            secretEncryptionKey = AESEncryption.getSecretEncryptionKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getId() {
        return UuidUtil.getUuid();
    }

    @Test
    void testDecrypt() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {

        String id = getId();
        System.out.println("明文：" + id);

        byte[] bytes = AESEncryption.encryptText(id, secretEncryptionKey);
        String string = Base64.getEncoder().encodeToString(bytes);
        System.out.println("加密：" + string);

        byte[] decode = Base64.getDecoder().decode(string);
        String s = AESEncryption.decryptText(decode, secretEncryptionKey);
        System.out.println("解密：" + s);

        Assert.equals(id, s);
    }

}

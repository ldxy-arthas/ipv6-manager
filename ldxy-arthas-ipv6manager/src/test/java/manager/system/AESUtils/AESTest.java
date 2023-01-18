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
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static manager.infrastructure.utils.Security.SecurityUtils.decrypt;
import static manager.infrastructure.utils.Security.SecurityUtils.encrypt;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-12  12:07
 * @Description: TODO
 */

public class AESTest {

    private static String getId() {
        return UuidUtil.getUuid();
    }

    @Test
    void testDecrypt() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {

        String id = getId();
        System.out.println("明文：" + id);

        byte[] bytes = AESEncryption.encryptText(id);
        String string = Base64.getEncoder().encodeToString(bytes);
        System.out.println("加密：" + string);

        byte[] decode = Base64.getDecoder().decode(string);
        String s = AESEncryption.decryptText(decode);
        System.out.println("解密：" + s);

        Assert.equals(id, s);
    }

    @Test
    void testDe() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String s = "76Hc1KzgUFjFFkpTms38fY8CBYF7yY+NXXdvUzdop4VjJSMX/jO8lEgcQncTor3ymMoBYPgsX/fpCEu8Ha8HB1kLLFOzepmv2fKb26rXlGS//F5a6aPaa/TSl8SBv/MubaM3Bkyj5ebgwVsKLflWAL3TQpErKXhIlEmy8nNI6d5mj0Nu+3rz3514dIq+zzFmMVHS";

        String decrypt = decrypt(s);
        System.out.println(decrypt);
    }

    @Test
    void testDecryptPlus() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {

        String id = getId();
        System.out.println("明文：" + id);

        String encrypt = encrypt(id);
        System.out.println("加密：" + encrypt);

        String decrypt = decrypt(encrypt);
        System.out.println("解密：" + decrypt);

        Assert.equals(id, decrypt);
    }

}

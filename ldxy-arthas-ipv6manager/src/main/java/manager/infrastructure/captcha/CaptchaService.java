package manager.infrastructure.captcha;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import manager.domain.system.model.vo.VerifyCodeVo;
import manager.infrastructure.cache.CaptchaCache;
import manager.infrastructure.utils.AESEncryption.AESEncryption;
import manager.infrastructure.utils.LoggerService;
import manager.infrastructure.utils.UuidUtil;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-11  19:24
 * @Description: TODO
 */

@Service
public class CaptchaService {

    private static final SecretKey secretEncryptionKey;

    static {
        try {
            secretEncryptionKey = AESEncryption.getSecretEncryptionKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public VerifyCodeVo getVerifyCode() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(150, 40, 6, 10);
        String codeId = encrypt(UuidUtil.getUuid());

        LoggerService.captchaServiceLogger.info("生成的验证码为：{}， 验证码id为：{}, 验证码加密值为：{}, 密钥为：{}", captcha.getCode(), codeId, codeId, secretEncryptionKey.toString());

        // 缓存
        CaptchaCache.set(codeId, captcha.getCode());
        String imageBase64 = captcha.getImageBase64();

        return VerifyCodeVo.builder()
                .codeKey(codeId)
                .base64ImgEncoding(imageBase64)
                .build();
    }
    
    public boolean checkCode(String codeId, String code) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        String cacheCode = (String) CaptchaCache.get(decrypt(codeId));

        return Objects.equals(code, cacheCode);
    }

    private static String encrypt(String codeId) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        byte[] bytes = AESEncryption.encryptText(codeId, secretEncryptionKey);
        return Base64.getEncoder().encodeToString(bytes);
    }

    private static String decrypt(String codeId) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        byte[] bytes = Base64.getDecoder().decode(codeId);
        return AESEncryption.decryptText(bytes, secretEncryptionKey);
    }

}

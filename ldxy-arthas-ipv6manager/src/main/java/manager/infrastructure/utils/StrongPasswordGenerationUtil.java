package manager.infrastructure.utils;

import java.util.Random;

/**
 * @Author: yuluo
 * @CreateTime: 2022-08-26  15:58
 * @Description: 密码生成 base64编码只包括数字，大小写英文字母
 */

public class StrongPasswordGenerationUtil {

    private StrongPasswordGenerationUtil() {
    }

    private static final char[] Number = {'1','2','3','4','5','6','7','8','9','0'};
    private static final char[] Lowercase = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private static final char[] Capitalization = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    // private static final Object[] AnyChar = { Symbol , Number , Lowercase , Capitalization };
    private static final Object[] AnyChar = { Number , Lowercase , Capitalization };

    // 随机查询，组合密码
    public static char RandomPos(){
        char[] currentChar = (char[]) AnyChar[new Random().nextInt(AnyChar.length)];
        int pos = new Random().nextInt(currentChar.length);
        int i = 0;
        for(char c : currentChar){
            if(i++ == pos){
                return c;
            }
        }
        return 0;
    }

    // 生成密码
    public static String DigitGenerate(int c){
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0 ; i < c ; i ++){
            stringBuffer.append(RandomPos());
        }
        return stringBuffer.toString();
    }


}

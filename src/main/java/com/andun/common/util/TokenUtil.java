package com.andun.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.Random;

/**
 * Author:wuxinrui
 * Date:2020-03-05  16:22
 * Description:
 */

public class TokenUtil {

    //选择加密的方式？
    private static final String THE_WAY = "MD5";
    //频率
    private static final int FREQUENCY = 2;
    /** XXTEA加密解密的密钥 */
    private static String secKey = "captcha";
    /** 验证码字符数 */
    private static int charCount = 4;

    //随机数
    private static Random rand = new Random();
    //
    private static final String[] codeBase = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D",
            "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
            "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z" };
    //加密  追溯doctor项目代码被使用场景（注册时   登录时   找回密码时）
    public static String encrypt(String crdentials) {
        return new SimpleHash(THE_WAY, crdentials, null, FREQUENCY).toString();
    }

    //加密  追溯doctor项目代码被使用场景（登录时）
    public static final String genToken() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < charCount; i++) {
            int randInt = Math.abs(rand.nextInt());
            sb.append(codeBase[randInt % codeBase.length]);
        }
        return XXTEAUtil.encrypt(String.format("%s_%d", sb.toString(),System.currentTimeMillis()), secKey);
    }




    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < charCount; i++) {
            int randInt = Math.abs(rand.nextInt());
            sb.append(codeBase[randInt % codeBase.length]);
        }

        System.out.println(sb);
        // 格林威治时间相差时间
        System.out.println(System.currentTimeMillis());
        //改编字符串 在string类型数据与date类型数据中间拼接_
        System.out.println(String.format("%s_%d", sb.toString(),System.currentTimeMillis()));

        System.out.println(XXTEAUtil.encrypt("opop", "321"));
    }
}

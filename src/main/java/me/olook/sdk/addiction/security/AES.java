package me.olook.sdk.addiction.security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.SecureRandom;

/**
 * @author Red
 */
public class AES {

    private static SecureRandom secureRandom = new SecureRandom();

    public static String gcmEncrypt(String content, String key) {
        try {
            byte[] hexStr = decodeHex(key.toCharArray());
            Cipher cipher = Cipher.getInstance("AES/GCM/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(hexStr, "AES");

            byte[] iv = new byte[12];
            secureRandom.nextBytes(iv);

            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new GCMParameterSpec(128, iv));
            byte[] encrypted = cipher.doFinal(content.getBytes());

            ByteBuffer byteBuffer = ByteBuffer.allocate(iv.length + encrypted.length);
            byteBuffer.put(iv);
            byteBuffer.put(encrypted);
            byte[] cipherMessage = byteBuffer.array();

            return Base64.encodeBase64String(cipherMessage);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] decodeHex(char[] data) throws Exception {
        int len = data.length;
        if ((len & 0x1) != 0) throw new Exception("Odd number of characters.");
        byte[] out = new byte[len >> 1];
        int i = 0;
        for (int j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f |= toDigit(data[j], j);
            j++;
            out[i] = ((byte) (f & 0xFF));
        }
        return out;
    }

    private static int toDigit(char ch, int index) throws Exception {
        int digit = Character.digit(ch, 16);
        if (digit == -1) throw new Exception("Illegal hexadecimal character " + ch + " at index " + index);
        return digit;
    }
}

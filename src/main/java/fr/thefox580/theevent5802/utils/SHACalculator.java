package fr.thefox580.theevent5802.utils;

import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;

public class SHACalculator {

    public static byte[] createSha1(URL url) throws Exception  {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        InputStream fis = url.openStream();
        int n = 0;
        byte[] buffer = new byte[8192];
        while (n != -1) {
            n = fis.read(buffer);
            if (n > 0) {
                digest.update(buffer, 0, n);
            }
        }
        fis.close();
        return digest.digest();
    }

}

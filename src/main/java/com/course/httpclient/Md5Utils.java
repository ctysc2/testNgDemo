package com.course.httpclient;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {

    public static String md5(String input) {
        String hashedPwd = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
             hashedPwd = new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException ignored) {

        }
        return hashedPwd;
    }
}

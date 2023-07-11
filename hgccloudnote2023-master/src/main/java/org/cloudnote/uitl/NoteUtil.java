package org.cloudnote.uitl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

public class NoteUtil {

    public static String createId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String md5(String str){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] input = str.getBytes();
            byte[] output = md.digest(input);
            return Base64.getEncoder().encodeToString(output);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) {
        System.out.println(createId());
        //System.out.println(md5("123"));
    }

}

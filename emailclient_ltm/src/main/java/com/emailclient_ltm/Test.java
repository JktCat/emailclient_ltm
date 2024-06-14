/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emailclient_ltm;

/**
 *
 * @author ThaiNTD
 */
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;

public class Test {

    public static void main(String[] args) {

//...
// Người gửi và chủ đề từ email
        String sender = "=?UTF-8?Q?Ng=C3=B4_Tr=E1=BA=A7n_=C4=90=E1=BB=A9c_Th=C3=A1i?= <ngotranducthai@gmail.com>";
        String subject = "Lập trình mạng";

// Giải mã tên người gửi
        try {
            InternetAddress parsedSender = new InternetAddress(sender);
            String decodedSender = MimeUtility.decodeText(parsedSender.getPersonal());
            System.out.println("Người gửi: " + decodedSender);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Chủ đề: " + subject);

    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emailclient_ltm;

/**
 *
 * @author ThaiNTD
 */
public class Email {
    private String sender;
    private String subject;
    private String message;

    public Email(String sender, String subject, String message) {
        this.sender = sender;
        this.subject = subject;
        this.message = message;
    }

    public Email() {
    }

    
    public String getSender() {
        return sender;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    
}


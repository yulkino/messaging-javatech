package com.example.messagingrabbitmq;


import java.io.Serializable;

public class Message implements Serializable {
    public long From;
    public String Message;

    public Message(long from, String message) {
        From = from;
        Message = message;
    }
}

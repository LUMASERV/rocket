package com.lumaserv.rocket.util;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.javawebstack.httpclient.HTTPClient;

import java.util.ArrayList;
import java.util.List;

public class SlackWebhook {

    private final HTTPClient client = new HTTPClient();
    private final String hook;

    public SlackWebhook(String hook){
        this.hook = hook;
    }

    public void send(Message message){
        try {
            client.post(hook, message).execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Message {
        String text;
        List<Attachment> attachments = new ArrayList<>();
        public Message attachment(Attachment attachment){
            attachments.add(attachment);
            return this;
        }
        public Message text(String text){
            this.text = text;
            return this;
        }
    }

    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Attachment {
        String title;
        String titleLink;
        public Attachment title(String title){
            this.title = title;
            return this;
        }
        public Attachment titleLink(String titleLink){
            this.titleLink = titleLink;
            return this;
        }
    }

}


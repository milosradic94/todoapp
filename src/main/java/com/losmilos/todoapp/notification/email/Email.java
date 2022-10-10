package com.losmilos.todoapp.notification.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Email {

    private String recipientEmail;
    private String body;
    private String subject;

}

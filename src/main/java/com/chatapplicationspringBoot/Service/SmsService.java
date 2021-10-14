package com.chatapplicationspringBoot.Service;

import com.chatapplicationspringBoot.Model.Entity.SMS;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;


@Service
public class SmsService {
    private String ACCOUNT_SID ="AC3fa3c0ff18fd3891dee5c43cc9b4d103";

    private String AUTH_TOKEN = "0f8aebd67673096e9dcb2dd5351d2b3c";

    private String FROM_NUMBER = "+12058594517";

    public void send(SMS sms) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(sms.getTo()), new PhoneNumber(FROM_NUMBER), sms.getMessage())
                .create();
        System.out.println("here is my id:"+message.getSid());// Unique resource ID created to manage this transaction

    }

    public void receive(MultiValueMap<String, String> smscallback) {
    }
}

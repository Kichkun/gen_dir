package com.example.kish.gendir.worker;

import com.example.kish.gendir.model.Payment;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public class JsonWorker {
    private String url = "http://hackatonrosbank-env.x7qgqvikjp.us-east-1.elasticbeanstalk.com/";

    private String token = "";

    public JsonWorker(String url){
        this.url = url;
    }

    public String login(String username, String password){
        return "";
    }

    public List<Payment> getAllPayments(){
        ObjectMapper mapper = new ObjectMapper();
        List<Payment> result = null;
        try {
            result = mapper.readValue(url + "/api/list_payment", new TypeReference<List<Payment>>(){});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

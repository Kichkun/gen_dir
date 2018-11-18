package com.example.kish.gendir.worker;

import com.example.kish.gendir.model.Payment;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonWorker implements Worker{
    private String url = "http://springboot-reportgenerator-dev.us-east-1.elasticbeanstalk.com";

    private String token = "";

    private static List<Payment> payments;
    private Thread thread = null;

    public JsonWorker(String url){

        this.url = url;

        if (payments != null)
            return;
        thread = new Thread(()->{
            try {
                ObjectMapper mapper = new ObjectMapper();
                payments = mapper.readValue(new URL(url + "/api/list_payment?token=%22%22"),
                        new TypeReference<List<Payment>>(){});
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        thread.start();
    }

    public String login(String username, String password){
        return "";
    }
    @Override
    public List<Payment> getListPayment() {
        while (payments == null);
        return payments;
    }

    @Override
    public void drop(Payment payment) {
        payments.remove(payment);
    }

    @Override
    public List<Map<String, Object>> toTable() {
        List<Map<String, Object>>results = new ArrayList<Map<String, Object>>();
        this.getListPayment().forEach(payment -> {
            Map<String, Object>map = new HashMap<String, Object>();
            map.put("Name", payment.getName());
            map.put("Date", payment.getDate().toString());
            map.put("Name_ContAgent", payment.getContrAgent().getName());
            map.put("Id_ContAgent", payment.getContrAgent().getId());
            map.put("Name_Customer", payment.getCustomer().getName());
            map.put("Id_Customer", payment.getCustomer().getId());
            map.put("Id_Bank", payment.getBank().getId());
            map.put("Type", payment.getOperationType());
            map.put("Summ", payment.getSumm());
            map.put("Status", payment.getStatus());
            results.add(map);
        });
        return results;
    }

    @Override
    public void deny(Payment payment) {
        payments.remove(payment);
    }
}

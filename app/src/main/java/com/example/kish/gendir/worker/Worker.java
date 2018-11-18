package com.example.kish.gendir.worker;

import com.example.kish.gendir.model.Payment;

import java.util.List;
import java.util.Map;

public interface Worker {

    List<Payment> getListPayment();

    void drop(Payment payment);

    List<Map<String, Object>>toTable();

    void deny(Payment payment);


}

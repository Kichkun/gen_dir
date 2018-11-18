package com.example.kish.gendir.worker;

import com.example.kish.gendir.model.Bank;
import com.example.kish.gendir.model.ContrAgent;
import com.example.kish.gendir.model.Customer;
import com.example.kish.gendir.model.Payment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvWorker implements Worker {
    private ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    private List<Customer>customers = new ArrayList<Customer>();
    private List<Bank> banks = new ArrayList<Bank>();
    private List<ContrAgent>contrAgents = new ArrayList<ContrAgent>();
    private static List<Payment>payments = new ArrayList<Payment>();

    public CsvWorker(InputStream bankIS, InputStream contAGIS, InputStream customerIS, InputStream payment){
        saveBank(bankIS);
        saveContAgent(contAGIS);
        saveCustomer(customerIS);
        savePayment(payment);
    }


    @Override
    public List<Payment> getListPayment() {
        return payments;
    }

    public void drop(Payment payment) {
        this.payments.remove(payment);
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
        this.payments.remove(payment);

    }

    public void savePayment(InputStream is){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));


            String csvLine;

            while ((csvLine = reader.readLine()) != null) {

                String[] record = csvLine.split(",");

                Payment payment = new Payment();
                payment.setId(record[0]);
                payment.setName(record[1]);
                //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm_dd:MM:yyyy", Locale.ENGLISH);
                //LocalDate localDate = LocalDate.parse(record[3], formatter);
                //payment.setDate(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));

                payment.setDate(new Date());
                ContrAgent ca = contrAgents.stream()
                        .filter(cona -> record[4].equals(cona.getId()))
                        .findAny().orElse(null);
                payment.setContrAgent(ca);
                Bank bank  = banks.stream()
                        .filter(bk->record[5].equals(bk.getId()))
                        .findAny()
                        .orElse(null);
                payment.setBank(bank);
                Customer customer = customers.stream()
                        .filter(cs->record[6].equals(cs.getId()))
                        .findAny().orElse(null);
                payment.setCustomer(customer);
                payment.setOperationType(record[7]);
                payment.setSumm(Float.parseFloat(record[9]));
                payment.setStatus(record[10]);
                payments.add(payment);

            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveContAgent(InputStream is){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));


            String csvLine;

            while ((csvLine = reader.readLine()) != null) {
                ContrAgent customer = new ContrAgent();
                String[] record =  csvLine.split(",");
                customer.setId(record[0]);
                customer.setName(record[1]);
                contrAgents.add(customer);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveCustomer(InputStream is){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));


            String csvLine;

            while ((csvLine = reader.readLine()) != null) {

                String[] record = csvLine.split(",");
                Customer customer = new Customer();
                customer.setId(record[0]);
                customer.setName(record[1]);
                customers.add(customer);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveBank(InputStream is){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));


            String csvLine;

            while ((csvLine = reader.readLine()) != null) {

                String[] record = csvLine.split(",");
                Bank bank = new Bank();
                bank.setId(record[0]);
                bank.setName(record[1]);
                banks.add(bank);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.example.kish.gendir.worker;

public class WorkerFactory {

    public static Worker getWorker(){
        return new JsonWorker("http://springboot-reportgenerator-dev.us-east-1.elasticbeanstalk.com");
    }
}

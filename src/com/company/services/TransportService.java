package com.company.services;

import com.company.classes.Car;
import com.company.classes.SynchronizedTransport;
import com.company.classes.abstracts.Model;
import com.company.classes.interfaces.ITransport;
import com.company.services.factory.AutoFactory;
import com.company.services.factory.ITransportFactory;

public class TransportService {
    private static ITransportFactory factory = new AutoFactory();

    public static double averagePrices(ITransport transport){
        double result = 0;
        double[] prices = transport.getPrices();

        for (int i = 0; i < prices.length; i++) {
            result += prices[i];
        }

        result /= prices.length;

        return result;
    }

    public static void writeTransport(ITransport transport){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Mark: " + transport.getMark() + "\n");
        stringBuilder.append(getModelsString(transport.getModels()));

        System.out.println(stringBuilder.toString());
    }

    private static String getModelsString(Model[] models){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("   Models:\n");

        for(Model model : models){
            stringBuilder.append("      Name: " + model.getName() +"\n");
            stringBuilder.append("      Price: " + model.getPrice() + "\n");
        }

        return stringBuilder.toString();
    }

    public static ITransport createInstance(String name, int size){
        return factory.createInstance(name, size);
    }

    public static void setTransportFactory(ITransportFactory factory){
        TransportService.factory = factory;
    }

    public static ITransport synchronizedTransport (ITransport transport){
        return new SynchronizedTransport(transport);
    }
}

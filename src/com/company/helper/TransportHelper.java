package com.company.helper;

import com.company.classes.abstracts.Model;
import com.company.classes.interfaces.ITransport;

import java.util.List;

public class TransportHelper {
    public static String getTransportsRowStrings(List<ITransport> transports){
        StringBuilder stringBuilder = new StringBuilder();
        for (ITransport transport: transports) {
            stringBuilder.append(getTransportRowString(transport));
        }
        return stringBuilder.toString();
    }

    public static String getTransportRowStrings(ITransport transport){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getTransportRowString(transport));
        return stringBuilder.toString();
    }

    private static String getTransportRowString(ITransport transport){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Mark: " + transport.getMark() + "; ");
        stringBuilder.append(getModelsRowString(transport.getModels()));

        return stringBuilder.toString();
    }


    private static String getModelsRowString(Model[] models){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" Models: ");

        for(Model model : models){
            stringBuilder.append(" Name: " + model.getName() + "; ");
            stringBuilder.append(" Price: " + model.getPrice() + "; ");
        }

        return stringBuilder.toString();
    }

    public static String getTransportsColumnStrings(List<ITransport> transports){
        StringBuilder stringBuilder = new StringBuilder();
        for (ITransport transport: transports) {
            stringBuilder.append(getTransportColumnString(transport));
        }
        return stringBuilder.toString();
    }

    public static String getTransportColumnStrings(ITransport transport){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getTransportColumnString(transport));
        return stringBuilder.toString();
    }


    private static String getTransportColumnString(ITransport transport){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Mark: " + transport.getMark() + "\n");
        stringBuilder.append(getModelsColumnString(transport.getModels()));
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }


    private static String getModelsColumnString(Model[] models){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("   Models:\n");

        for(Model model : models){
            stringBuilder.append("      Name: " + model.getName() +"\n");
            stringBuilder.append("      Price: " + model.getPrice() + "\n");
        }

        return stringBuilder.toString();
    }
}

package com.company.сhainOfResponsibility;

import com.company.classes.abstracts.Model;
import com.company.classes.interfaces.ITransport;
import com.company.helper.TransportHelper;

public class ColumnOutputHandler extends OutputHandler {

    public ColumnOutputHandler(){

    }

    @Override
    public void handle(){
        writeInFile();
    }

    @Override
    protected String getTransportsStrings(){
        return TransportHelper.getTransportsColumnStrings(this.transports);
    }
}

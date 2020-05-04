package com.company.сhainOfResponsibility;

import com.company.classes.abstracts.Model;
import com.company.classes.interfaces.ITransport;
import com.company.helper.TransportHelper;

public class RowOutputHandler extends OutputHandler {
    public RowOutputHandler(IOutputHandler next){
        this._next = next;
    }

    @Override
    public void handle(){
        if (this.transports.size() > 3){
            this._next.appendTransports(this.transports);
            this._next.handle();
        } else {
            writeInFile();
        }
    }

    @Override
    protected String getTransportsStrings(){
        return TransportHelper.getTransportsRowStrings(this.transports);
    }
}

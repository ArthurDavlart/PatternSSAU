package com.company.сhainOfResponsibility;

import com.company.classes.abstracts.Model;
import com.company.classes.interfaces.ITransport;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OutputHandler implements IOutputHandler {
    protected List<ITransport> transports;
    protected IOutputHandler _next;

    @Override
    public IOutputHandler next() {
        return _next;
    }

    @Override
    public void setNext(IOutputHandler handler) {
        _next = handler;
    }

    @Override
    public void handle() {
        _next.appendTransports(this.transports);
        _next.handle();
    }

    protected void writeInFile(){
        try(FileWriter writer = new FileWriter("notes3.txt", false))
        {
            writer.write(getTransportsStrings());

            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    protected String getTransportsStrings(){
        return "";
    }

    @Override
    public void appendTransport(ITransport transport){
        transports.add(transport);
    }

    @Override
    public void appendTransports(List<ITransport> transports) {
        this.transports = transports;
    }

    public OutputHandler(){
        this.transports = new ArrayList<ITransport>();
    }

}

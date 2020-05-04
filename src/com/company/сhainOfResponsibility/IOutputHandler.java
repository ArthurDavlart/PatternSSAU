package com.company.сhainOfResponsibility;

import com.company.classes.interfaces.ITransport;

import java.util.List;

public interface IOutputHandler {
    IOutputHandler next();
    void setNext(IOutputHandler handler);
    void handle();
    void appendTransport(ITransport transport);
    void appendTransports(List<ITransport> transports);
}

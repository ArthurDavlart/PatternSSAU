package com.company.classes;

import com.company.classes.abstracts.Model;
import com.company.classes.exceptions.DuplicateModelNameException;
import com.company.classes.exceptions.NoSuchModelNameException;
import com.company.classes.interfaces.ITransport;

public class SynchronizedTransport implements ITransport {
    private ITransport transport;

    public SynchronizedTransport(ITransport transport) {
        this.transport = transport;
    }

    @Override
    public synchronized String getMark() {
        return this.transport.getMark();
    }

    @Override
    public synchronized void setMark(String mark) {
        this.transport.setMark(mark);
    }

    @Override
    public synchronized Model[] getModels() {
        return this.transport.getModels();
    }

    @Override
    public synchronized String[] getModelsNames() {
        return this.transport.getModelsNames();
    }

    @Override
    public synchronized void changePrice(String modelName, double price) throws NoSuchModelNameException {
        this.transport.changePrice(modelName, price);
    }

    @Override
    public synchronized void changeNameModel(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        this.transport.changeNameModel(oldName, newName);
    }

    @Override
    public synchronized double[] getPrices() {
        return this.transport.getPrices();
    }

    @Override
    public synchronized void addModel(String modelName, double price) throws DuplicateModelNameException {
        this.transport.addModel(modelName, price);
    }

    @Override
    public synchronized void deleteModel(String modelName) throws NoSuchModelNameException {
        this.transport.deleteModel(modelName);
    }

    @Override
    public synchronized int getQuantityModels() {
        return this.transport.getQuantityModels();
    }

    @Override
    public synchronized double getPrice(String modelName) throws NoSuchModelNameException {
        return this.transport.getPrice(modelName);
    }

    @Override
    public synchronized ITransport clone() throws CloneNotSupportedException {
        return this.transport.clone();
    }
}

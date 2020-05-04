package com.company.services.factory;

import com.company.classes.Motorbike;
import com.company.classes.interfaces.ITransport;

public class MotorbikeFactory implements ITransportFactory {
    @Override
    public ITransport createInstance(String markName, int size) {
        return new Motorbike(markName, size);
    }
}

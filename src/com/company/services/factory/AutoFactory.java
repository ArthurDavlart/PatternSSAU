package com.company.services.factory;

import com.company.classes.Car;
import com.company.classes.interfaces.ITransport;

public class AutoFactory implements ITransportFactory {
    @Override
    public ITransport createInstance(String markName, int size) {
        return new Car(markName, size);
    }
}

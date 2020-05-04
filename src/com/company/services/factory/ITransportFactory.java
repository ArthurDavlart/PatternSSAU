package com.company.services.factory;

import com.company.classes.interfaces.ITransport;

public interface ITransportFactory {
    ITransport createInstance(String markName, int size);
}

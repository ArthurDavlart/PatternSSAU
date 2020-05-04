package com.company.adapters;

import java.io.IOException;

public interface IStringsAdapter {
    void write (String[] strings) throws IOException;
    void close() throws IOException;
    String read();
}

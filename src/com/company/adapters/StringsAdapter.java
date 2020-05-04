package com.company.adapters;

import java.io.*;

public class StringsAdapter implements IStringsAdapter {
    private OutputStream outputStream;

    public StringsAdapter(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public StringsAdapter() {
        this.outputStream = new ByteArrayOutputStream();
    }

    @Override
    public void write(String[] strings) throws IOException { 
        for (String str : strings) {
            this.outputStream.write(str.getBytes());
        }
    }

    @Override
    public String read() {
        StringBuilder stringBuilder = new StringBuilder();

        ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream) this.outputStream;

        byte[] array = byteArrayOutputStream.toByteArray();

        for (byte b: array) {
            stringBuilder.append((char)b);
        }

        return stringBuilder.toString();
    }

    @Override
    public void close() throws IOException {
        outputStream.close();
    }
}

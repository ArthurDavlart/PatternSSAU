package com.company.command;

import com.company.classes.interfaces.ITransport;
import com.company.helper.TransportHelper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class RowOutputCommand implements ICommand {
    @Override
    public void execute(FileOutputStream fileOutputStream, ITransport transport) {
        try {
            fileOutputStream.write(TransportHelper.getTransportRowStrings(transport).getBytes());

            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

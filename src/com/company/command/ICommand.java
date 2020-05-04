package com.company.command;

import com.company.classes.interfaces.ITransport;

import java.io.FileOutputStream;

public interface ICommand {
    void execute(FileOutputStream fileOutputStream, ITransport transport);
}

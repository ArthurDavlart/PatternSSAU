package com.company;

import com.company.classes.Car;
import com.company.classes.Motorbike;
import com.company.classes.exceptions.DuplicateModelNameException;
import com.company.classes.interfaces.ITransport;
import com.company.command.ColumnOutputCommand;
import com.company.command.RowOutputCommand;
import com.company.common.SettingsManager;
import com.company.services.TransportService;
import com.company.services.factory.AutoFactory;
import com.company.services.factory.ITransportFactory;
import com.company.services.factory.MotorbikeFactory;
import com.company.сhainOfResponsibility.ColumnOutputHandler;
import com.company.сhainOfResponsibility.OutputHandler;
import com.company.сhainOfResponsibility.RowOutputHandler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Main {
    private static SettingsManager _settingsManager;
    private static Properties _properties;
    // 0 - в работе
    // -1 завершилось с ошибкой
    // 1 завершилось без ошибок
    private static int currentProgramCondition;

    public static void main(String[] args) throws FileNotFoundException {
        currentProgramCondition = 0;

	    init();

	    while(true){
	        if (currentProgramCondition != 0) {
	            break;
            }

            System.out.println("Enter command...");
	        Scanner scanner = new Scanner(System.in);
	        String directive = scanner.next();

	        switch (directive){
                case "writeSettingsOnConsole":
                    writeSettingsOnConsole();
                    break;
                case "writeTransportTests":
                    writeTransportTests();
                    break;
                case "cloneTransportTests":
                    cloneTransportTests();
                    break;
                case "cloneCarTests":
                    cloneCarTests();
                    break;
                case "getClassTests":
                    getClassTests();
                    break;
                case "help":
                    help();
                    break;
                case "writeTransportCommandTests":
                    writeTransportCommandTests();
                    break;
                case "testAutoIterator":
                    testAutoIterator();
                    break;
                case "carMementoTest":
                    carMementoTest();
                    break;
                case "exit":
                    exit();
                    break;
                default:
                    System.out.println("I don't know this command. You can write command \"help\" and you will see all command.");
            }
        }

        System.out.println(currentProgramCondition);
    }

    private static void writeTransportTests(){
        OutputHandler outputHandler = new OutputHandler();
        outputHandler.setNext(new RowOutputHandler(new ColumnOutputHandler()));
        ITransport transport = TransportService.createInstance("mark1", 4);
        try {
            transport.addModel("model1", 10);
            transport.addModel("model2", 10);
            transport.addModel("model3", 10);
        } catch (DuplicateModelNameException e) {
            e.printStackTrace();
        }
        outputHandler.appendTransport(transport);
        outputHandler.appendTransport(transport);
        outputHandler.appendTransport(transport);
        outputHandler.appendTransport(transport);
        outputHandler.handle();
        //TransportService.writeTransport(transport);
    }

    private static void testAutoIterator(){
        Car car = new Car("mark1", 4);
        try {
            car.addModel("model1", 10);
            car.addModel("model2", 10);
            car.addModel("model3", 10);
        } catch (DuplicateModelNameException e) {
            e.printStackTrace();
        }
        Iterator iterator = car.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }
    }

    private static void writeTransportCommandTests() throws FileNotFoundException {
        Car car = new Car("mark1", 4);
        try {
            car.addModel("model1", 10);
            car.addModel("model2", 10);
            car.addModel("model3", 10);
        } catch (DuplicateModelNameException e) {
            e.printStackTrace();
        }

        car.setPrintCommand(new RowOutputCommand());
        car.print(new FileOutputStream("command.txt"));
    }

    private static void carMementoTest(){
        Car car = new Car("mark1", 4);
        try {
            car.addModel("model1", 10);
            car.addModel("model2", 10);
            car.addModel("model3", 10);
        } catch (DuplicateModelNameException e) {
            e.printStackTrace();
        }

        car.createMemento();

        TransportService.writeTransport(Car.CarMemento.getCar());
    }

    private static void cloneTransportTests(){
        ITransport transport = new Motorbike("mark1", 4);
        ITransport newTransport = null;
        try {
            transport.addModel("model1", 1);

            newTransport = transport.clone();

            newTransport.getModels()[0].setPrice(666);
        } catch (DuplicateModelNameException e) {
            e.printStackTrace();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        TransportService.writeTransport(transport);
        TransportService.writeTransport(newTransport);
    }

    public static void getClassTests(){
        System.out.println( TransportService.createInstance("mark1", 4).getClass());
        TransportService.setTransportFactory(new MotorbikeFactory());
        System.out.println(TransportService.createInstance("mark1", 4).getClass());
    }

    private static void cloneCarTests(){
        ITransport transport = new Car("mark1", 4);
        ITransport newTransport = null;
        try {
            transport.addModel("model1", 1);

            newTransport = transport.clone();

            newTransport.getModels()[0].setPrice(666);
        } catch (DuplicateModelNameException e) {
            e.printStackTrace();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        TransportService.writeTransport(transport);
        TransportService.writeTransport(newTransport);
    }

    private static void init(){
        _settingsManager = SettingsManager.getInstance();

        try {
            _properties = _settingsManager.getProperties();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
            currentProgramCondition = -1;
        }
    }

    private static void writeSettingsOnConsole(){
        String host = _properties.getProperty("db.host");
        String login = _properties.getProperty("db.login");
        String password = _properties.getProperty("db.password");

        System.out.println("HOST: " + host
                + ", LOGIN: " + login
                + ", PASSWORD: " + password);
    }

    private static void help(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("writeSettingsOnConsole - is command for write settings on console.\n");
        stringBuilder.append("exit - is command for exit program.\n");
        System.out.println(stringBuilder.toString());
    }

    private static void exit(){
        System.out.println("Thanks for you have been with us.");
        currentProgramCondition = 1;
    }
}

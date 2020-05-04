package com.company.classes.interfaces;

import com.company.classes.abstracts.Model;
import com.company.classes.exceptions.DuplicateModelNameException;
import com.company.classes.exceptions.ModelPriceOutOfBoundsException;
import com.company.classes.exceptions.NoSuchModelNameException;

public interface ITransport extends Cloneable {
    String getMark();
    void setMark(String mark);
    Model[] getModels();
    String[] getModelsNames();
    void changePrice(String modelName, double price) throws NoSuchModelNameException;
    void changeNameModel(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException;
    double[] getPrices();
    void addModel(String modelName, double price) throws DuplicateModelNameException;
    void deleteModel(String modelName) throws NoSuchModelNameException;
    int getQuantityModels();
    double getPrice(String modelName) throws NoSuchModelNameException;
    ITransport clone() throws CloneNotSupportedException;
}

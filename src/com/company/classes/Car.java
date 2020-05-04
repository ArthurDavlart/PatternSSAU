package com.company.classes;

import com.company.classes.abstracts.Model;
import com.company.classes.exceptions.DuplicateModelNameException;
import com.company.classes.exceptions.ModelPriceOutOfBoundsException;
import com.company.classes.exceptions.NoSuchModelNameException;
import com.company.classes.interfaces.ITransport;
import com.company.command.ICommand;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Car implements ITransport {
    protected static class CarModel extends Model{
        public CarModel(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public CarModel(){}

        public String toString(){
            return this.name + " " + this.price;
        }
    }

    private class AutoIterator implements Iterator{
        private Model[] carModels;
        private int quantityElements;

        AutoIterator(Model[] carModels){
            this.quantityElements = 0;
            this.carModels = carModels;
        }

        @Override
        public boolean hasNext() {
            return quantityElements < carModels.length;
        }

        @Override
        public Model next() {
            Model nextElement = carModels[quantityElements];
            quantityElements++;
            return nextElement;
        }
    }

    public Iterator iterator(){
        return new AutoIterator(getModels());
    }

    public static class CarMemento{
        private static ByteArrayOutputStream baos = new ByteArrayOutputStream();;
        private static final String separator = "&";


        public static void setCar(Car car){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(car.getMark());
            stringBuilder.append(separator);
            stringBuilder.append(car.getQuantityModels());
            stringBuilder.append(separator);
            stringBuilder.append(getModesStrings(car.getModels()));

            byte[] carBytes = stringBuilder.toString().getBytes();

            try{
                baos.write(carBytes);
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }

        private static String getModesStrings(Model[] models){
            StringBuilder stringBuilder = new StringBuilder();
            for (Model model: models) {
                stringBuilder.append(model.getName());
                stringBuilder.append(separator);
                stringBuilder.append(model.getPrice());
                stringBuilder.append(separator);
            }

            return stringBuilder.toString();
        }

        public static Car getCar(){
            Car car = new Car();
            String[] serializeFields = new String(baos.toByteArray()).split(separator);

            car.setMark(serializeFields[0]);
            int quantityModels = Integer.valueOf(serializeFields[1]);
            car.setQuantityModels(quantityModels);
            CarModel[] carModels = new CarModel[quantityModels];
            int count = 2;
            for (int i = 0; i < carModels.length; i++) {
                carModels[i] = new CarModel();
                carModels[i].setName(serializeFields[count]);
                count++;
                carModels[i].setPrice(Double.parseDouble(serializeFields[count]));
                count++;
            }

            car.setModels(carModels);
            return car;
        }
    }

    public void createMemento(){
        CarMemento.setCar(this);
        this.carMemento = new CarMemento();
    }

    public void setMemento(CarMemento carMemento){
        this.carMemento = carMemento;
    }
    private CarMemento carMemento;

    private String mark;
    private CarModel[] models;
    private int quantityModels;
    private ICommand command;

    public Car(){}

    public Car(String mark, int quantityModels) {
        this.mark = mark;
        this.models = new CarModel[quantityModels];
        this.quantityModels = 0;
    }

    public void setModels(CarModel[] models){
        this.models = models;
    }

    public void setQuantityModels(int quantityModels){
        this.quantityModels = quantityModels;
    }

    public void setPrintCommand(ICommand command){
        this.command = command;
    }

    public void print(FileOutputStream stream){
        this.command.execute(stream, this);
    }

    @Override
    public String getMark() {
        return this.mark;
    }

    @Override
    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public Model[] getModels() {
        return Arrays.copyOf(this.models, this.quantityModels);
    }

    @Override
    public String[] getModelsNames() {
        String[] result = new String[this.quantityModels];

        for (int i = 0; i < this.quantityModels; i++) {
            result[i] = this.models[i].getName();
        }

        return result;
    }

    @Override
    public void changePrice(String modelName, double price) throws  NoSuchModelNameException {
        checkCorrectPrice(price);
        int index = indexOfModel(modelName);

        if(index == -1){
            throw new NoSuchModelNameException("When trying to change price. No find model by name.");
        }

        this.models[index].setPrice(price);
    }

    @Override
    public void changeNameModel(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        int index = indexOfModel(oldName);

        if(index == -1){
            throw new NoSuchModelNameException("When trying to delete. No find model by name.");
        }

        if(indexOfModel(newName) != -1){
            throw new DuplicateModelNameException("When trying to delete. No find model by name.");
        }

        this.models[index].setName(newName);
    }

    @Override
    public double[] getPrices() {
        double[] result = new double[this.quantityModels];

        for (int i = 0; i < this.quantityModels; i++) {
            result[i] = this.models[i].getPrice();
        }

        return result;
    }

    @Override
    public void addModel(String modelName, double price) throws  DuplicateModelNameException {

        checkCorrectPrice(price);

        checkForDuplicateModelName(modelName);

        if (canAddNewModel()) {
            this.models = Arrays.copyOf(models, quantityModels * 2);
        }

        this.models[quantityModels] = new CarModel(modelName, price);
        this.quantityModels++;
    }

    private boolean canAddNewModel(){
        return this.quantityModels == this.models.length;
    }

    private void checkCorrectPrice(double price){
        if(price < 0){
            throw new ModelPriceOutOfBoundsException("Incorrect price. Price cannot be negative.");
        }
    }

    private void checkForDuplicateModelName(String modelName) throws DuplicateModelNameException {
        if(indexOfModel(modelName) != -1){
            throw new DuplicateModelNameException("Attempt to add a name that is already in models.");
        }
    }

    private int indexOfModel(String modelName){
        for (int i = 0; i < this.quantityModels; i++) {
            if (this.models[i].getName().equals(modelName)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void deleteModel(String modelName) throws NoSuchModelNameException {
        int index = indexOfModel(modelName);
        if(index == -1){
            throw new NoSuchModelNameException("When trying to delete. No find model by name.");
        }
        CarModel[] newModels = new CarModel[this.models.length];

        System.arraycopy(this.models,0, newModels, 0, index);
        System.arraycopy(this.models, index + 1, newModels, index, this.models.length - index -  1);

        this.models = newModels;
        this.quantityModels--;
    }

    @Override
    public int getQuantityModels() {
        return this.quantityModels;
    }

    @Override
    public double getPrice(String modelName) throws NoSuchModelNameException {
        int index = indexOfModel(modelName);
        if(index == -1){
            throw new NoSuchModelNameException("When trying to get price. No find model by name.");
        }

        return this.models[index].getPrice();
    }

    @Override
    public ITransport clone() throws CloneNotSupportedException {
        Car car = (Car) super.clone();
        car.models = this.cloneCarModels();
        return car;
    }

    private CarModel[] cloneCarModels(){
        CarModel[] result = new CarModel[this.models.length];
        for (int i = 0; i < this.quantityModels; i++) {
            CarModel model = this.models[i];
            result[i] = new CarModel(model.getName(), model.getPrice());
        }
        return result;
    }
}

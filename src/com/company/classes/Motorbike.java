package com.company.classes;

import com.company.classes.abstracts.Model;
import com.company.classes.exceptions.DuplicateModelNameException;
import com.company.classes.exceptions.ModelPriceOutOfBoundsException;
import com.company.classes.exceptions.NoSuchModelNameException;
import com.company.classes.interfaces.ITransport;
import com.company.instruments.customeCollection.CircularDoublyLinkedList;
import com.company.instruments.customeCollection.FindPredicate;
import com.company.instruments.customeCollection.ICircularDoublyLinkedList;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class Motorbike implements ITransport {

    private class MotorbikeModel extends Model {
        public MotorbikeModel() {
        }

        public MotorbikeModel(String name, double prises) {
            this.name = name;
            this.price = prises;
        }
    }

    private String mark;
    private ICircularDoublyLinkedList<MotorbikeModel> models;

    public Motorbike(String mark, int quantityModels) {
        this.mark = mark;
        this.models = new CircularDoublyLinkedList<>();
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
        // todo что это такое????? ПРобигаться по массиву второй раз, что бы сделать приведение типов???
        // ява ты меня убиваешь!!!!!
        Model[] models = new MotorbikeModel[this.models.size()];
        Object[] objectsModels = this.models.toArray();
        for (int i = 0; i < models.length; i++) {
            models[i] = (MotorbikeModel) objectsModels[i];
        }
        return models;
    }

    @Override
    public String[] getModelsNames() {
        String[] result = new String[this.models.size()];
        Iterator<MotorbikeModel> iterator = this.models.getIterator();
        int count = 0;
        while (iterator.hasNext()){
            result[count] = iterator.next().getName();
            count++;
        }
        return result;
    }

    @Override
    public void changePrice(String modelName, double price) throws NoSuchModelNameException {
        checkCorrectPrice(price);

        MotorbikeModel motorbikeModel = this.models.find(x -> x.getName().equals(modelName));
        // todo вынести в отдельный метод и текст вынести в отдельный класс
        if(motorbikeModel == null){
            throw new NoSuchModelNameException("When trying to change price. No find model by name.");
        }

        motorbikeModel.setPrice(price);
    }

    @Override
    public void changeNameModel(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        MotorbikeModel motorbikeModel = this.models.find(x -> x.getName().equals(oldName));

        if(motorbikeModel == null){
            throw new NoSuchModelNameException("No find model by name.");
        }

        if( this.models.find(x -> x.getName().equals(newName)) != null ){
            throw new DuplicateModelNameException("No find model by name.");
        }

        motorbikeModel.setName(newName);
    }

    @Override
    public double[] getPrices() {
        double[] result = new double[this.models.size()];
        Iterator<MotorbikeModel> iterator = this.models.getIterator();
        int count = 0;
        while (iterator.hasNext()){
            result[count] = iterator.next().getPrice();
            count++;
        }
        return result;
    }

    @Override
    public void addModel(String modelName, double price) throws DuplicateModelNameException {
        checkForDuplicateModelName(modelName);

        checkCorrectPrice(price);

        MotorbikeModel newModel = new MotorbikeModel(modelName, price);

        this.models.add(newModel);
    }

    @Override
    public void deleteModel(String modelName) throws NoSuchModelNameException {
        MotorbikeModel motorbikeModel = this.models.find(x -> x.getName().equals(modelName));
        if(motorbikeModel  == null){
            throw new NoSuchModelNameException("When trying to delete. No find model by name.");
        }

        assert this.models.remove(motorbikeModel);
    }

    @Override
    public int getQuantityModels() {
        return this.models.size();
    }

    @Override
    public double getPrice(String modelName) throws NoSuchModelNameException {

        MotorbikeModel motorbikeModel = this.models.find(x -> x.getName().equals(modelName));

        if(motorbikeModel == null){
            throw new NoSuchModelNameException("When trying to get price. No find model by name.");
        }

        return motorbikeModel.getPrice();
    }

    @Override
    public ITransport clone() throws CloneNotSupportedException {
        Motorbike motorbike = (Motorbike) super.clone();
        ICircularDoublyLinkedList<MotorbikeModel> newModelsList = new CircularDoublyLinkedList<>();

        Iterator<MotorbikeModel> iterator = this.models.getIterator();

        while (iterator.hasNext()){
            MotorbikeModel motorbikeModel = iterator.next();
            newModelsList.add(new MotorbikeModel(motorbikeModel.getName(), motorbikeModel.getPrice()));
        }

        motorbike.models = newModelsList;

        return motorbike;
    }

    //region private method

    private void checkCorrectPrice(double price){
        if(price < 0){
            throw new ModelPriceOutOfBoundsException("Incorrect price. Price cannot be negative.");
        }
    }

    private void checkForDuplicateModelName(String modelName) throws DuplicateModelNameException {
        if(this.models.contains(m -> m.getName().equals(modelName))){
            throw new DuplicateModelNameException("Attempt to add a name that is already in models.");
        }
    }

    //endregion
}

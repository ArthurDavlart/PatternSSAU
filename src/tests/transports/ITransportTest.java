package tests.transports;

import com.company.classes.Car;
import com.company.classes.Motorbike;
import com.company.classes.abstracts.Model;
import com.company.classes.exceptions.DuplicateModelNameException;
import com.company.classes.exceptions.ModelPriceOutOfBoundsException;
import com.company.classes.exceptions.NoSuchModelNameException;
import com.company.classes.interfaces.ITransport;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ITransportTest {
    private static final String markName = "mark";
    private static final int quantityModels = 2;
    private static final String transportProviderNameString = "transportProvider";

    @NotNull
    private static Stream<ITransport> transportProvider(){
        return Stream.of(
                new Car(markName, quantityModels),
                new Motorbike(markName, quantityModels) );
    }

    //region AddModel tests

    @ParameterizedTest
    @MethodSource(transportProviderNameString)
    public void ShouldQuantityModelsReturn1WhenAdd1Model(ITransport transport) throws DuplicateModelNameException {
        // ITransport transport = transports[getIndexImplTransport(repetitionInfo.getCurrentRepetition())];
        int expected = 1;

        transport.addModel("model1", 10);
        int actual = transport.getQuantityModels();

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource(transportProviderNameString)
    public void ShouldQuantityModelsReturn3WhenAdd3Model(ITransport transport) throws DuplicateModelNameException {
        int expected = 3;

        transport.addModel("model1", 10);
        transport.addModel("model2", 10);
        transport.addModel("model3", 10);
        int actual = transport.getQuantityModels();

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource(transportProviderNameString)
    public void ShouldThrowDuplicateModelNameExceptionWhenAddedNameModelIsInTransport(ITransport transport) throws DuplicateModelNameException {
        transport.addModel("model1", 10);

        assertThrows(DuplicateModelNameException.class, () -> transport.addModel("model1", 10));
    }

    @ParameterizedTest
    @MethodSource(transportProviderNameString)
    public void ShouldThrowModelPriceOutOfBoundsExceptionWhenAddModelWithNegativePrice(ITransport transport) throws DuplicateModelNameException {
        assertThrows(ModelPriceOutOfBoundsException.class, () -> transport.addModel("model1", -10));
    }

    //endregion

    //region GetPrice tests

    @ParameterizedTest
    @MethodSource(transportProviderNameString)
    public void ShouldGetPriceReturn10WhenModelIsInTransport(ITransport transport)  throws DuplicateModelNameException, NoSuchModelNameException {
        double expected = 10.0;
        String modelName = "model1";
        transport.addModel(modelName, 10);

        double actual = transport.getPrice(modelName);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource(transportProviderNameString)
    public void ShouldThrowNoSuchModelNameExceptionWhenGetPriceByNoSuchModelName(ITransport transport)  throws DuplicateModelNameException {
        transport.addModel("model1", 10);

        assertThrows(NoSuchModelNameException.class, () -> transport.getPrice("model2"));
    }

    //endregion

    //region Change price tests

    @ParameterizedTest
    @MethodSource(transportProviderNameString)
    public void ShouldGetPriceReturn5WhenChangedPriceFor5(ITransport transport)  throws DuplicateModelNameException, NoSuchModelNameException {
        double expected = 5.0;
        String modelName = "model1";
        transport.addModel(modelName, 10);

        transport.changePrice(modelName, 5);
        double actual = transport.getPrice(modelName);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource(transportProviderNameString)
    public void ShouldThrowNoSuchModelNameExceptionWhenChangePriceByNoSuchModelName(ITransport transport)  throws DuplicateModelNameException {
        transport.addModel("model1", 10);

        assertThrows(NoSuchModelNameException.class, () -> transport.changePrice("model2", 5));
    }

    @ParameterizedTest
    @MethodSource(transportProviderNameString)
    public void ShouldThrowModelPriceOutOfBoundsExceptionWhenChangedPriceISNegative(ITransport transport)  throws DuplicateModelNameException {
        transport.addModel("model1", 10);

        assertThrows(ModelPriceOutOfBoundsException.class, () -> transport.changePrice("model1", -5));
    }

    //endregion

    //region Delete model tests

    @ParameterizedTest
    @MethodSource(transportProviderNameString)
    public void ShouldReturn0WhenDeleteOneModelFromTransportWithOneModel(ITransport transport)  throws DuplicateModelNameException, NoSuchModelNameException {
        String modelName = "model1";
        int expected = 0;
        transport.addModel(modelName, 10);

        transport.deleteModel(modelName);
        int actual = transport.getQuantityModels();

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource(transportProviderNameString)
    public void ShouldThrowNoSuchModelNameExceptionWhenDeleteModelByNoSuchModelName(ITransport transport)  throws DuplicateModelNameException {
        transport.addModel("model1", 10);

        assertThrows(NoSuchModelNameException.class, () -> transport.deleteModel("model2"));
    }

    //endregion

    //region Get models tests

    @ParameterizedTest
    @MethodSource(transportProviderNameString)
    public void ShouldReturnModelsWhenDeleteFirstModel(ITransport transport)  throws DuplicateModelNameException, NoSuchModelNameException {
        String modelName = "model1";
        Model[] expected = {new TestModel("model2", 10),
                new TestModel("model3", 10)};
        transport.addModel(modelName, 10);
        transport.addModel("model2", 10);
        transport.addModel("model3", 10);
        transport.deleteModel(modelName);

        Model[] actual = transport.getModels();

        assertTrue(equalsArrays(expected, actual));
    }

    @ParameterizedTest
    @MethodSource(transportProviderNameString)
    public void ShouldReturnModelsWhenDeleteSecondModel(ITransport transport)  throws DuplicateModelNameException, NoSuchModelNameException {
        String deleteModel = "model2";
        Model[] expected = {new TestModel("model1", 10),
                new TestModel("model3", 10)};
        transport.addModel("model1", 10);
        transport.addModel("model2", 10);
        transport.addModel("model3", 10);
        transport.deleteModel(deleteModel);

        Model[] actual = transport.getModels();

        assertTrue(equalsArrays(expected, actual));
    }

    @ParameterizedTest
    @MethodSource(transportProviderNameString)
    public void ShouldReturnModelsWhenDeleteLastModel(ITransport transport)  throws DuplicateModelNameException, NoSuchModelNameException {
        String deleteModel = "model3";
        Model[] expected = {new TestModel("model1", 10),
                new TestModel("model2", 10)};
        transport.addModel("model1", 10);
        transport.addModel("model2", 10);
        transport.addModel("model3", 10);
        transport.deleteModel(deleteModel);

        Model[] actual = transport.getModels();

        assertTrue(equalsArrays(expected, actual));
    }

    //endregion

    //region Get mark tests

    @ParameterizedTest
    @MethodSource(transportProviderNameString)
    public void shouldReturnMarkFromTransport(ITransport transport) throws DuplicateModelNameException {
        String expected = "mark1";

        transport.setMark(expected);
        String actual = transport.getMark();

        assertEquals(expected, actual);
    }

    //endregion

    //region Get prices

    @ParameterizedTest
    @MethodSource(transportProviderNameString)
    public void shouldReturnModelsPrices(ITransport transport) throws DuplicateModelNameException {
        double[] expected = {10, 20};
        transport.addModel("model1", 10);
        transport.addModel("model2", 20);

        double[] actual = transport.getPrices();

        assertTrue(equalsDoubleArrays(expected, actual));
    }

    //endregion

    //region get models names tests

    @ParameterizedTest
    @MethodSource(transportProviderNameString)
    public void shouldReturnModelsNames(ITransport transport) throws DuplicateModelNameException {
        String[] expected = {"model1", "model2"};
        transport.addModel(expected[0], 10);
        transport.addModel(expected[1], 10);

        String[] actual = transport.getModelsNames();

        assertTrue(equalsArrays(expected, actual));
    }

    //endregion

    //region clone tests

    @ParameterizedTest
    @MethodSource(transportProviderNameString)
    public void shouldReturnClone(ITransport transport) throws DuplicateModelNameException, CloneNotSupportedException {
        transport.addModel("model1", 10);
        transport.addModel("model2", 10);

        ITransport actualTransport = transport.clone();

        assertTrue(transport != actualTransport);
        assertTrue(equalsTransport(transport, actualTransport));
    }

    //endregion

    @ParameterizedTest
    @MethodSource(transportProviderNameString)
    public void test() throws DuplicateModelNameException, NoSuchModelNameException {
        int[] arrays = {1,2,3};
        int[] newArrays = new int[arrays.length];
        int index = 1;
        System.arraycopy(arrays,0, newArrays, 0, index);
        System.arraycopy(arrays, index + 1, newArrays, index, arrays.length - index - 1);
        assertEquals(1, 1);
    }

    private boolean equalsTransport(ITransport t1, ITransport t2){
        Model[] modelsT1 = t1.getModels();
        Model[] modelsT2 = t2.getModels();

        if(modelsT1.length != modelsT2.length || modelsT1 == modelsT2){
            return false;
        }

        for (int i = 0; i < modelsT1.length; i++) {
            if(!equalsModels(modelsT1[i], modelsT2[i])){
                return false;
            }
        }

        return t1.getMark().equals(t2.getMark()) && equalsDoubleArrays(t1.getPrices(), t2.getPrices());
    }

    private boolean equalsModels(Model m1, Model m2){
        return m1.equals(m2);
    }

    private boolean equalsDoubleArrays(double[] d1, double[] d2){
        if (d1.length != d2.length){
            return false;
        }

        for (int i = 0; i < d1.length; i++) {
            if(d1[i] != d2[i]){
                return false;
            }
        }

        return true;
    }

    private <T> boolean equalsArrays(T[] m1, T[] m2){
        if (m1.length != m2.length){
            return false;
        }

        for (int i = 0; i < m1.length; i++) {
            if(!m1[i].equals(m2[i])){
                return false;
            }
        }

        return true;
    }

    private class TestModel extends Model{

        public TestModel(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }
}
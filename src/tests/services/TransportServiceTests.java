package tests.services;

import com.company.classes.Car;
import com.company.classes.Motorbike;
import com.company.classes.interfaces.ITransport;
import com.company.services.TransportService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransportServiceTests {
    private static final String markName = "mark";
    private static final int quantityModels = 2;
    private static final String transportClassProviderNameString = "transportClassProvider";

    private static Stream<Class<? extends ITransport>> transportClassProvider(){
        return Stream.of(
                Car.class,
                Motorbike.class);
    }

//    @ParameterizedTest
//    @MethodSource(transportClassProviderNameString)
//    public void ShouldReturn10(Class<? extends ITransport> transportClass) throws Exception {
//        TransportService.setTransportFactory(transportClass);
//        ITransport transport = TransportService.createInstance("mark1", 4);
//        transport.addModel("model1", 10);
//        transport.addModel("model2", 10);
//        transport.addModel("model3", 10);
//        double expected = 10;
//
//        double actual = TransportService.averagePrices();
//
//        assertEquals(expected, actual);
//    }

}

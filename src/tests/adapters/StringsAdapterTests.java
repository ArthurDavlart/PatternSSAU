package tests.adapters;

import com.company.adapters.StringsAdapter;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class StringsAdapterTests {
    @Test
    public void test() throws IOException {
        StringsAdapter stringsAdapter = new StringsAdapter();
        String[] strings = new String[] {"Hello,", " World"};
        String expected = "Hello, World";

        stringsAdapter.write(strings);
        String actual = stringsAdapter.read();

        assertTrue(actual.equals(expected));
    }
}

package firstTests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProvidersTests {
    @DataProvider(name = "test1")
    public Object[][] createData() {
        Object[][] objects = {
                {"Angel", (37)},
                {"Krasi", (40)},
            };
        return objects;
    }

    @Test(dataProvider = "test1")
    public void verifyData(String n1, Integer n2) {
        System.out.println(n1 + " " + n2);
    }

}

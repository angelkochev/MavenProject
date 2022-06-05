package firstTests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParametersTests {
//    @Test(groups = "parameters")
    @Parameters({ "parameter-name", "parameter-name2"})
//    public void singleParamTest(String suiteParam){
//        System.out.println("Test singleParamTest suite param is: " + suiteParam);
//    }
    @Test()
    public void simpleParametersTest(String name, String age){
        System.out.println("Hello " + name + ", you are " + age + " years old.");
        assert "37".equals(age);
    }
//    @Test(groups = "parameters")
//    @Parameters({ "suiteParam", "testParam" })
//    public void multiParamTest(String suiteParam, String testParam) {
//        System.out.println("Test multiParamTest suite param is: " + suiteParam);
//        System.out.println("Test multiParamTest param is: " + testParam);
//    }

}

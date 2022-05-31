import org.testng.annotations.Test;
import java.io.IOException;

public class ExceptionTests {

    @Test(groups = "example4", expectedExceptions = RuntimeException.class)
    public void throwExceptionExample() {
        System.out.println("Runtime Exception is triggered");
        throw new RuntimeException("Runtime Exception");
    }

    @Test(expectedExceptions = { IOException.class }, expectedExceptionsMessageRegExp = "Pass Message test")
    public void exceptionTestOne() throws Exception {
        throw new IOException("Pass Message test");
    }

    @Test(expectedExceptions = { IOException.class }, expectedExceptionsMessageRegExp = "Pass Message test")
    public void exceptionTestTwo() throws Exception {
        throw new IOException("Fail Message test");                                 //Fails
    }

    @Test(expectedExceptions = {NumberFormatException.class}, expectedExceptionsMessageRegExp = "Cannot parse string to int")
    public void exceptionParsing() throws Exception{
        String myString = new String("Hi");
        Integer.parseInt(myString);
    }
}

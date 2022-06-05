package firstTests;

import org.testng.annotations.Test;

public class PriorityTests {

    @Test(alwaysRun = true, priority = 2)
    public void sampleTestOne(){
        System.out.println("This test is first");
    }

    @Test(alwaysRun = true)
    public void sampleTestTwo(){
        System.out.println("This test is second");
    }

}

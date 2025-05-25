package e2e_Tugas2;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class retrySample  implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int maxRetryCount = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("Retrying test " + result.getName() + " for the " + retryCount + " time.");
            return true;
        }
        return false;
    }
}

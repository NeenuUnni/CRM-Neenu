package generaltests;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
public class Retry implements IRetryAnalyzer 
{
	private static final int    maxTry = 2; // number of re-try need to be executed
	private              int    count  = 0; // control the iteration

	@Override
	public boolean retry (ITestResult obj) {
		if (!obj.isSuccess ()) { 
			if (this.count < maxTry) {

				this.count++;
				return true;
			}
		}
		return false;
	}
}

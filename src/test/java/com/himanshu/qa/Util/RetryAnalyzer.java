/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.Util;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

	public class RetryAnalyzer implements IRetryAnalyzer {

		private int count = 0;
		private int maxCount = 3;

		public boolean retry(ITestResult result) {
			System.out.println("Running retry logic for  '" + result.getName()
					+ "' on class " + this.getClass().getName() + " with status "
					+ result.getStatus() + " Retrying " + count + "times");
			if (count < maxCount) {
				count++;
				return true;
			}
			return false;
		}
	}



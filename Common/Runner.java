package Common;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class Runner {

	 TestNG testNG = new TestNG();
	 List<String> suites = new ArrayList();
	 String crossbrowserPathXML = "Crossbrowser.xml";
	 
	 public void runner() {
		 suites.add(crossbrowserPathXML);
		 testNG.setTestSuites(suites);
		 testNG.run();
	 }
	
}

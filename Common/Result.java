package Common;

import java.util.List;

public class Result {
	
	

	String nameOfMethod;
	boolean testResult;
	String description;
	String browserName;
	List<String> inputParameters;
	
	
	
	public Result(String browserName, String nameOfMethod, boolean testResult , String description) {
		super();
		this.nameOfMethod = nameOfMethod;
		this.testResult = testResult;
		this.description = description;
		this.browserName = browserName;
	}

	public Result(String browserName, String nameOfMethod, boolean testResult, String description, List<String> inputParameters) {
		super();
		this.browserName = browserName;
		this.nameOfMethod = nameOfMethod;
		this.testResult = testResult;
		this.description = description;
		this.inputParameters = inputParameters;		
	}

	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public List<String> getInputParameters() {
		return inputParameters;
	}

	public void setInputParameters(List<String> inputParameters) {
		this.inputParameters = inputParameters;
	}

	public String getNameOfMethod() {
		return nameOfMethod;
	}

	public void setNameOfMethod(String nameOfMethod) {
		this.nameOfMethod = nameOfMethod;
	}

	public boolean getTestResult() {
		return testResult;
	}

	public void setTestResult(boolean testResult) {
		this.testResult = testResult;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void toPrint() {
		if(getInputParameters()!=null) {
		System.out.print(getBrowserName()+" "+getNameOfMethod()+"  "+getTestResult()+"   "+getDescription()+"  ");
		   for(int x=0; x<getInputParameters().size();x++) {
		    	System.out.print(getInputParameters().get(x)+"  ");
	    	}
		}else {
			System.out.print(getBrowserName()+" "+getNameOfMethod()+"  "+getTestResult()+"   "+getDescription()+"  ");
		}
		System.out.println("");
	}
	 
}

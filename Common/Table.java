package Common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

public class Table {
	
	public StringBuilder htmlStringBuilder;
	String red = "#f45042";
	String green = "#42f442";
	
	public void createTetable(List<Result> results) throws IOException{
		
		htmlStringBuilder = new StringBuilder();
		htmlStringBuilder.append("<html><head><title>Selenium Test</title></head>");
		htmlStringBuilder.append("<body>");
		htmlStringBuilder.append("<table border=\"1\" bordercolor=\"#000000\">");
		htmlStringBuilder.append("<tr>"
				+ "<td><b>Browser</b></td>"
				+ "<td><b>Name of Method</b></td>"
				+ "<td><b>Test result</b></td>"
				+ "<td><b>Description</b></td>"
				+ "</tr>");
		for(int x=0; x<results.size(); x++) {
			if(results.get(x).getTestResult()==false) { 
			htmlStringBuilder.append("<tr bgcolor = \""+red+"\">"
					+ "<td>"+results.get(x).getBrowserName()+"</td>"
					+ "<td>"+results.get(x).getNameOfMethod()+"</td>"
					+ "<td>"+results.get(x).getTestResult()+"</td>"
					+ "<td>"+results.get(x).getDescription()+"</td>"
					+"</tr>");
			}else if(results.get(x).getTestResult()==true) {
				htmlStringBuilder.append("<tr bgcolor = \""+green+"\">"
						+ "<td>"+results.get(x).getBrowserName()+"</td>"
						+ "<td>"+results.get(x).getNameOfMethod()+"</td>"
						+ "<td>"+results.get(x).getTestResult()+"</td>"
						+ "<td>"+results.get(x).getDescription()+"</td>"
						+"</tr>");
			}
		}			
		htmlStringBuilder.append("</table></body></html>");
		WriteToFile(htmlStringBuilder.toString(), "test-output/myReport.html");
	}

	private void WriteToFile(String filecontent, String fileName) throws IOException {
		String projectPath = System.getProperty("user.dir");
		String tempFile = projectPath+ File.separator+fileName;
		File file = new File(tempFile);
		if(file.exists()) {
			try {
				File newFileName = new File(projectPath+File.separator+"backUp"+fileName);
				file.renameTo(newFileName);
				file.createNewFile();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
		Writer writer = new OutputStreamWriter(outputStream);
		writer.write(filecontent);
		writer.close();
	}

	public Table() {
		super();
		 
	}

}

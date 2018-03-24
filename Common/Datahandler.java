package Common;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class Datahandler {
		
	public Datahandler() {
		super();		 
	}
		
    public int getMonthsBetween2dates(LocalDate date1, LocalDate date2) {
    	int x;
    	 
       	System.out.println("Дата 1: "+date1.toString()+"\nДата2: "+date2.toString());
    	if(date2.getDayOfMonth()>=date1.getDayOfMonth()) {
       	x= (int) ChronoUnit.MONTHS.between(date1, date2);
    	}else {
    	x=1+(int) ChronoUnit.MONTHS.between(date1, date2);
    	}    	 
    	System.out.println("Между ними "+x+ " месяцев.");
    	return x;
    }
    
    public int [] getValueOfDay(LocalDate date) {
    	int [] xpathValues = new int [2];
    	int dayOfWeek = date.getDayOfWeek().getValue();
    	int dayOfMonth = date.getDayOfMonth();
    	int firstDayOfMonth_DayOfWeek = date.with(TemporalAdjusters.firstDayOfMonth()).getDayOfWeek().getValue();
		if(isFirstWeek(date)==true) {
			xpathValues [0] = 3;
			if(firstDayOfMonth_DayOfWeek==1||firstDayOfMonth_DayOfWeek==2) {
				xpathValues [1] = dayOfWeek;
			}else {
				xpathValues [1] = dayOfMonth+1;
			}
		}else {
			int x=3;
			int y=8-firstDayOfMonth_DayOfWeek;
			while(y<dayOfMonth) {				 
				y+=7;
				x++;				 
			}			  
			xpathValues[0]=x;
			xpathValues[1]=dayOfWeek;
		}
		
		
    	return xpathValues;
    }
    
    public boolean isFirstWeek(LocalDate date) {
    	boolean result= true;
    	int dayOfMonth = date.getDayOfMonth();
    	int firstDayOfWeek = date.with(TemporalAdjusters.firstDayOfMonth()).getDayOfWeek().getValue();
		if((8-firstDayOfWeek)<dayOfMonth) {
			result=false;
		} 
    	return result;    	
    }
    
    
    
    
    
    
	
	
	   /* LocalDate dataNow = LocalDate.now();
	    LocalDate dataNotNow = LocalDate.of(2016, 07, 25);
	    Integer x = Period.between(dataNow, dataNotNow).getMonths();
	    System.out.println(x);
		*/
	
	
	

}

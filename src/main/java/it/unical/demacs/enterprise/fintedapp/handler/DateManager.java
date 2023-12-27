package it.unical.demacs.enterprise.fintedapp.handler;

import java.util.Calendar;
import java.util.Date;

public class DateManager {
    private static DateManager instance;
    private DateManager(){}

    public static DateManager getInstance(){
        if(instance == null)
            instance = new DateManager();
        
        return instance;
    }

    public Date currentDate(){
        return new Date(Calendar.getInstance().getTime().getTime());
    }
    
    public java.sql.Date currentDateSQLFormat(){
    	return new java.sql.Date(Calendar.getInstance().getTimeInMillis());
    }
 
}

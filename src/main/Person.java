package main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
    public String lastName, firstName, gender, color;
    Date date;
    String newDate;

    Person(String lastName, String firstName, String gender, Date date, String color){
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.date = date;
        this.color = color;
    }

    @Override
    public String toString() {
        String pattern = "M/d/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        newDate = dateFormat.format(date);
        return
                lastName + ' ' +
                firstName + ' ' +
                gender + ' ' +
                newDate + ' ' +
                color;
    }

    public String getGender(){
        return gender;
    }

    public Date getDate(){
        return date;
    }

    public String getLastName(){
        return lastName;
    }
}

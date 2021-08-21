/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Main {
    static ArrayList<File> files = new ArrayList<>();

    //Read all files into an array list of files
    public void readFiles(){
        try {
            File comma = new File("comma.txt");
            files.add(comma);
            File space = new File("space.txt");
            files.add(space);
            File pipes = new File("pipe.txt");
            files.add(pipes);
        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }

    //Sort the first output by LastName then Gender then write output to file
    public static void sortOutput1(ArrayList<Person> people, BufferedWriter writer){
        people.sort(Comparator.comparing(Person::getLastName));
        people.sort(Comparator.comparing(Person::getGender));
        try {
            writer.write("Output 1:");
            writer.newLine();
            for (Person person : people) {
                writer.write(person.toString());
                writer.newLine();
            }
            writer.newLine();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    //Sort by birthdate & last name ascending then write output to file
    public static void sortOutput2(ArrayList <Person> people, BufferedWriter writer){
        people.sort(Comparator.comparing(Person::getLastName));
        Collections.reverse(people);
        people.sort(Comparator.comparing(Person::getDate));
        try {
            writer.write("Output 2:");
            writer.newLine();
            for (Person person : people) {
                writer.write(person.toString());
                writer.newLine();
            }
            writer.newLine();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //Sort by lastname ascending then write to file
    public static void sortOutput3(ArrayList <Person> people, BufferedWriter writer){
        people.sort(Comparator.comparing(Person::getLastName));
        Collections.reverse(people);
        try {
            writer.write("Output 3:");
            writer.newLine();
            for (Person person : people) {
                writer.write(person.toString());
                writer.newLine();
            }
            writer.newLine();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.readFiles();
        Reader newReader = new Reader(files);
        File file;
        try {
            file = new File("text_output.txt");
            BufferedWriter output = new BufferedWriter(new FileWriter(file, true));
            sortOutput1(newReader.getResult(), output);
            sortOutput2(newReader.getResult(), output);
            sortOutput3(newReader.getResult(), output);
            output.flush();
            output.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }    
}

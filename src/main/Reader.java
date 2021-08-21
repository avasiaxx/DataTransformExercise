package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Reader {
    //Each item contains a list of information arranged by input file
    private ArrayList<Person> result = new ArrayList<>();
    enum State {
        COMMAS, SPACES, PIPES
    }
    Reader(ArrayList<File> files){
        for(File file : files){
            ArrayList<String> lines = getLines(file);
            State state = checkFileType(lines);
            result.addAll(parse(state, lines));
        }
    }

    //Read the file lines then add to the ArrayList of Strings
    private ArrayList<String> getLines(File file){
        ArrayList<String> lines = new ArrayList<>();
        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine())!=null){
                lines.add(line);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return lines;
    }

    //Check the string list to see what it contains as delimiters then set the file state
    private State checkFileType(ArrayList<String> lines){
        State state;
        String line = lines.get(0);

        if(line.contains("|")) {
            state = State.PIPES;
        } else if(line.contains(",")) {
            state = State.COMMAS;
        } else {
            state = State.SPACES;
        }
        return state;
    }

    //Format strings to remove delimiters & format date requirement depending on what file state was given
    private ArrayList<Person>  parse(State state, ArrayList<String> lines){
        ArrayList<List<String>> trimmed = new ArrayList<>();
        ArrayList<Person> result = new ArrayList<>();

        for(String line: lines) {
           String[] arr = line.replace(",", "")
                    .replace("|", "")
                   .replaceAll("\\s+", " ")
                    .split(" ");
           ArrayList<String> temp = new ArrayList<>(Arrays.asList(arr));
           trimmed.add(temp);
        }
        switch (state){
            case COMMAS:
                for(List<String> item: trimmed) {
                    //Swap Date & Colors in item
                    Collections.swap(item, item.size()-1, item.size() - 2);
                    Person person = new Person(item.get(0), item.get(1), item.get(2), formatDate(item.get(3)), item.get(4));
                    result.add(person);
                }
                break;
            case PIPES:
                for(List<String> item : trimmed){
                    //Remove unneeded middle initial from item
                    item.remove(2);
                    //Fix Gender Format
                    if(item.get(2).equals("F")){
                        item.set(2, "Female");
                    }else if(item.get(2).equals("M")){
                        item.set(2, "Male");
                    }
                    //Swap Date & Colors in item
                    Collections.swap(item, item.size()-1, item.size() - 2);
                    Person person = new Person(item.get(0), item.get(1), item.get(2),formatDate(item.get(3)), item.get(4));
                    result.add(person);
                }
                break;
            case SPACES:
                for(List<String> item : trimmed){
                    //Remove unneeded middle initial from item
                    item.remove(2);
                    //Fix Gender Format
                    if(item.get(2).equals("F")){
                        item.set(2, "Female");
                    }else if(item.get(2).equals("M")){
                        item.set(2, "Male");
                    }
                    //Date Format
                    Person person = new Person(item.get(0), item.get(1), item.get(2), formatDate(item.get(3)), item.get(4));
                    result.add(person);
                }
                break;
        }
        return result;
    }
    //Format Person's date
    public Date formatDate(String date){
        date = date.replace("-", "/");
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
        String newDateString = date;
        Date d = null;
        try {
            d = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public ArrayList<Person> getResult(){
        return result;
    }
}


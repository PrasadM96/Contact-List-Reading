
import java.io.*;
import java.io.IOException;
import java.util.*;


public class Contact {
    public static Map<String, List<String>> map  = new HashMap<>();//creating data structure
   // private static int flag=0;
    
    public static void main(String[] args) {
        String csvFile = "./contacts.csv"; //contact list file name with location
        BufferedReader br = null; //creating object for file reading
        String inputName = "";
        String line = "";
        String SplitBy = ",";
        String prefix = "";
       int flag=0;

        try {
            br = new BufferedReader(new FileReader(csvFile)); //initializing reading object
            line = br.readLine();//read line
            //read file until detect a null row
            while ((line = br.readLine()) != null) {
                String[] str = line.split(SplitBy);// use comma as separator
                str[0] = str[0] + " ";//get fulname
                
                List<String> num = new ArrayList<String>(); //list for store the numbers
                num.add(str[8]);
                num.add(str[9]);
                map.put(str[0]+str[1], num); //include data to the structure                         
            }

        } catch (FileNotFoundException e) {
            System.out.println(e); //print exception message when file  not found
        } catch (IOException e) {
            System.out.println(e); //input file name exception
        } finally {
            if (br != null) {
                try {
                    br.close(); //close bufferefresder
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            inputName = br.readLine(); //read user input as F:firstname or L:Lastname
            String temp[] = inputName.split(":");
            prefix = temp[0]; //get prefix F or L
            inputName = temp[1]; //get name
        } catch (IOException e) {
            System.out.println("Invalid syntax"); //input error message
        }

        if (prefix.equals("F") || prefix.equals("L")) { //check whether prefix match or not
            if(prefix.equals("F")){
                flag = findNumber(inputName,0);//find number according to firstname
            }else if(prefix.equals("L")){
                flag = findNumber(inputName,1);//find number according to name
            }
              if(flag==0){
                System.out.println("Not Found"); //print when name is not exist
               }
            } else {
                System.out.println("Enter your input as F:firname or L:Lastname"); //advice for error input
            }       
    }
    
    public static int findNumber(String inputName,int index){
        int flag=0;
         for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                String key = entry.getKey();
                String []name=key.split(" ");//split the fullname into two parts   
                if(inputName.equals(name[index])){
                      List<String> values = entry.getValue();//if name matches print the phone numbers
                      System.out.println(values);
                      flag=1;
           }
    }
         return flag;
}
}

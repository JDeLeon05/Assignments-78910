
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class BubbleSort {
    

    public static void main(String args[]){
        //variables
        Scanner scnr = new Scanner(System.in);

        int[] fileNums;
        int[] bubbleNums;
        int[] mergeNums;
        String input;

        //Program tells user how to use the program
        System.out.println("=============================================================================");
        System.out.println("Welcome to Bubble Sort");
        System.out.println("This program creates and sorts arrays of integers");
        System.out.println("To use, simply follow the prompts provided");
        System.out.println("When asked for an input, simply type a response and press ENTER to submit it");

        while(1==1){
            //program gives user options
            System.out.println("=============================================================================");
            System.out.println("Our following options are below");
            System.out.println("(NOTE: there is no need to worry about white space or capitalization):");
            System.out.println("- to generate a random array with valeus from 0-100, enter 'generate'");
            System.out.println("- to read & sort the array from least to greatest, simply enter 'sort'");
            
            //user inputs what they'd like to do
            input = scnr.nextLine();
            input = input.replaceAll("\\s+", "");
            input = input.toLowerCase();

            System.out.println("");


            if(input.equals("generate")){
                //program asks user for the size of the array
                System.out.println("You have chosen to generate an array");
                System.out.println("Before that, you need to specify the size of the array");
                System.out.println("enter the integer value for the array size: ");

                //size of array is stored in an iteger
                int arraySize = scnr.nextInt();
                scnr.nextLine();
                
                //program makes an array of randomly generated numbers
                fileNums = CreateArray(arraySize).clone();

                System.out.println("");

                //program stores array into RandomArray.txt file
                System.out.print("Your array ");
                ArraytoFile(fileNums, "RandomArray.txt");

                //end if
            } else if(input.equals("sort")){
                //contents of RandomArray.txt are extracted and stored into an array of integers
                fileNums = FileToArray("RandomArray.txt").clone();

                //array is printed for the player to see
                System.out.println("The array below has been extracted from a file: ");
                for(int i : fileNums){
                    System.out.println(i);
                }//end for

                //program sorts the elements from least to greatest, prints result, & stores it into SortedArray.txt file
                bubbleSort(fileNums);

                //end else if
            } else{
                System.out.println("Your response did not match any of our options, please try again");
            }//end else

            //program asks user if they'd like to continue with the program
            System.out.println("");
            System.out.println("Would you like to go again? If so, press yes");
            System.out.println("If not, enter any other value");

            System.out.println("");

            //user input is filtered and stored into a string
            input = scnr.nextLine();
            input = input.replaceAll("\\s+","");
            input = input.toLowerCase();

            System.out.println("");

            //program ends if user does not wish to continue
            if(!input.equals("yes")){
                System.out.println("Thanks for Playing!");
                scnr.close();
                break;
            }

        }//end while
    }

    public static int[] CreateArray(int arrayLength){
        //integer array where random numbers are generated
        int[] randomArray = new int[arrayLength];

        //each element in array is assigned a randomly generated number
        for(int i = 0; i < arrayLength; i++){
            randomArray[i] = (int)(Math.random() * 100);
        }

        //array is returned
        return randomArray;
    }// end CreateArray

    public static void ArraytoFile(int[] array, String fileName){
        try(BufferedWriter bfw = new BufferedWriter(new FileWriter(fileName))){
            //each element in array is written to a file
            for(int ar : array){
                bfw.write(Integer.toString(ar));
                bfw.newLine();
            }//end for

            //program informs user that the array has been stored
            System.out.println("has been stored into a file");

            //end try
        } catch(Exception e){
            System.out.println(e);
        }//end catch
    }// end ArrayToFile

    public static int[] FileToArray(String fileName){
        //variables
        int[] extractedArray;
        ArrayList<String> line = new ArrayList<String>();
        int count = 0;

        try(BufferedReader bfr = new BufferedReader(new FileReader(fileName))){  
            //each line is extracted into a list of strings
            do{

                line.add(bfr.readLine());
            
            } while(line.get(line.size()-1) != null);// end do-while
            
            //last line is empty so it is removed
            line.remove(line.size() - 1);

        } catch (Exception e) { System.out.println(e);  }//end try-catch

        //size of int array set to size of line list
        extractedArray = new int[line.size()];

        //each element in Line list is converted to int and stored into extractedArray
        for(String i : line){
            extractedArray[count] = Integer.valueOf(i);
            count++;
        }

        return extractedArray;
    }//end FileToArray

    public static void bubbleSort(int[] array){
        int hold;
        //bubble sort loop moves larger elements to the right
        for(int i = 0; i < array.length - 1; i++){
           //element is moved to the right until its at the end or its next to a larger number
            for(int j = 0; j < array.length - i - 1; j++){
                //if an element is less than an element ahead of it, it switches places with it
                if(array[j] > array[j+1]){
                    hold = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = hold;
                }

            }

        }//end for

        //sorted array is printed for the user to see
        System.out.println("Below is your sorted array: ");
        for(int i : array){
            System.out.println(i);
        }//end for

        System.out.println("");

        System.out.print("Your sorted array ");
        //array is stored into SortedArray.txt
        ArraytoFile(array, "SortedArray.txt");
    }// end bubbleSort
}// end bubbleSort class

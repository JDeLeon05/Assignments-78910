import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class GenericSort {
    
    public static void main(String args[]){
        //variables
        Scanner scnr = new Scanner(System.in);

        double[] fileNums;
        double[] bubbleNums;
        double[] mergeNums;
        GenericClass<String> input = new GenericClass<String>();

        //Program tells user how to use the program
        System.out.println("=============================================================================");
        System.out.println("Welcome to Bubble Merge Sort");
        System.out.println("This program creates and sorts arrays of integers or decimal numbers");
        System.out.println("Using merge & bubble sort & compares them");
        System.out.println("To use, simply follow the prompts provided");
        System.out.println("When asked for an input, simply type a response and press ENTER to submit it");

        while(1==1){
            //program gives user options
            System.out.println("=============================================================================");
            System.out.println("Our following options are below");
            System.out.println("(NOTE: there is no need to worry about white space or capitalization):");
            System.out.println("- to generate a random array with values from 0-100, enter 'generate'");
            System.out.println("- to read & sort the array from least to greatest, simply enter 'sort'");
            
            //user inputs what they'd like to do
           
            input.setValue(scnr.nextLine());
            input.filterAnswer(input.getValue());

            System.out.println("");

            if(input.getValue().equals("generate")){
                //program asks user for the size of the array
                System.out.println("You have chosen to generate an array");
                System.out.println("Before that, you need to specify a few things");
                
                System.out.println("What size would you like your array? Enter the number below:");
                //size of array is stored in an iteger
                int arraySize = scnr.nextInt();
                scnr.nextLine();

                System.out.println("Would you like the array to be an integer or double?");
                input.setValue(scnr.nextLine());
                input.filterAnswer(input.getValue());

                //program makes an array of randomly generated numbers
                fileNums = CreateArray(arraySize , input.getValue());

                System.out.println("");

                //program stores array into RandomArray.txt file
                System.out.print("Your array ");
                ArraytoFile(fileNums, "RandomArray.txt");

                //end if
            } else if(input.getValue().equals("sort")){
                //contents of RandomArray.txt are extracted and stored into an array of integers
                fileNums = FileToArray("RandomArray.txt").clone();

                //array is printed for the player to see
                System.out.println("The array below has been extracted from a file: ");
                for(double i : fileNums){
                    System.out.println(i);
                }

                //array will be merge & bubble sorted in different variables so they don't influence each other
                bubbleNums = fileNums.clone();
                mergeNums = fileNums.clone();

                //program bubble sorts the elements from least to greatest, prints result, & stores it into BubbleArray.txt file
                System.out.println("");
                System.out.println("Below is the process of bubble sorting step-by-step:");
                bubbleSort(bubbleNums);

                //program merge sorts the elements from least to greatest, prints result, & stores it into MergeArray.txt file
                System.out.println("");
                System.out.println("Bellow is the process of merge sorting step-by-step:");
                MergeSort(mergeNums, 0, mergeNums.length - 1, mergeNums.length - 1);  

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
            input.setValue(scnr.nextLine());
            input.filterAnswer(input.getValue());

            System.out.println("");

            //program ends if user does not wish to continue
            if(!input.getValue().equals("yes")){
                System.out.println("Thanks for Playing!");
                scnr.close();
                break;
            }
            
        }//end while
    }//end main


    public static double[] CreateArray(int arrayLength, String arrayType){
        //integer array where random numbers are generated
        GenericClass<Double> element = new GenericClass(); 
        double[] randomArray = new double[arrayLength];

        //each element in array is assigned a randomly generated number
        for(int i = 0; i < arrayLength; i++){
            if(arrayType.equals("integer")){
                element.setValue(element.randomizeInt());

            } else {
                element.setValue(element.randomize());
            }
            randomArray[i] = element.getValue();
        }        
        
        //array is returned
        return randomArray;
    }// end CreateArray

    public static void ArraytoFile(double[] array, String fileName){
        try(BufferedWriter bfw = new BufferedWriter(new FileWriter(fileName))){
            //each element in array is written to a file
            for(double ar : array){
                bfw.write(String.valueOf(ar));
                bfw.newLine();
            }//end for

            //program informs user that the array has been stored
            System.out.println("has been stored into a file");

            //end try
        } catch(Exception e){
            System.out.println(e);
        }//end catch
    }// end ArrayToFile

    public static double[] FileToArray(String fileName){
        //variables
        double[] extractedArray;
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
        extractedArray = new double[line.size()];

        //each element in Line list is converted to int and stored into extractedArray
        for(String i : line){
            extractedArray[count] = Double.valueOf(i);
            count++;
        }

        return extractedArray;
    }//end FileToArray 


    public static void bubbleSort(double[] array){
        double hold;
        //bubble sort loop moves larger elements to the right
        for(int i = 0; i < array.length - 1; i++){
            hold = -1;
           //element is moved to the right until its at the end or its next to a larger number
            for(int j = 0; j < array.length - i - 1; j++){

                //if an element is less than an element ahead of it, then...
                if(array[j] > array[j+1]){
                    //...it switches places with it
                    hold = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = hold;
                    
                    //the whole array is printed to demonstrate the process
                    for(double a: array){
                        System.out.print(a + " ");
                    }// end for

                    System.out.println("");
                }// end if

            }//end for

            //sorting ends if now elements were switched
            if(hold == -1){
                continue;
            }
            System.out.println("");
            
        }//end for

        //sorted array is printed for the user to see
        System.out.println("Below is your bubble sorted array: ");
        for(double i : array){
            System.out.println(i);
        }//end for

        System.out.println("");

        System.out.print("Your bubble sorted array ");
        //array is stored into SortedArray.txt
        ArraytoFile(array, "BubbleArray.txt");
    }//end BubbleSort

    public static void MergeSort(double array[], int firstElement, int lastElement, int originalSize){
        //program prints out the process of splitting the elements in the 1/2 arrays
        for(int i = firstElement; i <= lastElement; i++){
            System.out.print(array[i] + " ");
        }//end for
        System.out.println("");

        //method doesn't recur if array is reduced to 1 element
        if(firstElement < lastElement){
            
            //middle element is identified
            int middle = ((firstElement + lastElement)/2);         
            
            //array is split into 2 parts
            MergeSort(array, firstElement, middle, originalSize);
            
            MergeSort(array, middle + 1, lastElement, originalSize);
            

            //size of the first & second halfs are identified
            int firstSize = middle - firstElement + 1;
            int lastSize = lastElement - middle;
            
            //arrays for storing the 2 halfs are initialized
            double[] firstArr = new double[firstSize];
            double[] lastArr = new double[lastSize];
            
            //values of main array are copied to the half arrays
            System.arraycopy(array, firstElement, firstArr, 0, firstSize);

            System.arraycopy(array, middle + 1, lastArr, 0, lastSize);
            

            //counters initialized
            int i = 0, j = 0, k = firstElement;          

            //elements of 1/2 arrays are sorted to the main array based on lowest value until one is completely transferred
            while((i < firstSize) && (j < lastSize)){

                
                if(firstArr[i] < lastArr[j]){
                    
                    array[k] = firstArr[i];
                    i++;
                    k++;
                    //end if
                }else{

                    array[k] = lastArr[j];
                    j++;
                    k++;

                }//end else
            }//end while

            //remaining elements of 1st 1/2 array are transferred
            while(i < firstSize){
                array[k] = firstArr[i];
                i++;
                k++;
            }//end while

            //remaining elements of 2nd 1/2 array are transferred
            while(j < lastSize){
                array[k] = lastArr[j];
                j++;
                k++;
            }//end while

            //program prints out the process of organizing the elements
            for(int a = firstElement ; a < k; a++ ){
                System.out.print(array[a] + " ");
            }//end for
            System.out.println("");    
            
            //elements of final....
            if((firstSize - 1) == originalSize/2){
                System.out.println("");    
                //are printed
                System.out.println("Below is your Merge sorted array: ");
                for(double f : array){
                    System.out.println(f);
                }//end for

                //stored into a file
                System.out.print("Your bubble sorted array ");
                ArraytoFile(array, "MergeArray.txt");
               
            }//end if

        }
    }//end MergeSort

}//end class

class GenericClass<T>{
    //variable stored in class
    private T value;

    //sets a value for the value variable
    public void setValue(T val){
        value = val;
    }

    //retrieves value for value variable
    public T getValue(){
        return value;
    }

    //gets rid of white spaces and lower cases player response to eliminate errors
    public String filterAnswer(String filter){
        filter = filter.replaceAll("\\s+", "");
        filter = filter.toLowerCase();
        return filter;
    }

    //generates a random double variable
    public Double randomize(){
        Double randomNum = Math.random() * 101;
        randomNum = Math.round(randomNum * 100.0) / 100.0;
        return randomNum;
    }

    //generates a random integer variable
    public Double randomizeInt(){
        double randomNum;
        int ran = (int)(Math.random() * 101);
        randomNum = ran;
        return randomNum;
    }
    
}

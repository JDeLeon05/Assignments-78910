
public class Fibonacci {
    public static void main(String[] args) {

        try{
            //program takes input from user and adjusts it for sequence
            int sequenceLength = Integer.parseInt(args[0]);
            sequenceLength++;

            //first 2 numbers are made for sequence
            int[] firstNumbers = {0, 1};
            
            //sequence increases based on input from user
            sequence(sequenceLength, firstNumbers.clone(), 2);

        } catch(Exception e){
            //program warns user that their input is invalid
            System.out.println("Please enter a positive integer");
        }
        
        
    }

    public static void sequence(int n,int[] fibonacci, int count){
        //fibonacci sequence is expanded
        int[] fibonacci2 = new int[count + 1];

        //values of old sequence are added to new sequence
        for(int i = 0; i < fibonacci.length; i++){

            fibonacci2[i] = fibonacci[i];
            
        }
        
        //the sum of the last 2 values is added to the sequence
        fibonacci2[count] = fibonacci2[count - 1] + fibonacci2[count - 2];
        
        if (count < n - 1){
            //program continues the sequence
            count++;
            sequence(n, fibonacci2.clone(), count);

        } else{
            //program ends sequence and prints out last element
            System.out.println("F(" + (n - 1) + ") = " + fibonacci2[n - 1]);
          
        }
        
    }
}

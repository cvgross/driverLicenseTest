import java.util.Scanner;
import java.io.*;
import java.text.*;

class Main 
{
  public static void main(String[] args) 
  {
    User test = new User();
    test.userInput();
    System.out.println(test.correct);
    boolean testPassed = test.userPassed();
    test.outputResults(testPassed);
  }
}

class User
{
  int SIZE = 20;
  public int correct = 0;
  double percent;
  char[] answerSheet = new char[SIZE];
  
  
  PrintWriter myFileOut;
  
  public void userInput()
  {
    for(int k = 0; k < answerSheet.length; k++)
    {
      //ask user for answer
      System.out.println("\nEnter your answer for question #" + (k+1) + ": ");
      Scanner input = new Scanner(System.in);
      char answer = input.next().charAt(0);
      
      //check answers
      if(answer == 'A' || answer == 'B' || answer == 'C' || answer == 'D')
      {
        try
        {
          Scanner reader = new Scanner(new FileInputStream("DMVanswers.txt"));
          while(reader.hasNext())
          {
            for(int i = 0; i < answerSheet.length; i++)
            {
              String s = reader.next();
              char c = s.charAt(0);
              if(answerSheet[k] == c)
              {
                correct++;
              }
            }
            
          }
          reader.close();
        }
        catch(FileNotFoundException e)
        {
          System.out.println("\nFile not found.");
        }
        
        
      }
      else
      {
        while(answer != 'A' && answer != 'B' && answer != 'C' && answer != 'D')
        {
          System.out.println("Your answer is not one of the choices.  Please enter a valid answer:");
          Scanner in = new Scanner(System.in);
          answer = in.next().charAt(0);
        }
      }
    }
  }

  public boolean userPassed()
  {
    if(correct >= 15)
    {
       return true;
    }
    else
    {
      return false;
    }   
  }

  public void outputResults(boolean testPassed)
  {
    try
    {
      PrintWriter myFileOut = new PrintWriter("report.txt");
      if(testPassed)
      {
        System.out.println("You passed! Drive safely!");
        myFileOut.println("User passed.");
      }
      else
      {
        System.out.println("You failed.  Better luck next time!");
        myFileOut.println("User failed.");
      }
      percent = (correct/20) * 100;
      myFileOut.println("User answered " + correct + " questions correctly. Score is " + percent + "%." );
      myFileOut.close();
    }
    catch(FileNotFoundException e)
    {
      System.out.println("\nFile not found.");
    }
  }
}

/*

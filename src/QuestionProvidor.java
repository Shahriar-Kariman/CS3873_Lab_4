import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;;

public class QuestionProvidor {
  private File file;
  private Scanner scan;

  public static QuestionProvidor instance = null;

  private QuestionProvidor(){
    String path = "../questions.txt";
    file = new File(path);
    try{
      scan = new Scanner(file);
    }
    catch(FileNotFoundException e){
      System.err.println("Program: I have never met this file in my entire life.");
    }
  }
  
  public static QuestionProvidor getInstance(){
    if(instance==null){
      instance = new QuestionProvidor();
    }
    return instance;
  }

  public String nextQuestion(){
    return scan.nextLine();
  }

  public boolean hasMoreQuestions(){
    return scan.hasNextLine();
  }
}

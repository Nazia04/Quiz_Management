import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class StudentQuiz {
    public static void main(String[] args) throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);

        System.out.print("System:> Enter your username\nStudent:>");
        String username = sc.nextLine();

        System.out.print("System:> Enter password\nStudent:>");
        String password = sc.nextLine();

        JSONParser userParser = new JSONParser();
        JSONArray userArray = (JSONArray) userParser.parse(new FileReader("./src/main/resources/users.json"));

        boolean Student = false;
        for (Object obj : userArray) {
            JSONObject user = (JSONObject) obj;
            String Username = (String) user.get("username");
            String Password = (String) user.get("password");

            if (Username.equals(username) && Password.equals(password)) {
                Student = true;
                break;
            }
        }

        if (!Student) {
            System.out.println("System:> Invalid student credentials.");
            sc.close();
            return;
        }
        System.out.println("System:> Welcome" + username + "to the quiz! We will throw you 10 questions. Each MCQ mark is 1 and no negative marking. Are you ready? Press 's' to start.");

        String start = sc.nextLine();
        while (start.equalsIgnoreCase("s")){
            JSONParser questionParser = new JSONParser();
            JSONArray questionArray = (JSONArray) questionParser.parse(new FileReader("./src/main/resources/quiz.json"));
            Random rand = new Random();
            int score = 0;
            int qCount = 1;

            while (qCount <= 10){
                int index = rand.nextInt(questionArray.size());
                JSONObject q = (JSONObject) questionArray.get(index);
                String question = (String) q.get("question");
                JSONArray options = (JSONArray) q.get("options");
                String answer = (String) q.get("answer");
                System.out.println("\nSystem:> [Question " + qCount + "] " + question);

                int i = 0;
                while (i < options.size()) {
                    System.out.println((i + 1) + ". " + options.get(i));
                    i++;
                }

                System.out.print("Student:> ");
                String studentAnswer = sc.nextLine();

                if (studentAnswer.equals(answer)) {
                    score++;
                }

                qCount++;
            }

            // Result
            System.out.println("\nResult:");
            if (score >= 8) {
                System.out.println("Excellent! You have got " + score + " out of 10");
            } else if (score >= 5) {
                System.out.println("Good. You have got " + score + " out of 10");
            } else if (score >= 3) {
                System.out.println("Very poor! You have got " + score + " out of 10");
            } else {
                System.out.println("Very sorry you are failed. You have got " + score + " out of 10");
            }

            // Restart option
            System.out.println("\nSystem:> Would you like to start again? Press 's' for start or 'q' for quit");
            start = sc.nextLine();
        }
        sc.close();
    }
}

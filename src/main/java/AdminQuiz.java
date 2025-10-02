import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AdminQuiz {
    public static void main(String[] args) throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);

        System.out.print("System:> Enter your username\nAdmin:>");
        String username = sc.nextLine();

        System.out.print("System:> Enter password\nAdmin:>");
        String password = sc.nextLine();

        JSONParser userParser = new JSONParser();
        JSONArray userArray = (JSONArray) userParser.parse(new FileReader("./src/main/resources/users.json"));

        boolean Admin = false;
        for (Object obj : userArray) {
            JSONObject user = (JSONObject) obj;
            String Username = (String) user.get("username");
            String Password = (String) user.get("password");

            if (Username.equals(username) && Password.equals(password)) {
                Admin = true;
                break;
            }
        }

        if (!Admin) {
            System.out.println("System:> Invalid admin credentials.");
            sc.close();
            return;
        }
        System.out.println("System:> Welcome admin! Please create new questions in the question bank.");

        JSONParser questionParser = new JSONParser();
        JSONArray questionArray = (JSONArray) questionParser.parse(new FileReader("./src/main/resources/users.json"));
        while (true) {
            System.out.print("System:> Input your question\nAdmin:> ");
            String question = sc.nextLine();

            System.out.print("System:> Input option 1\nAdmin:> ");
            String option1 = sc.nextLine();

            System.out.print("System:> Input option 2\nAdmin:> ");
            String option2 = sc.nextLine();

            System.out.print("System:> Input option 3\nAdmin:> ");
            String option3 = sc.nextLine();

            System.out.print("System:> Input option 4\nAdmin:> ");
            String option4 = sc.nextLine();

            System.out.print("System:> What is the answer key? (1-4)\nAdmin:> ");
            String answerKey = sc.nextLine();

            JSONObject mcq = new JSONObject();
            mcq.put("question", question);

            JSONArray options = new JSONArray();
            options.add(option1);
            options.add(option2);
            options.add(option3);
            options.add(option4);

            mcq.put("options", options);
            mcq.put("answer", answerKey);

            questionArray.add(mcq);
            System.out.println("System:> Question saved successfully!");

            // Ask admin if they want to continue
            System.out.print("System:> Do you want to add more questions? Press 's' to continue or 'q' to quit\nAdmin:> ");
            String choice = sc.nextLine();
            if (choice.equalsIgnoreCase("q")) {
                break;
            }
        }
        FileWriter fwQuestions = new FileWriter("./src/main/resources/quiz.json");
        fwQuestions.write(questionArray.toJSONString());
        fwQuestions.flush();
        fwQuestions.close();

        sc.close();
    }
}

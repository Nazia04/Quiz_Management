import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;


public class UsersLogin {
    public static void main(String[] args) throws IOException, ParseException {

        JSONObject adminUser = new JSONObject();
        adminUser.put("username", "admin");
        adminUser.put("password", "1234");
        adminUser.put("role", "admin");

        JSONObject studentUser = new JSONObject();
        studentUser.put("username", "Nazia");
        studentUser.put("password", "1234");
        studentUser.put("role", "student");

        JSONArray usersArray = new JSONArray();
        usersArray.add(adminUser);
        usersArray.add(studentUser);

        System.out.println(usersArray.toJSONString());

        FileWriter fw = new FileWriter("./src/main/resources/users.json");
        fw.write(usersArray.toJSONString());
        fw.flush();
        fw.close();
    }
}

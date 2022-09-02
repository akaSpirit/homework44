package lesson44;

import com.sun.net.httpserver.HttpExchange;
import server.ContentType;
import server.ResponseCodes;
import server.Utils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class Lesson45Server extends lesson44.Lesson44Server {
    String id;
    String email;

    public String getEmail() {
        return email;
    }

    String password;
    String username;
    String firstname;
    String lastname;
    EmployeeModel.Employee emp;

    public EmployeeModel.Employee getEmp() {
        return emp;
    }

    public Lesson45Server(String host, int port) throws IOException {
        super(host, port);
        registerGet("/login", this::loginGet);
        registerPost("/login", this::loginPost);
        registerGet("/register", this::registerFormGet);
        registerPost("/register", this::registerFormPost);
        registerGet("/profile", this::profileGet);

    }

    public void loginGet(HttpExchange exchange) {
        Path path = makeFilePath("login.html");
        sendFile(exchange, path, ContentType.TEXT_HTML);
    }

    public void loginPost(HttpExchange exchange) {
        String raw = getBody(exchange);

        Map<String, String> map = Utils.parseUrlEncoded(raw, "&");
        email = map.get("email");
        password = map.get("password");
        username = map.get("username");
        firstname = map.get("firstname");
        lastname = map.get("lastname");
        var users = UserFileService.readJson();
        emp = new EmployeeModel.Employee(users.size(), firstname, lastname, username, password);


        if(users.containsKey(email)) {
            if (users.get(email).getPassword().equals(password)) {
                profileGetData(exchange);
//                redirect303(exchange, "/profile");
            }
            return;
        }
        respond403(exchange);
//        registerGet("/profile", this::profileGet);

    }

    public void registerFormGet(HttpExchange exchange) {
        Path path = makeFilePath("register.html");
        sendFile(exchange, path, ContentType.TEXT_HTML);
    }

    public void registerFormPost(HttpExchange exchange) {
        String raw = getBody(exchange);

        var users = UserFileService.readJson();

        Map<String, String> parsed = Utils.parseUrlEncoded(raw, "&");

        email = parsed.get("email");
        password = parsed.get("password");
        username = parsed.get("username");
        firstname = parsed.get("firstname");
        lastname = parsed.get("lastname");

        if (users.containsKey(email)) {
            String str = "Данный email уже используется";
            try {
                sendByteData(exchange, ResponseCodes.ALREADY_EXISTS, ContentType.TEXT_PLAIN, str.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        users.put(email, new EmployeeModel.Employee(users.size() + 1, firstname, lastname, username, password));
        id = String.format("%s", users.size() + 1);
        UserFileService.writeJson(users);

        redirect303(exchange, "/login");
    }

    public void profileGetData(HttpExchange exchange) {
        renderTemplate(exchange, "profile.html", new EmployeeModel());
    }

    public void profileGet(HttpExchange exchange) {
        Path path = makeFilePath("emptyprofile.html"); //emptyprofile
        sendFile(exchange, path, ContentType.TEXT_HTML);
    }
}

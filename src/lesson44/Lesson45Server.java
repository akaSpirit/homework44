package lesson44;

import com.sun.net.httpserver.HttpExchange;
import server.ContentType;
import server.ResponseCodes;
import server.Utils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class Lesson45Server extends lesson44.Lesson44Server {
    public Lesson45Server(String host, int port) throws IOException {
        super(host, port);
        registerGet("/login", this::loginGet);
        registerPost("/login", this::loginPost);
    }
    public void loginGet(HttpExchange exchange) {
        Path path = makeFilePath("login.html");
        sendFile(exchange, path, ContentType.TEXT_HTML);
    }

    public void loginPost(HttpExchange exchange) {
//        String cType = getContentType(exchange);
//        String raw = getBody(exchange);
//
//        Map<String, String> parsed = Utils.parseUrlEncoded(raw, "&");
//
//        String data = String.format("<p>Необработанные данные: <b>%s</b></p>" +
//                "<p>Content-Type: <b>%s</b></p>" +
//                "<p>После обработки: <b>%s</b></p>", raw, cType, parsed);
//        try{
//            sendByteData(exchange, ResponseCodes.OK, ContentType.TEXT_HTML, data.getBytes());
//        } catch (IOException e){
//            e.printStackTrace();
//        }
        redirect303(exchange, "/");
    }

    public void registerGet(HttpExchange exchange) {
        Path path = makeFilePath("register.html");
        sendFile(exchange, path, ContentType.TEXT_HTML);
    }

    public void registerPost(HttpExchange exchange) {
        redirect303(exchange, "/");
    }
}

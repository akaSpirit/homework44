package lesson44;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileService {
    private List<BookModel.Book> books;
    private List<EmployeeModel.Employee> employees;

    private FileService(String fileName) {
        var filePath = Path.of("data", fileName);
        Gson gson = new Gson();
        try {
            books = List.of(gson.fromJson(Files.readString(filePath), BookModel.Book[].class));
            employees = List.of(gson.fromJson(Files.readString(filePath), EmployeeModel.Employee[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FileService read(String fileName) {
        var file = new FileService(fileName);
        return file;
    }

    public List<BookModel.Book> getBooks() {
        return books;
    }

    public List<EmployeeModel.Employee> getEmployees() {
        return employees;
    }
}

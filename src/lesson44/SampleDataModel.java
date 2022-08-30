package lesson44;

import java.time.LocalDateTime;
import java.util.List;

public class SampleDataModel {
    private LocalDateTime currentDateTime = LocalDateTime.now();
    private List<Employee> employees = FileService.read("employees.json").getEmployees();
    private List<Book> books = FileService.read("books.json").getBooks();

    public LocalDateTime getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(LocalDateTime currentDateTime) {
        this.currentDateTime = currentDateTime;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public static class Employee {
        private String userID;
        private String firstName;
        private String lastName;

        public Employee(String userID, String firstName, String lastName) {
            this.userID = userID;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
}

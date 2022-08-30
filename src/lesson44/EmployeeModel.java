package lesson44;

import java.time.LocalDateTime;
import java.util.List;

public class EmployeeModel {
    private LocalDateTime currentDateTime = LocalDateTime.now();
    private List<Employee> employees = FileService.read("employees.json").getEmployees();

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



    public static class Employee {
        private int empID;
        private String firstName;
        private String lastName;
        private int[] readID;
        private int[] readNowID;

        public Employee(int empID, String firstName, String lastName) {
            this.empID = empID;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public int getEmpID() {
            return empID;
        }

        public void setEmpID(int empID) {
            this.empID = empID;
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

        public String getReadBooks() {
            List<BookModel.Book> books = FileService.read("books.json").getBooks();
            String fmt = "";
            for (int i = 0; i < readID.length; i++) {
                if (readID[i] != 0)
                    fmt += String.format("'%s' by %s. ", books.get(readID[i]).getBookName(), books.get(readID[i]).getAuthorName());
            }
            return fmt;
        }

        public String getReadNowBooks() {
            List<BookModel.Book> books = FileService.read("books.json").getBooks();
            String fmt = "";
            for (int i = 0; i < readNowID.length; i++) {
            if(readNowID[i] != 0)
                fmt += String.format("'%s' by %s. ", books.get(readNowID[i]).getBookName(), books.get(readNowID[i]).getAuthorName());
            }
            return fmt;
        }
    }
}

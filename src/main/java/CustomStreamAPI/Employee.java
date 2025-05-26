package CustomStreamAPI;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Employee {

    String name;
    int id;
    double salary;
    int departmentId;

    public Employee(int id, String name, double salary, int departmentId) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }
    public int getDepartmentId() { return departmentId; }

    public static void main(String[] args) {
        List<Employee> empList = Arrays.asList(
        new Employee(101, "Alice", 75000.0, 10),
        new Employee(102, "Bob", 62000.0, 20),
        new Employee(103, "Charlie", 85000.0, 10),
        new Employee(104, "Diana", 90000.0, 30),
        new Employee(105, "Ethan", 58000.0, 20),
        new Employee(106, "Fiona", 72000.0, 40),
        new Employee(107, "George", 67000.0, 30),
        new Employee(108, "Hannah", 94000.0, 10),
        new Employee(109, "Ian", 81000.0, 40),
        new Employee(110, "Jane", 99000.0, 20));

        long startTime = System.nanoTime();
        //Use Java Streams API to get the total Salary per department.
        Map<Integer, Double> streamResult = empList.stream()
                .filter(e -> e.name.length() > 4)
                .collect(Collectors.groupingBy(e -> e.departmentId,
                Collectors.summingDouble(e -> e.salary)));
        long standardStreamTime = System.nanoTime() - startTime;

        streamResult.forEach((dept, totalSalary) -> System.out.println("DeptId : " + dept + " Total Salary :" + totalSalary));

        startTime = System.nanoTime();
        Map<Integer, Double> customStreamResult = CustomStream.of(empList)
                .filter(e -> e.name.length() > 4)
                .collect(CustomCollectors.groupingBy(
                        Employee::getDepartmentId,
                        Employee::getSalary
                ));
        long customStreamTime = System.nanoTime() - startTime;
        System.out.println("***************************************************");
        customStreamResult.forEach((dept, totalSalary) -> System.out.println("DeptId : " + dept + " Total Salary :" + totalSalary));

        System.out.printf("Standard Stream Time: %.3f ms%n", standardStreamTime / 1_000_000.0);
        System.out.printf("Custom Stream Time: %.3f ms%n", customStreamTime / 1_000_000.0);
    }
}

import java.util.Objects;

public class Department {
   public static final int size = 5;
    private static int count;
    private final int departmentID;
    private String name; //Название отдела
    private double salary;  // Зарплата в отделе

    public Department(String name, int payment) {
        count++;
        this.departmentID = count;
        this.name = name;
        this.salary = payment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() { return salary; }

    public int getDepartmentID() { return departmentID; }

    public void setSalary(int payment) {
        this.salary = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department that)) return false;
        return getSalary() == that.getSalary() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSalary());
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentID=" + departmentID +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}

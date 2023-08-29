import java.util.Objects;

public class Departament {
   public static final int size = 5;
    private static int count;
    private final int departamentID;
    private String name; //Название отдела
    private double salary;  // Зарплата в отделе

    public Departament(String name, int payment) {
        count++;
        this.departamentID = count;
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

    public int getDepartamentID() { return departamentID; }

    public void setSalary(int payment) {
        this.salary = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departament that)) return false;
        return getSalary() == that.getSalary() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSalary());
    }

    @Override
    public String toString() {
        return "Departament{" +
                "departamentID=" + departamentID +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}

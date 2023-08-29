public class Employee {
    public static final int size = 10;
    private static int count;
    private final int employeeID;
    public static final boolean FIO_RIGHT = false;
    public static final boolean FIO_LEFT = true;
    private String firstName; //    – name - для имени
    private String lastName; //– lastName - для фамилии
    private String midleName; //– middleName - для отчества
    private int departmentID; // номер отдела
    private double fullSalary;  // Зарплата
    private float scaleRatio; // коэффициент оклада

    public Employee(String lastName, String firstName, String midleName,
                    int departmentID, float salaryRate) {
        count++;
        this.employeeID = count;
        this.firstName = upCharName(firstName);
        this.lastName = upCharName(lastName);
        this.midleName = upCharName(midleName);
        this.departmentID = departmentID;
        this.scaleRatio = salaryRate;
    }
    private String upCharName(String Name) {
        char firstChar = Name.charAt(0); // получаем первый символ
        char upperFirstChar = Character.toUpperCase(firstChar); // преобразуем его в нижний регистр
        return upperFirstChar + Name.substring(1);
    }
    public String getEmployeeFIO(boolean flag) {
        return (flag) ? (getFirstName().charAt(0) + ". " + getMidleName().charAt(0) + ". " + getLastName()) :
                (getLastName() + " " + getFirstName().charAt(0) + ". " + getMidleName().charAt(0) + ".");
    }



    public static int getCount() {
        return count;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = upCharName(firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = upCharName(lastName);
    }

    public String getMidleName() {
        return midleName;
    }
    public void setMidleName(String midleName) {
        this.midleName = upCharName(midleName);
    }

    public int getDepartmentID() {
        return departmentID;
    }
    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }
    public double getScaleRatio() { return scaleRatio; }
    public void setScaleRatio(float scaleRatio) {
        this.scaleRatio = scaleRatio;
    }

   

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", midleName='" + midleName + '\'' +
                ", departmentID=" + departmentID +
                ", fullSalary=" + fullSalary +
                ", scaleRatio=" + scaleRatio +
                '}';
    }
}

public class Employee {
    public static final int size = 10;
    private static int count;
    private int employeeID;
    public static final boolean FIO_RIGHT = false;
    public static final boolean FIO_LEFT = true;
    private String ferstName; //    – name - для имени
    private String lastName; //– lastName - для фамилии
    private String midleName; //– middleName - для отчества
    private int departmentID; // номер отдела
    private double fullSalary;  // Зарплата
    private float scaleRatio; // коэффициент оклада

    public Employee(String lastName, String ferstName, String midleName,
                    int departmentID, float salaryRate) {
        count++;
        this.employeeID = count;
        this.ferstName = upCharName(ferstName);
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
        return (flag) ? (ferstName.charAt(0) + ". " + midleName.charAt(0) + ". " + lastName) :
                (lastName + " " + ferstName.charAt(0) + ". " + midleName.charAt(0) + ".");
    }



    public static int getCount() {
        return count;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getFerstName() {
        return ferstName;
    }

    public void setFerstName(String ferstName) {
        this.ferstName = ferstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMidleName() {
        return midleName;
    }

    public void setMidleName(String midleName) {
        this.midleName = midleName;
    }

    public int getDepartmentID() {
        return departmentID;
    }
    public double getScaleRatio() { return scaleRatio; }
    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", ferstName='" + ferstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", midleName='" + midleName + '\'' +
                ", departmentID=" + departmentID +
                ", fullSalary=" + fullSalary +
                ", scaleRatio=" + scaleRatio +
                '}';
    }
}

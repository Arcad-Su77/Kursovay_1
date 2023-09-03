import java.util.Scanner;
import static java.lang.Math.abs;

public class EmployeeBook {
    private double miniSalary;
    private double midleSalary;
    private double maxiSalary;
    private final Employee[] employees;
    private final Department[] departments;


    public EmployeeBook() {
        this.employees = new Employee[10];
        this.departments = new Department[5];
    }

    public EmployeeBook(Employee[] inEmployee, Department[] inDepartment) {
        this.employees = inEmployee;
        this.departments = inDepartment;
        updateVolume();
    }
    public double getMiniSalary() {
        return miniSalary;
    }
    public void setMiniSalary(double miniSalary) {
        this.miniSalary = miniSalary;
    }
    public double getMidleSalary() {
        return midleSalary;
    }
    public void setMidleSalary(double midleSalary) {
        this.midleSalary = midleSalary;
    }
    public double getMaxiSalary() {
        return maxiSalary;
    }
    public void setMaxiSalary(double maxiSalary) {
        this.maxiSalary = maxiSalary;
    }
    private void updateVolume() {
        setMidleSalary(getSalary(employees[0]));
        setMidleSalary(getSumSalary()/Employee.getCount());
        setMaxiSalary(getSalary(employees[0]));
        for (Employee emp : employees) {
            if (emp != null) {
                double salary = getSalary(emp);
                if (salary < getMiniSalary()) {
                    setMiniSalary(salary);
                }
                if (salary > getMaxiSalary()) {
                    setMaxiSalary(salary);
                }
            }
        }
    }

    // Реализуем метод printAllEmployee (распечатать всех сотрудников)
    public void printAllEmployee() {
        System.out.println("ID\tСотрудник\t\tЗарплата\tОтдел");
        for (Employee emp : employees) {
            if (emp != null) {
                System.out.println(emp.getEmployeeID() +
                        "\t" + emp.getEmployeeFIO() +
                        "\t" + getSalary(emp)  +
                        "\t" + emp.getDepartmentID()+ " " + departments[emp.getDepartmentIndexID()].getName());
            }
        }
    }
    public void printEditEmployee() {
        System.out.println("ID\tСотрудник\t\tОтдел\tКоэф.Зарплата");
        for (Employee emp : employees) {
            if (emp != null) {
                System.out.println(emp.getEmployeeID() +
                        "\t" + emp.getEmployeeFIO() +
                        "\t" + emp.getDepartmentID()+ " " + departments[emp.getDepartmentIndexID()].getName() +
                        "\t" + emp.getScaleRatio());
            }
        }
    }
    public void printAllDepartment() {
        System.out.println("ID\tОклад отдела\tНаименование отдела\n----------------------------");
        for (Department dep : departments) {
            if (dep != null) {
                System.out.println(dep.getDepartmentID() +
                        "\t" + dep.getSalary() +
                        "\t\t" + dep.getName());
            }
        }
    }

    public double getSumSalary() {
        double sumSalary = 0;
        for (Employee emp : employees) {
            if (emp != null) {
                sumSalary += getSalary(emp);
            }
        }
        return sumSalary;
    }
    public double getSalary(Employee emp) {
        double oklad = departments[emp.getDepartmentIndexID()].getSalary();
        double salary = oklad * emp.getScaleRatio();
        return (double) Math.round(salary * 100) / 100;
    }
    public String getEmployee(Employee emp) {
    	return emp.getEmployeeShortFIO() + ", Отдел:" + departments[emp.getDepartmentIndexID()].getName() + ", Зарплата: " + getSalary(emp);
    }
    public Employee findEmployeeMiniSalary() {
        Employee retEmployee;
         // MINI_SALARY
        setMiniSalary(getSalary(employees[0]));
        retEmployee = employees[0];
        for (Employee emp : employees) {
            if (emp != null) {
                double empFor = getSalary(emp);
                if (empFor < getMiniSalary()) {
                    setMiniSalary(empFor);
                    retEmployee = emp;
                }
            }
        }
        return retEmployee;
    }
    public Employee findEmployeeMaxiSalary() { // MAXI_SALARY
        Employee retEmployee;
        setMaxiSalary(getSalary(employees[0]));
        retEmployee = employees[0];
        for (Employee emp : employees) {
            if (emp != null) {
                double empFor = getSalary(emp);
                if (empFor > getMaxiSalary()) {
                    setMaxiSalary(empFor);
                    retEmployee = emp;
                }
            }
        }
        return retEmployee;
    }
    public Employee findEmployeeMidleSalary() { // MIDLE_SALARY
    	Employee retEmployee = null;
        setMidleSalary(getSumSalary() / Employee.getCount());
        double delta = abs(getMidleSalary() - getSalary(employees[0]));
        for (Employee emp : employees) {
            if (emp != null) {
                double empFor = abs(getMidleSalary() - getSalary(emp));
                if (empFor < delta) {
                    delta = empFor;
                    retEmployee = emp;
                }
            }
        }
        return retEmployee;
    }
    public void editDepartment(Scanner scan) {
        printAllDepartment();
        System.out.println("""
                Для изменения информации о департаменте, введите новые данные.
                формат ввода  <ID> <Оклад> <Название отдела>\s
                Данные разделяйте пробелами, если данные изменять не нужно то используете *""");
        String[] inEdit = {"*", "*", "*"};
        try {
            inEdit = scan.nextLine().split(" ");
        } catch (Exception e) {
            //e.printStackTrace(); // Выводит сообщение об ошибке
        }
        if (inEdit[0] != null && !inEdit[0].isEmpty() && inEdit.length == 3) {
            if (!inEdit[0].equals("*")) {
                int inID = Integer.parseInt(inEdit[0]);
                for (Department dep : departments) {
                    if (dep.getDepartmentID() == inID) {
                        if (inEdit[1] != null)
                            if (!inEdit[1].equals("*")) {
                                dep.setSalary(Integer.parseInt(inEdit[1]));
                            }
                        if (inEdit[2] != null)
                            if (!inEdit[2].equals("*")) {
                                dep.setName(inEdit[2]);
                            }
                        break;
                    }
                }
            }
            printAllDepartment();
            updateVolume();
        } else System.out.println("Вводите корректно: <ID> <Оклад> <Наименование>");
    }
    public void editEmployee(Scanner scan) {
        printEditEmployee();
        System.out.println("""
                Для изменения информации о сотруднике, введите новые данные.
                формат ввода  [<ID>] <Фамилия> <Имя> <Отчество> <Отдел> <Коэф.зарплаты>
                Данные разделяйте пробелами, если данные изменять не нужно то используете *""");
        String[] inEdit = {"*", "*", "*", "*", "*", "*"};
        try {
            inEdit = scan.nextLine().split(" ");
        } catch (Exception e) {
            //e.printStackTrace(); // Выводит сообщение об ошибке
        }
        if (inEdit[0] != null && !inEdit[0].isEmpty() && inEdit.length == 6) {
            if (!inEdit[0].equals("*")) {
                int inID = Integer.parseInt(inEdit[0]);
                for (Employee emp : employees) {
                    if (emp.getEmployeeID() == inID) {
                        if (inEdit[1] != null && !inEdit[1].equals("*")) {
                                emp.setLastName(inEdit[1]);
                            }
                        if (inEdit[2] != null && !inEdit[2].equals("*")) {
                                emp.setFirstName(inEdit[2]);
                            }
                        if (inEdit[3] != null && !inEdit[3].equals("*")) {
                            emp.setMidleName(inEdit[3]);
                        }
                        if (inEdit[4] != null && !inEdit[4].equals("*")) {
                            emp.setDepartmentID(Integer.parseInt(inEdit[4]));
                        }
                        if (inEdit[5] != null && !inEdit[5].equals("*")) {
                            emp.setScaleRatio(Float.parseFloat(inEdit[5].replace(",", ".")));
                        }
                        break;
                    }
                }
            }
            printAllEmployee();
            updateVolume();
        } else System.out.println("[<ID>] <Фамилия> <Имя> <Отчество> <Отдел> <Коэф.зарплаты>");
    }
}

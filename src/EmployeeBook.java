import java.util.Scanner;
import static java.lang.Math.abs;

public class EmployeeBook {

    private final Employee[] employees;
    private final Department[] departments;


    public EmployeeBook() {
        this.employees = new Employee[10];
        this.departments = new Department[5];
    }

    public EmployeeBook(Employee[] inEmployee, Department[] inDepartment) {
        this.employees = inEmployee;
        this.departments = inDepartment;
    }

    // Реализуем метод printAllEmployee (распечатать всех сотрудников)
    public void printAllEmployee() {
        System.out.println("ID\tСотрудник\t\tЗарплата\tОтдел");
        for (Employee emp : employees) {
            if (emp != null) {
                System.out.println(emp.getEmployeeID() +
                        "\t" + emp.getEmployeeFIO() +
                        "\t" + getSalary(emp)  +
                        "\t" + emp.getDepartmentID()+ " " + departments[emp.getDepartmentID() - 1].getName());
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
    	return emp.getEmployeeFIO(emp.FIO_RIGHT) + ", Отдел:" + departments[emp.getDepartmentIndexID()].getName() + ", Зарплата: " + getSalary(emp);
    }
    public Employee findEmployeeMiniSalary() {
        Employee retEmployee = null;
         // MINI_SALARY
        double mini = getSalary(employees[0]);
        retEmployee = employees[0];
        for (Employee emp : employees) {
            if (emp != null) {
                if (getSalary(emp) < mini) {
                    mini = getSalary(emp);
                    retEmployee = emp;
                }
            }
        }
        return retEmployee;
    }
    
    public Employee findEmployeeMaxiSalary() { // MAXI_SALARY
        Employee retEmployee = null;
        double maxi = getSalary(employees[0]);
        retEmployee = employees[0];
        for (Employee emp : employees) {
            if (emp != null) {
                if (getSalary(emp) > maxi) {
                    maxi = getSalary(emp);
                    retEmployee = emp;
                }
            }
        }
        return retEmployee;
    }
    
    public Employee findEmployeeMidleSalary() { // MIDLE_SALARY
    	Employee retEmployee = null;
        double midle = getSumSalary()/Employee.getCount();
        double delta = abs(midle - getSalary(employees[0]));
        for (Employee emp : employees) {
            if (emp != null) {
                if ((abs(midle - getSalary(emp))) < delta) {
                    delta = abs(midle - getSalary(emp));
                    retEmployee = emp;
                }
            }
        }
        return retEmployee;
    }
    
    public void editDepartment(Scanner scan) {
        printAllDepartment();
        System.out.println("Для изменения информации о департаменте, введите новые данные.\n" +
                "формат ввода  <ID> <Оклад> <Название отдела> \n" +
                "Данные разделяйте пробелами, если данные изменять не нужно то используете *");
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
            } printAllDepartment();
        } else System.out.println("Вводите корректно: <ID> <Оклад> <Наименование>");
    }
}

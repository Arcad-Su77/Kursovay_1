import java.util.Scanner;
import static java.lang.Math.abs;

public class EmployeeBook {

    public static final int MINI_SALARY = 1;
    public static final int MAXI_SALARY = 2;
    public static final int MIDLE_SALARY = 3;
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
                        "\t" + emp.getEmployeeFIO(Employee.FIO_RIGHT) +
                        "\t" + getSalary(emp)  +
                        "\t" + emp.getDepartmentID()+ " " + departments[emp.getDepartmentID() - 1].getName());
            }
        }
    }
    public void printEditEmployee() {
        System.out.println("ID\tСотрудник\t\tОтдел\tКоэф.Зарплата");
        for (Employee emp : employees) {
            if (emp != null) {
                System.out.println(emp.getEmployeeID() +
                        "\t" + emp.getEmployeeFIO() +
                        "\t" + emp.getDepartmentID()+ " " + departments[emp.getDepartmentID() - 1].getName() +
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
        double oklad = departments[emp.getDepartmentID()-1].getSalary();
        double salary = oklad * emp.getScaleRatio();
        return (double) Math.round(salary * 100) / 100;
    }

    public Employee findEmployee(int route) {
        Employee retEmployee = null;
        switch (route) {
            case 1 -> { // MINI_SALARY
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
            }
            case 2 -> { // MAXI_SALARY
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
            }
            case 3 -> { // MIDLE_SALARY
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
            }
            default -> throw new IllegalStateException("Unexpected value: " + route);
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
            }
        } else System.out.println("Вводите корректно: <ID> <Оклад> <Наименование>");
    }
    public void editEmployee(Scanner scan) {
        printEditEmployee();
        System.out.println("Для изменения информации о сотруднике, введите новые данные.\n" +
                "формат ввода  [<ID>] <Фамилия> <Имя> <Отчество> <Отдел> <Коэф.зарплаты>\n" +
                "Данные разделяйте пробелами, если данные изменять не нужно то используете *");
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
            } printAllEmployee();
        } else System.out.println("[<ID>] <Фамилия> <Имя> <Отчество> <Отдел> <Коэф.зарплаты>");
    }
}

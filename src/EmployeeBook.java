import java.util.Scanner;

import static java.lang.Math.abs;

public class EmployeeBook {

    public static final int MINI_SALARY = 1;
    public static final int MAXI_SALARY = 2;
    public static final int MIDLE_SALARY = 3;
    private final Employee[] employees;
    private final Departament[] departaments;
    private int size;

    public EmployeeBook() {
        this.employees = new Employee[10];
        this.departaments = new Departament[5];
    }

    public EmployeeBook(Employee[] inEmployee, Departament[] inDepartament) {
        this.employees = inEmployee;
        this.departaments = inDepartament;
    }

    public int getSize() {
        return size;
    }

    // Реализуем метод addEmplovee (создать сотрудника):
//    public void addEmployee(String contactNick, String phone) {
//        if (Employee.getCount() >= Employee.size) {
//            System.out.println("Нельзя добавить контакт, закончилось место");
//        }
//        Employee newEmployee = new Employee(String ferstName,
//                String lastName, String midleName, int departmentID);
//        employees[size++] = newEmployee;
//    }
//
//    // Реализуем метод removeEmployee (удалить контакт)
//    // Метод сдвигает массив влево на ячейку, удаляя ячейку под номером i
//    public void removeEmployee(String ferstName, String lastName, String midleName) {
//        for (int i = 0; i < employees.length; i++) {
//            if (employees[i].getFerstName().equals(ferstName) ||
//                    employees[i].getLastName().equals(lastName) ||
//                    employees[i].getMidleName().equals(midleName)) {
//                System.out.println(employees[i].getEmployeeFIO(Employee.FIO_RIGHT) + " удален");
//                System.arraycopy(employees, i + 1, employees, i, size - i - 1);
//                employees[size - 1] = null;
//                this.size--;
//                return;
//            }
//        }
//    }
//    // Реализуем метод findContact (найти контакт)
//    public void findContact(String employeeNick) {
//        for (int i = 0; i < size; i++) {
//            Employee employee = employees[i];
//            if (employee.getFerstName().equals(employeeNick)) {
//                System.out.println("ID: " + i + " Сотрудник :" + employee.getEmployeeFIO(Employee.FIO_RIGHT));
//                return;
//            }
//        }
//        System.out.println(employeeNick + " не найден");
//    }

    // Реализуем метод printAllEmployee (распечатать все контакты)
    public void printAllEmployee() {
        System.out.println("ID\tСотрудник\t\tЗарплата\tОтдел");
        for (Employee emp : employees) {
            if (emp != null) {
                System.out.println(emp.getEmployeeID() +
                        "\t" + emp.getEmployeeFIO(Employee.FIO_RIGHT) +
                        "\t" + getSalary(emp)  +
                        "\t" + emp.getDepartmentID()+ " " + departaments[emp.getDepartmentID() - 1].getName());
            }
        }
    }

    public void printAllDepartament() {
        System.out.println("ID\tОклад отдела\tНаименование отдела\n----------------------------");
        for (Departament dep : departaments) {
            if (dep != null) {
                System.out.println(dep.getDepartamentID() +
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
        double oklad = departaments[emp.getDepartmentID()-1].getSalary();
        double salary = oklad * emp.getScaleRatio();
        return Math.round(salary * 100) / 100;
    }

    public Employee findEmployee(int route) {
        Employee retEmployeer = null;
        switch (route) {
            case 1 -> { // MINI_SALARY
                double mini = getSalary(employees[0]);
                retEmployeer = employees[0];
                for (int i = 1; i < employees.length; i++) {
                    if (employees[i] != null) {
                        if (getSalary(employees[i]) < getSalary(employees[i - 1])) {
                            mini = getSalary(employees[i]);
                            retEmployeer = employees[i];
                        }
                    }
                }
            }
            case 2 -> { // MAXI_SALARY
                double maxi = getSalary(employees[0]);
                retEmployeer = employees[0];
                for (int i = 1; i < employees.length; i++) {
                    if (employees[i] != null) {
                        if (getSalary(employees[i]) > getSalary(employees[i - 1])) {
                            maxi = getSalary(employees[i]);
                            retEmployeer = employees[i];
                        }
                    }
                }
            }
            case 3 -> { // MIDLI_SALARY
                double midli = getSumSalary()/Employee.getCount();
                double dmidli = abs(midli - getSalary(employees[0]));;
                for (Employee emp : employees) {
                    if (emp != null) {
                        if ((abs(midli - getSalary(emp))) < dmidli) {
                            dmidli = abs(midli - getSalary(emp));
                            retEmployeer = emp;
                        }
                    }
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + route);
        }
        return retEmployeer;

    }

    public void editDepartament(Scanner scan) {
        printAllDepartament();
        System.out.println("Для изменения информации о департаменте, введите новые данные.\n" +
                "формат ввода  <ID> <Оклад> <Название отдела> \n" +
                "Данные разделяйте пробелами, если данные изменять не нужно то используете *");
        String[] inEdit = {"*", "*", "*"};
        try {
            inEdit = scan.nextLine().split(" ");
        } catch (Exception e) {
            //e.printStackTrace(); // Выводит сообщение об ошибке
        }
        if (inEdit[0] != null && inEdit[0]!="" && inEdit.length == 3) {
            if (!inEdit[0].equals("*")) {
                int inID = Integer.parseInt(inEdit[0]);
                for (Departament dep : departaments) {
                    if (dep.getDepartamentID() == inID) {
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
}

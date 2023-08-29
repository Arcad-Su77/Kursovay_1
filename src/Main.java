import javax.swing.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static boolean EXIT = true;
    private static int MENU_ROUTR = 0;

    public static void main(String[] args) {
        Departament[] departaments = InitialDep();
        Employee[] emploees = InitialEmployee();
        EmployeeBook employeeBook = new EmployeeBook(emploees, departaments);
        Scanner scan = new Scanner(System.in);
        int taskNumberRun = -1;
        while (EXIT) {
            System.out.println("\n\n==================================");
            switch (MENU_ROUTR) {
                case 0 -> System.out.println(DataProperties.menu_0);
                case 6 -> System.out.println(DataProperties.menu_1);
            }
            try {
                taskNumberRun = Integer.parseInt(scan.nextLine());
            } catch (Exception e) {
                taskNumberRun = -1;
                //e.printStackTrace(); // Выводит сообщение об ошибке
            }
            switch ((MENU_ROUTR+taskNumberRun)) {
                case 0 -> EXIT = false;
                case 1 -> employeeBook.printAllEmployee();    //Список сотрудников
                case 2 -> System.out.println("Сумма затрат на зарплату: "  + employeeBook.getSumSalary());
                case 3 -> System.out.println("Сотрудник с минимальной зарплатой: " + employeeBook.findEmployee(EmployeeBook.MINI_SALARY).getEmployeeFIO(Employee.FIO_RIGHT));
                case 4 -> System.out.println("Сотрудник с максимальной зарплатой: " + employeeBook.findEmployee(EmployeeBook.MAXI_SALARY).getEmployeeFIO(Employee.FIO_RIGHT));
                case 5 -> System.out.println("Средня зарплата сотрудников: " + employeeBook.getSumSalary() / Employee.getCount());
                case 6 -> menuRoute(6);
                case 7 -> employeeBook.printAllDepartament();
                case 8 -> employeeBook.editDepartament(scan);
//            case 9 -> task9();    //Задание 9
//            case 10 -> task10();    //Задание 10
//            case 11 -> task11();    // Задание 12
                default -> System.out.println("Вы не выбрали задание [1-0]1: " + taskNumberRun);
            }
            if (MENU_ROUTR == 0)
                if (EXIT) {
                    System.out.println("\n=====================\nДля выхода в меню введите любой символ.");
                    scan.nextLine();
                } else {
                    System.out.println("\n=====================\nСпасибо за использование нашего продукта.\n==============");
                }
        }
        scan.close();

        System.out.println("departaments = " + Arrays.toString(departaments));
        System.out.println("emploees = " + Arrays.toString(emploees));

        employeeBook.printAllEmployee();

    }

    private static Employee[] InitialEmployee() {
        Employee[] employees = new Employee[Employee.size];
        for (String[] strings : DataProperties.inEmployee) {
            int depID = Integer.parseInt(strings[3]);
            float salaryRate = Float.parseFloat(strings[4]);
            employees[Employee.getCount()] = new Employee(strings[0], strings[1], strings[2],
                    depID, salaryRate);
        }
        return employees;
    }
    private static Departament[] InitialDep() {
        int i = 0;
        Departament[] departaments = new Departament[Departament.size];
        for (String[] strings : DataProperties.inDepartment) {
            int payment = Integer.parseInt(strings[1]);
            departaments[i] = new Departament(strings[0], payment);
            i++;
        }
        return departaments;
    }

    public static void menuRoute(int menuRoute) {
        MENU_ROUTR = (MENU_ROUTR == menuRoute) ? 0 : menuRoute;
    }

}
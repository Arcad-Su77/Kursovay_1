public class DataProperties {
    public static String[][] inEmployee = {
//            Фамилия   Имя     Отчество    №Отдела Коэф.Оклада
            {"Иванов", "Иван", "Иванович", "1", "1.2"},
            {"Иванов", "Иван", "Кузмич", "2", "1.25"},
            {"Иванов", "Фома", "Ильич", "3", "1.12"},
            {"Семенов", "Иван", "Иванович", "4", "1.05"},
            {"Фролов", "Сергей", "Фомич", "5", "1.01"}
        };
    public static String[][] inDepartment = {
//            Имя   Зарплата
            {"АУП", "200000"},
            {"Бухгалтерия", "170000"},
            {"Front-разработчики", "150000"},
            {"Back-разработчики", "140000"},
            {"Тестировщики", "120000"}
        };
    public static String menu_0 = "Выберете действие:\n" +
            "1. Список сотрудников.\n" +
            "2. Сумма затрат на зарплаты.\n" +
            "3. Найти сотрудника с минимальной заплатой.\n" +
            "4. Найти сотрудника с средней заплатой.\n" +
            "5. Найти сотрудника с максимальной зарплатой.\n" +
            "6. Подсчитать среднее значение зарплат.\n" +
            "7. Управление отделами.\n" +
            "0. Завершить программу";
    public static String menu_1 = "Выберете действие для работы с отделами:\n" +
            "1. Вывести список отделов.\n" +
            "2. Изменить значение отдела.\n" +
            "0. Завершить программу";

}

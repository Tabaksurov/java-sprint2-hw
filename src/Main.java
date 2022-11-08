import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        // Поехали!
        Scanner scanner = new Scanner(System.in);

        ArrayList<MonthlyReport> monthlyReports = new ArrayList<>();
        YearlyReport yearlyReport = new YearlyReport();
        boolean isMonthlyReportReady = false;
        boolean isYearlyReportReady = false;

        while (true) {
            printMenu(); //печать меню
            String input = scanner.next();
            Integer userInput = -1;
try {
    userInput = Integer.parseInt(input);
} catch (NumberFormatException e) {
    System.out.println("Ошибка ввода. Введите число.");
    continue;
}
            if (userInput == 1) { // Считать все месячные отчёты
                for (int i = 1; i <= 3; i++) {
                    MonthlyReport report = new MonthlyReport(i);
                    report.read();
                    monthlyReports.add(report);
                }
                System.out.println("Месячные отчёты считаны");
                isMonthlyReportReady = true;
            } else if (userInput == 2) { // Считать годовой отчёт
                yearlyReport.read();
                System.out.println("Годовой отчёт считан");
                isYearlyReportReady = true;
            } else if (userInput == 3) { // Сверить отчёты
                if (isMonthlyReportReady && isYearlyReportReady) {
                    for (int i = 1; i <= 3; i++) {
                        MonthlyReport report = monthlyReports.get(i - 1);
                        Integer yearlyExpense = yearlyReport.getExpense(i);
                        Integer yearlyIncome = yearlyReport.getIncome(i);
                        if (yearlyExpense == null || yearlyIncome == null ||
                                report.getTotalExpense() != yearlyExpense || report.getTotalIncome() != yearlyIncome) {
                            System.out.println("В месяце " + i + " обнаружена ошибка!!!");
                        }
                    }
                    System.out.println("Сверка проведена.");
                }
                else {
                    System.out.println("Считайте месячные и годовой отчёт!");
                }
            } else if (userInput == 4) { // Вывести информацию о всех месячных отчётах
                if (isMonthlyReportReady && isYearlyReportReady) {
                    for (int i = 1; i <= 3; i++) {
                        System.out.println("Отчёт за месяц " + i + ".");
                        MonthlyReport report = monthlyReports.get(i - 1);
                        System.out.println("Самый прибыльный товар \"" + report.getMostProfitableProductName() + "\" на сумму " + report.getMostProfitableProductSum());
                        System.out.println("Самая большая трата \"" + report.getMostExpenseName() + "\" на сумму " + report.getMostExpenseSum());
                    }
                }
                else {
                        System.out.println("Считайте месячные и годовой отчёт!");
                    }
            } else if (userInput == 5) { // Вывести информацию о годовом отчёте
                int averageExpanseAllMonthsYear = 0;
                int averageIncomeAllMonthsYear = 0;
                if (isMonthlyReportReady && isYearlyReportReady) {
                System.out.println("Годовой отчёт за 2021 год");
                for (int i = 1; i <= 3; i++) {
                    MonthlyReport report = monthlyReports.get(i-1);
                    System.out.println("Прибыль за " + i + " месяц " + report.getTotalIncome() + ".");
                    averageExpanseAllMonthsYear = averageExpanseAllMonthsYear + report.getTotalExpense();
                    averageIncomeAllMonthsYear = averageIncomeAllMonthsYear + yearlyReport.getIncome(i);
                }
                System.out.println("Средний расход за все месяцы в году " + averageExpanseAllMonthsYear / 3);
                System.out.println("Средний доход за все месяцы в году " + averageIncomeAllMonthsYear / 3);
                }
                else {
                    System.out.println("Считайте месячные и годовой отчёт!");
                }

            } else if (userInput == 6) { //  Завершение программы
                break;
            }
            else  { //  Завершение программы
                System.out.println("Такой комманды нет. Повторите ввод.");
            }
        }
        scanner.close();

    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("6 - Для выхода из приложения");

    }
}



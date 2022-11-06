import java.util.ArrayList;


public class MonthlyReport {
    int month;
    ArrayList<MonthlyReportRecord> records = new ArrayList<>();

    MonthlyReport(int month) {
        this.month = month;
    }

    public void read() { // чтение месячного отчёта из файла
        String content = SimpleFileReader.readFileContentsOrNull("resources/m.20210" + this.month + ".csv");
        if (content == null) {
            return;
        }

        String[] lines = content.split("\\n");
        for (int i = 1; i < lines.length; i++) {
            String Line = lines[i];
            String[] parts = Line.split(",");
            String itemName = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sumOfOne = Integer.parseInt(parts[3]);

            records.add(new MonthlyReportRecord(itemName, isExpense, quantity, sumOfOne));
        }
    }

    public int getTotalExpense() { // расход за месяц
        int totalExpense = 0;
        for (MonthlyReportRecord record : records) {
            if (record.isExpense) {
                totalExpense = totalExpense + (record.quantity * record.sumOfOne);
            }
        }
        return totalExpense;
    }

    public int getTotalIncome() { // доход за месяц
        int totalIncome = 0;
        for (MonthlyReportRecord record : records) {
            if (!record.isExpense) {
                totalIncome = totalIncome + (record.quantity * record.sumOfOne);
            }
        }
        return totalIncome;
    }

    public String getMostProfitableProductName() { // Имя самого прибыльного товара
        int sum = 0;
        String mostProfitableProduct = "";
        for (MonthlyReportRecord record : records) {
            if (!record.isExpense && record.quantity * record.sumOfOne > sum) {
                sum = record.quantity * record.sumOfOne;
                mostProfitableProduct = record.itemName;
            }
        }
        return mostProfitableProduct;
    }

    public int getMostProfitableProductSum() { // Стоимость самого прибыльного товара
        int sum = 0;
        for (MonthlyReportRecord record : records) {
            if (!record.isExpense && record.quantity * record.sumOfOne > sum) {
                sum = record.quantity * record.sumOfOne;
            }
        }
        return sum;
    }

    public int getMostExpenseSum() { // Стоимость самой большой траты
        int value = 0;
        for (MonthlyReportRecord record : records) {
            if (record.isExpense && record.sumOfOne > value) {
                value = record.sumOfOne;
            }
        }
        return value;
    }

    public String getMostExpenseName() { // Имя самой большой траты
        int value = 0;
        String name = "";
        for (MonthlyReportRecord record : records) {
            if (record.isExpense && record.sumOfOne > value) {
                value = record.sumOfOne;
                name = record.itemName;
            }
        }
        return name;
    }

    public int getProfit() { // Сумма прибыли
        int profit = 0;
        int expense = 0;
        int income = 0;
        for (MonthlyReportRecord record : records) {
            if (record.isExpense) {
                expense = expense + record.quantity * record.sumOfOne;
            }
            if (!record.isExpense) {
                income = income + record.quantity * record.sumOfOne;
            }
        }
        profit = income - expense;
        return profit;
    }
}

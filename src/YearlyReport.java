import java.util.ArrayList;

public class YearlyReport {
    ArrayList<YearlyReportRecord> records = new ArrayList<>();

    public void add(YearlyReportRecord record) {
        records.add(record);

    }

    public void read() {
        String content = SimpleFileReader.readFileContentsOrNull("resources/y.2021.csv");
        if (content == null) {
            return;
        }

        String[] lines = content.split("\\n");
        for (int i = 1; i < lines.length; i++) {
            String Line = lines[i];
            String[] parts = Line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);


            this.add(new YearlyReportRecord(month, amount, isExpense));
        }
    }

    public Integer getExpense(int month) {
        for (YearlyReportRecord record : records) {
            if (record.isExpense && record.month == month) {
                return record.amount;
            }
        }
        return null;
    }

    public Integer getIncome(int month) {
        for (YearlyReportRecord record : records) {
            if (!record.isExpense && record.month == month) {
                return record.amount;
            }
        }
        return null;
    }
}

import java.util.Scanner;
import java.text.DecimalFormat;

public class TaxCalculator {
    public static void main(String[] args) {
        DecimalFormat peso = new DecimalFormat("#,##0.00");

        try (Scanner input = new Scanner(System.in)) {
            System.out.println("=======================================");
            System.out.println("      💰 TAX CALCULATOR (PH TRAIN LAW)");
            System.out.println("=======================================");

            System.out.print("Select mode (monthly / annual): ");
            String mode = input.next().toLowerCase();

            System.out.print("Enter your income (₱): ");
            double income = input.nextDouble();

            if (income < 0) {
                System.out.println("⚠️ Please enter a valid income amount.");
                return;
            }

            double annualIncome = mode.equals("monthly") ? income * 12 : income;
            double tax = 0;

            if (annualIncome <= 250000) {
                tax = 0;
            } else if (annualIncome <= 400000) {
                tax = (annualIncome - 250000) * 0.20;
            } else if (annualIncome <= 800000) {
                tax = 30000 + (annualIncome - 400000) * 0.25;
            } else if (annualIncome <= 2000000) {
                tax = 130000 + (annualIncome - 800000) * 0.30;
            } else if (annualIncome <= 8000000) {
                tax = 490000 + (annualIncome - 2000000) * 0.32;
            } else {
                tax = 2410000 + (annualIncome - 8000000) * 0.35;
            }

            double netIncome = annualIncome - tax;
            double monthlyTax = tax / 12;
            double monthlyNet = netIncome / 12;

            System.out.println("\n========== RESULT ==========");
            System.out.println("Income Type: " + (mode.equals("monthly") ? "Monthly" : "Annual"));
            System.out.println("Total Income: ₱" + peso.format(annualIncome));
            System.out.println("Tax Due: ₱" + peso.format(tax));
            System.out.println("Net Income: ₱" + peso.format(netIncome));

            if (mode.equals("monthly")) {
                System.out.println("-----------------------------");
                System.out.println("Monthly Tax: ₱" + peso.format(monthlyTax));
                System.out.println("Monthly Net Income: ₱" + peso.format(monthlyNet));
            }

            if (tax == 0) {
                System.out.println("\n✅ You are tax-exempt (₱250,000 and below)");
            }

            System.out.println("===============================");
        }
    }
}

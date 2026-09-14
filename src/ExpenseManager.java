import java.util.HashMap;
import java.util.Scanner;

public class ExpenseManager {

    private HashMap<String, Double> expenses;
    private Scanner scanner;

    public ExpenseManager() {
        this.expenses = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("=== QUẢN LÝ CHI TIÊU CÁ NHÂN ===");
        while (true) {
            System.out.println("\nChọn chức năng: [1] Thêm | [2] Xóa | [3] Xem danh sách | [4] Tổng chi | [5] Thoát");

            try {
                int choice = Integer.valueOf(scanner.nextLine());

                if (choice == 5) {
                    System.out.println("Đã thoát ứng dụng. Hẹn gặp lại!");
                    break;
                } else if (choice == 1) {
                    addExpense();
                } else if (choice == 2) {
                    deleteExpense();
                } else if (choice == 3) {
                    viewExpenses();
                } else if (choice == 4) {
                    calculateTotal();
                } else {
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập từ 1 đến 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng chỉ nhập con số menu hợp lệ!");
            }
        }
    }

    private void addExpense() {
        System.out.println("Nhập tên mục chi tiêu:");
        String name = scanner.nextLine();

        System.out.println("Nhập số tiền:");
        try {
            double amount = Double.valueOf(scanner.nextLine());
            expenses.put(name, amount);
            System.out.println("-> Đã thêm thành công!");
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Số tiền không hợp lệ. Hủy thao tác thêm.");
        }
    }

    private void deleteExpense() {
        System.out.println("Nhập tên mục muốn xóa:");
        String name = scanner.nextLine();

        if (expenses.containsKey(name)) {
            expenses.remove(name);
            System.out.println("-> Đã xóa mục: " + name);
        } else {
            System.out.println("Lỗi: Không tìm thấy mục chi tiêu này.");
        }
    }

    private void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("-> Sổ chi tiêu đang trống.");
            return;
        }

        System.out.println("--- DANH SÁCH CHI TIÊU ---");
        for (String key : expenses.keySet()) {
            System.out.println("- " + key + ": " + expenses.get(key));
        }
    }

    private void calculateTotal() {
        double total = 0.0;
        for (double amount : expenses.values()) {
            total = total + amount;
        }
        System.out.println("=> Tổng chi tiêu hiện tại: " + total);
    }

    public static void main(String[] args) {
        ExpenseManager app = new ExpenseManager();
        app.start();
    }
}
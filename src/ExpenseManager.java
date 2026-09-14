import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseManager {
    private ArrayList<Expense> expenses;
    private Scanner scanner;

    public ExpenseManager() {
        this.expenses = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("QUẢN LÝ SỔ NHẬT KÝ CHI TIÊU");
        while (true) {
            System.out.println("Chọn: [1] Thêm | [2] Xóa | [3] Xem danh sách | [4] Tổng chi | [5] Thoát");

            try {
                int choice = Integer.valueOf(scanner.nextLine());

                if (choice == 5) {
                    System.out.println("Đã thoát ứng dụng.");
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
                System.out.println("Lỗi: Vui lòng chỉ nhập con số.");
            }
        }
    }

    private void addExpense() {
        System.out.println("Nhập tên mục chi tiêu:");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Lỗi: Tên mục chi tiêu không được để trống.");
            return;
        }

        System.out.println("Nhập số tiền:");
        try {
            double amount = Double.valueOf(scanner.nextLine());
            if (amount < 0) {
                System.out.println("Lỗi: Số tiền không thể là số âm.");
                return;
            }

            Expense newExpense = new Expense(name, amount);
            expenses.add(newExpense);
            System.out.println("-> Đã thêm thành công!");
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Số tiền không hợp lệ. Hủy thao tác thêm.");
        }
    }

    private void deleteExpense() {
        if (expenses.isEmpty()) {
            System.out.println("Sổ chi tiêu đang trống, không có gì để xóa.");
            return;
        }

        System.out.println("CHỌN MỤC CẦN XÓA");
        for (int i = 0; i < expenses.size(); i++) {
            Expense item = expenses.get(i);
            System.out.println("[" + (i + 1) + "] " + item.getName() + ": " + item.getAmount());
        }

        System.out.println("Nhập số thứ tự muốn xóa:");
        try {
            int index = Integer.valueOf(scanner.nextLine()) - 1;

            if (index >= 0 && index < expenses.size()) {
                Expense removedItem = expenses.remove(index);
                System.out.println("-> Đã xóa: " + removedItem.getName() + " (" + removedItem.getAmount() + ")");
            } else {
                System.out.println("Lỗi: Số thứ tự không tồn tại trong danh sách.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập một con số hợp lệ.");
        }
    }

    private void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("Sổ chi tiêu đang trống.");
            return;
        }

        System.out.println("CHI TIẾT SỔ NHẬT KÝ");
        for (int i = 0; i < expenses.size(); i++) {
            Expense item = expenses.get(i);
            System.out.println((i + 1) + ". " + item.getName() + ": " + item.getAmount());
        }
    }

    private void calculateTotal() {
        double total = 0.0;
        for (Expense item : expenses) {
            total += item.getAmount();
        }
        System.out.println("Tổng chi tiêu hiện tại: " + total);
    }

    public static void main(String[] args) {
        ExpenseManager app = new ExpenseManager();
        app.start();
    }
}
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
            // Đã thêm tùy chọn [2] Sửa và đẩy các số thứ tự sau lên
            System.out.println("\nChọn: [1] Thêm | [2] Sửa | [3] Xóa | [4] Xem danh sách | [5] Tổng chi | [6] Thoát");

            try {
                int choice = Integer.valueOf(scanner.nextLine());

                if (choice == 6) {
                    System.out.println("Đã thoát ứng dụng.");
                    break;
                } else if (choice == 1) {
                    addExpense();
                } else if (choice == 2) {
                    editExpense(); // Kích hoạt hàm sửa
                } else if (choice == 3) {
                    deleteExpense();
                } else if (choice == 4) {
                    viewExpenses();
                } else if (choice == 5) {
                    calculateTotal();
                } else {
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập từ 1 đến 6.");
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

    // Phương thức mới: Sửa chi tiêu
    private void editExpense() {
        if (expenses.isEmpty()) {
            System.out.println("Sổ chi tiêu đang trống, không có gì để sửa.");
            return;
        }

        System.out.println("CHỌN MỤC CẦN SỬA");
        for (int i = 0; i < expenses.size(); i++) {
            Expense item = expenses.get(i);
            System.out.println("[" + (i + 1) + "] " + item.getName() + ": " + item.getAmount());
        }

        System.out.println("Nhập số thứ tự muốn sửa:");
        try {
            int index = Integer.valueOf(scanner.nextLine()) - 1;

            if (index >= 0 && index < expenses.size()) {
                System.out.println("Nhập tên mục mới:");
                String newName = scanner.nextLine().trim();

                if (newName.isEmpty()) {
                    System.out.println("Lỗi: Tên mục không được để trống.");
                    return;
                }

                System.out.println("Nhập số tiền mới:");
                double newAmount = Double.valueOf(scanner.nextLine());
                if (newAmount < 0) {
                    System.out.println("Lỗi: Số tiền không thể là số âm.");
                    return;
                }

                // Cấp phát Object mới trên Heap và ghi đè vào vị trí cũ trong ArrayList
                expenses.set(index, new Expense(newName, newAmount));
                System.out.println("-> Đã cập nhật thành công!");
            } else {
                System.out.println("Lỗi: Số thứ tự không tồn tại trong danh sách.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập một con số hợp lệ.");
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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private String url = "jdbc:postgresql://localhost:5432/restaurant";
    private String user = "postgres";
    private String password = "789456";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // --- CRUD: CREATE (Запись) ---
    public void saveItem(BaseItem item) {
        String sql = "INSERT INTO menu_items(name, price, item_type, calories, is_cold) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, item.getName());
            pstmt.setDouble(2, item.getPrice());

            if (item instanceof FoodItem) {
                pstmt.setString(3, "FOOD");
                pstmt.setInt(4, 500); // Пример: калории
                pstmt.setNull(5, Types.BOOLEAN);
            } else {
                pstmt.setString(3, "DRINK");
                pstmt.setNull(4, Types.INTEGER);
                pstmt.setBoolean(5, true); // Пример: холодный
            }
            pstmt.executeUpdate();
        } catch (SQLException e) { System.out.println(e.getMessage()); }
    }

    // --- CRUD: READ (Чтение) ---
    public void readMenu() {
        String sql = "SELECT * FROM menu_items";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getString("name") + " | " + rs.getDouble("price") + " KZT");
            }
        } catch (SQLException e) { System.out.println(e.getMessage()); }
    }

    // --- CRUD: UPDATE (Обновление) ---
    public void updatePrice(String name, double newPrice) {
        String sql = "UPDATE menu_items SET price = ? WHERE name = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newPrice);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
            System.out.println("Цена обновлена!");
        } catch (SQLException e) { System.out.println(e.getMessage()); }
    }

    // --- CRUD: DELETE (Удаление) ---
    public void deleteItem(String name) {
        String sql = "DELETE FROM menu_items WHERE name = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            System.out.println("Блюдо удалено из базы.");
        } catch (SQLException e) { System.out.println(e.getMessage()); }
    }
}
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSQL {
    private static final String data = "jdbc:sqlite:Data/data.db";
    private boolean connectToFile = false;
    private Connection conn;

    private void createMainTable() throws SQLException {
        String userTable = this.createUserTable();
        String eventTable = this.createEventTable();
        String orderingTable = this.createOrderingTable();

        try (Statement state = this.conn.createStatement()) {
            state.executeUpdate(userTable);
            state.executeUpdate(eventTable);
            state.executeUpdate(orderingTable);
        }
    }

    public DataSQL() {
        try {
            this.conn = DriverManager.getConnection(data);
            this.createMainTable();
            this.connectToFile = true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Can not Perform the Action");
            this.connectToFile = false;
        }
    }

    public boolean isConnectedToFile() {
        return this.connectToFile;
    }

    private String createUserTable() {
        return """
                CREATE TABLE IF NOT EXISTS users (
                    user_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    user_name TEXT NOT NULL,
                    user_gmail TEXT NOT NULL
                );
                """;

    }

    private String createEventTable() {
        return """
                CREATE TABLE IF NOT EXISTS event (
                    event_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    event_name TEXT NOT NULL,
                    total_seats INTEGER DEFAULT 10,
                    available_seats INTEGER NOT NULL,
                    price DOUBLE NOT NULL
                );
                """;

    }

    private String createOrderingTable() {
        return """
                CREATE TABLE IF NOT EXISTS ordering_table (
                    ordering_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    user_id INTEGER NOT NULL,
                    event_id INTEGER NOT NULL,
                    quantity INTEGER NOT NULL,
                    order_date DATETIME DEFAULT (datetime('now', 'localtime')),
                    FOREIGN KEY (user_id) REFERENCES users(user_id),
                    FOREIGN KEY (event_id) REFERENCES event(event_id)
                );
                """;

    }
}

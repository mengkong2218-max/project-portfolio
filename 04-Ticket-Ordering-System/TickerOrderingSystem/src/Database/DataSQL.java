package Database;


import java.sql.*;

public class DataSQL {
    private static final String data = "jdbc:sqlite:Data/data.db";
    private boolean connectToFile = false;
    private Connection conn;
    private boolean executeSuccessfully;

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

    public ResultSet loadData(String table) {
        if (!table.equals("users") && !table.equals("event") && !table.equals("ordering_table")) {
            System.out.println("No Database Found.");
            this.connectToFile = false;
            return null;
        }
        String task = "SELECT * FROM " + table + ";";
        return this.executeQueryCommand(task);
    }

    public ResultSet executeQueryCommand(String cmd, Object... objs) {
        try {
            PreparedStatement preState = this.conn.prepareStatement(cmd);
            for (int i = 0; i < objs.length; i++) {
                preState.setObject(i + 1, objs[i]);
            }
            this.executeSuccessfully = true;
            return preState.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            this.executeSuccessfully = false;
        }
        return null;
    }

    public void executeUpdateCommand(String cmd, Object... objs) {
        try (PreparedStatement preState = this.conn.prepareStatement(cmd)) {
            for (int i = 0; i < objs.length; i++) {
                preState.setObject(i + 1, objs[i]);
            }
            this.executeSuccessfully = true;
            preState.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            this.executeSuccessfully = false;
        }
    }

    public int executeInsert(String cmd, Object... objs) {
        try (PreparedStatement preState = this.conn.prepareStatement(cmd, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < objs.length; i++) {
                preState.setObject(i + 1, objs[i]);
            }
            this.executeSuccessfully = true;
            preState.executeUpdate();
            try (ResultSet keyID = preState.getGeneratedKeys()) {
                if (keyID.next()) {
                    return keyID.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.executeSuccessfully = false;
        }
        return -1;
    }

    public boolean isExecuteSuccessfully() {
        return this.executeSuccessfully;
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

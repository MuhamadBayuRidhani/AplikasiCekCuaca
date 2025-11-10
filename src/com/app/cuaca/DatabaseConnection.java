package com.app.cuaca;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static Connection conn;
    private static final String DB_URL = "jdbc:sqlite:kota_favorit.db";

    // Membuat koneksi ke database
    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(DB_URL);
                System.out.println("Koneksi SQLite berhasil.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    // Membuat tabel favorit jika belum ada
    public static void setupDatabase() {
        try (Connection conn = DatabaseConnection.getConnection();
             java.sql.Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS favorit (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                         "nama_kota TEXT UNIQUE)";
            stmt.execute(sql);
            System.out.println("Tabel favorit siap digunakan.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

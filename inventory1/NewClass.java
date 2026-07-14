/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventory1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author winar
 */
public class NewClass{
    public static Connection Bukakoneksi() {
        Connection sambungan = null; // deklarasi di luar try

        try {
            // Load driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Buat koneksi ke database
            sambungan = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/gudang", "root", ""
            );

            System.out.println("Koneksi berhasil!");
            JOptionPane.showMessageDialog(null, "Koneksi berhasil!");

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Koneksi gagal: " + e.getMessage());
        }

        return sambungan; // kembalikan hasil koneksi
    }
}

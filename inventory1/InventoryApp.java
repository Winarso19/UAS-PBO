package inventory1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryApp {

    private static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/gudang",
                    "root",
                    "");

        } catch (Exception e) {
            System.out.println("Koneksi gagal : " + e.getMessage());
            return null;
        }
    }

    //======================= SIMPAN ===========================
    public void tambahBarang(Barang barang) {

        String sql = "INSERT INTO penyimpanan "
                + "(nama_barang, tanggal_beli, Harga, jumlah_barang) "
                + "VALUES (?,?,?,?)";

        try (Connection conn = getConnection();
                PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, barang.getNama());
            pst.setString(2, barang.getTglBeli());
            pst.setDouble(3, barang.getHarga());
            pst.setInt(4, barang.getJumlah());

            pst.executeUpdate();

            System.out.println("Data berhasil disimpan.");

        } catch (SQLException e) {

            System.out.println("Gagal menambahkan data : " + e.getMessage());

        }

    }

    //======================= TAMPIL ===========================
    public List<Barang> getAllBarang() {

        List<Barang> list = new ArrayList<>();

        String sql = "SELECT * FROM penyimpanan ORDER BY ID_barang";

        try (Connection conn = getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Barang barang = new Barang(
                        rs.getInt("ID_barang"),
                        rs.getString("nama_barang"),
                        rs.getString("tanggal_beli"),
                        rs.getDouble("Harga"),
                        rs.getInt("jumlah_barang"));

                list.add(barang);

            }

        } catch (SQLException e) {

            System.out.println("Gagal mengambil data : " + e.getMessage());

        }

        return list;

    }

    //======================= UPDATE ===========================
    public boolean updateBarang(Barang barang) {

        String sql = "UPDATE penyimpanan SET "
                + "nama_barang=?,"
                + "tanggal_beli=?,"
                + "Harga=?,"
                + "jumlah_barang=? "
                + "WHERE ID_barang=?";

        try (Connection conn = getConnection();
                PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, barang.getNama());
            pst.setString(2, barang.getTglBeli());
            pst.setDouble(3, barang.getHarga());
            pst.setInt(4, barang.getJumlah());
            pst.setInt(5, barang.getId());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Update gagal : " + e.getMessage());

            return false;

        }

    }

    //======================= HAPUS ===========================
    public boolean deleteBarang(int id) {

        String sql = "DELETE FROM penyimpanan WHERE ID_barang=?";

        try (Connection conn = getConnection();
                PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println("Hapus gagal : " + e.getMessage());

            return false;

        }

    }

}
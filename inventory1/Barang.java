package inventory1;

public class Barang {
    private int id;
    private String nama_barang;
    private String tanggal_Beli;
    private double harga;
    private int jumlah;

    // Constructor lengkap (dengan ID)
    public Barang(int id, String nama, String tglBeli, double harga, int jumlah) {
        this.id = id;
        this.nama_barang = nama;
        this.tanggal_Beli = tglBeli;
        this.harga = harga;
        this.jumlah = jumlah;
    }

    // ✅ Constructor tanpa ID (untuk tambah barang)
    public Barang(String nama, String tglBeli, double harga, int jumlah) {
        this.nama_barang = nama;
        this. tanggal_Beli = tglBeli;
        this.harga = harga;
        this.jumlah = jumlah;
    }

    // Getter & Setter
    public int getId() { return id; }
    public String getNama() { return nama_barang; }
    public String getTglBeli() { return  tanggal_Beli; }
    public double getHarga() { return harga; }
    public int getJumlah() { return jumlah; }

    public void setId(int id) { this.id = id; }
    public void setNama(String nama) { this.nama_barang = nama; }
    public void setTglBeli(String tglBeli) { this. tanggal_Beli = tglBeli; }
    public void setHarga(double harga) { this.harga = harga; }
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }
}
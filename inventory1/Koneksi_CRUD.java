package inventory1;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Koneksi_CRUD {

    private static InventoryApp db = new InventoryApp();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;
        do {
            tampilkanMenu();
            System.out.print("Masukkan pilihan: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // konsumsi newline

            switch (pilihan) {
                case 1 -> tambahBarang();
                case 2 -> tampilkanBarang();
                case 3 -> ubahBarang();
                case 4 -> hapusBarang();
                case 0 -> System.out.println("Terima kasih! Program selesai.");
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    private static void tampilkanMenu() {
        System.out.println("\n===== MENU INVENTORI =====");
        System.out.println("1. Tambah Barang");
        System.out.println("2. Lihat Semua Barang");
        System.out.println("3. Ubah Barang");
        System.out.println("4. Hapus Barang");
        System.out.println("0. Keluar");
        System.out.println("===========================");
    }

    private static void tambahBarang() {
        System.out.print("Nama Barang: ");
        String nama = scanner.nextLine();

        System.out.print("Tanggal Beli (YYYY-MM-DD): ");
        String tglBeli = scanner.nextLine();

        System.out.print("Harga Barang: ");
        double harga = scanner.nextDouble();

        System.out.print("Jumlah Barang: ");
        int jumlah = scanner.nextInt();
        scanner.nextLine();

        Barang barang = new Barang(nama, tglBeli, harga, jumlah);
        db.tambahBarang(barang);
    }

    private static void tampilkanBarang() {
        List<Barang> list = db.getAllBarang();
        System.out.println("\n--- DAFTAR BARANG ---");
        if (list.isEmpty()) {
            System.out.println("Belum ada data barang di database.");
            return;
        }
        System.out.printf("%-3s %-20s %-12s %-10s %-6s%n", "ID", "Nama", "Tanggal", "Harga", "Jumlah");
        for (Barang b : list) {
            System.out.printf("%-3d %-20s %-12s %-10.2f %-6d%n",
                    b.getId(), b.getNama(), b.getTglBeli(), b.getHarga(), b.getJumlah());
        }
    }

    private static void ubahBarang() {
        tampilkanBarang();
        System.out.print("\nMasukkan ID barang yang ingin diubah: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nama baru: ");
        String nama = scanner.nextLine();

        System.out.print("Tanggal Beli baru (YYYY-MM-DD): ");
        String tgl = scanner.nextLine();

        System.out.print("Harga baru: ");
        double harga = scanner.nextDouble();

        System.out.print("Jumlah baru: ");
        int jumlah = scanner.nextInt();
        scanner.nextLine();

        Barang barang = new Barang(id, nama, tgl, harga, jumlah);
        if (db.updateBarang(barang)) {
            System.out.println("Barang berhasil diupdate!");
        } else {
            System.out.println("Gagal mengupdate barang!");
        }
    }

    private static void hapusBarang() {
        tampilkanBarang();
        System.out.print("\nMasukkan ID barang yang ingin dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (db.deleteBarang(id)) {
            System.out.println("Barang berhasil dihapus!");
        } else {
            System.out.println("Gagal menghapus barang!");
        }
    }
}
package Listrik;

import java.util.Scanner;

public class BillingApp {

    private static String accountName;
    private static String username;
    private static String password;
    private static String userID;
    private static boolean isPaskabayar;
    private static boolean isPrabayar;
    private static String customerID;
    private static boolean isTagihanDitampilkan;
    private static boolean isPembayaranLanjut;
    private static int metodePembayaran;
    private static int jumlahTransaksi;
    private static String[][] transaksi = new String[100][4];

    public static void setAccountName(String accountName) {
        BillingApp.accountName = accountName;
    }

    public static void setUsername(String username) {
        BillingApp.username = username;
    }

    public static void setPassword(String password) {
        BillingApp.password = password;
    }

    public static void setUserID(String userID) {
        BillingApp.userID = userID;
    }

    public static void main(String[] args) {
        BillingApp billingApp = new BillingApp();
        billingApp.start();
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            Scanner scanner = new Scanner(System.in);
            int mainMenuChoice = scanner.nextInt();
            exit = processChoice(mainMenuChoice);
        }
    }

    public void displayMenu() {
        System.out.println("=== Billing App ===");
        System.out.println("1. Daftar Akun");
        System.out.println("2. Paskabayar (Token) atau Prabayar");
        System.out.println("3. Masukkan Nomor ID Pelanggan");
        System.out.println("4. Display Tagihan dan Lanjutkan Pembayaran");
        System.out.println("5. Ganti Metode Pembayaran");
        System.out.println("6. Tampilkan Batas Waktu Pembayaran");
        System.out.println("7. Lihat Transaksi");
        System.out.println("8. Keluar");
        System.out.print("Pilih menu: ");
    }

    public boolean processChoice(int choice) {
        Scanner scanner = new Scanner(System.in);

        switch (choice) {
            case 1:
                registerAccount();
                break;
            case 2:
                paskabayarPrabayar();
                System.out.println("Lanjutkan ke menu utama");
                break;
            case 3:
                masukkanNomorIDPelanggan();
                break;
            case 4:
                if (isPaskabayar || isPrabayar) {
                    displayTagihan();
                    lanjutkanPembayaran();
                } else {
                    System.out.println("Anda belum memilih jenis layanan. Silakan pilih menu 'Paskabayar atau Prabayar' terlebih dahulu.");
                }
                break;
            case 5:
                gantiMetodePembayaran();
                break;
            case 6:
                tampilkanBatasWaktuPembayaran();
                break;
            case 7:
                lihatTransaksi();
                break;
            case 8:
                System.out.println("Terima kasih! Sampai jumpa lagi.");
                return true;
            default:
                System.out.println("Pilihan tidak valid.");
                break;
        }

        return false;
    }

    private void registerAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Pendaftaran Akun ===");
        System.out.print("Masukkan nama akun: ");
        String accountName = scanner.nextLine();
        setAccountName(accountName);

        System.out.print("Masukkan username: ");
        String username = scanner.nextLine();
        setUsername(username);

        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();
        setPassword(password);

        System.out.print("Masukkan ID pengguna: ");
        String userID = scanner.nextLine();
        setUserID(userID);

        System.out.println("Akun berhasil didaftarkan!");
        System.out.println("Selamat datang, " + accountName + "!");
        System.out.println();
    }

    private void paskabayarPrabayar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Paskabayar atau Prabayar ===");
        System.out.println("1. Paskabayar");
        System.out.println("2. Prabayar");
        System.out.print("Pilih jenis layanan: ");
        int jenisLayanan = scanner.nextInt();

        if (jenisLayanan == 1) {
            isPaskabayar = true;
            isPrabayar = false;
            System.out.println("Anda telah memilih layanan Paskabayar.");
        } else if (jenisLayanan == 2) {
            isPrabayar = true;
            isPaskabayar = false;
            System.out.println("Anda telah memilih layanan Prabayar.");
        } else {
            System.out.println("Pilihan tidak valid.");
        }

        System.out.println();
    }

    private void masukkanNomorIDPelanggan() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Masukkan Nomor ID Pelanggan ===");
        System.out.print("Masukkan nomor ID pelanggan: ");
        String customerID = scanner.nextLine();
        BillingApp.customerID = customerID;

        System.out.println("Nomor ID pelanggan berhasil disimpan.");
        System.out.println();
    }

    private void displayTagihan() {
        System.out.println("=== Display Tagihan ===");
        // Kode untuk menampilkan tagihan
        System.out.print("Masukkan nominal tagihan: ");
        Scanner scanner = new Scanner(System.in);
        double nominalTagihan = scanner.nextDouble();
        System.out.println("Nominal tagihan: " + nominalTagihan);
        System.out.println();
        isTagihanDitampilkan = true;

        // Simpan transaksi ke dalam array
        transaksi[jumlahTransaksi][0] = customerID;
        transaksi[jumlahTransaksi][1] = "Tanggal"; // Ganti dengan tanggal yang sesuai
        transaksi[jumlahTransaksi][2] = String.valueOf(nominalTagihan);
        transaksi[jumlahTransaksi][3] = "Metode Pembayaran"; // Ganti dengan metode pembayaran yang sesuai
        jumlahTransaksi++;
    }

    private void lanjutkanPembayaran() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Lanjutkan Pembayaran ===");
        System.out.println("1. Virtual Account");
        System.out.println("2. Kartu Debit Instan");
        System.out.println("3. Dompet digital");
        System.out.println("4. Cash");
        System.out.println("5. Kembali ke menu utama");
        System.out.print("Pilih menu: ");
        int menuChoice = scanner.nextInt();

        switch (menuChoice) {
            case 1:
            case 2:
            case 3:
            case 4:
                if (isTagihanDitampilkan) {
                    System.out.println("Pembayaran dilakukan.");
                    isPembayaranLanjut = true;
                } else {
                    System.out.println("Anda belum menampilkan tagihan. Silakan pilih menu 'Display Tagihan' terlebih dahulu.");
                }
                break;
            case 5:
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                break;
        }

        System.out.println();
    }

    private void gantiMetodePembayaran() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Ganti Metode Pembayaran ===");
        System.out.println("Pilih metode pembayaran:");
        System.out.println("1. Virtual Account");
        System.out.println("2. Kartu Debit Instan");
        System.out.println("3. Dompet digital");
        System.out.println("4. Cash");
        System.out.print("Masukkan pilihan: ");
        metodePembayaran = scanner.nextInt();
        System.out.println("Metode pembayaran berhasil diubah.");
        System.out.println();
    }

    private void tampilkanBatasWaktuPembayaran() {
        System.out.println("=== Batas Waktu Pembayaran ===");
        // Kode untuk menampilkan batas waktu pembayaran
        System.out.println("Batas waktu pembayaran: 30 hari setelah tagihan diterbitkan");
        System.out.println();
    }

    private void lihatTransaksi() {
        System.out.println("=== Transaksi ===");
        System.out.println("Daftar Transaksi:");
        for (int i = 0; i < jumlahTransaksi; i++) {
            System.out.println("Transaksi ke-" + (i + 1) + ":");
            System.out.println("Nomor ID Pelanggan: " + transaksi[i][0]);
            System.out.println("Tanggal: " + transaksi[i][1]);
            System.out.println("Nominal Tagihan: " + transaksi[i][2]);
            System.out.println("Metode Pembayaran: " + transaksi[i][3]);
            System.out.println();
        }

        if (jumlahTransaksi == 0) {
            System.out.println("Belum ada transaksi.");
        }

        System.out.println();
    }
}

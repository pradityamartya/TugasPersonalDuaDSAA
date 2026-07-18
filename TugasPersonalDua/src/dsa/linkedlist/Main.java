// Deklarasi package
package dsa.linkedlist;

//import library Scanner
import java.util.Scanner;

// Deklarasi class Node
class Node {
	String kodeBuku;
	String judulBuku;
	String penulisBuku;
	Node next;
	
	// Constructor
    Node(String kodeBuku, String judulBuku, String penulisBuku) {
        this.kodeBuku = kodeBuku;
        this.judulBuku = judulBuku;
        this.penulisBuku = penulisBuku;
        this.next = null;
    }
}

// Deklarasi class LinkedList    
class LinkedList {
	private Node head = null;
	
	// Method untuk Menghitung Total Buku Saat Ini
    public int hitungTotalBuku() {
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    // Method untuk Menambah Buku
    public void tambahBuku(Scanner inputBuku) {
        String kode;
        
        // Validasi Input Kalau Kode Buku Maksimal 5 Karakter
        while (true) {
            System.out.print("Masukkan Kode Buku: ");
            kode = inputBuku.next();
            if (kode.length() <= 5) {
                break;
            }
            System.out.println("[Error] Kode buku maksimal 5 karakter! Silahkan masukkan lagi.");
        }
        
        // Input Data Buku
        inputBuku.nextLine(); // Membersihkan buffer setelah next()
        System.out.print("Masukkan Judul: ");
        String judul = inputBuku.nextLine();
        System.out.print("Masukkan Penulis: ");
        String penulis = inputBuku.nextLine();
        
        // Membuat Node Baru
        Node newNode = new Node(kode, judul, penulis);
        
        if (head == null) {
            head = newNode;
        } else {

            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        System.out.println("Data berhasil ditambahkan!");
    }

    // Method untuk Menghapus Buku
    public void hapusBuku() {
        if (head == null) {
            System.out.println("Tidak ada data untuk dihapus.");
            return;
        }
        
        if (head.next == null) {
            head = null;
        } else {

            Node temp = head;
            while (temp.next.next != null) {
                temp = temp.next;
            }

            temp.next = null;
        }
        System.out.println("Buku terakhir berhasil dihapus.");
    }

    // Method untuk Mencari Buku Berdasarkan Kode Buku
    public void cariBuku(Scanner inputBuku) {
        if (head == null) {
            System.out.println("Daftar buku kosong.");
            return;
        }
        
        System.out.print("Masukkan Kode Buku yang dicari: ");
        String kode = inputBuku.next();
        
        Node temp = head;
        boolean ditemukan = false;
        
        while (temp != null) {
            if (temp.kodeBuku.equalsIgnoreCase(kode)) {
                System.out.println("\nBuku Ditemukan:");
                System.out.println("Kode: " + temp.kodeBuku + " | Judul: " + temp.judulBuku + " | Penulis: " + temp.penulisBuku);
                ditemukan = true;
                break;
            }
            temp = temp.next;
        }
        if (!ditemukan) {
            System.out.println("Buku tidak ditemukan.");
        }
    }

    // Method untuk Menampilkan Daftar Buku
    public void tampilkanDataBuku() {
        if (head == null) {
            System.out.println("Daftar Buku:");
            System.out.println("(Kosong)");
            System.out.println("Total Buku: 0");
            return;
        }
        
        System.out.println("Daftar Buku:");
        Node temp = head;
        
        while (temp != null) {
            System.out.println("Kode: " + temp.kodeBuku + " | Judul: " + temp.judulBuku + " | Penulis: " + temp.penulisBuku);
            temp = temp.next;
        }
        System.out.println("Total Buku: " + hitungTotalBuku());
    }
}

// Deklarasi class Main
public class Main {
	public static void main(String[] args) {
		// Membuat Object Scanner untuk Menerima Input
		Scanner inputBuku = new Scanner(System.in);
		// Membuat Object LinkedList untuk Me-manage Buku
		LinkedList list = new LinkedList();
		
		int daftar;
        
		// Loop untuk Menu Utama yang bisa Dipilih Pengguna
        do {
            System.out.println("===== SISTEM DATA BUKU =====");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Hapus Buku");
            System.out.println("3. Cari Buku");
            System.out.println("4. Lihat Semua Buku");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            
            // Validasi Input Menu agar Tidak Error Jika Pengguna Salah Input
            while (!inputBuku.hasNextInt()) {
                System.out.println("[Error] Masukkan angka pilihan menu!");
                System.out.print("Pilih menu: ");
                inputBuku.next();
            }
            daftar = inputBuku.nextInt();
            inputBuku.nextLine();
            System.out.println();
            
            // Menu yang bisa Dipilih Pengguna dari Angka 1-5
            switch (daftar) {
                // Menu Tambah Buku dan Dipilih dengan Memasukkan Angka 1
                case 1:
                    list.tambahBuku(inputBuku);
                    System.out.println();
                    break;
                // Menu Hapus Buku dan Dipilih dengan Memasukkan Angka 2
                case 2:
                    list.hapusBuku();
                    System.out.println();
                    break;
                // Menu Cari Buku dan Dipilih dengan Memasukkan Angka 3
                case 3:
                    list.cariBuku(inputBuku);
                    System.out.println();
                    break;
                // Menu Tampilkan Semua Buku dan Dipilih dengan Memasukkan Angka 4                    
                case 4:
                    list.tampilkanDataBuku();
                    System.out.println();
                    break;
                // Menu Keluar dan Dipilih dengan Memasukkan Angka 5
                case 5:
                    if (list.hitungTotalBuku() < 5) {
                        System.out.println("[Peringatan] Jumlah buku saat ini kurang dari 5.");
                        System.out.println("Jumlah saat ini : " + list.hitungTotalBuku());
                    }

                    System.out.println("Keluar dari program. Terima kasih!");
                    System.out.println();
                    break;
                // Menu yang Akan Berjalan Jika Pengguna Memasukkan Angka Selain 1-5
                default:
                    System.out.println("Pilihan menu tidak tersedia!");
            }
        } while (daftar != 5);
        
	    // Menutup Scanner
        inputBuku.close();
    }
}
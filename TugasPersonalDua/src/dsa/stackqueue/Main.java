// Deklarasi package
package dsa.stackqueue;

// Import library Scanner
import java.util.Scanner;

// Deklarasi class Node
class Node {
	String nomorAntrian;
	String namaPelanggan;
	double totalBelanja;
	Node next;
	
	// Constructor
	Node(String nomorAntrian, String namaPelanggan, double totalBelanja) {
		this.nomorAntrian = nomorAntrian;
		this.namaPelanggan = namaPelanggan;
		this.totalBelanja = totalBelanja;
		this.next = null;
	}
}

// Deklarasi class Queue
class Queue {
	private Node front = null;
	private Node rear = null;
	
    // Method untuk Mengecek Apakah Queue Kosong
    boolean isEmpty() {
        return front == null;
    }

    // Method untuk Menambah Elemen (enqueue)
    public void enqueue(String nomor, String nama, double total) {
        Node updateNode = new Node(nomor, nama, total);
        if (rear == null) {
            front = rear = updateNode;
            return;
        }

        rear.next = updateNode;
        rear = updateNode;
    }

    // Method untuk Menghapus Elemen (dequeue)
    public Node dequeue() {
        if (isEmpty()) {
            System.out.println("Tidak Ada");
            return null;
        }

        Node temp = front;
        front = front.next;

        if (front == null)
            rear = null;
        
        temp.next = null;
        return temp;
    }
    
    // Menampilkan Isi Queue
    public void display() {
        if (isEmpty()) {
            System.out.println("Antrian Kosong");
            return;
        }

        Node temp = front;
        while (temp != null) {
            System.out.println(temp.nomorAntrian + " | " + temp.namaPelanggan + " | " + temp.totalBelanja);
            temp = temp.next;
        }
        System.out.println();
    }
    
    // Menghitung Jumlah Antrian
	public int count() {
		int jumlah = 0;
		Node temp = front;
		
        while (temp != null) {
        	jumlah++;
            temp = temp.next;
        }
        return jumlah;
	}
}

// Deklarasi class Stack
class Stack {
	private Node top = null;
	
    // Method untuk Mengecek Apakah Stack Kosong
    boolean isEmpty() {
        return top == null;
    }
	
    // Method untuk Menambah Elemen (push)
	public void push(Node current) {
		current.next = top;
		top = current;
	}
	
	// Method untuk Menampilkan Transaksi
	public void display() {
        if (isEmpty()) {
            System.out.println("Antrian Kosong");
            return;
        }

        Node temp = top;
        while (temp != null) {
            System.out.println(temp.nomorAntrian + " | " + temp.namaPelanggan + " | " + temp.totalBelanja);
            temp = temp.next;
        }
	}
}

//Deklarasi class Main
public class Main {
		public static void main(String[] args) {
			// Membuat Object Scanner untuk Menerima Input
			Scanner inputPilihan = new Scanner(System.in);
			// Membuat Object Queue dan Stack untuk Antrian Pelanggan dan Riwayat Transaksi
			Queue updateQueue = new Queue();
			Stack updateStack = new Stack();
			// Status apakah pelayanan sudah boleh dimulai
	        boolean waktuPelayanan = false;
			
			int daftar;
	        
			// Loop untuk Menu Utama yang bisa Dipilih Pengguna
	        do {
	            System.out.println("===== SISTEM KASIR TOKO =====");
	            System.out.println("1. Tambah Antrian");
	            System.out.println("2. Layani Pelanggan");
	            System.out.println("3. Tampilkan Antrian");
	            System.out.println("4. Lihat Riwayat Transaksi");
	            System.out.println("5. Keluar");
	            System.out.print("Pilih menu: ");
	            
	            // Validasi Input Menu agar Tidak Error Jika Pengguna Salah Input
	            while (!inputPilihan.hasNextInt()) {
	                System.out.println("[Error] Masukkan angka pilihan menu!");
	                System.out.print("Pilih menu: ");
	                inputPilihan.next();
	            }
	            daftar = inputPilihan.nextInt();
	            inputPilihan.nextLine();
	            System.out.println();
	        
	            // Menu yang bisa Dipilih Pengguna dari Angka 1-5
	            switch (daftar) {
	            // Menu Tambah Antrian dan Dipilih dengan Memasukkan Angka 1
                case 1:
                    System.out.print("Masukkan Nomor Antrian : ");
                    String nomor = inputPilihan.nextLine();
                    System.out.print("Masukkan Nama Pelanggan : ");
                    String nama = inputPilihan.nextLine();
                    System.out.print("Masukkan Total Belanja : ");
                    double total = inputPilihan.nextDouble();                    
                    inputPilihan.nextLine();
                    updateQueue.enqueue(nomor, nama, total);
                    System.out.println("Data pelanggan ditambahkan ke antrian!");
                    System.out.println();
                    break;
    	        // Menu Layani Pelanggan dan Dipilih dengan Memasukkan Angka 2
                case 2:
                    if (!waktuPelayanan) {
                        if (updateQueue.count() < 5) {
                            System.out.println("Minimal harus terdapat 5 pelanggan dalam antrian.");
                            System.out.println("Jumlah saat ini : " + updateQueue.count());
                            System.out.println();
                            break;
                        }
                        waktuPelayanan = true;
                    }
                    Node usai = updateQueue.dequeue();
                    if (usai == null) {
                        System.out.println("Antrian kosong.");
                    } else {
                        System.out.println("Melayani pelanggan "
                                + usai.nomorAntrian + " (" + usai.namaPelanggan + ")");
                        updateStack.push(usai);
                        System.out.println("Transaksi disimpan ke riwayat.");
                    }
                    System.out.println();
                    break;
    	        // Menu Tampilkan Antrian dan Dipilih dengan Memasukkan Angka 3
                case 3:
                    System.out.println("\n=== DAFTAR ANTRIAN ===");
                    updateQueue.display();
                    System.out.println("Total Antrian : " + updateQueue.count());
                    System.out.println();
                    break;
    	        // Menu Lihat Riwayat Transaksi dan Dipilih dengan Memasukkan Angka 4                    
                case 4:
                    System.out.println("\n=== RIWAYAT TRANSAKSI ===");
                    updateStack.display();
                    System.out.println();
                    break;
                // Menu Keluar dan Dipilih dengan Memasukkan Angka 5
                case 5:
                	System.out.println("Keluar dari program. Terima kasih!");
                    break;
                // Menu yang Akan Berjalan Jika Pengguna Memasukkan Angka Selain 1-5
                default:
                    System.out.println("Menu tidak tersedia.");
            }
        } while (daftar != 5);

	    // Menutup Scanner
        inputPilihan.close();
	}
}	     
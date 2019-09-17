import java.util.*;

final public class Matriks {
    Scanner scanner = new Scanner(System.in); // Untuk Input
    private final int baris; // Baris
    private final int kolom; // Kolom
    public double[][] Mat; // isi array, double biar threadsafe
    // Manggil isi matriks-nya matriks.Mat[baris][kolom]

    // Konstruktor
    public Matriks(int baris, int kolom) {
        this.baris = baris;
        this.kolom = kolom;
        this.Mat = new double[baris][kolom];
    }

    // Baca Matriks
    public void BacaMat() {
        for (int i = 0; i < this.baris; i++) {
            for (int j = 0; j < this.kolom; j++) {
                this.Mat[i][j] = scanner.nextInt();
            }
        }
        System.out.print("\n");
    }

    // Menulis Matriks
    public void TulisMat() {
        for (int i = 0; i < this.baris; i++) {
            for (int j = 0; j < this.kolom; j++) {
                System.out.printf("%.2f ", this.Mat[i][j]);
            }
            System.out.print("\n");
        }
    }

    // Nuker baris
    private void swap(int a, int b) {
        double[] temp = Mat[a];
        Mat[a] = Mat[b];
        Mat[b] = temp;
    }

    double determinan(Matriks M) {
        return 0;
    }
}

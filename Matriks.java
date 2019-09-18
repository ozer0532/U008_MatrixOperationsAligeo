import java.util.*;

final public class Matriks {
    Scanner scanner = new Scanner(System.in); // Untuk Input
    private final int Baris; // Baris
    private final int Kolom; // Kolom
    public double[][] Mat; // isi array, double biar threadsafe
    // Manggil isi matriks-nya matriks.Mat[Baris][Kolom]
    // Indeks Baris & Kolom mulai dari 0

    // Konstruktor
    public Matriks(int baris, int kolom) {
        this.Baris = baris;
        this.Kolom = kolom;
        this.Mat = new double[baris][kolom];
    }

    // Kalo langsung dikasih Mat
    public Matriks(double[][] mat){
        this.Baris = mat.length;
        this.Kolom = mat[0].length;
        this.Mat = new double[mat.length][mat[0].length];
        for (int i=0; i < mat.length; i++)
            for (int j=0; j < mat[0].length; j++)
                this.Mat[i][j] = mat[i][j];
    }

    public static Matriks Identitas(int N) {
        Matriks I = new Matriks(N, N);
        for (int i = 0; i < N; i++)
            I.Mat[i][i] = 1;
        return I;
    }

    // Baca Matriks
    public void BacaMat() {
        for (int i = 0; i < this.Baris; i++) {
            for (int j = 0; j < this.Kolom; j++) {
                this.Mat[i][j] = scanner.nextInt();
            }
        }
        System.out.print("\n");
    }

    // Menulis Matriks
    public void TulisMat() {
        for (int i = 0; i < this.Baris; i++) {
            for (int j = 0; j < this.Kolom; j++) {
                System.out.printf("%.2f ", this.Mat[i][j]);
            }
            System.out.print("\n");
        }
    }

    // Nuker Baris
    public void Swap(int a, int b) {
        double[] temp = Mat[a];
        Mat[a] = Mat[b];
        Mat[b] = temp;
    }

    // Perkalian Baris dengan sebuah integer
    public void KaliBaris(int n, int a){
        for(int i = 0; i <= Kolom; i++){
            Mat[n][i] *= a;
        }
    }

    // Penjumlahan Baris
    // Baris ke-a ditambah dengan bilangan di baris ke-b
    public void PlusBaris(int a, int b){
        for(int i = 0; i <= Kolom; i++){
            Mat[a][i] += Mat[b][i];
        }
    }

    // Pengurangan Baris
    // Baris ke-a dikurangi dengan bilangan di baris ke-b
    public void MinusBaris(int a, int b){
        for(int i = 0; i <= Kolom; i++){
            Mat[a][i] -= Mat[b][i];
        }
    }

    // Determinan
    // I.S : Martriks valid persegi
    public double Determinan(Matriks M) {
        if ((M.Baris == 1) && (M.Kolom == 1)){ // Untuk Matriks 2x2, buat rekursif
            return M.Mat[0][0]*M.Mat[1][1] - M.Mat[0][1]*M.Mat[1][0];
        }
        else {
            return 0;
        }
    }
}

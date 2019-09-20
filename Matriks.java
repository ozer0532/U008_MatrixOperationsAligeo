import java.util.*;

final public class Matriks {
    Scanner scanner = new Scanner(System.in); // Untuk Input
    public int KolMin = 0;
    public int BrsMin = 0;
    public int KolMax = 100;
    public int BrsMax = 100;
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

    public Matriks(double[][] Mat) {
        // Konstruktor dari tabel
        this.Baris = Mat.length;
        this.Kolom = Mat[0].length;
        this.Mat = new double[Mat.length][Mat[0].length];
        for (int i = 0; i < Mat.length; i++)
            for (int j = 0; j < Mat[0].length; j++)
                this.Mat[i][j] = Mat[i][j];
    }

    // Selektor
    public int GetFirstIdxBrs(Matriks M) {
        return BrsMin;
    }

    public int GetFirstIdxKol(Matriks M) {
        return KolMin;
    }

    public int GetLastIdxBrs(Matriks M) {
        return M.Baris - 1;
    }

    public int GetLastIdxKol(Matriks M) {
        return M.Kolom - 1;
    }

    public int NbElmt(Matriks M) {
        return (M.Baris * M.Kolom);
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
    public void Swap(int Brs1, int Brs2) {
        double[] temp = Mat[Brs1];
        Mat[Brs1] = Mat[Brs2];
        Mat[Brs2] = temp;
    }

    public void KaliBaris(int Baris, double x) {
        // Perkalian Baris dengan sebuah bilangan real
        for (int i = 0; i < Kolom; i++) {
            Mat[Baris][i] *= x;
        }
    }

    public void PlusBaris(int Baris1, int Baris2) {
        // Baris ke-a ditambah dengan bilangan di baris ke-b
        for (int i = 0; i < Kolom; i++) {
            Mat[Baris1][i] += Mat[Baris2][i];
        }
    }

    public void MinusBaris(int a, int b) {
        // Baris ke-a dikurangi dengan bilangan di baris ke-b
        for (int i = 0; i < Kolom; i++) {
            Mat[a][i] -= Mat[b][i];
        }
    }

    
    private Matriks Minor(Matriks M, int i, int j) {
        // Minor M(i,j) dari matriks M
        Matriks Minor = new Matriks(M.Baris - 1, M.Kolom - 1);
        int iMi, jMi, iM, jM;
        iMi = GetFirstIdxBrs(Minor);
        for (iM = GetFirstIdxBrs(M); iM <= GetLastIdxBrs(M); iM++)
            if (iM != i) {
                jMi = GetFirstIdxKol(Minor);
                for (jM = GetFirstIdxKol(M); jM <= GetLastIdxKol(M); jM++)
                    if (jM != GetFirstIdxKol(M)) {
                        Minor.Mat[iMi][jMi] = M.Mat[iM][jM];
                        jMi++;
                    }
                iMi++;
            }
        return Minor;
    }

    // Determinan
    public double Determinan() {
        /* Prekondisi: M bujur sangkar */
        /* Menghitung nilai determinan M */
        double det;

        if ((this.Baris == 1) && (this.Kolom == 1)) // Basis 1x1
            det = this.Mat[0][0];
        else { // Rekurens nxn
            det = 0;
            for (int i = GetFirstIdxBrs(this); i <= GetLastIdxBrs(this); i++)
                det += (i % 2 == 0 ? 1 : -1) * this.Mat[i][GetFirstIdxKol(this)]
                        * Minor(this, i, GetFirstIdxKol(this)).Determinan();
        }

        return det;
    }

    private Matriks Cofaktor(Matriks M){
        Matriks temp = new Matriks(M.Baris, M.Kolom);
        for (int i = 0; i < M.Baris; i++)
            for (int j = 0; i < M.Kolom; j++){
                
            }
        return temp;
    }

    public static void EliminasiGauss(Matriks M) {
        
        for (int i = M.GetFirstIdxKol(M); i <= M.GetLastIdxKol(M); i++) {
            // Ubah angka depan jadi 1
            M.KaliBaris(i, 1/M.Mat[i][i + M.GetFirstIdxKol(M)]);
            M.TulisMat();

            // Pengurangan baris dibawahnya
            for (int j = i + 1; j <= M.GetLastIdxKol(M); j++) {   
                M.KaliBaris(j, 1/M.Mat[j][i + M.GetFirstIdxKol(M)]);
                M.TulisMat();
                M.MinusBaris(j, i);
            }
            System.out.print("\n");
        }
        
    }
}

import java.util.*;

public class Matriks {
    Scanner scanner = new Scanner(System.in); // Untuk Input
    public int KolMin = 0;
    public int BrsMin = 0;
    public int KolMax = 100;
    public int BrsMax = 100;
    private int Baris; // Baris
    private int Kolom; // Kolom
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
                this.Mat[i][j] += 0; // Agar tidak ada (-0)
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

    public void PlusBaris(int Baris1, int Baris2, double k) {
        // Baris ke-a ditambah dengan bilangan di baris ke-b
        for (int i = 0; i < Kolom; i++) {
            Mat[Baris1][i] += Mat[Baris2][i] * k;
        }
    }

    public void PlusBaris(int Baris1, int Baris2) {
        // Baris ke-a ditambah dengan bilangan di baris ke-b
        PlusBaris(Baris1, Baris2, 1);
    }

    public void MinusBaris(int a, int b, double k) {
        // Baris ke-a dikurangi dengan bilangan di baris ke-b * k
        PlusBaris(a, b, -k);
    }

    public void MinusBaris(int a, int b) {
        // Baris ke-a dikurangi dengan bilangan di baris ke-b
        MinusBaris(a, b, 1);
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
    public double DeterminanCofaktor(Matriks M) {
        /* Prekondisi: M bujur sangkar */
        /* Menghitung nilai determinan M */
        double det;

        if ((M.Baris == 1) && (M.Kolom == 1)) // Basis 1x1
            det = M.Mat[0][0];
        else { // Rekurens nxn
            det = 0;
            for (int i = GetFirstIdxBrs(M); i <= GetLastIdxBrs(M); i++)
                det += M.Mat[i][GetFirstIdxKol(M)] * Cofaktor(M, i, GetFirstIdxKol(M));
        }

        return det;
    }

    private double Cofaktor(Matriks M, int i, int j) {
        return DeterminanCofaktor(Minor(M, i, j)) * (((i+j)%2==0)?1:-1);
    }

    public void EliminasiGauss(Matriks M) {
        // I.S. M terdefinisi
        // F.S. M diubah menjadi matriks eselonnya
        // Proses: Eliminasi Gauss
        
        // Proses mengurutkan baris
        int[] zeroCount = new int[M.Baris];
        for (int i = 0; i < M.Baris; i++) {         // Kalkulasi jumlah 0
            zeroCount[i] = 0;
            int j = 0;
            while (j < M.Kolom && M.Mat[i][j] == 0) {
                zeroCount[i]++;
                j++;
            }
        }
        for (int i = 0; i < M.Baris; i++) {         // Algoritma Pengurutan
            for (int j = 0; j < M.Baris - 1; j++) {
                if (zeroCount[j] > zeroCount[j+1]) {
                    int temp;
                    M.Swap(j, j+1);
                    temp = zeroCount[j];
                    zeroCount[j] = zeroCount[j+1];
                    zeroCount[j+1] = temp;
                }
            }
        }

        // Proses mereduksi baris
        int indent = 0;

        for (int i = 0; i < M.Baris; i++) {
            // Mencari sel bernilai
            while (i + indent < M.Kolom && M.Mat[i][i + indent] == 0) {
                indent++;
            }
                
            if (i + indent < M.Kolom) {
                // Ubah angka depan jadi 1
                M.KaliBaris(i, 1 / M.Mat[i][i + indent]);

                // Pengurangan baris dibawahnya
                for (int j = i + 1; j < Baris; j++) {
                    if (M.Mat[j][i + indent] != 0) {
                        M.KaliBaris(j, 1 / M.Mat[j][i + indent]);
                        M.MinusBaris(j, i);
                    }
                }
            }
        }
    }

    public void EliminasiGaussJordan(Matriks M) {
        // I.S. M terdefinisi
        // F.S. M diubah menjadi matriks eselon-terreduksinya
        // Proses: Eliminasi Gauss Jordan
        EliminasiGauss(M);
        int indent = 0;

        for (int i = 0; i < Baris; i++) {
            // Pencarian sel tidak nol
            while (i + indent < M.Kolom && M.Mat[i][i + indent] == 0) {
                indent++;
            }

            if (i + indent < M.Kolom) {

                // Pengurangan baris diatasnya
                for (int j = i - 1; j >= 0; j--) {
                    if (M.Mat[j][i + indent] != 0) {
                        M.MinusBaris(j, i, M.Mat[j][i + indent]);
                    }
                }
            }
        }
    }

    public void Transpose() {
        /* I.S. M terdefinisi dan IsBujursangkar(M) */
        /*
         * F.S. M "di-transpose", yaitu setiap elemen M(i,j) ditukar nilainya dengan
         * elemen M(j,i)
         */
        Matriks M1 = new Matriks(this.Kolom, this.Baris);

        for (int i = GetFirstIdxBrs(M1); i <= GetLastIdxBrs(M1); i++) {
            for (int j = GetFirstIdxKol(M1); j <= GetLastIdxKol(M1); j++) {
                M1.Mat[i][j] = this.Mat[j][i];
            }
        }

        this.Baris = M1.Baris;
        this.Kolom = M1.Kolom;
        this.Mat = M1.Mat;
    }

    public void MatCofaktor(){
        Matriks M = new Matriks(this.Kolom, this.Baris);
        for (int i = GetFirstIdxBrs(this); i <= GetLastIdxBrs(this); i++) 
            for (int j = GetFirstIdxKol(this); j <= GetLastIdxKol(this); j++) {
                M.Mat[i][j] = Cofaktor(this, i, j);
            }
        this.Mat = M.Mat;
    }

    // Masih salah hasilnya
    public void Adjoin(){
        this.MatCofaktor();
        this.Transpose();
    }
    
}

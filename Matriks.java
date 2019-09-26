import java.util.*;

//import jdk.nashorn.internal.parser.Scanner;

import java.io.*;

public class Matriks {
    public static final double decPoint = 10000000000d;

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

    /* ********** KONSTRUKTOR ********** */
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

    // Tolong di cek, size udah bener input blm mau
    public Matriks(String file_name) throws FileNotFoundException {
        ArrayList<ArrayList<Double>> Mat = new ArrayList<ArrayList<Double>>();
        File file = new File(file_name);
        Scanner input = new Scanner(file);
        int Baris = -1;
        int Kolom = -1;
        while (input.hasNextLine()) {
            Baris++;
            Mat.add(new ArrayList<Double>());
            String baris = input.nextLine();
            Scanner scanBaris = new Scanner(baris);
            while (scanBaris.hasNextDouble()) {
                Double element = scanBaris.nextDouble();
                Mat.get(Baris).add(element);
            }
        }

        if (Baris == 0) {
            System.out.println("Tidak dapat membaca file");
        } else {
            Kolom = Mat.get(0).size();
            this.Mat = new double[Mat.size()][Mat.get(0).size()];
            for (int i = BrsMin; i <= Baris; i++) {
                for (int j = KolMin; j < Kolom; j++) {
                    this.Mat[i][j] = Mat.get(i).get(j);
                }
            }
            this.Baris = Baris + 1;
            this.Kolom = Kolom;
        }
    }

    /* ********** SELEKTOR ********** */
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

    /* ********** TIPE MATRIKS UMUM ********** */
    public static Matriks Identitas(int N) {
        Matriks I = new Matriks(N, N);
        for (int i = 0; i < N; i++)
            I.Mat[i][i] = 1;
        return I;
    }

    public static Matriks Hilbert(int N) {
        Matriks H = new Matriks(N, N+1);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                H.Mat[i][j] = 1.0 / (i + j + 1);
            }
        }
        return H;
    }

    /* ********** INPUT/OUTPUT MATRIKS ********** */
    // Baca Matriks
    public void BacaMat() {
        for (int i = 0; i < this.Baris; i++) {
            for (int j = 0; j < this.Kolom; j++) {
                this.Mat[i][j] = scanner.nextDouble();
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

    /* ********** OPERASI MATRIKS DASAR ********** */
    public static Matriks Kali(Matriks M, double k) {
        Matriks out = new Matriks(M.Baris, M.Kolom);
        for (int i = 0; i < M.Baris; i++) {
            for (int j = 0; j < M.Kolom; j++) {
                out.Mat[i][j] = M.Mat[i][j] * k;
            }
        }
        return out;
    }

    public void Kali(double k) {
        this.Mat = Kali(this, k).Mat;
    }

    public static Matriks Kali(Matriks M, Matriks N) {
        Matriks out = new Matriks(M.Baris, N.Kolom);

        for (int i = 0; i < out.Baris; i++) {
            for (int j = 0; j < out.Kolom; j++) {
                out.Mat[i][j] = 0;
                for (int k = 0; k < M.Kolom; k++) {
                    out.Mat[i][j] += M.Mat[i][k] * N.Mat[k][j];
                }
            }
        }

        return out;
    }

    /* ********** OPERASI BARIS ELEMENTER ********** */
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

    /* ********** SIFAT MATRIKS ********** */
    public static boolean IsIdentitas(Matriks M) {
        boolean out = true;
        for (int i = 0; i < M.Baris; i++) {
            for (int j = 0; j < M.Kolom; j++) {
                if (!(((i == j) && M.Mat[i][j] == 1) || ((i != j) && M.Mat[i][j] == 0))) {
                    out = false;
                }
            }
        }
        return out;
    }

    /* ********** FUNGSI SKALAR ********** */
    // Determinan
    public double DeterminanOBE(Matriks M) {
        Matriks N = Copy(M);

        // Proses mengurutkan baris
        int[] zeroCount = new int[N.Baris];
        int swapCount = 0;
        for (int i = 0; i < N.Baris; i++) { // Kalkulasi jumlah 0
            zeroCount[i] = 0;
            int j = 0;
            while (j < N.Kolom && N.Mat[i][j] == 0) {
                zeroCount[i]++;
                j++;
            }
        }
        for (int i = 0; i < N.Baris; i++) { // Algoritma Pengurutan
            for (int j = 0; j < N.Baris - 1; j++) {
                if (zeroCount[j] > zeroCount[j + 1]) {
                    int temp;
                    N.Swap(j, j + 1);
                    swapCount++;
                    temp = zeroCount[j];
                    zeroCount[j] = zeroCount[j + 1];
                    zeroCount[j + 1] = temp;
                }
            }
        }
        // Proses mereduksi baris
        int indent = 0;

        for (int i = 0; i < N.Baris; i++) {
            // Mencari sel bernilai
            while (i + indent < N.Kolom && N.Mat[i][i + indent] == 0) {
                indent++;
            }

            if (i + indent < N.Kolom) {
                // Pengurangan baris dibawahnya
                for (int j = i + 1; j < N.Baris; j++) {
                    N.MinusBaris(j, i, N.Mat[j][i + indent] / N.Mat[i][i + indent]);

                }
            }
        }

        // Proses menghitung jumlah diagonal
        double det = N.Mat[0][0];
        for (int i = 1; i < N.Baris; i++) {
            det *= N.Mat[i][i];
        }
        det *= ((swapCount & 2) == 0) ? 1 : -1;
        return det;
    }

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
        return DeterminanCofaktor(Minor(M, i, j)) * (((i + j) % 2 == 0) ? 1 : -1);
    }

    /* ********** MANIPULASI MATRIKS ********** */
    private Matriks Minor(Matriks M, int i, int j) {
        // Minor M(i,j) dari matriks M
        Matriks Minor = new Matriks(M.Baris - 1, M.Kolom - 1);
        int iMi, jMi, iM, jM;
        iMi = GetFirstIdxBrs(Minor);
        for (iM = GetFirstIdxBrs(M); iM <= GetLastIdxBrs(M); iM++)
            if (iM != i) {
                jMi = GetFirstIdxKol(Minor);
                for (jM = GetFirstIdxKol(M); jM <= GetLastIdxKol(M); jM++)
                    if (jM != j) {
                        Minor.Mat[iMi][jMi] = M.Mat[iM][jM];
                        jMi++;
                    }
                iMi++;
            }
        return Minor;
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

    public void MatCofaktor() {
        Matriks M = new Matriks(this.Kolom, this.Baris);
        for (int i = GetFirstIdxBrs(this); i <= GetLastIdxBrs(this); i++)
            for (int j = GetFirstIdxKol(this); j <= GetLastIdxKol(this); j++) {
                M.Mat[i][j] = Cofaktor(this, i, j);
            }
        this.Mat = M.Mat;
    }

    public void Adjoin() {
        if (this.NbElmt(this) != 1) {
            this.MatCofaktor();
            this.Transpose();
        } else {
            this.Mat[0][0] = 1;
        }
    }

    public static boolean InversGaussJordan(Matriks in, Matriks out) {
        // in terdefinisi dan IsBujurSangkar(in), Program menghasilkan invers dari in
        // dengan Eliminasi Gauss-Jordan
        // Jika gagal maka out = in
        Matriks M = Copy(in);

        M = ConcatHorizontally(M, Identitas(M.Baris));
        M = EliminasiGaussJordan(M);

        Matriks N = new Matriks(in.Baris, in.Kolom);
        for (int i = 0; i < N.Baris; i++) {
            for (int j = 0; j < N.Kolom; j++) {
                N.Mat[i][j] = M.Mat[i][j];
            }
        }

        if (IsIdentitas(N)) {
            Copy(in, out);

            for (int i = 0; i < out.Baris; i++) {
                for (int j = 0; j < out.Kolom; j++) {
                    out.Mat[i][j] = M.Mat[i][j + out.Kolom];
                }
            }
            return true;
        } else {
            Copy(in, out);
            return false;
        }
    }

    public boolean InversAdjoin(Matriks in, Matriks out) {
        Copy(in, out);
        double det = DeterminanCofaktor(out);
        if (det != 0) {
            out.Adjoin();
            out.Kali(1 / det);
            return true;
        } else {
            return false;
        }
    }

    public static Matriks EliminasiGauss(Matriks in) {
        // I.S. M terdefinisi
        // F.S. M diubah menjadi matriks eselonnya
        // Proses: Eliminasi Gauss

        // Inisialisasi
        Matriks M = new Matriks(1, 1);
        M = Copy(in);

        // Proses mengurutkan baris
        int[] zeroCount = new int[M.Baris];
        for (int i = 0; i < M.Baris; i++) { // Kalkulasi jumlah 0
            zeroCount[i] = 0;
            int j = 0;
            while (j < M.Kolom && M.Mat[i][j] == 0) {
                zeroCount[i]++;
                j++;
            }
        }
        for (int i = 0; i < M.Baris; i++) { // Algoritma Pengurutan
            for (int j = 0; j < M.Baris - 1; j++) {
                if (zeroCount[j] > zeroCount[j + 1]) {
                    int temp;
                    M.Swap(j, j + 1);
                    temp = zeroCount[j];
                    zeroCount[j] = zeroCount[j + 1];
                    zeroCount[j + 1] = temp;
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
                for (int j = i + 1; j < M.Baris; j++) {
                    if (M.Mat[j][i + indent] != 0) {
                        M.KaliBaris(j, 1 / M.Mat[j][i + indent]);
                        M.MinusBaris(j, i);
                    }
                }
            }
        }
        M.Approximate();

        return M;
    }

    public static Matriks EliminasiGaussJordan(Matriks in) {
        // I.S. M terdefinisi
        // F.S. M diubah menjadi matriks eselon-terreduksinya
        // Proses: Eliminasi Gauss Jordan

        // Proses
        Matriks M = EliminasiGauss(in);
        int indent = 0;

        for (int i = 0; i < M.Baris; i++) {
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

        M.Approximate();
        return M;
    }

    public static void Copy(Matriks dari, Matriks ke) {
        // I.S. dari dan ke terdefinisi
        // F.S. ke berisi sama dengan dari
        ke.Baris = dari.Baris;
        ke.Kolom = dari.Kolom;
        ke.Mat = new double[dari.Baris][dari.Kolom];

        for (int i = 0; i < ke.Baris; i++) {
            for (int j = 0; j < ke.Kolom; j++) {
                ke.Mat[i][j] = dari.Mat[i][j];
            }
        }
    }

    // Varian fungsi dari Copy Matriks diatas
    public static Matriks Copy(Matriks dari) {
        Matriks ke = new Matriks(1, 1);
        Copy(dari, ke);
        return ke;
    }

    // Menyambungkan matriks M dan N
    public static Matriks ConcatHorizontally(Matriks M, Matriks N) {
        // M dan N terdefinisi dan jumlah baris sama, fungsi mengoutput hasil gabungan M
        // dan N
        Matriks out = new Matriks(M.Baris, M.Kolom + N.Kolom);

        for (int i = 0; i < out.Baris; i++) {
            for (int j = 0; j < out.Kolom; j++) {
                if (j < M.Kolom) {
                    out.Mat[i][j] = M.Mat[i][j];
                } else {
                    out.Mat[i][j] = N.Mat[i][j - M.Kolom];
                }
            }
        }

        return out;
    }

    // Menyambungkan matriks M dan N
    public static Matriks ConcatVertically(Matriks M, Matriks N) {
        // M dan N terdefinisi dan jumlah kolom sama, fungsi mengoutput hasil gabungan M
        // dan N
        Matriks out = new Matriks(M.Baris + N.Baris, M.Kolom);

        for (int i = 0; i < out.Baris; i++) {
            for (int j = 0; j < out.Kolom; j++) {
                if (i < M.Baris) {
                    out.Mat[i][j] = M.Mat[i][j];
                } else {
                    out.Mat[i][j] = N.Mat[i - M.Baris][j];
                }
            }
        }

        return out;
    }

    private static double Approximate(double x) {
        return (Math.round(x * decPoint) / decPoint);
    }

    private void Approximate() {
        for (int i = 0; i < Baris; i++) {
            for (int j = 0; j < Kolom; j++) {
                Mat[i][j] = Approximate(Mat[i][j]);
            }
        }
    }

    public static void TulisFile(String file, Matriks M) {
        try {
            File F = new File(file);
            if (!(F.exists())) {
                F.createNewFile();
            }

            FileWriter FF = new FileWriter(file);
            PrintWriter print = new PrintWriter(FF);

            for (int i = 0; i < M.Baris; i++) {
                for (int j = 0; j < M.Kolom; j++) {
                    M.Mat[i][j] += 0;
                    print.printf("%.2f ", M.Mat[i][j]);
                }
                print.printf("\n");
            }

            print.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void h(){
        this.Mat[BrsMin][GetLastIdxKol(this)] = 1;
    }
}

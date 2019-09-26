import java.util.*;

public class SPL {
    static Scanner scanner = new Scanner(System.in);

    public static void SPLGauss(String suffix) {
        System.out.print("Masukan m : ");
        int m = scanner.nextInt();
        System.out.print("Masukan n : ");
        int n = scanner.nextInt();
        Matriks M = new Matriks(m, n + 1);

        M.BacaMat();

        SPLGauss(M, suffix);

    }

    public static void SPLGauss(Matriks M, String suffix) {

        boolean tidakBernilai = false;

        int n = M.GetLastIdxKol(M);
        M = Matriks.EliminasiGauss(M);

        Expression[] hasil = new Expression[n];
        for (int i = 0; i < hasil.length; i++) {
            hasil[i] = new Expression();
        }

        for (int i = M.GetLastIdxBrs(M); i >= M.GetFirstIdxBrs(M); i--) {
            int idxPertama = -1;
            Expression value = new Expression();
            for (int j = M.GetFirstIdxKol(M); j <= M.GetLastIdxKol(M); j++) {
                if (M.Mat[i][j] != 0) {
                    if (j != n) {
                        if (hasil[j].IsEmpty()) { // Belum ada nilai
                            if (idxPertama == -1) { // Variabel baru pertama
                                idxPertama = j;
                            } else { // Buat variabel parametrik
                                value.SetVariable(hasil[j].GenerateNewVar(), -M.Mat[i][j]);
                            }
                        } else { // Sudah ada nilai
                            for (int k = 0; k < Nilai.varList.length(); k++) {
                                value.SetVariable(k,
                                        value.GetVariable(k).val - (hasil[j].GetVariable(k).val * M.Mat[i][j]));
                            }
                        }
                    } else {
                        value.SetConst(value.GetConst() + M.Mat[i][j]);
                    }
                }
            }
            if (idxPertama == -1) {
                if (M.Mat[i][n] != 0) {
                    tidakBernilai = true;
                }
            } else {
                hasil[idxPertama] = value;
            }
        }

        /*
         * ************************************** OUTPUT
         * **************************************
         */

        // Cek bernilai atau tidak
        if (tidakBernilai) {
            System.out.println("SPL tersebut tidak memiliki solusi");
        } else {
            for (int i = 0; i < hasil.length; i++) {
                System.out.println(suffix + (i + 1) + " = " + hasil[i].ToString());
            }
        }

        Expression.ResetVars();

    }

    public static void SPLGaussJordan(String suffix) {

        boolean tidakBernilai = false;
        System.out.print("Masukan m : ");
        int m = scanner.nextInt();
        System.out.print("Masukan n : ");
        int n = scanner.nextInt();
        Matriks M = new Matriks(m, n + 1);

        M.BacaMat();

        M = Matriks.EliminasiGaussJordan(M);

        Expression[] hasil = new Expression[n];
        for (int i = 0; i < hasil.length; i++) {
            hasil[i] = new Expression();
        }

        for (int i = M.GetLastIdxBrs(M); i >= M.GetFirstIdxBrs(M); i--) {
            int idxPertama = -1;
            Expression value = new Expression();
            for (int j = M.GetFirstIdxKol(M); j <= M.GetLastIdxKol(M); j++) {
                if (M.Mat[i][j] != 0) {
                    if (j != n) {
                        if (hasil[j].IsEmpty()) { // Belum ada nilai
                            if (idxPertama == -1) { // Variabel baru pertama
                                idxPertama = j;
                            } else { // Buat variabel parametrik
                                value.SetVariable(hasil[j].GenerateNewVar(), -M.Mat[i][j]);
                            }
                        } else { // Sudah ada nilai
                            for (int k = 0; k < Nilai.varList.length(); k++) {
                                value.SetVariable(k,
                                        value.GetVariable(k).val - (hasil[j].GetVariable(k).val * M.Mat[i][j]));
                            }
                        }
                    } else {
                        value.SetConst(value.GetConst() + M.Mat[i][j]);
                    }
                }
            }
            if (idxPertama == -1) {
                if (M.Mat[i][n] != 0) {
                    tidakBernilai = true;
                }
            } else {
                hasil[idxPertama] = value;
            }
        }

        /*
         * ************************************** OUTPUT
         * **************************************
         */

        // Cek bernilai atau tidak
        if (tidakBernilai) {
            System.out.println("SPL tersebut tidak memiliki solusi");
        } else {
            for (int i = 0; i < hasil.length; i++) {
                System.out.println(suffix + (i + 1) + " = " + hasil[i].ToString());
            }
        }

        Expression.ResetVars();

    }

    public static void SPLInvers(String suffix) {
        System.out.print("Masukan m : ");
        int n = scanner.nextInt();
        Matriks M = new Matriks(n, n + 1);

        M.BacaMat();

        // SPLIT INPUT
        Matriks A = new Matriks(n, n);
        for (int i = A.GetFirstIdxBrs(A); i <= A.GetLastIdxBrs(A); i++) {
            for (int j = A.GetFirstIdxKol(A); j <= A.GetLastIdxKol(A); j++) {
                A.Mat[i][j] = M.Mat[i][j];
            }
        }

        Matriks B = new Matriks(n, 1);
        for (int i = B.GetFirstIdxBrs(B); i <= B.GetLastIdxBrs(B); i++) {
            B.Mat[i][0] = M.Mat[i][n];
        }

        // OUTPUT
        Matriks AInvers = new Matriks(n, n);
        if (Matriks.InversGaussJordan(A, AInvers)) {
            Matriks X = Matriks.Kali(AInvers, B); // PROSES SEBELUM OUTPUT

            for (int i = X.GetFirstIdxBrs(X); i <= X.GetLastIdxBrs(X); i++) {
                System.out.println(suffix + (i + 1) + " = " + X.Mat[i][0]);
            }
        } else {
            System.out.println("SPL tersebut tidak memiliki solusi");
        }

    }

    public static void SPLCramer(String suffix) {
        System.out.print("Masukan m : ");
        int n = scanner.nextInt();
        Matriks M = new Matriks(n, n + 1);

        M.BacaMat();

        // SPLIT INPUT
        Matriks A = new Matriks(n, n);
        for (int i = A.GetFirstIdxBrs(A); i <= A.GetLastIdxBrs(A); i++) {
            for (int j = A.GetFirstIdxKol(A); j <= A.GetLastIdxKol(A); j++) {
                A.Mat[i][j] = M.Mat[i][j];
            }
        }

        Matriks B = new Matriks(n, 1);
        for (int i = B.GetFirstIdxBrs(B); i <= B.GetLastIdxBrs(B); i++) {
            B.Mat[i][0] = M.Mat[i][n];
        }

        // PROSES & OUTPUT
        double detA = A.DeterminanCofaktor(A);
        if (detA != 0) {
            double[] x = new double[n];
            for (int j = A.GetFirstIdxKol(A); j <= A.GetLastIdxKol(A); j++) {
                Matriks Aj = new Matriks(n, n);
                Matriks.Copy(A, Aj);
                for (int i = A.GetFirstIdxBrs(M); i <= A.GetLastIdxBrs(A); i++) {
                    Aj.Mat[i][j] = B.Mat[i][0];
                }

                x[j] = Aj.DeterminanCofaktor(Aj) / detA;
            }

            // OUTPUT
            for (int i = 0; i < n; i++) {
                System.out.println(suffix + (i + 1) + " = " + x[i]);
            }
        } else {
            System.out.println("SPL tersebut tidak memiliki solusi");
        }
    }


    //Studi kasus
    public static void SPLCramer1(Matriks M, int n, String suffix) {

        // SPLIT INPUT
        Matriks A = new Matriks(n, n);
        for (int i = A.GetFirstIdxBrs(A); i <= A.GetLastIdxBrs(A); i++) {
            for (int j = A.GetFirstIdxKol(A); j <= A.GetLastIdxKol(A); j++) {
                A.Mat[i][j] = M.Mat[i][j];
            }
        }

        Matriks B = new Matriks(n, 1);
        for (int i = B.GetFirstIdxBrs(B); i <= B.GetLastIdxBrs(B); i++) {
            B.Mat[i][0] = M.Mat[i][n];
        }

        // PROSES & OUTPUT
        double detA = A.DeterminanCofaktor(A);
        if (detA != 0) {
            double[] x = new double[n];
            for (int j = A.GetFirstIdxKol(A); j <= A.GetLastIdxKol(A); j++) {
                Matriks Aj = new Matriks(n, n);
                Matriks.Copy(A, Aj);
                for (int i = A.GetFirstIdxBrs(M); i <= A.GetLastIdxBrs(A); i++) {
                    Aj.Mat[i][j] = B.Mat[i][0];
                }

                x[j] = Aj.DeterminanCofaktor(Aj) / detA;
            }

            // OUTPUT
            for (int i = 0; i < n; i++) {
                System.out.println(suffix + (i + 1) + " = " + x[i]);
            }
        } else {
            System.out.println("SPL tersebut tidak memiliki solusi");
        }
    }
}
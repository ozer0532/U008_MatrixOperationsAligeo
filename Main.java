import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Untuk Input

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Matriks M = new Matriks(n, m);
        Matriks N = new Matriks(1, 1);

        M.BacaMat();
        // M.Adjoin();
        M.CopyMatriks(M, N);
        M.EliminasiGaussJordan(M);
        M.TulisMat();
        N.TulisMat();
        System.out.println();
        double a = M.DeterminanCofaktor(M);
        System.out.println(a);
        // M.Swap(0, 1);
        M.TulisMat();

        scanner.close();
    }
}
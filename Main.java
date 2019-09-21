import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Untuk Input

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Matriks M = new Matriks(n, m);

        M.BacaMat();
        // M.EliminasiGaussJordan(M);
        // M.Adjoin();
        M.TulisMat();
        System.out.println();
        double a = M.DeterminanCofaktor(M);
        System.out.println(a);
        // M.Swap(0, 1);
        M.TulisMat();

        scanner.close();
    }
}
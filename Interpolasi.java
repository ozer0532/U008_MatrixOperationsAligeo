import java.util.*;

public class Interpolasi {
    public static void Cari () {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        Matriks M = new Matriks(n, 2);

        M.BacaMat();
        
        // SPLIT INPUT
        Matriks A = new Matriks(n, n + 1);
        for (int i = A.GetFirstIdxBrs(A); i <= A.GetLastIdxBrs(A); i++) {
            for (int j = A.GetFirstIdxKol(A); j <= A.GetLastIdxKol(A) - 1; j++) {
                A.Mat[i][j] = Math.pow(M.Mat[i][0], j);
            }
        }
        for (int i = A.GetFirstIdxBrs(A); i <= A.GetLastIdxBrs(A); i++) {
            A.Mat[i][n] = M.Mat[i][1];
        }

        SPL.SPLGauss(A, "a");

    }
}
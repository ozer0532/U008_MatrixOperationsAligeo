import java.util.Scanner;

class Determinan {
    static Scanner scanner = new Scanner(System.in);

    public static Matriks InputMatriks() {
        int n = scanner.nextInt();

        Matriks M = new Matriks(n, n);

        M.BacaMat();

        return M;
    }

    public static void DeterminanOBE() {
        Matriks M = InputMatriks();
        System.out.println("Determinan dari matriks tersebut adalah " + M.DeterminanOBE(M));
    }
}
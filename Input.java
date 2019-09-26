import java.util.Scanner;

class Input {
    static Scanner scanner = new Scanner(System.in);

    public static Matriks InputMatriks(Boolean persegi) {
        int n = scanner.nextInt();
        int m = n;

        if (!persegi) {
            m = scanner.nextInt();
        }
        Matriks M = new Matriks(n, m);

        M.BacaMat();

        return M;
    }
}
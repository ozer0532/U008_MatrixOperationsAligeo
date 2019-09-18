public class Main {

    public static void main(String[] args) {
        Matriks M = new Matriks(3, 3);

        M.BacaMat();
        M.TulisMat();
        System.out.println();
        double a = M.Determinan(M);
        System.out.println(a);
        M.Swap(0, 1);
        M.TulisMat();
    }
}
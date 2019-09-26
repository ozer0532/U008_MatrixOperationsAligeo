import java.util.*;

class Menu {

    private static Matriks inpuMatriks() {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        int m = s.nextInt();

        Matriks M = new Matriks(n, m);
        M.BacaMat();

        return M;
    }

    public static int menu() {
        Scanner s = new Scanner(System.in);
        int result = 0;

        System.out.println("\nMenu");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks balikan");
        System.out.println("4. Matriks kofaktor");
        System.out.println("5. Adjoin");
        System.out.println("6. Interpolasi Polinom");
        System.out.println("7. Keluar\n==============");

        System.out.print("Masukan: ");

        result = s.nextInt();

        return result;
    }

    public static int menuInput() {
        Scanner s = new Scanner(System.in);
        System.out.println("\n1. Input dari Keyboard");
        System.out.println("2. Input dari File\n==============");
        System.out.print("Masukan: ");

        int result = s.nextInt();

        return result;
    }

    public static int pilihan1() {
        Scanner s = new Scanner(System.in);
        System.out.println("\n1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer\n==============");
        System.out.print("Masukan: ");

        int result = s.nextInt();

        return result;
    }

    public static int pilihan2() {
        Scanner s = new Scanner(System.in);
        System.out.println("\n1. Metode OBE");
        System.out.println("2. Metode eliminasi Gauss-Jordan\n==============");
        System.out.print("Masukan: ");

        int result = s.nextInt();

        return result;
    }

    public static int pilihan3() {
        Scanner s = new Scanner(System.in);
        System.out.println("\n1. Metode Adjoin");
        System.out.println("2. Metode eliminasi Gauss-Jordan\n==============");
        System.out.print("Masukan: ");

        int result = s.nextInt();

        return result;
    }

    public static void Run() {
        boolean jalan = true;
        int menu, Pilihan1, Pilihan2, Pilihan3;
        while (jalan) {
            menu = Menu.menu();
            if (menu == 1) {
                Pilihan1 = Menu.pilihan1();
                if (Pilihan1 == 1) {
                    SPL.SPLGauss("x");
                } else if (Pilihan1 == 2) {
                    SPL.SPLGaussJordan("x");
                } else if (Pilihan1 == 3) {
                    SPL.SPLInvers("x");
                } else if (Pilihan1 == 4) {
                    SPL.SPLCramer("x");
                } else {
                    System.out.println("Perintah tidak tersedia");
                }
            } else if (menu == 2) {
                Pilihan2 = Menu.pilihan2();
                if (Pilihan2 == 1) {
                    Determinan.DeterminanOBE();
                } else if (Pilihan2 == 2) {
                    System.out.println("Perintah 2");
                } else {
                    System.out.println("Perintah tidak tersedia");
                }
            } else if (menu == 3) {
                Pilihan3 = Menu.pilihan3();
                if (Pilihan3 == 1) {

                } else if (Pilihan3 == 2) {

                } else {
                    System.out.println("Perintah tidak tersedia");
                }
            } else if (menu == 4) {

                Matriks M = Menu.inpuMatriks();

                M.TulisMat();
                M.MatCofaktor();
                M.TulisMat();
            } else if (menu == 6) {
                Matriks Mi = new Matriks(InputFile.BacaFile());
                Mi.TulisMat();
            } else if (menu == 7) {
                break;
            } else {
                System.out.println();
                System.out.println("Menu Tidak Valid");
            }
        }
    }
}
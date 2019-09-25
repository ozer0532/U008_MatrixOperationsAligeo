import java.util.*;

class Menu {

    static Scanner s = new Scanner(System.in);

    public static int menu() {
        int result = 0;

        System.out.println("\nMenu");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks balikan");
        System.out.println("4. Matriks kofaktor");
        System.out.println("5. Adjoin");
        System.out.println("6. Interpolasi Polinom");
        System.out.println("7. Keluar\n==============");

        System.out.println("Masukan: ");
        result = s.nextInt();

        return result;
    }

    public static int pilihan1() {
        System.out.println("\n1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer\n==============");
        System.out.print("Masukan: ");

        int result = s.nextInt();

        return result;
    }

    public static int pilihan2() {
        System.out.println("1. Metode OBE");
        System.out.println("2. Metode eliminasi Gauss-Jordan\n==============");
        System.out.print("Masukan: ");

        int result = s.nextInt();

        return result;
    }

    public static void Run() {
        boolean jalan = true;
        // int menu, Pilihan;
        while (jalan) {
            int menu = Menu.menu();
            if (menu == 1) {
                int Pilihan1 = Menu.pilihan1();
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
                int Pilihan2 = Menu.pilihan2();
                if (Pilihan2 == 1) {
                    Determinan.DeterminanOBE();
                } else if (Pilihan2 == 2) {
                    System.out.println("Perintah 2");
                } else {
                    System.out.println("Perintah tidak tersedia");
                }
            }
        }
        s.close();
    }
}
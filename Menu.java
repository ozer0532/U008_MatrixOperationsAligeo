import java.util.*;

class Menu {

    public static int menu() throws NoSuchElementException {
        Scanner s = new Scanner(System.in);
        int result = 0;

        System.out.println("\nMenu");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Matriks Kofaktor");
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

    public static String inputFile() {
        Scanner s = new Scanner(System.in);
        System.out.print("\nMasukkan nama file(.txt): ");

        String result = s.nextLine();

        return result;
    }

    public static int spl() {
        Scanner s = new Scanner(System.in);
        System.out.println("\n1. Metode Eliminasi Gauss");
        System.out.println("2. Metode Eliminasi Gauss-Jordan");
        System.out.println("3. Metode Matriks Balikan");
        System.out.println("4. Kaidah Cramer\n==============");
        System.out.print("Masukan: ");

        int result = s.nextInt();

        return result;
    }

    public static int determinan() {
        Scanner s = new Scanner(System.in);
        System.out.println("\n1. Metode Operasi Baris Elementer");
        System.out.println("2. Metode Matriks Kofaktor\n==============");
        System.out.print("Masukan: ");

        int result = s.nextInt();

        return result;
    }

    public static int invers() {
        Scanner s = new Scanner(System.in);
        System.out.println("\n1. Metode Adjoin");
        System.out.println("2. Metode Eliminasi Gauss-Jordan\n==============");
        System.out.print("Masukan: ");

        int result = s.nextInt();

        return result;
    }

    public static void outputFile(Matriks M) {
        Scanner s = new Scanner(System.in);
        System.out.print("Apakah Anda ingin menyimpannya dalam file (y/n)? ");
        char a = s.next().charAt(0);
        if (a == 'y' || a == 'Y') {
            System.out.print("Masukan nama file (.txt): ");
            String file = s.next();
            Matriks.TulisFile(file, M);
        }
    }

    public static void Run() {
        Scanner s = new Scanner(System.in);
        boolean jalan = true;
        int menu, spl, determinan, invers;
        Matriks Mspl;
        while (jalan) {
            menu = Menu.menu();
            if (menu == 1) {
                spl = Menu.spl();
                if (spl == 1) {
                    int inputspl = menuInput();
                    if (inputspl == 1) {
                        SPL.SPLGauss("x");
                    } else if (inputspl == 2) {
                        String filename = inputFile();
                        try {
                            Mspl = new Matriks(filename);
                            SPL.SPLGauss(Mspl, "x");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else if (spl == 2) {
                    int inputspl = menuInput();
                    if (inputspl == 1) {
                        SPL.SPLGaussJordan("x");
                    } else if (inputspl == 2) {
                        String filename = inputFile();
                        try {
                            Mspl = new Matriks(filename);
                            SPL.SPLGaussJordan(Mspl, "x");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }

                } else if (spl == 3) {
                    int inputspl = menuInput();
                    if (inputspl == 1) {
                        SPL.SPLInvers("x");
                    } else if (inputspl == 2) {
                        String filename = inputFile();
                        try {
                            Mspl = new Matriks(filename);
                            SPL.SPLInvers(Mspl, "x");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else if (spl == 4) {
                    SPL.SPLCramer("x");
                } else {
                    System.out.println("Perintah tidak tersedia");
                }
            } else if (menu == 2) {
                determinan = Menu.determinan();
                if (determinan == 1) {
                    Determinan.DeterminanOBE();
                } else if (determinan == 2) {
                    Determinan.DeterminanCofaktor();
                } else {
                    System.out.println("Perintah tidak tersedia");
                }
            } else if (menu == 3) {
                invers = Menu.invers();
                if (invers == 1) {
                    Invers.InversAdjoin();
                } else if (invers == 2) {
                    Invers.InversGaussJordan();
                } else {
                    System.out.println("Perintah tidak tersedia");
                }
            } else if (menu == 4) {
                Matriks M = Input.InputMatriks(true);

                M.TulisMat();
                M.MatCofaktor();
                System.out.println();
                M.TulisMat();
                outputFile(M);
            } else if (menu == 5) {
                Matriks M = Input.InputMatriks(true);

                M.Adjoin();
                M.TulisMat();
            } else if (menu == 6) {
                Interpolasi.Cari();
            } else if (menu == 7) {
                break;
            } else {
                System.out.println();
                System.out.println("Menu Tidak Valid");
            }

            System.out.println();
            System.out.print("Memulai lagi(y/n) ? ");
            char mulai = s.next().charAt(0);
            if (mulai != 'y' && mulai != 'Y') {
                jalan = false;
            }
        }
    }
}
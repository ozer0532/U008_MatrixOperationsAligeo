class Determinan {
    public static void DeterminanOBE() {
        Matriks M = Input.InputMatriks(true);
        System.out.println("Determinan dari matriks tersebut adalah " + M.DeterminanOBE(M));
    }

    public static void DeterminanCofaktor() {
        Matriks M = Input.InputMatriks(true);
        System.out.println("Determinan dari matriks tersebut adalah " + M.DeterminanCofaktor(M));
    }
}
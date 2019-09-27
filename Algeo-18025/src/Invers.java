class Invers {
    public static void InversGaussJordan() {
        Matriks M1 = Input.InputMatriks(true);
        Matriks M2 = new Matriks(M1.Mat);
        Matriks.InversGaussJordan(M1, M2);

        M2.TulisMat();
    }

    public static void InversAdjoin() {
        Matriks M1 = Input.InputMatriks(true);
        Matriks M2 = new Matriks(M1.Mat);
        M1.InversAdjoin(M1, M2);

        M2.TulisMat();
    }
}
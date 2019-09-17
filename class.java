final public class matrix {
    private final int m; // Baris
    private final int n; // Kolom
    private final double[][] mat; //isi array, double biar threadsafe
    // Manggil isi matrix-nya matrix.mat[baris][kolom]

    // Konstruktor
    public matrix(int M, int N){
        this.m = M;
        this.n = N;
        mat = new double[M][N];
    }

    private void swap(int a, int b){
        // Nuker baris
        double[] temp = mat[a];
        mat[a] = a[b];
        mat[b] = temp;
    }
}

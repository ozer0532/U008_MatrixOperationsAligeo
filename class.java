final public class matrix {
    private final int m; // Baris
    private final int n; // Kolom
    private final double[][] a; //isi array, double biar threadsafe
    // Manggil isi matrix-nya matrix.a[baris][kolom]

    // Konstruktor
    public matrix(int M, int N){
        this.m = M;
        this.n = N;
        a = new double[M][N];
    }

    private void swap(int a, int b){
        // Nuker baris
        double[] temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }
}
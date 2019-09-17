final public class Matriks {
    private final int baris; // Baris
    private final int kolom; // Kolom
    private double[][] mat; // isi array, double biar threadsafe
    // Manggil isi matriks-nya matriks.mat[baris][kolom]

    // Konstruktor
    public Matriks(int baris, int kolom) {
        this.baris = baris;
        this.kolom = kolom;
        this.mat = new double[baris][kolom];
    }

    private void swap(int a, int b) {
        // Nuker baris
        double[] temp = mat[a];
        mat[a] = mat[b];
        mat[b] = temp;
    }

    double determinan(Matriks M) {

    }
}

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in); // Untuk Input
        
        Menu.Run();
        String file = "mat.txt";
        Matriks M = new Matriks(file);
        M.TulisMat();
        scanner.close();
    }
}
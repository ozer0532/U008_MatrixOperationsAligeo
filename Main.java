import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in); // Untuk Input
        
        // Menu.Run();
        Scanner input = new Scanner (new File("mat.txt"));
        Matriks M = new Matriks(input);
        M.TulisMat();
        scanner.close();
        input.close();
    }
}
import java.util.*;
import java.io.*;

public class InputFile {

    public static void BacaFile(Matriks M) {
        try {
            Matriks Min;
            Scanner scanner = new Scanner(System.in);
            String file;
            int Bar = -1;
            int Kol = -1;
            ArrayList<ArrayList<Double>> mat = new ArrayList<ArrayList<Double>>();

            file = scanner.nextLine();
            File f = new File(file);

            Scanner scanfile = new Scanner(f);

            while (scanfile.hasNextLine()) {
                String baris = scanfile.nextLine();
                Scanner scanbaris = new Scanner(baris);
                Bar++;
                mat.add(new ArrayList<Double>());

                while (scanbaris.hasNextLine()) {
                    Double element = scanbaris.nextDouble();
                    mat.get(Bar).add(element);
                }
            }
            Kol = mat.get(0).size();
            Min = new Matriks(mat.size(), mat.get(0).size());
            for (int i = 0; i <= Bar; i++) {
                for (int j = 0; j <= Kol; j++) {
                    Min.Mat[i][j] = mat.get(i).get(j);
                }
            }

            M = Min;
        } catch (Exception error) {
            System.out.println("Error : " + error);
        }
    }
}
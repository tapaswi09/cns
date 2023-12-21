import java.util.*;
import java.io.*;

public class Railfence {
    public static int key[] = new int[7];
    public char mat[][] = new char[10][7];
    public char cmat[][] = new char[10][7];
    String plain = "";
    String cipher = "";
    int rows = 0;

    public String encrypt(String text1) {
        int i, j, len, ch, k, p = 0;
        String enctxt = "";
        String text = "";
        len = text1.length();
        for (i = 0; i < len; i++)
            text += text1.charAt(i);
        if ((len % 7) != 0) {
            rows = (len / 7) + 1;
            ch = len % 7;
            for (i = 0; i < (7 - ch); i++)
                text += 'X';
        } else
            rows = len / 7;
        k = 0;
        for (i = 0; i < rows; i++) {
            for (j = 0; j < 7; j++)
                mat[i][j] = text.charAt(k++);
        }
        for (i = 0; i < rows; i++) {
            for (j = 0; j < 7; j++)
                System.out.print(mat[i][j] + " ");
            System.out.println();
        }
        k = 1;
        j = 0;
        while (k <= 7) {
            for (p = 0; p < 7; p++) {
                if (k == key[p]) {
                    j = p;
                    k++;
                    break;
                }
            }
            for (i = 0; i < rows; i++)
                enctxt += mat[i][j];
        }
        System.out.println(enctxt);
        return enctxt;
    }

    public String decrypt(String txt, int plength) {
        int i, j = 1, len, k = 1, p, q = 0;
        String dectxt = "";
        while (k <= 7) {
            for (p = 0; p < 7; p++) {
                if (key[p] == k) {
                    j = p;
                    k++;
                    break;
                }
            }
            for (i = 0; i < rows; i++)
                cmat[i][j] = txt.charAt(q++);
        }
        for (i = 0; i < rows; i++) {
            for (j = 0; j < 7; j++)
                System.out.print(cmat[i][j] + " ");
            System.out.println();
        }
        for (i = 0; i < rows; i++) {
            for (j = 0; j < 7; j++)
                dectxt += cmat[i][j];
        }
        len = dectxt.length();
        if (plength < len) {
            for (i = 0; i < plength; i++)
                plain += dectxt.charAt(i);
        }
        return plain;
    }

    public static void main(String[] args) throws IOException {
        int i = 0;
        String c;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Railfence rf = new Railfence();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter key");
        for (i = 0; i < 7; i++) {
            key[i] = i + 1;
        }

        Random rand = new Random();
        for (i = 6; i > 0; i--) {
            int randIndex = rand.nextInt(i + 1);
            int temp = key[i];
            key[i] = key[randIndex];
            key[randIndex] = temp;
        }

        for (i = 0; i < 7; i++)
            System.out.print(key[i] + " ");

        System.out.println("Enter PLAIN TEXT");
        rf.plain = sc.nextLine();
        int k = rf.plain.length();
        System.out.println(rf.plain);

        String ctext = rf.encrypt(rf.plain);
        System.out.println();
        System.out.println("CIPHER TEXT :" + ctext);
        System.out.println();

        String plaintext = rf.decrypt(ctext, k);
        System.out.println();
        System.out.println("PLAIN TEXT :" + plaintext);
        sc.close();
    }
}
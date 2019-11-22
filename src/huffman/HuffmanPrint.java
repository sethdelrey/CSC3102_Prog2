/*
Raquel Hodgeson (rhodg14@lsu.edu, 89-399-5177)
Seth Richard (sric111@lsu.edu, 89-053-2395)
Programming Project 2
CSC 3102 - Dr. Shah
11/23/19
 */

package huffman;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class HuffmanPrint {

    HashMap<Character, String> codeMap = new HashMap<Character, String>();
    Scanner s;

    HuffmanPrint(File f) throws FileNotFoundException {
        s = new Scanner(f);
    }

    //maps each character to its huffman code
    public void encode(Pair root, String s) throws FileNotFoundException {

        if (root.left == null && root.right == null) {
            codeMap.put(root.c, s);
            return;
        }
        encode(root.left, s+"0");
        encode(root.right, s+"1");
    }

    //prints out huffman encoding of original file to new file called "huffmanoutput.txt"
    public void printCode() throws IOException {
        FileWriter out = new FileWriter(new File("huffmanoutput.txt"));
        char ch;
        s.useDelimiter("");
        while (s.hasNext()) {
            ch = s.next().toLowerCase().charAt(0);
            if (!Character.isLetterOrDigit(ch))
                ch = ' ';
            out.write(codeMap.get(ch));
        }
        out.close();
    }
}

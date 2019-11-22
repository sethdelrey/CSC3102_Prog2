package huffman;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class HuffmanPrint {

    HashMap<Character, String> codeMap = new HashMap<Character, String>();
//    char[] strArray;
    Scanner s;

    HuffmanPrint(File f) throws FileNotFoundException {
        s = new Scanner(f);
    }

    public void encode(Pair root, String s) throws FileNotFoundException {

        if (root.left == null && root.right == null) {
            codeMap.put(root.c, s);
            return;
        }
        encode(root.left, s+"0");
        encode(root.right, s+"1");
    }

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
//        for (int i = 0; i<strArray.length; i++) {
//            out.write(codeMap.get(strArray[i]));
//        }
        out.close();
    }
}

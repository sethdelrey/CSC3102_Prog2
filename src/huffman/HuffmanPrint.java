package huffman;

import java.io.*;
import java.util.HashMap;

public class HuffmanPrint {

    HashMap<Character, String> codeMap = new HashMap<Character, String>();
    char[] strArray;

    HuffmanPrint(char[] arr) {
        strArray = arr;
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
        for (int i = 0; i<strArray.length; i++) {
            out.write(codeMap.get(strArray[i]));
        }
        out.close();
    }
}

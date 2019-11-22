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
        try (Scanner fin = new Scanner(new File("english"))) {
            while (fin.hasNextLine()) {
                String line = fin.nextLine() + "\n";
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter("");
                while (lineScanner.hasNext()) {
                    ch = lineScanner.next().toLowerCase().charAt(0);
                    if (!Character.isLetterOrDigit(ch)) {
                        out.write(codeMap.get(' '));
                        if (ch == '\n') {
                            out.write('\n');
                        }
                    } else {
                        out.write(codeMap.get(ch));
                    }
                }
            }
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
}

package huffman;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

class Pair implements Comparable<Pair> {

    int freq;
    char c;
    Pair right;
    Pair left;

    public int getFreq(){
        return this.freq;
    }

    public char getChar(){
        return this.c;
    }

    public int compareTo(Pair other) {
        //returns negative if object is LESS than other
        int retVal = 0;
        if (freq < other.freq)
            retVal = -1;
        else if (freq > other.freq)
            retVal = 1;
        return retVal;
    }
}

public class Huffman {

    public static void main(String[] args) {
        String str = "";
        try {
//            Scanner s = new Scanner(new File("huffmaninput"));
//            while (s.hasNextLine()) {
//                str += s.nextLine();
//            }
//
//            str = str.replaceAll("[^a-zA-Z0-9]", " ");
//            str = str.toLowerCase();
//            char[] strArray = str.toCharArray();

            CharFrequency fq = new CharFrequency();
//            fq.charCount(str);
            File f = new File("huffmaninput");
            fq.charCount(f);

            char[] charArray = fq.getCharArray();
            int[] charFreq = fq.getFreqArray();
            int n = charArray.length;

            MinHeap q = new MinHeap(n, 2);

            // creates Pairs and adds them to MinHeap
            for (int i = 0; i < n; i++) {
                Pair hn = new Pair();
                hn.c = charArray[i];
                hn.freq = charFreq[i];

                hn.left = null;
                hn.right = null;

                q.insert(hn);
            }

            Pair root = null;

            //extract 2 min nodes from the heap and make a parent node that
            // stores their added frequencies and points to the 2 char nodes
            while (q.getSize() > 1) {
                Pair x = q.extractMin();

                Pair y = q.extractMin();

                Pair t = new Pair();

                t.freq = x.freq + y.freq;
                t.c = '-';

                t.left = x;
                t.right = y;
                root = t;
                q.insert(t);
            }

            //decodes file


            HuffmanPrint p = new HuffmanPrint(f);
            HashMap<Character, String> codeMap = new HashMap<Character, String>();
            p.encode(root, "");
            decode(root);
            p.printCode();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void decode(Pair root) {
        try {
            FileWriter out = new FileWriter("decodedhuffman.txt");
            Pair curr = root;
//            char[] line;
            char x;
            String ans = "";
            Scanner fin = new Scanner(new File("huffmanoutput.txt"));
            fin.useDelimiter("");
            while (fin.hasNext()) {
                x = fin.next().charAt(0);
                if (x == '0')
                    curr = curr.left;
                else
                    curr = curr.right;

                //reached leaf
                if (curr.left == null && curr.right == null) {
//                    ans += curr.c;
                    out.write(curr.c);
                    curr = root;
                }
            }
            out.close();



//            while (fin.hasNextLine()) {
//                line = fin.nextLine().toCharArray();
//                for (int i = 0; i < line.length; i++) {
//                    if (line[i] == '0') {
//                        curr = curr.left;
//                    }
//                }
//            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


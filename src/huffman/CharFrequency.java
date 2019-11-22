package huffman;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class CharFrequency {

    HashMap<Character, Integer> charCountMap = new HashMap<Character, Integer>();
    char[] keys;
    int[] values;

//    public void charCount(String inputString) {
        public void charCount(File f) throws FileNotFoundException {
//
//        inputString = inputString.replaceAll("[^a-zA-Z0-9]", " ");
//        char[] strArray = inputString.toCharArray();
//        int i = 0;
//
//        for(char c: strArray) {
//            if (charCountMap.containsKey(c)) {
//                charCountMap.put(c, charCountMap.get(c) + 1);
//            } else {
//                charCountMap.put(c, 1);
//            }
//        }
        char c;
        Scanner s = new Scanner(f);
        s.useDelimiter("");
        while (s.hasNext()) {
            c = s.next().toLowerCase().charAt(0);
            if (!Character.isLetterOrDigit(c))
                c = ' ';
            if (charCountMap.containsKey(c)) {
                charCountMap.put(c, charCountMap.get(c) + 1);
            } else {
                charCountMap.put(c, 1);
            }
        }

        int i = 0;
        //creates 2 arrays for keys and values
        keys = new char[charCountMap.size()];
        values = new int[charCountMap.size()];
        for (Map.Entry<Character, Integer> mapEntry : charCountMap.entrySet()) {
            keys[i] = mapEntry.getKey();
            values[i] = mapEntry.getValue();
            i++;
        }
    }

//    public int getFrequency(char entry) {
//        for (Map.Entry<Character, Integer> c : charCountMap.entrySet()) {
//            if (entry == c.getKey())
//                return c.getValue();
//        }
//        return 0;
//    }

    public char[] getCharArray() {
        return keys;
    }

    public int[] getFreqArray() {
        return values;
    }
}

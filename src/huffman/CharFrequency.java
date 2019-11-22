/*
Raquel Hodgeson (rhodg14@lsu.edu, 89-399-5177)
Seth Richard (sric111@lsu.edu, 89-053-2395)
Programming Project 2
CSC 3102 - Dr. Shah
11/23/19
 */
package huffman;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.rmi.server.ExportException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class CharFrequency {

    HashMap<Character, Integer> charCountMap = new HashMap<Character, Integer>();
    char[] keys;
    int[] values;

    public void charCount(File f) throws FileNotFoundException {
        char c;
        try (BufferedReader br = new BufferedReader(new FileReader(new File("english_50MB.txt")))) {
            String line;
            Scanner s;
            while ((line = br.readLine()) != null) {
                line += "\n";
                s = new Scanner(line);
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
            }
        }
        catch (Exception ex) {
            int i = 0;
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

    public char[] getCharArray() {
        return keys;
    }

    public int[] getFreqArray() {
        return values;
    }
}

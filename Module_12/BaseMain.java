import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
/**
 * Program for generating kanji component dependency order via topological sort.
 * 
 * @author (your name), Acuna
 * @version (version)
 */
public class BaseMain {
    
    /**
     * Entry point for testing.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //TODO: implement this 
        //Freebie: this is one way to load the UTF8 formated character data.
        //BufferedReader indexReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("data-kanji.txt")), "UTF8"));
        try {
            // Load character data from data-kanji.txt
            BufferedReader indexReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:\\Users\\chiki\\Desktop\\CSE240\\RVCWEBUI\\SER222\\Module_12\\data-kanji.txt")), "UTF8"));

            // Create a hashtable to map IDs to characters
            Map<Integer, String> idToCharacter = new HashMap<>();

            // Populate hashtable and add IDs as nodes in the graph
            String line;
            while ((line = indexReader.readLine()) != null) {
                // Skip comment lines
                if (line.startsWith("#")) {
                    continue;
                }

                // Split line into ID and character
                String[] parts = line.split("\t");
            
                // Ensure the parts array has at least two elements before attempting to parse
                if (parts.length >= 2) {
                    int id = Integer.parseInt(parts[0]);
                    String character = parts[1];
            
                    // Add to hashtable and graph
                    idToCharacter.put(id, character);
                }
            }

            // Create a graph and add edges from data-components.txt
            BetterDiGraph graph = new BetterDiGraph();
            BufferedReader componentsReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("data-components.txt")), "UTF8"));
            while ((line = componentsReader.readLine()) != null) {
                // Skip comment lines
                if (line.startsWith("#")) {
                    continue;
                }

                // Split line into parent and child IDs
                String[] parts = line.split("\t");
                int parentID = Integer.parseInt(parts[0]);
                int childID = Integer.parseInt(parts[1]);

                // Add edges to the graph
                graph.addEdge(parentID, childID);
            }

            // Create an IntuitiveTopological object and use it to sort the graph
            IntuitiveTopological topologicalSort = new IntuitiveTopological(graph);
            Iterable<Integer> order = topologicalSort.order();

            // Display the characters in the ordering
            System.out.println("Topological Order of Characters:");
            for (int id : order) {
                String character = idToCharacter.get(id);
                System.out.println(character);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
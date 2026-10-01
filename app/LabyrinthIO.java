import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

/** To avoid hardcoding messy values, this class reads labyrinths from a (mostly) human readable .txt file.
@author Lincoln*/
public class LabyrinthIO{
   /** Put all roooms into a 1D ArrayList useful for iterating through*/
   public static ArrayList<Chamber> flatten(Chamber entrance){
      ArrayList<Chamber> toVisit = new ArrayList<Chamber>();
      ArrayList<Chamber> visited = new ArrayList<Chamber>();
      toVisit.add(entrance);
      for (int i = 0; !toVisit.isEmpty(); i++){ 
         Chamber room = toVisit.get(0);
         toVisit.remove(0);
         
         if (!visited.contains(room)){ //only save each room once.
            visited.add(room);
            toVisit.addAll(room.passages);
         }
      }
      return visited;
   }
   
   
   /** Reads a labyrinth from a (mostly) human readable .txt file.
   @param filename Looks for this file in the same directory
   @return Entrance This node is the entrance/root of the labyrinth.*/
   public static Chamber readLabyrinthFile(String filename){
      try {
         Scanner sc = new Scanner(new File(filename));
         Chamber[] labyrinth = new Chamber[sc.nextInt()];
         for (int i = 0; i < labyrinth.length; i++){
            if (i != sc.nextInt()) System.out.print("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            if (sc.nextBoolean()) //is leaf node --> use the relic constructor
               labyrinth[i] = new Chamber(sc.nextLine().split("\\|"));
            else //is not leaf node --> use danger constructor
               labyrinth[i] = new Chamber(sc.nextInt());
         }
         for (int i = 0; i < labyrinth.length; i++){
            if (i != sc.nextInt()) System.out.print("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
            Scanner connections = new Scanner(sc.nextLine());
            while (connections.hasNext())
               labyrinth[i].passages.add(labyrinth[connections.nextInt()]);
         }
         return labyrinth[0];
      }
      catch (FileNotFoundException e){
         System.out.println("File not found" + e);
      }
      return null;
   }
   
   /** Saves a labyrinth into a (mostly) human readable .txt file to be used later. The root node is assigned index 0.
   @param filename Creates or overwrites the labyrinth in this file.
   @param entrance The root node of the labyrinth. Traverses from here through the graph.*/ 
   public static void saveLabyrinthFile(String filename, Chamber entrance){
      ArrayList<Chamber> rooms = flatten(entrance);
      //Wrap filewriter in a try catch
      try {
         BufferedWriter file = new BufferedWriter(new FileWriter(filename));
         file.write(rooms.size()+""); //write number of rooms
         file.newLine();
         //Save each room and whether it contains an artifact or danger
         for (int i = 0; i < rooms.size(); i++){
            Chamber room = rooms.get(i);
            file.write(i + " ");
            file.write(room.isLeaf() ? "true " : "false ");
            if (room.isLeaf()) file.write("".format("%s|%s", room.getRelic().getName(), room.getRelic().getOrigin()));
            else file.write(room.getDanger()+"");
            file.newLine();
         }
         //Save the connection list of each room. Using a second loops simplifies reading later.
         for (int i = 0; i < rooms.size(); i++){
            Chamber room = rooms.get(i);
            file.write(i + " ");
            for (Chamber connection: room.passages) file.write(Arrays.asList(rooms).indexOf(connection) + " ");
            file.newLine();
         }
         file.close();
         System.out.println("done");
      }
      catch (IOException e){
         System.out.println("Failed to write file: " + e);
      }          
   }
}
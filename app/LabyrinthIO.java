import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

/** To avoid hardcoding messy values, this class reads labyrinths from a (mostly) human readable .txt file.
@author Lincoln*/
public class LabyrinthIO{

   public static void main(String[] args){
      readLabyrinthFile("DemoLabyrinth.txt");
   }

   
   
   
   /** Reads a labyrinth from a (mostly) human readable .txt file.
   @param filename Looks for this file in the same directory
   @return Entrance This node is the entrance/root of the labyrinth.*/
   public static ChamberI readLabyrinthFile(String filename){
      try {
         Scanner sc = new Scanner(new File(filename));
         ChamberI[] labyrinth = new ChamberI[sc.nextInt()];
         for (int i = 0; i < labyrinth.length; i++){
            if (i != sc.nextInt()) System.out.print("Error index is wrong is file reader");
            if (sc.nextBoolean()) //is leaf node --> use the relic constructor
               labyrinth[i] = new Relic(sc.nextLine().split("\\|"));
            else //is not leaf node --> use danger constructor
               labyrinth[i] = new Chamber(sc.nextInt());
         }
         for (int i = 0; i < labyrinth.length; i++){
            labyrinth[i].setRoomName(sc.next());
            Scanner connections = new Scanner(sc.nextLine());
            if (labyrinth[i] instanceof Chamber) while (connections.hasNext())
               ((Chamber)labyrinth[i]).addConnection(labyrinth[connections.nextInt()]);
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
   public static void saveLabyrinthFile(String filename, ArrayList<ChamberI> rooms){
      //Wrap filewriter in a try catch
      try {
         BufferedWriter file = new BufferedWriter(new FileWriter(filename));
         file.write(rooms.size()+""); //write number of rooms
         file.newLine();
         //Save each room and whether it contains an artifact or danger
         for (int i = 0; i < rooms.size(); i++){
            ChamberI room = rooms.get(i);
            file.write(i + " ");
            file.write(room.toSaveString());
            file.newLine();
         }
         //Save the connection list of each room. Using a second loops simplifies reading later.
         for (int i = 0; i < rooms.size(); i++){
            ChamberI room = rooms.get(i);
            file.write(i + " ");
            if (rooms.get(i) instanceof Chamber) for (ChamberI connection: ((Chamber)room).getPassages()) 
               file.write(Arrays.asList(rooms).indexOf(connection) + " ");
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
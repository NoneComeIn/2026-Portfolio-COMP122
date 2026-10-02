import java.util.ArrayList;
import java.lang.StringBuilder;

public class Chamber implements ChamberI{
   /** Chamber class made by Lincoln*/
   private int danger;
   private String name;
   /** connectedRooms A list of all connected rooms in the labyrinth. 
   Note that this is a directional graph. Connections only go one way.
   I am assuming that there are no cycles in the graph.*/
   private ArrayList<ChamberI> passages = new ArrayList<ChamberI>();
   private Path path;
   
   public Chamber(int danger){
      this.danger = danger;
   }
   
   
   public boolean isBlocked(){
      return danger > 100;
   }
   
   
   public boolean getReachable(){
      return path != null && path.isOpen();
   }
   public int getDanger(){
      return danger;
   }
   public ChamberI[] getPassages(){
      return passages.toArray(new ChamberI[0]);
   }
   public String getPassagesAsString(){
      StringBuilder output = new StringBuilder();
      output.append("\tConnecting passages lead to:");
      for (ChamberI room: passages){
         output.append("\n\t\tRoom ");
         output.append(room.getRoomName());
      }
      return output.toString();
   }
   public String getRoomName(){
      return name;
   }
   public String getPath(){
      return "".format("\tPath: %s%s\n\t\t%s", path, name, path.getStats()); 
   }
   
   
   public void addConnection(ChamberI neighbour){
      passages.add(neighbour);
   }
   
   
   public void setPath(Path path){
      this.path = path;
   }
   public void setRoomName(String name){
      this.name = name;
   }
   
   
   public String toString(){
      return "".format("Room %s is a%sreachable chamber: ", name, getReachable() ? " " : "n un");
   }
   public String toSaveString(){
      return "true " + danger;
   }
}

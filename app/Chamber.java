import java.util.ArrayList;
import java.lang.StringBuilder;

/** Chamber class
@author Lincoln*/
public class Chamber{
   /** The danger level of this node in the labyrinth*/
   private ChamberContent content;
   /** The name of this node of the labyrinth*/
   private String name;
   /** passages A list of all connected rooms in the labyrinth. 
   Note that the labyrinth is a is a directed graph. Connections only go one way. The graph must not be cyclic.*/
   private ArrayList<Chamber> passages = new ArrayList<Chamber>();
   /**path A private Path object that holds the names of intermediate nodes from the labyrinth entrance to here, plus miscellaneous path data. Does not record its own node.*/
   private Path path;
   
   
   /** Main constructor*/
   public Chamber(String name, ChamberContent content){
      this.name = name;
      this.content = content;
   }
   
   
   // Getters
   
   /** Calcuates if this node is reachable by traversing the stored path. If path is null, returns false.
   @return reachable*/
   public boolean getReachable(){
      return path != null && path.isOpen();
   }
   /** returns the list of connected passages as an immutable array to prevent sideeffects
   @return passages the connected nodes of the labyrinth.*/
   public Chamber[] getPassages(){
      return passages.toArray(new Chamber[0]);
   }
   /** Returns the name of the labyrinth node.
   @return roomName*/
   public String getRoomName(){
      return name;
   }
   public ChamberContent getContent(){
      return content;
   }
   public String getPath(){
      return path.toString() + " --> " + name;
   }
   public boolean hasConnections(){
      return !passages.isEmpty();
   }
   
   
   // Mutators
   
   /** connections mutator because when reading from a file the connected nodes cannot be constructed until after every node has been declared and the constructor has exitted.
   @param neighbour the ChamberI object to be included in passages.*/
   public void addConnection(Chamber neighbour){
      passages.add(neighbour);
   }
   
   
   // Setters
   
   /** Path setter because the path cannot be calculated until after the constructor ended.
   @param path the path object containing the route to relic from the entrance.*/
   public void setPath(Path path){
      this.path = path;
      if (content instanceof Relic) ((Relic)content).setReachable(getReachable());
   }
   
   
   //toString() variants
   
   /** Returns the passages of this node as a formatted string.
   @return A string representation of this node's connections*/
   public String getPassagesAsString(){
      StringBuilder output = new StringBuilder();
      output.append("\tConnecting passages lead to:");
      for (Chamber room: passages){
         output.append("\n\t\tRoom ");
         output.append(room.getRoomName());
      }
      return output.toString();
   }
   /** Returns the labyrinth related information of this node as a formatted string
   @return A string representation of the relic.*/
   public String toString(){
      return "".format("Room %s is a%sreachable chamber ", name, getReachable() ? " " : "n un");
   }
   /** Returns the information of this node as a string formatted for a save file
   @return A save compatible string representation of the relic.*/
   public String toSaveString(){
      return "".format(content.toSaveString(), name);
   }
}

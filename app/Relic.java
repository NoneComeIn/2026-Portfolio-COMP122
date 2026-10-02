import java.util.ArrayList;

/** Relic is one of two treasure types, and is able to occupy a position in the labyrinth. It no longer has any relationship with the Chamber class.
@author Lincoln*/
public class Relic extends Treasure implements ChamberI{
   /**roomName Labels the relic's position in the labyrinth. Is distinct from [treasure] name.*/
   private String roomName;
   /**path A private Path object that holds the names of intermediate nodes from the labyrinth entrance to here, plus miscellaneous path data. Does not record its own node.*/
   private Path path;
   
   
   // Constructors
   
   /** Main constructor*/
   public Relic(String name, String origin){
      super(name, origin);
   }
   /** This constructor is to make loading from a file easier. Calls main constructor.*/
   public Relic(String[] args){
      this(args[0].strip(), args[1]);
   }
   
   
   // Setters
   
   /** Path setter because the path cannot be calculated until after the constructor ended.
   @param path the path object containing the route to relic from the entrance.*/
   public void setPath(Path path){
      this.path = path;
   }
   /** roomName setter. roomName relates to labyrinth position and is distinct from (treasure) name.
   @param name the name of the relic's position in the labyrinth.*/
   public void setRoomName(String name){
      this.roomName = name;
   }
   
   
   // Getters
   
   /** Calculates the value of the relic by whether it is reachable in the maze.
   @return Value of the relic*/
   public int getValue(){
      return getReachable() ? 250 : 10000;
   }
   /** Calcuates if this node is reachable by traversing the stored path. If path is null, returns false.
   @return reachable*/
   public boolean getReachable(){
      return path != null && path.isOpen();
   }
   /** Returns the name of the labyrinth node, NOT the name of the relic.
   @return roomName*/
   public String getRoomName(){
      return roomName;
   }
   
   
   //toString variants
   
   /** Returns the path to this node as a formatted string. Also exists to enforce encapsulation.
   @return A string representation of the stored path.*/
   public String getPath(){
      return "".format("\tPath: %s%s\n\t\t%s", path, roomName, path.getStats()); 
   }
   /** Returns the *treasure* related information of this node as a formatted string
   @return A string representation of the relic.*/
   public String getRelic(){
      return "".format("\t$%d relic:\n\t\t%s\n\t\t\"%s\"", getValue(), name, origin);
   }
   /** Returns the *labyrinth* related information of this node as a formatted string
   @return A string representation of the relic.*/
   public String toString(){
      return "".format("Room %s is a%sreachable chamber: ", roomName, getReachable() ? " " : "n un");
   }
   /** Returns the information of this node as a string formatted for a save file
   @return A save compatible string representation of the relic.*/
   public String toSaveString(){
      return "".format("false %s|%s", name, origin);
   }
}
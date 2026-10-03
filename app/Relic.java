import java.util.ArrayList;

/** Relic is a hybrid class that is one of two treasure types, and is able to occupy a position in the labyrinth. It no longer has any relationship with the Chamber class.
@author Lincoln*/
public class Relic extends Treasure implements ChamberI{
   /**roomName Labels the relic's position in the labyrinth. Is distinct from [treasure] name.*/
   private String roomName;
   /**path A private Path object that holds the names of intermediate nodes from the labyrinth entrance to here, plus miscellaneous path data. Does not record its own node.*/
   private Path path;
   
   
   // Constructors
   
   /** Main constructor*/
   public Relic(String roomName, String name, String origin){
      super(name, origin);
      this.roomName = roomName;
   }
   /** This constructor is to make loading from a file easier. Calls main constructor.*/
   public Relic(String roomName, String[] args){
      this(roomName, args[0].strip(), args[1]);
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
   @Override
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
   /** Returns the type of the object as a string
   @return "Gemstone"*/
   @Override
   public String getType(){
      return "Relic";
   }
   /** Returns the path to here as a string
   @return the full representation of the path here.*/
   @Override
   public String getAttribute1(){
      return "".format("Path: %s%s", path, roomName);
   }
   /** Returns secondary path info
   @return the max danger whether there is a blocked of on the path here*/
   @Override
   public String getAttribute2(){
      return path.getStats();
   }
   
   
   //toString variants
   
   /** Returns the path to this node as a formatted string. Also exists to enforce encapsulation.
   @return A string representation of the stored path.*/
   public String getPath(){
      return "".format("\t%s\n\t\t%s", getAttribute1(), getAttribute2()); 
   }
   /** Returns the *treasure* related information of this node as a formatted string
   @return A string representation of the relic.*/
   public String getRelic(){
      return "".format("$%d relic:\n\t\t%s\n\t\t\"%s\"", getValue(), name, origin);
   }
   /** Returns the *labyrinth* related information of this node as a formatted string
   @return A string representation of the relic.*/
   public String toString(){
      return "".format("Room %s is a%sreachable chamber: ", roomName, getReachable() ? " " : "n un");
   }
   /** Returns the information of this node as a string formatted for a save file
   @return A save compatible string representation of the relic.*/
   public String toSaveString(){
      return "".format("true %s %s|%s", roomName, name, origin);
   }
}
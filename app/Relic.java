import java.util.ArrayList;

/** Relic is a hybrid class that is one of two treasure types, and is able to occupy a position in the labyrinth. It no longer has any relationship with the Chamber class.
@author Lincoln*/
public class Relic extends Treasure implements ChamberContentI{

   Path routeTo;
   

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

   /** roomName setter. roomName relates to labyrinth position and is distinct from (treasure) name.
   @param name the name of the relic's position in the labyrinth.*/
   public void setReachable(Path path){
      this.routeTo = path;
   }
   
   
   // Getters
   
   /** Calculates the value of the relic by whether it is reachable in the maze.
   @return Value of the relic*/
   @Override
   public int getValue(){
      return routeTo.isOpen() ? 250 : 10000;
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
      return "".format("Path: %s", routeTo);
   }
   /** Returns secondary path info
   @return the max danger whether there is a blocked of on the path here*/
   @Override
   public String getAttribute2(){
      return routeTo.getStats();
   }
   
   
   //toString variants
   
   /** Returns the *treasure* related information of this node as a formatted string
   @return A string representation of the relic.*/
   public String toString(){
      return "".format("\t$%d relic:\n\t\t%s\n\t\t\"%s\"", getValue(), name, origin);
   }
   /** Returns the information of this node as a string formatted for a save file
   @return A save compatible string representation of the relic.*/
   public String toSaveString(){
      return "".format("true \\%s %s|%s", name, origin);
   }
}
import java.util.ArrayList;

/**
@author Lincoln*/
public class Relic extends Treasure implements ChamberI{
   private boolean reachable;
   private ArrayList<Character> path;
   
   
   public Relic(String name, String origin){
      super(name, origin);
   }
   public Relic(String[] args){
      this(args[0].strip(), args[1]);
   }
   
   
   public boolean isLeaf(){
      return true;
   }
   public int calcValue(){
      return reachable ? 250 : 10000;
   }
   
   public void setReachable(boolean reachable){
      this.reachable = reachable;
   }
   

   public boolean getReachable(){
      return reachable;
   }
   
   public String toString(){
      return "".format("$%d Relic: \n\t%s\n\t\"%s\"", calcValue(), name, origin); //is only called in Labyrinth.display() rn. Might need to be changed if used elsewhere.
   }
   public String toSaveString(){
      return "".format("false %s|%s", name, origin);
   }
}
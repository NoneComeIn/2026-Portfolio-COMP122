import java.util.ArrayList;

/**
@author Lincoln*/
public class Relic extends Treasure implements ChamberI{
   private String roomName;
   private Path path;
   
   
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
      return getReachable() ? 250 : 10000;
   }
   
   
   public void setPath(Path path){
      this.path = path;
   }
   public void setRoomName(String name){
      this.roomName = name;
   }
   

   public boolean getReachable(){
      return path != null && path.isOpen();
   }
   public String getRoomName(){
      return roomName;
   }
   public String getPath(){
      return "".format("\tPath: %s%s\n\t\t%s", path, roomName, path.getStats()); 
   }
   public String getRelic(){
      return "".format("\t$%d relic:\n\t\t%s\n\t\t\"%s\"", calcValue(), name, origin);
   }

   
   public String toString(){
      return "".format("Room %s is a%sreachable chamber: ", roomName, getReachable() ? " " : "n un");
   }
   public String toSaveString(){
      return "".format("false %s|%s", name, origin);
   }
}
import java.util.ArrayList;

/** A lite interface for labyrinth nodes. Not designed to be expanded.
@author Lincoln*/
public interface ChamberI{
   void setPath(Path path);
   String getPath();
   boolean getReachable();
   String toSaveString();
   void setRoomName(String name);
   String getRoomName();
}
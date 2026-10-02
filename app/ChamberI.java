import java.util.ArrayList;

/**
@author Lincoln*/
public interface ChamberI{
   boolean isLeaf();
   void setPath(Path path);
   String getPath();
   boolean getReachable();
   String toSaveString();
   void setRoomName(String name);
   String getRoomName();
}
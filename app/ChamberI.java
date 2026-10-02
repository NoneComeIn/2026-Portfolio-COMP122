import java.util.ArrayList;

public interface ChamberI{
   boolean isLeaf();
   void setReachable(boolean r);
   boolean getReachable();
   public String toSaveString();
}
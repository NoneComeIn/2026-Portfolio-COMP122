/** Created and managed by Reid Penwarden. Parent class to Mercenary and Treasure.
@author Penwarden
@author Lincoln*/
public abstract class Entity
{
   
   protected String name;
   
   public Entity(String name)
   {
      this.name = name;
   }
   /*
   
   */ 
   //FIX ME - Implement everywhere!
   
   
   public String getName(){//Lincoln wuz here
      return name;
   }
   public int getThreat(){//Lincoln wuz here
      int value = getValue();
      if (value < 100  ) return 0;
      if (value < 500  ) return 1;
      if (value < 1500 ) return 2;
      if (value < 5000 ) return 3;
      if (value < 10000) return 4;
      else               return 5;
   }
   public String getThreatString(){
      return "".format("Level %d", getThreat());
   }   public String getValueString(){
      return "".format("%,d", getValue());
   }
   public String getAction(){
      return new String[]{"No Action","Log only","Watch status","Arm passive trap","Arm lethal traps","Full lair lockout"}[getThreat()];
   }
   
   public String toString(String name, String origin)
   {
      return name + origin;
   }
   public abstract int getValue();
   public abstract String getType();
   public String getAttribute1() { return ""; }
   public String getAttribute2() { return ""; }
   public String getAttribute3() { return ""; }
}
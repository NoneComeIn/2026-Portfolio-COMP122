public abstract class Entity
{
   private String name;
   private String origin;
   
   public Entity(String name, String origin)
   {
      this.name = name;
      this.origin = origin;
   }
   /*
   public abstract String getAtributes();
   public abstract String getName();
   public abstract int getValue();
   public abstract int getThreat();
   public abstract String getAction();
   public abstract String getType();
   */ 
   // FIX ME - Implement everywhere!
   
   
   public String toString(String name, String origin)
   {
      return name + origin;
   }
 
}
public abstract class Entity
{
   /**
      Created and managed by Reid Penwarden. Parent class to Mercenary and Treasure.
   */
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
   public abstract int getThreat();
   public abstract String getAction();
   public abstract String getType();
   */ 
   //FIX ME - Implement everywhere!
   
   
   public String toString(String name, String origin)
   {
      return name + origin;
   }
   public abstract int calcValue();
 
}
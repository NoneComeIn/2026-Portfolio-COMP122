public abstract class Entity
{
   /**
      Created and managed by Reid Penwarden. Parent class to Mercenary and Treasure.
   */
   private String name;
   
   public Entity(String name)
   {
      this.name = name;
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
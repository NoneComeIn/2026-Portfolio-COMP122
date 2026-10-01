public class Treasure extends Entity
{
   /**
      Created and managed by Reid Penwarden - Parent class to all treasures, handles name and origin.
   */
   
   protected String origin;
   public Treasure(String name, String origin)
   {
      super(name);
      this.origin = origin;
   }
   
   public String getOrigin(){//Lincoln wuz here 2
      return origin;
   }

   public int calcValue()
   {
      return -1;
   }
}
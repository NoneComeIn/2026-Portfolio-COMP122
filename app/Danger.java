

class Danger implements ChamberContentI{
   /** The danger level of this node in the labyrinth*/
   private int danger;
   
   public Danger(int danger){
      this.danger = danger;
   }
   
   /** returns the danger value of this node of the labyrinth
   @return danger The danger of this node*/
   public int getDanger(){
      return danger;
   }
   
   public String toSaveString(){
      return "".format("false \\%s %d", danger);
   }
   public String toString(){
      return "".format("\tDanger rating: %s", danger);
   }
}
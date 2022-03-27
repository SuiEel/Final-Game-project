class timer {

  int s;

    void createnumber(){
      s = counter * 60 + second() - startvalue;
    }
    
    void displaynumber(){
      textSize(32);
      text("SCORE:", 10, 30);
      text(s, 130, 30);

      if (second() == 59){
        if (N == false){
          counter += 1;
        }
        while(second() == 59) {
          N = true;
        }
      }      
      else{
        N = false;
      }
    }
    
}

class Button{
  
  int x, y;
  int h_size,w_size;
  color basecolor, highlightcolor;
  color currentcolor;
  boolean over = false;
  boolean pressed = false;
  
  void update(){
    if(over()) {
      currentcolor = highlightcolor;
    } else {
      currentcolor = basecolor;
    }
  }


  boolean pressed(){
    if(over){
      locked = true;
      return true;
    }else {
      locked = false;
      return false;
    }    

  }



  boolean over(){ 
    return true; 
  }



  boolean overRect(int x, int y, int width, int height){
    if (mouseX >= x-width/2 && mouseX <= x+width/2 &&mouseY >= y-height/2 && mouseY <= y+height/2) {
      return true;
    }else {
      return false;
    }
  }
}

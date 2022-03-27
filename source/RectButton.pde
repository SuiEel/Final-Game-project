class RectButton extends Button{
  String text;
  RectButton(int ix, int iy, int hsize, int wsize, color icolor, color ihighlight, String word){
    x = ix;
    y = iy;
    h_size = hsize;
    w_size = wsize;
    text = word;
    basecolor = icolor;
    highlightcolor = ihighlight;
    currentcolor = basecolor;
  }



  boolean over(){
    if( overRect(x, y, h_size, w_size) ) {
      over = true;
      return true;
    }else {
      over = false;
      return false;
    }
  }

  void display(){
    stroke(255);
    fill(currentcolor);
    //noStroke():
    //noFill();
    rect(x, y, h_size, w_size);
    fill(255);
    textSize(20);
    text(text, x, y, h_size, w_size);

  }

}

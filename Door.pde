class Door {
  float x, y, w, h;
  int doorNum = 0;
  float spawnLocx = 0;
  float spawnLocy = 0;
  float r, g, b;
  boolean cleared;
  boolean hubDoor;
  
    Door(float x, float y, boolean hubDoor) {
    this.x=x;
    this.y=y;
    this.w=40;
    this.h=50;
    this.hubDoor = hubDoor;
    cleared = false;
    
    
  }
  void display() { 
    //draws the block
    r = random(0, 255);
    g = random(0, 255);
    b = random(0, 255);
    if (cleared) {
      fill(r, g, b);
    }else{
      noFill();
    }
    if (hubDoor) {
      stroke(255);
      strokeWeight(4);
      ellipse(x, y, w, h);
      strokeWeight(1);
      
    }
    
    else {
     stroke(r, g, b, r);
     strokeWeight(4);
     ellipse(x, y, w, h);
     strokeWeight(1); 
      
    }
    
  }
  
  void goThruDoor(float fx, float fy, float fw, float fh){
    if (openDoor(fx,fy,fw,fh)){
    //enter new room
    }
  }
  
  boolean openDoor(float fx, float fy, float fw, float fh) { //returns true if the player is touching the door
    float dx=abs(fx-x);
    float dy=abs(fy-y);
    return dx<(fw+w)/2 && dy<(fh+h)/2;
  }
}

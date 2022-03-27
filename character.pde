int animationTimer = 0;
int animationTimerValue = 50; 


class Player {
  float x, y, w, h, vx, vy, a;
  float moveSpeed;
  float jumpSpeed;
  float pWidth;
  
  

  //moves the player
  void playerMove() {
    a = 0.015;
    if (vx == 0) {
      moveSpeed = 1.7;
    }
    moveSpeed += a;
    if (moveSpeed >= 3) {
      moveSpeed = 3;
    }
    x+=vx;
    y+=vy;
    if (y>=height-h/2) {
      y=height-h/2;
      vy=0;  //floor
    } else if (y<height) {
      vy+=0.22;  //gravity
    }
  }

  
  //Sprite Code
  /*PImage[] playerSprite;
  int imgCount;
  int frame;*/
  
  /*Player(String imgName, int count) {
    imgCount = count;
    playerSprite = new PImage[imgCount];
    
    for ( int i = 0; i < imgCount; i ++) {
      String filename = imgName + nf(i+1, 2) + ".gif";
      playerSprite[i] = loadImage(filename); 
    }  
  }*/
  
  
  void display() {
    fill(0, 0);
    noStroke();
    rect(x, y, w, h);
    
  }
  /*void display() {
    //frame = (frame+1) % imgCount;
    image(playerSprite[frame], x, y);
    if ((millis() - animationTimer) >= animationTimerValue) {
     frame = (frame + 1) % imgCount; 
     animationTimer = millis();
    }
  }
  
  int getWidth() {
    return playerSprite[0]. width;
  }*/
    void collide(float bx, float by, float bw, float bh) { 
    if (collidedWithBlock(bx, by, bw, bh)) {
      float dx=abs(bx-x);
      float dy=abs(by-y);
      float gapx=dx-(w+bw)/2;
      float gapy=dy-(h+bh)/2;
      if (gapx<=gapy) {
        if (vy<=0 && y>by+bh/2) { //hit bottom of block
          y+=jumpSpeed/2;
          vy=0;
        } else if (vy>0 && y<by+bh/2) { //hit top of block
          y=by-((h+bh)/2-1);
          vy=0;
        }
      } else {
        if (vx<0 && x>bx) {  //hit right of block
          x=bx+(w+bw)/2;
        }
        if (vx>0 && x<bx) { //hit left of block
          x=bx-(w+bw)/2;
        }
      }
    }
  }
  
  
  void collidedoors(float bx, float by, float bw, float bh) { 
    if (collidedWithBlock(bx, by, bw, bh)) {
      float dx=abs(bx-x);
      float dy=abs(by-y);
      float gapx=dx-(w+bw)/2;
      float gapy=dy-(h+bh)/2;
      if (gapx<=gapy) {
        if (vy<=0 && y>by+bh/2) { //hit bottom of block
          y+=jumpSpeed/2;
          vy=0;
        } else if (vy>0 && y<by+bh/2) { //hit top of block
          y=by-((h+bh)/2-1);
          vy=0;
        }
      } else {
        if (vx<0 && x>bx) {  //hit right of block
          x=bx+(w+bw)/2;
        }
        if (vx>0 && x<bx) { //hit left of block
          x=bx-(w+bw)/2;
        }
      }
    }
  }
  
  boolean collidedWithBlock(float bx, float by, float bw, float bh) { //returns true if the player is touching the block
    float dx=abs(bx-x);
    float dy=abs(by-y);
    return dx<(bw+w)/2 && dy<(bh+h)/2;
  }
  /*/
  boolean doorBool(){
  for (int i=0;i<arraydoors.size();i++) {
    Door d=arraydoors.get(i);
    float dx=abs(
    float dy=abs(
    if (x<d.x && y<){
      return true;
    }
  }
}
/*/

}

class Particles extends Player {
  float partx, party;
  float vx, vy;
  float r;
  float f;
  float a;
  float m = 1.0;
  float ks = 0.1;
  float kd = 0.1;
  float lifespan;
  
  Particles(float _vx, float _vy, float _r) {
    lifespan = 255;
    partx = player.x + 5;
    party = player.y;
    vx = _vx;
    vy = _vy;
    r = _r;
  }
  void applyForces(float _fx, float _fy) {
    
    
    vx += _fx;
    vy += _fy;
    party -= vy;
    partx += vx;
    lifespan -= 1;
    
  }
  void display() {
    fill(255, lifespan);
    noStroke();
    ellipse(partx, party, r, r);
  }
  boolean dead() {
    if (lifespan < 0.0) {
      return true;
    } else {
      
      return false;
    }
    
    
  }
  
  
}

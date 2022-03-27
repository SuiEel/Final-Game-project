class Platform {
  float x, y, w, h;
  //float movingx;
  //float movingy;
  float movementValue;
  boolean movingalongx;
  boolean movingalongy;
  boolean reversed;
  float snapshotx;
  float snapshoty;
  
  
  
  Platform(float x, float y, float w, float h, boolean movingalongx, boolean movingalongy, boolean reversed) {
    this.x=x;
    this.y=y;
    this.w=w;
    this.h=h;
    this.snapshotx = x;
    this.snapshoty = y;
    this.reversed = reversed;
    this.movingalongx = movingalongx;
    this.movingalongy = movingalongy;
    if (this.reversed) {
      this.movementValue = -1;
    } else {
      this.movementValue = 1;
    }
    
  }

  void display() { //draws the block
    fill(0, 0, 0, 0);
    rect(x, y, w, h);
    if (this.movingalongx) {
      this.x += this.movementValue;
    }
    if (this.movingalongy) {
      this.y += this.movementValue;
    }
  }
}

class PlatParticles extends Platform {
  float partx, party;
  float vx, vy;
  float r;
  float f;
  float a;
  float m = 1.0;
  float ks = 0.1;
  float kd = 0.1;
  float lifespan;
  float red, g, b;
  
  
  PlatParticles(float x, float y, float w, float h, boolean movingalongx, boolean movingalongy, boolean reversed) {
    super(x, y, w, h, movingalongx, movingalongy, reversed);
    lifespan = 255;
    partx = random(x - w / 2, x + w /2);
    party = y - h / 2;
    vx = 0;
    vy = 0;
    r = 1;
    red = random(0, 255);
    g = random(0, 255);
    b = random(0, 255);
  }
  void applyForces(float _fx, float _fy) {
    
    
    vx += _fx;
    vy += _fy;
    party -= vy;
    partx += vx;
    lifespan -= 1;
    
  }
  void display() {
    fill(red, g, b, lifespan);
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

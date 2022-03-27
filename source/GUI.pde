
RectButton [] pbutton = new RectButton[3];
RectButton [] mbutton = new RectButton[2];
boolean locked = false;
color currentcolor, buttoncolor, highlight;
boolean paused = false;

void p_update() {
  if (locked == false) {
    pbutton[0].update();
    pbutton[1].update();
    pbutton[2].update();
  } else {
    locked = false;
  }
  if (mousePressed && state == 1) {
    if (pbutton[0].pressed()) {
      paused = false;
      state = 2;
    } else if (pbutton[1].pressed()) {
      paused = true;
      state = 0;
    } else if (pbutton[2].pressed()) {
      exit();
    }
    
  }
}

void m_update(){
   if (locked == false) {
    mbutton[0].update();
    mbutton[1].update();
  } else {
    locked = false;
  }
  if (mousePressed && state == 0) {
    if (mbutton[0].pressed()) {
    paused = false;
     state = 2;
     print("ERROR\n");
    } else if (mbutton[1].pressed()) {
      exit();
    }
  }
}

void Paused() {
  p_update();
  rectMode(CENTER);
  textAlign(CENTER, CENTER);
  stroke(255);
  fill(currentcolor);
  rect(640, 200, 150, 60);
  fill(255);
  text("Paused", 640, 200);
  pbutton[0].display();
  pbutton[1].display();
  pbutton[2].display(); 
  rectMode(CORNER);
  textAlign(BASELINE);
}

void menu(){  
  fill(0);
  rect(0,0,width,height);
  rectMode(CENTER);

  textAlign(CENTER, CENTER);
  stroke(255);
  fill(currentcolor);
  //rect(640, 200, 150, 60);
  //fill(255);
  //text("Paused", 640, 200);
  mbutton[0].display();
  mbutton[1].display();
  rectMode(CORNER);
  textAlign(BASELINE);
  m_update();
}

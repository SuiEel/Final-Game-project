import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.Random; 
import ddf.minim.*; 
import processing.serial.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Final_Assignment extends PApplet {


Random randint = new Random();
Player player;

Minim minim;
AudioPlayer audio;


Serial myPort;

String [] ports;

final int MAIN_MENU = 0;
final int GAME = 2;
final int PAUSE = 1;
final int TEST = 3;
int state = 0;
int dJump = 2;

int s;
int startvalue;
int counter;
boolean N;
ArrayList<Platform> hubArea = new ArrayList<Platform>();
ArrayList<PlatParticles> platParticles = new ArrayList<PlatParticles>();
ArrayList<Platform> blocks1 = new ArrayList<Platform>();
ArrayList<Platform> blocks2 = new ArrayList<Platform>();
ArrayList<Platform> blocks3 = new ArrayList<Platform>();
ArrayList<Platform> blocks4 = new ArrayList<Platform>();
ArrayList<Platform> blocks5 = new ArrayList<Platform>();
ArrayList<Particles> particles = new ArrayList<Particles>();
//ArrayList<StationaryBlock> blocks=new ArrayList<StationaryBlock>();
ArrayList<Door> arraydoors= new ArrayList<Door>();
Door levelDoor1;
Door levelDoor2;
Door levelDoor3;
Door levelDoor4;
Door levelDoor5;
int openDoor = 0;
float respawnLocationx, respawnLocationy;
float flash = 0;
float movingValuex;
boolean moveLimit;
boolean arduino = true;

public void setup(){
  
  ports = Serial.list();
  
  try {
   print(ports[0]);

} catch (IndexOutOfBoundsException e) {
    System.err.println("IndexOutOfBoundsException: " + e.getMessage() + "\nArduino not detected\nSetting Arduino to FALSE");    
    arduino = false;
  }

     //arduino code
  if (arduino){
    myPort = new Serial(this, ports[0], 9600);
    myPort.bufferUntil('\n');
  }
  
  buttoncolor = color(102);
  highlight = color(51); 
  pbutton[0] = new RectButton(640, 300, 85, 30, buttoncolor, highlight, "Resume");
  pbutton[1] = new RectButton(640, 350, 125, 30, buttoncolor, highlight, "Main Menu");
  pbutton[2] = new RectButton(640, 400, 60, 30, buttoncolor, highlight, "Exit");
  mbutton[0] = new RectButton(640, 300, 80, 30, buttoncolor, highlight, "Begin");
  mbutton[1] = new RectButton(640, 400, 60, 30, buttoncolor, highlight, "Exit");

    //audiofile
  minim = new Minim(this);
  audio = minim.loadFile("TYMELAPSE - We Became Strangers.. Again.wav");
  audio.play();

  startvalue = second();
  counter = 0;
  N = false;
  
  player=new Player();
  player.x = 200;
  player.y = 700;
  player.w = 10;
  player.h = 10;
  player.vy=0;
  rectMode(CENTER);
  player.moveSpeed=1.7f;
  player.jumpSpeed=6.8f;
  
   hubArea.add(new Platform(width/2, 750, 80, 600, false, false, false));
  hubArea.add(new Platform(width/2 - 80, 850, 80, 600, false, false, false));
  hubArea.add(new Platform(width/2 + 80, 850, 80, 600, false, false, false));
  hubArea.add(new Platform(width/2 + 160, 950, 80, 600, false, false, false));
  hubArea.add(new Platform(width/2 - 160, 950, 80, 600, false, false, false));
  hubArea.add(new Platform(640,720,width,10, false, false, false)); 
  //print(width / 2);
  
  blocks1.add(new Platform(200, 300, 40, 20, false, false, false));
  blocks1.add(new Platform(300, 400, 40, 20, false, false, false));
  blocks1.add(new Platform(400, 500, 40, 20, false, false, false));
  blocks1.add(new Platform(500, 600, 40, 20, false, false, false));
  blocks1.add(new Platform(600, 700, 40, 20, false, false, false));
  blocks1.add(new Platform(700, 600, 40, 20, false, false, false));
  blocks1.add(new Platform(800, 500, 40, 20, false, false, false));
  blocks1.add(new Platform(900, 400, 40, 20, false, false, false));
  blocks1.add(new Platform(1000, 500, 40, 20, false, false, false));
  blocks1.add(new Platform(1100, 600, 40, 20, false, false, false));
  blocks1.add(new Platform(1200, 500, 100, 20, false, false, false));
  blocks1.add(new Platform(100, 200, 100, 20, false, false, false));
  
  
  blocks2.add(new Platform(0, 200, width - 100, 20, false, false, false));
  blocks2.add(new Platform(1280, 200, width - 100, 20, false, false, false));
  blocks2.add(new Platform(width/2, 600, 100, 20, true, false, false));
  blocks2.add(new Platform(width/2, 400, 100, 20, true, false, true));
  blocks2.add(new Platform(1100, 700, 100, 20, false, false, false));
  
  blocks3.add(new Platform(100, 200, 300, 20, false, false, false));
  blocks3.add(new Platform(300, 400, 30, 20, false, false, false));
  //blocks3.add(new Platform(200, 500, 30, 20, false, false, false));
  //blocks3.add(new Platform(100, 600, 30, 20, false, false, false));
  blocks3.add(new Platform(width /2, 600, 100, 20, true, false, true));
  blocks3.add(new Platform(1100, 700, 100, 20, false, false, false));
  
  blocks4.add(new Platform(100, 200, 100, 20, false, false, false));
  blocks4.add(new Platform(210, 350, 100, 20, false, true, false));
  blocks4.add(new Platform(410, 350, 100, 20, false, true, true));
  blocks4.add(new Platform(610, 350, 100, 20, false, true, false));
  blocks4.add(new Platform(810, 350, 100, 20, false, true, true));
  blocks4.add(new Platform(1010, 350, 100, 20, false, true, false));
  blocks4.add(new Platform(1210, 350, 100, 20, false, true, true));
  //blocks4.add(new Platform(400, 400, 100, 20, true, true, true));
  //blocks4.add(new Platform(600, 400, 100, 20, true, true, false));
  
  blocks5.add(new Platform(100, 650, 100, 20, false, false, false));
  blocks5.add(new Platform(200, 550, 100, 20, false, false, false));
  blocks5.add(new Platform(300, 450, 100, 20, false, true, false));
  blocks5.add(new Platform(520, 500, 150, 20, true, true, true));
  blocks5.add(new Platform(740, 600, 100, 20, false, false, false));
  blocks5.add(new Platform(920, 600, 100, 20, true, false, false));
  blocks5.add(new Platform(1180, 600, 100, 20, true, false, true));


  
  arraydoors.add(new Door(width/2,420, true));
  arraydoors.add(new Door(width/2 - 80, 520, true));
  arraydoors.add(new Door(width/2 + 80, 520, true));
  arraydoors.add(new Door(width/2 + 160,620, true));
  arraydoors.add(new Door(width/2 - 160,620, true));
  for (int i=0;i<arraydoors.size();i++) {
    Door d=arraydoors.get(i);
    d.doorNum = i + 1;
  }
  levelDoor1 = new Door(1200, 460, false);
  levelDoor2 = new Door(1100, 650, false);
  levelDoor3 = new Door(1100, 650, false);
  levelDoor4 = new Door(1210, 500, false);
  levelDoor5 = new Door(1210, 550, false);
  
  rectMode(CENTER);

}

float randFloaty = random(0, .01f);
float randFloatx = random(0, .01f);

public void draw(){ 
  
  switch(state){
  case MAIN_MENU:
    menu();
    break;
  case PAUSE:
    Paused();
    //debug();
    break;
  case GAME:
  	Game();
  break;
  case TEST:
  
  break;
  
}
//print(state);

//print(paused);
if (player.vy==0 && paused == false) { //double jump
    dJump = 2;
  }
}
boolean holdLeft = false, holdRight = false, holdSprint = false, holdJump = false;
int jumpMax = 500;
boolean falling = false;
/*/
boolean doorbool(){
  for (int i=0;i<arraydoors.size();i++) {
    Door d=arraydoors.get(i);
    if player.x<d;
  }
}
/*/

public void setSignal (boolean setTo) {
  if (key == 'q'){
    holdSprint = setTo;  
  }  
}
 
public void keyPressed() {
  if (key==CODED && keyCode==LEFT && paused == false) player.vx=-1*player.moveSpeed; //move left
  if (key==CODED && keyCode==RIGHT && paused == false) player.vx=player.moveSpeed; //move right
  if (key == ' ' && dJump > 0 && paused == false) { //jump
    player.y-=player.jumpSpeed;
    player.vy=-1*player.jumpSpeed;
    dJump -= 1;
  }
  if ((key == 'p' || key =='P') && state != 0){
    paused = !paused;
    state = 1;
    setSignal(false);  
  }else if (paused == false){
  setSignal(true);
  }
}
 
public void keyReleased() { 
  if (key==CODED && keyCode==LEFT && player.vx<0) player.vx=0;
  if (key==CODED && keyCode==RIGHT && player.vx>0) player.vx=0;
  setSignal(false);
}
class Button{
  
  int x, y;
  int h_size,w_size;
  int basecolor, highlightcolor;
  int currentcolor;
  boolean over = false;
  boolean pressed = false;
  
  public void update(){
    if(over()) {
      currentcolor = highlightcolor;
    } else {
      currentcolor = basecolor;
    }
  }


  public boolean pressed(){
    if(over){
      locked = true;
      return true;
    }else {
      locked = false;
      return false;
    }    

  }



  public boolean over(){ 
    return true; 
  }



  public boolean overRect(int x, int y, int width, int height){
    if (mouseX >= x-width/2 && mouseX <= x+width/2 &&mouseY >= y-height/2 && mouseY <= y+height/2) {
      return true;
    }else {
      return false;
    }
  }
}
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
  public void display() { 
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
  
  public void goThruDoor(float fx, float fy, float fw, float fh){
    if (openDoor(fx,fy,fw,fh)){
    //enter new room
    }
  }
  
  public boolean openDoor(float fx, float fy, float fw, float fh) { //returns true if the player is touching the door
    float dx=abs(fx-x);
    float dy=abs(fy-y);
    return dx<(fw+w)/2 && dy<(fh+h)/2;
  }
}

RectButton [] pbutton = new RectButton[3];
RectButton [] mbutton = new RectButton[2];
boolean locked = false;
int currentcolor, buttoncolor, highlight;
boolean paused = false;

public void p_update() {
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

public void m_update(){
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

public void Paused() {
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

public void menu(){  
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
public void Game(){
    if (flash > 0){
    flash -= 1;
  }
  background(flash);
  if (openDoor == 0) {
    
    player.playerMove();
    player.display();
    particles.add(new Particles(0, 0, 3));
    
    
    for (int i=0;i<hubArea.size();i++) { 
      //displays all blocks and checks if they are colliding with the player
      Platform b=hubArea.get(i);
      platParticles.add(new PlatParticles(b.x, b.y, b.w, b.h, b.movingalongx, b.movingalongy, b.reversed));
      for (int j = 0; j < platParticles.size(); j ++) {
        PlatParticles particle = platParticles.get(j);
        particle.display();
        randFloaty = random(0, .005f);
        randFloatx = random(-.01f, .01f);
        particle.applyForces(randFloatx, randFloaty);
        if (particle.dead()) {
          platParticles.remove(j);
        }
      }
      player.collide(b.x, b.y, b.w, b.h);
      platParticles.get(i).display();
      b.display();
    }
    for (int i=0;i<arraydoors.size();i++) { 
      Door d=arraydoors.get(i);
      //player.collide(d.x, d.y, d.w, d.h);
      d.display();
      if (d.cleared == false){
        if (keyCode == UP && player.x <= d.x + d.w && player.x >= d.x - d.w) {
          if (keyCode == UP && player.y <= d.y + d.h && player.y >= d.y - d.h) {
            print ("Door", str(i + 1), "found");
            openDoor = d.doorNum;
            player.x = 100;
            player.y = 50;
            respawnLocationx = player.x;
            respawnLocationy = player.y;
         
          }
        }
      }
      
      }
    for (int i = 0; i < particles.size(); i ++) {
      Particles particle = particles.get(i);
      particle.display();
      randFloaty = random(0, .01f);
      randFloatx = random(-.01f, .01f);
      particle.applyForces(randFloatx, randFloaty);
      if (particle.dead()) {
        particles.remove(i);
      }
    }
  }
   
   
  //level 1
  if (openDoor == 1) {
    player.playerMove();
    player.display();
    particles.add(new Particles(0, 0, 3));
    levelDoor1.display();
    if (keyCode == UP && player.x <= levelDoor1.x + levelDoor1.w && player.x >= levelDoor1.x - levelDoor1.w) {
        if (keyCode == UP && player.y <= levelDoor1.y + levelDoor1.h && player.y >= levelDoor1.y - levelDoor1.h) {
          print ("levelDoor1 found");
          openDoor = levelDoor1.doorNum;
          player.x = 100;
          player.y = 50;
          respawnLocationx = player.x;
          respawnLocationy = player.y;
           for (int i=0;i<arraydoors.size();i++) {
            Door d=arraydoors.get(i);
            if (i == 0) {
              d.cleared = true;
            }
          }
        }
      }
    
    if (player.y >= 710) {
      player.x = respawnLocationx;
      player.y = respawnLocationy;
      flash = 255;
    }
    
    
    
    for (int i=0;i<blocks1.size();i++) { //displays all blocks and checks if they are colliding with the player
    //displays all blocks and checks if they are colliding with the player
      Platform b=blocks1.get(i);
      platParticles.add(new PlatParticles(b.x, b.y, b.w, b.h, b.movingalongx, b.movingalongy, b.reversed));
      for (int j = 0; j < platParticles.size(); j ++) {
        PlatParticles particle = platParticles.get(j);
        particle.display();
        randFloaty = random(0, .005f);
        randFloatx = random(-.01f, .01f);
        particle.applyForces(randFloatx, randFloaty);
        if (particle.dead()) {
          platParticles.remove(j);
        }
      }
      player.collide(b.x, b.y, b.w, b.h);
      platParticles.get(i).display();
      b.display();
    }
    
    for (int i = 0; i < particles.size(); i ++) {
      Particles particle = particles.get(i);
      particle.display();
      randFloaty = random(0, .01f);
      randFloatx = random(-.01f, .01f);
      particle.applyForces(randFloatx, randFloaty);
      if (particle.dead()) {
        particles.remove(i);
      }
    }
  }
  
  //level 2
  if (openDoor == 2) {
    
    //myPort.write(""+6);


    player.playerMove();
    player.display();
    particles.add(new Particles(0, 0, 3));
    
    levelDoor2.display();
    if (keyCode == UP && player.x <= levelDoor2.x + levelDoor2.w && player.x >= levelDoor2.x - levelDoor2.w) {
        if (keyCode == UP && player.y <= levelDoor2.y + levelDoor2.h && player.y >= levelDoor2.y - levelDoor2.h) {
          print ("levelDoor2 found");
          openDoor = levelDoor2.doorNum;
          player.x = 100;
          player.y = 50;
          respawnLocationx = player.x;
          respawnLocationy = player.y;
           for (int i=0;i<arraydoors.size();i++) {
            Door d=arraydoors.get(i);
            if (i == 1) {
              d.cleared = true;
            }
          }
        }
      }
    
    if (player.y >= 710) {
      player.x = respawnLocationx;
      player.y = respawnLocationy;
      flash = 255;
    }
    
    
    
    for (int i=0;i<blocks2.size();i++) { //displays all blocks and checks if they are colliding with the player
    //displays all blocks and checks if they are colliding with the player
      Platform b=blocks2.get(i);
      platParticles.add(new PlatParticles(b.x, b.y, b.w, b.h, b.movingalongx, b.movingalongy, b.reversed));
      for (int j = 0; j < platParticles.size(); j ++) {
        PlatParticles particle = platParticles.get(j);
        particle.display();
        
        randFloaty = random(0, .005f);
        randFloatx = random(-.01f, .01f);
        particle.applyForces(randFloatx, randFloaty);
        if (particle.dead()) {
          platParticles.remove(j);
        }
      }
      player.collide(b.x, b.y, b.w, b.h);
      platParticles.get(i).display();
      
      if (b.x > b.snapshotx + 300) {
       b.movementValue = -1;
        
      }else if (b.x < b.snapshotx - 300) {
        b.movementValue = 1;
      }
      
      b.display();
       
    }
    
    for (int i = 0; i < particles.size(); i ++) {
      Particles particle = particles.get(i);
      particle.display();
      randFloaty = random(0, .01f);
      randFloatx = random(-.01f, .01f);
      particle.applyForces(randFloatx, randFloaty);
      if (particle.dead()) {
        particles.remove(i);
      }
    }
  }
  
  //level 3
  if (openDoor == 3) {
    
    player.playerMove();
    player.display();
    particles.add(new Particles(0, 0, 3));
    
    
    levelDoor3.display();
    if (keyCode == UP && player.x <= levelDoor3.x + levelDoor3.w && player.x >= levelDoor3.x - levelDoor3.w) {
        if (keyCode == UP && player.y <= levelDoor3.y + levelDoor3.h && player.y >= levelDoor3.y - levelDoor3.h) {
          print ("levelDoor3 found");
          openDoor = levelDoor3.doorNum;
          player.x = 100;
          player.y = 50;
          respawnLocationx = player.x;
          respawnLocationy = player.y;
           for (int i=0;i<arraydoors.size();i++) {
            Door d=arraydoors.get(i);
            if (i == 2) {
              d.cleared = true;
            }
          }
        }
      }
    
    
    
    if (player.y >= 710) {
      player.x = respawnLocationx;
      player.y = respawnLocationy;
      flash = 255;
    }
    
    
    
    for (int i=0;i<blocks3.size();i++) { //displays all blocks and checks if they are colliding with the player
    //displays all blocks and checks if they are colliding with the player
      Platform b=blocks3.get(i);
      platParticles.add(new PlatParticles(b.x, b.y, b.w, b.h, b.movingalongx, b.movingalongy, b.reversed));
      for (int j = 0; j < platParticles.size(); j ++) {
        PlatParticles particle = platParticles.get(j);
        particle.display();
        
        randFloaty = random(0, .005f);
        randFloatx = random(-.01f, .01f);
        particle.applyForces(randFloatx, randFloaty);
        if (particle.dead()) {
          platParticles.remove(j);
        }
      }
      player.collide(b.x, b.y, b.w, b.h);
      platParticles.get(i).display();
      
      if (b.x > b.snapshotx + 520) {
       b.movementValue = -1;
        
      }else if (b.x < b.snapshotx - 520) {
        b.movementValue = 1;
      }
      
      b.display();
       
    }
    
    for (int i = 0; i < particles.size(); i ++) {
      Particles particle = particles.get(i);
      particle.display();
      randFloaty = random(0, .01f);
      randFloatx = random(-.01f, .01f);
      particle.applyForces(randFloatx, randFloaty);
      if (particle.dead()) {
        particles.remove(i);
      }
    }
    
    
    
  }
  //level 4
  if (openDoor == 4) {
    
    player.playerMove();
    player.display();
    particles.add(new Particles(0, 0, 3));
    levelDoor4.display();
    if (keyCode == UP && player.x <= levelDoor4.x + levelDoor4.w && player.x >= levelDoor4.x - levelDoor4.w) {
        if (keyCode == UP && player.y <= levelDoor4.y + levelDoor4.h && player.y >= levelDoor4.y - levelDoor4.h) {
          print ("levelDoor4 found");
          openDoor = levelDoor4.doorNum;
          player.x = 100;
          player.y = 50;
          respawnLocationx = player.x;
          respawnLocationy = player.y;
           for (int i=0;i<arraydoors.size();i++) {
            Door d=arraydoors.get(i);
            if (i == 3) {
              d.cleared = true;
            }
          }
        }
      }
    
    if (player.y >= 710) {
      player.x = respawnLocationx;
      player.y = respawnLocationy;
      flash = 255;
    }
    
    
    
    for (int i=0;i<blocks4.size();i++) { //displays all blocks and checks if they are colliding with the player
    //displays all blocks and checks if they are colliding with the player
      Platform b=blocks4.get(i);
      platParticles.add(new PlatParticles(b.x, b.y, b.w, b.h, b.movingalongx, b.movingalongy, b.reversed));
      for (int j = 0; j < platParticles.size(); j ++) {
        PlatParticles particle = platParticles.get(j);
        particle.display();
        
        randFloaty = random(0, .005f);
        randFloatx = random(-.01f, .01f);
        particle.applyForces(randFloatx, randFloaty);
        if (particle.dead()) {
          platParticles.remove(j);
        }
      }
      player.collide(b.x, b.y, b.w, b.h);
      platParticles.get(i).display();
      
      if (b.x > b.snapshotx + 120) {
       b.movementValue = -1;
        
      }else if (b.x < b.snapshotx - 120) {
        b.movementValue = 1;
      }
      
      if (b.y > b.snapshoty + 200) {
       b.movementValue = -1;
        
      }else if (b.y < b.snapshoty - 200) {
        b.movementValue = 1;
      }
      
      b.display();
       
    }
    
    for (int i = 0; i < particles.size(); i ++) {
      Particles particle = particles.get(i);
      particle.display();
      randFloaty = random(0, .01f);
      randFloatx = random(-.01f, .01f);
      particle.applyForces(randFloatx, randFloaty);
      if (particle.dead()) {
        particles.remove(i);
      }
    }
  }
  
  
  //level 5
  if (openDoor == 5) {
    player.playerMove();
    player.display();
    particles.add(new Particles(0, 0, 3));
    levelDoor5.display();
    if (keyCode == UP && player.x <= levelDoor5.x + levelDoor5.w && player.x >= levelDoor5.x - levelDoor5.w) {
        if (keyCode == UP && player.y <= levelDoor5.y + levelDoor5.h && player.y >= levelDoor5.y - levelDoor5.h) {
          print ("levelDoor4 found");
          openDoor = levelDoor5.doorNum;
          player.x = 100;
          player.y = 50;
          respawnLocationx = player.x;
          respawnLocationy = player.y;
           for (int i=0;i<arraydoors.size();i++) {
            Door d=arraydoors.get(i);
            if (i == 4) {
              d.cleared = true;
            }
          }
        }
      }
    
    if (player.y >= 710) {
      player.x = respawnLocationx;
      player.y = respawnLocationy;
      flash = 255;
    }
    
    
    
    for (int i=0;i<blocks5.size();i++) { //displays all blocks and checks if they are colliding with the player
    //displays all blocks and checks if they are colliding with the player
      Platform b=blocks5.get(i);
      platParticles.add(new PlatParticles(b.x, b.y, b.w, b.h, b.movingalongx, b.movingalongy, b.reversed));
      for (int j = 0; j < platParticles.size(); j ++) {
        PlatParticles particle = platParticles.get(j);
        particle.display();
        
        randFloaty = random(0, .005f);
        randFloatx = random(-.01f, .01f);
        particle.applyForces(randFloatx, randFloaty);
        if (particle.dead()) {
          platParticles.remove(j);
        }
      }
      player.collide(b.x, b.y, b.w, b.h);
      platParticles.get(i).display();
      
      if (b.x > b.snapshotx + 120) {
       b.movementValue = -1;
        
      }else if (b.x < b.snapshotx - 120) {
        b.movementValue = 1;
      }
      
      if (b.y > b.snapshoty + 200) {
       b.movementValue = -1;
        
      }else if (b.y < b.snapshoty - 200) {
        b.movementValue = 1;
      }
      
      b.display();
       
    }
    
    for (int i = 0; i < particles.size(); i ++) {
      Particles particle = particles.get(i);
      particle.display();
      randFloaty = random(0, .01f);
      randFloatx = random(-.01f, .01f);
      particle.applyForces(randFloatx, randFloaty);
      if (particle.dead()) {
        particles.remove(i);
      }
    }
    
    
  }
}
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

  public void display() { //draws the block
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
  float m = 1.0f;
  float ks = 0.1f;
  float kd = 0.1f;
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
  public void applyForces(float _fx, float _fy) {
    
    
    vx += _fx;
    vy += _fy;
    party -= vy;
    partx += vx;
    lifespan -= 1;
    
  }
  public void display() {
    fill(red, g, b, lifespan);
    noStroke();
    ellipse(partx, party, r, r);
  }
  public boolean dead() {
    if (lifespan < 0.0f) {
      return true;
    } else {
      
      return false;
    }
    
    
  }
  
  
}
class RectButton extends Button{
  String text;
  RectButton(int ix, int iy, int hsize, int wsize, int icolor, int ihighlight, String word){
    x = ix;
    y = iy;
    h_size = hsize;
    w_size = wsize;
    text = word;
    basecolor = icolor;
    highlightcolor = ihighlight;
    currentcolor = basecolor;
  }



  public boolean over(){
    if( overRect(x, y, h_size, w_size) ) {
      over = true;
      return true;
    }else {
      over = false;
      return false;
    }
  }

  public void display(){
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
int animationTimer = 0;
int animationTimerValue = 50; 


class Player {
  float x, y, w, h, vx, vy, a;
  float moveSpeed;
  float jumpSpeed;
  float pWidth;
  
  

  //moves the player
  public void playerMove() {
    a = 0.015f;
    if (vx == 0) {
      moveSpeed = 1.7f;
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
      vy+=0.22f;  //gravity
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
  
  
  public void display() {
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
    public void collide(float bx, float by, float bw, float bh) { 
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
  
  
  public void collidedoors(float bx, float by, float bw, float bh) { 
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
  
  public boolean collidedWithBlock(float bx, float by, float bw, float bh) { //returns true if the player is touching the block
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
  float m = 1.0f;
  float ks = 0.1f;
  float kd = 0.1f;
  float lifespan;
  
  Particles(float _vx, float _vy, float _r) {
    lifespan = 255;
    partx = player.x + 5;
    party = player.y;
    vx = _vx;
    vy = _vy;
    r = _r;
  }
  public void applyForces(float _fx, float _fy) {
    
    
    vx += _fx;
    vy += _fy;
    party -= vy;
    partx += vx;
    lifespan -= 1;
    
  }
  public void display() {
    fill(255, lifespan);
    noStroke();
    ellipse(partx, party, r, r);
  }
  public boolean dead() {
    if (lifespan < 0.0f) {
      return true;
    } else {
      
      return false;
    }
    
    
  }
  
  
}
class timer {

  int s;

    public void createnumber(){
      s = counter * 60 + second() - startvalue;
    }
    
    public void displaynumber(){
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
  public void settings() {  size(1280, 720); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Final_Assignment" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

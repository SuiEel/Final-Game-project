import java.util.Random;
Random randint = new Random();
Player player;
import ddf.minim.*;
Minim minim;
AudioPlayer audio;

import processing.serial.*;
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

void setup(){
  size(1280, 720);
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
  player.moveSpeed=1.7;
  player.jumpSpeed=6.8;
  
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

float randFloaty = random(0, .01);
float randFloatx = random(0, .01);

void draw(){ 
  
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

void setSignal (boolean setTo) {
  if (key == 'q'){
    holdSprint = setTo;  
  }  
}
 
void keyPressed() {
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
 
void keyReleased() { 
  if (key==CODED && keyCode==LEFT && player.vx<0) player.vx=0;
  if (key==CODED && keyCode==RIGHT && player.vx>0) player.vx=0;
  setSignal(false);
}

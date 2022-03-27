void Game(){
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
        randFloaty = random(0, .005);
        randFloatx = random(-.01, .01);
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
      randFloaty = random(0, .01);
      randFloatx = random(-.01, .01);
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
        randFloaty = random(0, .005);
        randFloatx = random(-.01, .01);
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
      randFloaty = random(0, .01);
      randFloatx = random(-.01, .01);
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
        
        randFloaty = random(0, .005);
        randFloatx = random(-.01, .01);
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
      randFloaty = random(0, .01);
      randFloatx = random(-.01, .01);
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
        
        randFloaty = random(0, .005);
        randFloatx = random(-.01, .01);
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
      randFloaty = random(0, .01);
      randFloatx = random(-.01, .01);
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
        
        randFloaty = random(0, .005);
        randFloatx = random(-.01, .01);
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
      randFloaty = random(0, .01);
      randFloatx = random(-.01, .01);
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
        
        randFloaty = random(0, .005);
        randFloatx = random(-.01, .01);
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
      randFloaty = random(0, .01);
      randFloatx = random(-.01, .01);
      particle.applyForces(randFloatx, randFloaty);
      if (particle.dead()) {
        particles.remove(i);
      }
    }
    
    
  }
}

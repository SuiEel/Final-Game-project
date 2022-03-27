void setup() {                
  // initialize the digital pins as an output.
  pinMode(2, OUTPUT);
  pinMode(3, OUTPUT);
  pinMode(4, OUTPUT);
  pinMode(5, OUTPUT);
  pinMode(6, OUTPUT);
  pinMode(7, OUTPUT);
  pinMode(8, OUTPUT);
  pinMode(9, OUTPUT);
  pinMode(10, OUTPUT);
// Turn the Serial Protocol ON
  Serial.begin(9600);
}

void loop() {
  byte byteRead;

   /*  check if data has been sent from the computer: */
  if (Serial.available()) {
  
    /* read the most recent byte */
    byteRead = Serial.read();
    //You have to subtract '0' from the read Byte to convert from text to a number.
    byteRead=byteRead-'0';
    
    //Turn off all LEDS
    for(int i=2; i<11; i++){
      digitalWrite(i, LOW);
    }
    
    if(byteRead>0){
      //Turn on the relevant LEDs
      for(int i=1; i<(byteRead+1); i++){
        digitalWrite(i+1, HIGH);
      }
    }
    //else{
      //for(int i=2; i<11; i++){
      //digitalWrite(i, 0);
      //}
    //}
  }
}

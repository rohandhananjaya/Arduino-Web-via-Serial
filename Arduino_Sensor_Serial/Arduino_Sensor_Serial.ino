
#include <DHT.h>
#include <DHT_U.h>

#define DHTPIN 12

#define DHTTYPE DHT11 



DHT_Unified dht(DHTPIN, DHTTYPE);

uint32_t delayMS;
int incomingByte = 0;
char receivedChar;
void setup() {
  Serial.begin(9600);
  // Initialize device.
  dht.begin();

  // Print temperature sensor details.
  sensor_t sensor;


void loop() {
  //---------------Do Not Change-----------------//
          sensors_event_t event;
          dht.temperature().getEvent(&event);
          
          if (isnan(event.temperature)) {
          }
          else {
            Serial.print(event.temperature);
          }
          dht.humidity().getEvent(&event);
          if (isnan(event.relative_humidity)) {
          }
          else {
            Serial.print(event.relative_humidity);
          }         
          Serial.println(F("\n"));  
          delay(5000);
 //---------------Do Not Change-----------------//

 
}

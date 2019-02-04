#include "SoftwareSerial.h"
#include "DHT.h"

#define DHTPIN 8
#define DHTTYPE DHT11

String ssid     = "Huawei-Hotspot";
String password = "12345678a";
String server   = "192.168.137.1";
String url      = "/Arduino.php";
String data, suhu_udara, nilai_kelembaban_tanah, kelembaban_udara;

int isconnect = 0;
int value1    = 10;
int value2    = 20;

int val_kelembaban_tanah = 0;
int soilPin = A0;
int soilPower = 9;

DHT dht(DHTPIN, DHTTYPE);

SoftwareSerial esp(6, 7);

int readSoil(){
  digitalWrite(soilPower, HIGH);
  delay(1000);
  val_kelembaban_tanah = analogRead(soilPin);
  digitalWrite(soilPower, LOW);

  return val_kelembaban_tanah;
}

void reset() {
  Serial.println ("Memanggil Methode Reset . . . ");

  esp.println("AT+RST");
  delay(1000);
  if(esp.find("OK"))
    Serial.println("Module Reset");

  esp.println("AT+CWMODE=1");
  delay(1000);
}

void connectWifi(int i) {
  if (i == 0)
    Serial.println ("Memanggil Method connectWifi . . .");

  String cmd = "AT+CWJAP=\"" +ssid+"\",\"" + password + "\"";
  esp.println(cmd);
  delay(4000);

  if(esp.find("OK")) {
    isconnect = 1;
      Serial.println("Berhasil Terkoneksi dengan WiFi . .");
  } else {
    Serial.print(". ");
    connectWifi(1);
  }
};

// boot setup
void setup() {
  esp.begin(9600);
  Serial.begin(9600);
  Serial.println("Memulai . . . . ");
  reset();
  int param = 0;
  connectWifi(param);
  pinMode(soilPower, OUTPUT);
  digitalWrite(soilPower, LOW);
  dht.begin();
}

// loop setup
void loop () {
  float h = dht.readHumidity(); //mengambil nilai lembab udara
  float t = dht.readTemperature(); //mengambil nilai suhu udara

  suhu_udara  = String((int)t);
  nilai_kelembaban_tanah = readSoil();
  kelembaban_udara = String((int)t);
  data = "id_board=4567&suhu_udara=06&kelembaban_tanah=007&kelembaban_udara=08";
  httppost();
  delay(1000);

  // update selama 2 menit sekali
  delay (10000);
}

void printResponse() {
  while (esp.available()) {
    Serial.println(esp.readStringUntil('\n'));
  }
}

void httppost () {
  //start a TCP connection.
  esp.println("AT+CIPSTART=\"TCP\",\"" + server + "\",80");
  if( esp.find("OK")) {
    Serial.println("Terkoneksi dengan Server . . .");
  } else {
    Serial.println ("Tidak Terkoneksi dengan server");
  }

  delay(1000);

  String postRequest =
    "POST " + url + " HTTP/1.0\r\n" +
    "Host: " + server + "\r\n" +
    "Accept: *" + "/" + "*\r\n" +
    "Content-Length: " + data.length() + "\r\n" +
    "Content-Type: application/x-www-form-urlencoded\r\n" +
    "\r\n" + data;

  String sendCmd = "AT+CIPSEND=";
  esp.print(sendCmd);
  esp.println(postRequest.length() );
  delay(500);
  if(esp.find(">")) {
    Serial.println("Sending..");
    esp.print(postRequest);
    if( esp.find("SEND OK")) {
      Serial.println("Packet sent");
      while (esp.available()) {
        String tmpResp = esp.readString();
        Serial.println(tmpResp);
      }
      // close the connection
      esp.println("AT+CIPCLOSE");
    }
  }
}

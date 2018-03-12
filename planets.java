
 //Following along with CodingTrain:
//int[] nums = {1, 2, 1, 4, 2};

//int[][] path = {{}};
// how to draw trails?

// Needed to use List:
import java.util.*;

// But this will only be able to hold float values, not objects containing two float values:
List<Float> allPath = new ArrayList<Float>();

Planet sun;
Dot dot;

void setup() {
  //print(path);

  size(800, 600);
  sun = new Planet(50, 0, 0);
  sun.spawnMoons(3, 1);
}

void draw() {
  background(200);
  //fill(10);
  //ellipse(50, 50, 100, 10*nums[1]);
  translate(width/2, height/2);
  sun.show();
  sun.orbit();
}


class Dot {
  float x;
  float y;

  Dot(float ex, float why) {
    x = ex;
    y = why;
  }

  void show() {
    fill(200);
    ellipse(x, y, 2, 2);
  }
}

// No parentheses after Planet:
class Planet {
  float radius;
  float theta;
  float dist;
  Planet[] planets;
  float orbitspeed;

  Planet(float r, float d, float o) {
    radius = r;
    dist = d;
    theta = random(TWO_PI);
    orbitspeed = o;
  }

  void orbit() {
    theta = theta + orbitspeed;
    if (planets != null) {
        for (int i=0; i < planets.length; i++) {
          planets[i].orbit();
          //println(planets[i].theta);
          double cos = java.lang.Math.cos( planets[i].theta);
          float cosf = (float)cos;
          //print(cos);
          double sin = java.lang.Math.sin(planets[i].theta);
          float sinf = (float)sin;
          dot = new Dot(planets[i].dist * cosf, planets[i].dist * sinf);
          allPath.add(cosf);
          dot.show();
          print(allPath);
        }
    }
  }

  void spawnMoons(int total, int level) {
    planets = new Planet[total];
    for (int i=0; i < planets.length; i++) {
      float r = radius / (1.6*level);
      float d = random(75, 250);
      float o = random(-0.06, 0.06);
      planets[i] = new Planet(r, d/level, o);
      // this is where you change level:
      //if (level < 3) {
      //  int num = int(random(0, 4));
      //  planets[i].spawnMoons(num, level+1);
      //}
    }
  }

  void show() {
    pushMatrix();
    fill(10, 50);
    rotate(theta);
    translate(dist, 0);
    ellipse(0, 0, radius*2, radius*2);
    if (planets != null) {
      for (int i=0; i < planets.length; i ++) {
        planets[i].show();
      }
    }
    popMatrix();
  }

}

package hyh;

//import static robocode.util.Utils.normalRelativeAngleDegrees;
import robocode.Robot;
//import robocode.HitRobotEvent;
//import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import java.awt.Color;
import java.awt.geom.Point2D;
//import robocode.HitByBulletEvent;
/**
 * Implements a competitive robot.  
 * 
 * @author Yong Hong Hsu
 */
public class Beater extends Robot {
  //private byte moveDirection = 1;
  private AdvancedEnemyBot enemy = new AdvancedEnemyBot();  
  /**
   * Moves to successive corners and rotates the robot to maximize the chances
   * of scanning another robot. 
   *
   */
  double turnGunAmt;
  /**
   * start to run.  
   * 
   */
  public void run() {
    setBodyColor(Color.red);
    // change bullet color to red.
    setBulletColor(Color.red);
    // change radar color to black.
    setRadarColor(Color.black); 
    double fWidth = 0; //battle field width
    double fHeight = 0; //battle height   
    setBodyColor(Color.RED);
    fWidth = getBattleFieldWidth();
    fHeight = getBattleFieldHeight();
       
    turnGunAmt = 5;
    turnLeft(getHeading());
    turnGunRight(turnGunAmt);
    turnRight((absoluteBearing(getX(),getY(),0,fHeight / 2)));
    while (true) {
      
      ahead(Point2D.distance(getX(), getY(), 0, fHeight / 2));
      
      //turnRight((absoluteBearing(0, fHeight / 2,fWidth / 2,fHeight)));
      turnRight(360 - getHeading() + absoluteBearing(0, fHeight / 2,fWidth / 2,fHeight));
      ahead(Point2D.distance(0,fHeight / 2, fWidth / 2, fHeight));
      
      //turnRight((180 - getHeading() - absoluteBearing(getX(), getY(),fWidth,fHeight / 2)));
      turnRight(75);
      ahead(Point2D.distance(fWidth / 2, fHeight, fWidth, fHeight / 2)); 
           
      turnRight(180 - 75);
      ahead(Point2D.distance(fWidth, fHeight / 2, fWidth / 2, 0));
      //turnRight(180 - getHeading() - absoluteBearing(fWidth / 2, 0, 0, fHeight / 2));
      turnRight(75);
   }
 }
  /**
   * @return ang- a bearing
   * @param angle - a bearing
   */
  double normalizeBearing(double angle) {
    double ang = angle;
    while (ang >  180) {
      ang -= 360;
    }
    while (ang < -180) {
      ang += 360;
    }
    return ang;
  }
  /**
   * Fires at any robots it finds with a power that is based on distance from target.
   * 
   * @param e contains information about the enemy robot, e.g. its location
   */
  public void onScannedRobot(ScannedRobotEvent e) {
    double firePower;
    if ( enemy.none() || e.getDistance() < enemy.getDistance() - 70 ||
      e.getName().equals(enemy.getName())) {
      // track him using the NEW update method
      enemy.update(e, this);
      // calculate firepower based on distance
      firePower = Math.min(400 / e.getDistance(), 3);
      // calculate speed of bullet
      double bulletSpeed = 20 - firePower * 3;
      // distance = rate * time, solved for time
      long time = (long)(e.getDistance() / bulletSpeed);
      // calculate gun turn to predicted x,y location
      double futureX = enemy.getFutureX(time);
      double futureY = enemy.getFutureY(time);
      double absDeg = absoluteBearing(getX(), getY(), futureX, futureY);
      turnGunAmt = (normalizeBearing(absDeg - getGunHeading()));
      if (getGunHeat() == 0) {
        this.fire(firePower);
      }
    } 
    scan();
  } 
  /**
   * Fires at any robots it finds with a power that is based on distance from target.
   * 
   * @param e contains information about the enemy robot, e.g. its location
   */
  /*public void onHitWall(HitWallEvent e) { 
       moveDirection *= -1; 
    //   ahead(-100);
  }
  /**
   * Fires at any robots it finds with a power that is based on distance from target.
   * 
   * @param e contains information about the enemy robot, e.g. its location
   */
  /*public void onHitRobot(HitRobotEvent e) { 
    moveDirection *= -1; 
    //    ahead(-100);
  }
  /**
   * Fires at any robots it finds with a power that is based on distance from target.
   * 
   * @param e contains information about the enemy robot, e.g. its location
   */
  /**
   * this is a absolute bearing.
   * @return bearing a bearing.
   * @param x1 postion.
   * @param x2 postion.
   * @param y1 position.
   * @param y2 position.
   */
  double absoluteBearing(double x1, double y1, double x2, double y2) {
    double xo = x2 - x1;
    double yo = y2 - y1;
    double hyp = Point2D.distance(x1, y1, x2, y2);
    double arcSin = Math.toDegrees(Math.asin(xo / hyp));
    double bearing = 0;
    if (xo > 0 && yo > 0) { // both pos: lower-Left
      bearing = arcSin;
    } 
    else if (xo < 0 && yo > 0) { // x neg, y pos: lower-right
      bearing = 360 + arcSin; // arcsin is negative here, actuall 360 - ang
    } 
    else if (xo > 0 && yo < 0) { // x pos, y neg: upper-left
      bearing = 180 - arcSin;
    } 
    else if (xo < 0 && yo < 0) { // both neg: upper-right
      bearing = 180 - arcSin; // arcsin is negative here, actually 180 + ang
    }
    return bearing;
  }
}

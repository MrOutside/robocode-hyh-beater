package hyh;

import robocode.Robot;
import robocode.ScannedRobotEvent;

/**
 * This class updating information.
 * 
 * @author Yong Hong Hsu
 */
public class AdvancedEnemyBot extends EnemyBot {
  // Declare 2 new private variables in AdvancedEnemyBot called: x and y. They will be of type
  // double
  private double x;
  private double y;

  /**
   * constructor for resetting.
   * 
   */
  // public AdvancedEnemyBot() {
  // reset();
  // }

  /**
   * get robot's x position.
   * 
   * @return x the robot x position.
   */
  public double getX() {
    return x;
  }

  /**
   * get robot's y position.
   * 
   * @return y the robot y position.
   */
  public double getY() {
    return y;
  }

  /**
   * call parent reset method to reset those variables in parent class. also reset the variables in
   * this class.
   */
  public void reset() {
    super.reset();
    x = 0;
    y = 0;
  }

  /**
   * updating information.
   * 
   * @param e event.
   * @param robot my robot.
   */
  public void update(ScannedRobotEvent e, Robot robot) {
    super.update(e);
    double absBearingDeg = (robot.getHeading() + e.getBearing());
    if (absBearingDeg < 0) {
      absBearingDeg += 360;
    }
    x = robot.getX() + Math.sin(Math.toRadians(absBearingDeg)) * e.getDistance();
    y = robot.getY() + Math.cos(Math.toRadians(absBearingDeg)) * e.getDistance();
  }

  /**
   * predict the enemy's future x position.
   * 
   * @return x the enemy future position.
   * 
   * @param time which calculate in Beater class.
   */
  public double getFutureX(long time) {
    return x + Math.sin(Math.toRadians(getHeading())) * getVelocity() * time;
  }

  /**
   * predict the enemy's future y position.
   * 
   * @return y the enemy future position.
   * 
   * @param time which calculate in Beater class.
   */

  public double getFutureY(long time) {
    return y + Math.cos(Math.toRadians(getHeading())) * getVelocity() * time;
  }
}

package hyh;

import robocode.control.testing.RobotTestBed;
/**
 * test absolute bearing.  
 * 
 * @author Yong Hong Hsu
 */
public class TestAbsoluteBearing extends RobotTestBed {
    /**
     * @return robot name. 
     */
    @Override
  public String getRobotNames() {
    // TODO Auto-generated method stub
    return "sample.SittingDuck,hyh.Beater";
  }
  /**
   * This test the bearing. 
   */ 
  public void testBearing() {
    Beater beat = new Beater();
    assert (beat.absoluteBearing(0,  0, 50, 50) == 45);
  }
}

package hyh;
import static org.junit.Assert.assertEquals;
import robocode.control.testing.RobotTestBed;
import robocode.BattleResults;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.events.TurnEndedEvent;

/**
 * Illustrates JUnit testing of Robocode robots.
 * This test simply verifies that Beater always beats SittingDuck.
 * 
 * @author Yong Hong Hsu
 */
public class TestBeaterVsSittingDuck extends RobotTestBed {
  /**
   * Specifies that SittingDuck and Beater are to be matched up in this test case.
   * @return The comma-delimited list of robots in this match.
   */
  @Override
  public String getRobotNames() {
    return "sample.SittingDuck,hyh.Beater";
  }
  
  /**
   * This test runs for 10 rounds.
   * @return The number of rounds. 
   */
  @Override
  public int getNumRounds() {
    return 10;
  }
  
  /**
   * The actual test, which asserts that DaCruzer has won every round against SittingDuck.
   * @param event Details about the completed battle.
   */
  @Override
  public void onBattleCompleted(BattleCompletedEvent event) {
    // Return the results in order of getRobotNames.
    BattleResults[] battleResults = event.getIndexedResults();
    // Sanity check that results[1] is Beater.
    BattleResults beaterResults = battleResults[1];
    String robotName = beaterResults.getTeamLeaderName();
    assertEquals("Check that results[1] is Beater", robotName, "hyh.Beater*");   
    assertEquals("Check Beater winner", beaterResults.getFirsts(), getNumRounds());
  }
  /**
   * Called after each turn. 
   * Provided here to show that you could use this method as part of your testing.  
   * @param event The TurnEndedEvent.
   */
  @Override
  public void onTurnEnded(TurnEndedEvent event) {
    // You could add code here to check a condition after every turn or collect data. 
  }
  
  /**
   * Returns true if the battle should be deterministic and thus robots will always start
   * in the same position each time. 
   * 
   * Override to return false to support random initialization. 
   * @return True if the battle will be deterministic.
   */
//  @Override
 // public boolean isDeterministic() {
  //  return true;
  //}

  /**
   * Specifies how many errors you expect this battle to generate.
   * Defaults to 0. Override this method to change the number of expected errors. 
   * @return The expected number of errors.
   */
  @Override
  protected int getExpectedErrors() {
    return 0;
  }
}
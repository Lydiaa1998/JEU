package unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This class defines all the tests to be carried out to check the correct operation
 * of the various functions.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({DungeonTestPlayer.class, DungeonTestMonster.class, DungeonTestMonsterAttack.class, DungeonTestPlayerAttack.class})
public class DungeonTestSuite {

}

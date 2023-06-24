package testSuit;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({JDBC.DB_controllerTest.class})
public class AllTests {

}

package JDBC;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MsgTest {
	
	private static Msg msgBeingTested;
	private static ArrayList<ArrayList<Object>> data;
	private static MyObject myObj1;
	private static MyObject myObj2;
	private static Integer int1;
	private static Integer int2;
	private static String str1;
	private static String str2;
	private static ArrayList<Object> tmpLst;
	private static ArrayList<MyObject> expected;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		myObj1 = new MyObject(50, "name1");
		myObj2 = new MyObject(80, "name2");
		int1 = 50;
		int2 = 80;
		str1 = new String("name1");
		str2 = new String("name2");
		
	}
	
	@BeforeEach
	void setUpBeforeTest() throws Exception {
		msgBeingTested = new Msg(MsgType.data);
		msgBeingTested.setData(data);
		expected = new ArrayList<MyObject>();
	}
	
    // Define the DataObject class for testing on
    static class MyObject {
        private Integer intVal;
        private String stringVal;
        
        // constructor 1:
        public MyObject() {
        }
        
        // constructor 2:
        public MyObject(Integer intVal) {
            this.intVal = intVal;
        }
        
        // constructor 3:
        public MyObject(String stringVal) {
            this.stringVal = stringVal;
        }

        // constructor 4:
        public MyObject(Integer intVal, String stringVal) {
            this.intVal = intVal;
            this.stringVal = stringVal;
        }
        
        // toString method:
        public String toString() {
        	return "MyObject["+intVal.toString()+", "+stringVal+"]";
        }
    }
    
    
    // Description: 
    // Input: 
    // Expected Result:
	@Test
	void test() {
		//ArrayList<Object>
		assertTrue(true);
	}

}

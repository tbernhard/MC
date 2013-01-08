//import org.junit.Test;
//
//import ch.tom.mc.Message;
//import ch.tom.mc.MessageProcessor;
//import ch.tom.mc.SMS;
//
//
//public class HelloWorldTest {
//
//	@Test
//	public void testValidSms() {
//
//		MessageProcessor p = new MessageProcessor(); 
//		Message m = new SMS(); //"hamlet", "01312312", "text"
//		p.send(m); 
//		// no exception means everything must be OK! 
//	}
//
//	@Test
//	public void testInvalidSms() {
//
//		MessageProcessor p = new MessageProcessor(); 
//		Message m = new SMS("hamlet", "01312312", 
//				"0123456789" + 
//						"0123456789" + 
//						"0123456789" + 
//						"0123456789" + 
//						"0123456789" + 
//						"0123456789" + 
//						"0123456789" + 
//						"0123456789" + 
//						"0123456789" + 
//						"0123456789" + 
//						"0123456789" + 
//						"0123456789" + 
//						"0123456789" + 
//						"0123456789" + 
//						"0123456789" + 
//						"01234567891"); // 161 is too many! 
//		try {
//			p.send(m); 
//			fail("An sms of length 161 should always throw an exception"); 
//		} catch (IllegalArgumentException e) {
//			// we expect this exception to occur
//		}
//	}
//
//}

package doc.cba;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.After;

/**
 * Unit test for simple Account.
 */
public class AccountTest 
    extends TestCase
{
				Account account;
				@Before
				public void init() {
								account = new Account();
				}

				@After
				public void after() {
								System.out.println("why");
				}
				/**
				* Create the test case
				*
				* @param testName name of the test case
				*/
				public AccountTest( String testName )
				{
								super( testName );
				}

				/**
				* @return the suite of tests being tested
				*/
				public static Test suite()
				{
								return new TestSuite( AccountTest.class );
				}

				public void setUp() {
								account = new Account();
				}
				//@Before 
				//public void setUp() {
								//account = new Account();
				//}

				public void testDefaultName() {
								assertEquals("Default", account.getName());
				}

				public void testDefaultBalance() {
								assertEquals(0, account.getBalance());
				}	 

				public void testSetBalance() {
								account.setBalance(5);
								assertEquals(5, account.getBalance());
				}

				public void testWithdrawTooMuch() {
								assertFalse(account.withdraw(10));
				}

				public void testWithdrawNegative() {
								assertFalse(account.withdraw(-1));
				}

				public void testWithdrawZero() {
								assertTrue(account.withdraw(0));
				}
				
				public void testWithdrawAppropriate() {
								account.setBalance(5);
								assertTrue(account.withdraw(5));
				}

				public void testWithdrawMultipleTimes() {
								account.setBalance(5);
								account.withdraw(5);
								assertFalse(account.withdraw(1));
				}

				public void testDepositNegative() {
								assertFalse(account.deposit(-1));
				}

				public void testDepositZero() {
								assertTrue(account.deposit(0));
				}

				public void testDepositPositive() {
								assertTrue(account.deposit(1));
				}

				public void testDepositStore() {
								account.setBalance(0);
								account.deposit(5);
								assertEquals(5, account.getBalance());
				}
}

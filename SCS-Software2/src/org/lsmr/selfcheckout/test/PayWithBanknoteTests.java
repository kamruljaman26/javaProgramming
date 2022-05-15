package iter1tests;

import static org.junit.Assert.*;
import org.junit.*;
import org.lsmr.selfcheckout.*;
import org.lsmr.selfcheckout.devices.*;
import org.lsmr.selfcheckout.devices.observers.*;

import controllers.PayWithBanknote;
import controllers.Paycoin;

import java.math.*;
import java.util.*;


public class PayWithBanknoteTests {
	
	//declare testing variables and objects	
	
	SelfCheckoutStation scs;
	BigDecimal val = new BigDecimal(1.00);
	BigDecimal[] cdenom_array = {val};
	int scaleMaximumWeight = 3;
	int scaleSensitivity = 3;
	

	//will become valid banknote denominations
	int val1 = 1;
	int val2 = 5;
	int val3 = 10;
	int val4 = 20;
	int[] denom_array = {val1, val2, val3, val4};
	
	//default currency canadian dollars
	Currency defcur = Currency.getInstance("CAD");
	
	//these are the valid banknotes
	Banknote dollar_bill;
	Banknote fiver1;
	Banknote fiver2;
	Banknote ten_dollars;
	Banknote twenty_bucks;
	
	//invalid currency set to usd
	Currency invcur = Currency.getInstance("USD");
	
	//invalid bankenotes
	Banknote invalid1;
	Banknote invalid2;
	int inval = 15;

	//total values
	BigDecimal totalOwed = new BigDecimal(15.25);
	BigDecimal expectedTotal = new BigDecimal(0);
	BigDecimal actualTotal = new BigDecimal(0);
	BigDecimal deposited = new BigDecimal(0);
	
	PayWithBanknote paybanknote;
	
	
	@Before
	//runs before each test
	public void setUp() {
		scs = new SelfCheckoutStation(defcur, denom_array, cdenom_array, scaleMaximumWeight, scaleSensitivity);

		paybanknote = new PayWithBanknote(scs, totalOwed);
		
		//banknotes
		dollar_bill = new Banknote(defcur, val1);
		fiver1 = new Banknote(defcur, val2);
		fiver2 = new Banknote(defcur, val2);
		ten_dollars = new Banknote(defcur, val3);
		twenty_bucks = new Banknote(defcur, val4);
		
		invalid1 = new Banknote(invcur, val1);
		invalid2 = new Banknote(defcur, inval);
		
	
	}

	@After
	public void tearDown() {

		//don't worry about it
		try {
			scs.banknoteInput.removeDanglingBanknote();
			
		} catch (SimulationException e) {
			//everything is fine
		}
	}
	
	//tests
	
	//=================================================
	// Testing that when a banknote is input then
	// total owed changes accordingly.
	//=================================================
	
	//=================================================
	// Testing valid banknotes
	//=================================================

	@Test
	public void testValidBill1() throws DisabledException, OverloadException {
		//input a fiver
		scs.banknoteInput.accept(fiver1);
		//expected new total owed is original - value of the bill
		BigDecimal depositedVal = new BigDecimal(fiver1.getValue());
		deposited = deposited.add(depositedVal);
		expectedTotal = totalOwed.subtract(deposited);
		actualTotal = paybanknote.remainingAmountToBePaid();
		//check that the total is properly changed
		assertEquals("unexpected change owed.",
				expectedTotal, actualTotal);
	}
	
	@Test
	public void testValidBills1() throws DisabledException, OverloadException {
		//input 2 fivers
		scs.banknoteInput.accept(fiver1);
		scs.banknoteInput.accept(fiver2);
		//expected new total owed is original - value of the coin
		BigDecimal depositedVal = new BigDecimal(fiver1.getValue());
		deposited = deposited.add(depositedVal);
		depositedVal = new BigDecimal(fiver2.getValue());
		deposited = deposited.add(depositedVal);
		expectedTotal = totalOwed.subtract(deposited);
		actualTotal = paybanknote.remainingAmountToBePaid();
		//check that the total is properly changed
		assertEquals("unexpected change owed.",
				expectedTotal, actualTotal);
	}
	
	//=================================================
	// Testing invalid banknotes
	//=================================================
	
	@Test
	public void testinvalidBill1() throws DisabledException, OverloadException {
		//input invalid bill
		scs.banknoteInput.accept(invalid1);
		//expected new total owed is unchanged
		expectedTotal = totalOwed.subtract(deposited);
		actualTotal = paybanknote.remainingAmountToBePaid();
		//check that the total is properly changed
		assertEquals("unexpected change owed.",
				expectedTotal, actualTotal);
	}
	
	@Test
	public void testinvalidBill2() throws DisabledException, OverloadException {
		//input invalid bill
		scs.banknoteInput.accept(invalid2);
		//expected new total owed is unchanged
		expectedTotal = totalOwed.subtract(deposited);
		actualTotal = paybanknote.remainingAmountToBePaid();
		//check that the total is properly changed
		assertEquals("unexpected change owed.",
				expectedTotal, actualTotal);
	}
	
	@Test
	public void testinvalidBills1() throws DisabledException, OverloadException {
		//input a fiver and an invalid bill
		scs.banknoteInput.accept(fiver1);
		scs.banknoteInput.accept(invalid1);
		//expected new total owed is original - value of valid bill
		BigDecimal depositedVal = new BigDecimal(fiver1.getValue());
		deposited = deposited.add(depositedVal);
		expectedTotal = totalOwed.subtract(deposited);
		actualTotal = paybanknote.remainingAmountToBePaid();
		//check that the total is properly changed
		assertEquals("unexpected change owed.",
				expectedTotal, actualTotal);
	}
	
	@Test
	public void testinvalidBills2() throws DisabledException, OverloadException {
		//input a fiver and an invalid bill
		scs.banknoteInput.accept(fiver1);
		scs.banknoteInput.accept(invalid2);
		//expected new total owed is original - value of valid bill
		BigDecimal depositedVal = new BigDecimal(fiver1.getValue());
		deposited = deposited.add(depositedVal);
		expectedTotal = totalOwed.subtract(deposited);
		actualTotal = paybanknote.remainingAmountToBePaid();
		//check that the total is properly changed
		assertEquals("unexpected change owed.",
				expectedTotal, actualTotal);
	}
	
	@Test
	public void testinvalidBills3() throws DisabledException, OverloadException {
		//input an invalid bill first
		scs.banknoteInput.accept(invalid1);
		scs.banknoteInput.removeDanglingBanknote();
		scs.banknoteInput.accept(fiver1);
		//expected new total owed is original - value of valid bill
		BigDecimal depositedVal = new BigDecimal(fiver1.getValue());
		deposited = deposited.add(depositedVal);
		expectedTotal = totalOwed.subtract(deposited);
		actualTotal = paybanknote.remainingAmountToBePaid();
		//check that the total is properly changed
		assertEquals("unexpected change owed.",
				expectedTotal, actualTotal);
	}
	
	@Test
	public void testinvalidBills4 () throws DisabledException, OverloadException {
		//input an invalid bill first
		scs.banknoteInput.accept(invalid2);
		scs.banknoteInput.removeDanglingBanknote();
		scs.banknoteInput.accept(fiver1);
		//expected new total owed is original - value of valid bill
		BigDecimal depositedVal = new BigDecimal(fiver1.getValue());
		deposited = deposited.add(depositedVal);
		expectedTotal = totalOwed.subtract(deposited);
		actualTotal = paybanknote.remainingAmountToBePaid();
		//check that the total is properly changed
		assertEquals("unexpected change owed.",
				expectedTotal, actualTotal);
	}
	

	
	
}



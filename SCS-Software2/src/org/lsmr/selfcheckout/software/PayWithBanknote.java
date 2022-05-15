package controllers;
import java.math.BigDecimal;
import java.util.Currency;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BanknoteDispenser;
import org.lsmr.selfcheckout.devices.BanknoteStorageUnit;
import org.lsmr.selfcheckout.devices.BanknoteValidator;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.BanknoteDispenserObserver;
import org.lsmr.selfcheckout.devices.observers.BanknoteStorageUnitObserver;
import org.lsmr.selfcheckout.devices.observers.BanknoteValidatorObserver;

public class PayWithBanknote implements BanknoteValidatorObserver
{
	private BigDecimal totalAmountPaid = new BigDecimal(0.0); 
	private BigDecimal totalValueofCart = new BigDecimal(0.0); 
	
	SelfCheckoutStation scs;

	public PayWithBanknote(SelfCheckoutStation scs, BigDecimal totalValueOfCart)
	{
		this.totalValueofCart = totalValueOfCart;
		this.scs = scs;
		scs.banknoteValidator.attach(this);
	}
	
	public boolean allItemsPaid() 
	{ 
		// if valid banknote detected calculate the total amount paid and check if it's >= total cost of cart 
		// then disable bankNoteInput 
		if (remainingAmountToBePaid().compareTo(totalAmountPaid) <= 0) 
		{
			scs.banknoteInput.disable();
			return true;
		}
		
		return false;			// checks whether the total amount has been paid for or not
	}
	
	public BigDecimal remainingAmountToBePaid() 
	{
		return totalValueofCart.subtract(totalAmountPaid);
	}
	

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void validBanknoteDetected(BanknoteValidator validator, Currency currency, int value) {
		totalAmountPaid = totalAmountPaid.add(BigDecimal.valueOf(value));		// update the total amount paid depending on the value of the banknote 
		
	}

	@Override
	public void invalidBanknoteDetected(BanknoteValidator validator) {
		// TODO Auto-generated method stub
		
	}

}

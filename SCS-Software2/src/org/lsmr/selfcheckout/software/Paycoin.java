package controllers;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Queue;

import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.CoinDispenser;
import org.lsmr.selfcheckout.devices.CoinSlot;
import org.lsmr.selfcheckout.devices.CoinValidator;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.CoinDispenserObserver;
import org.lsmr.selfcheckout.devices.observers.CoinSlotObserver;
import org.lsmr.selfcheckout.devices.observers.CoinValidatorObserver;

public class Paycoin implements CoinDispenserObserver {
	

private BigDecimal totalowed=new BigDecimal(0.00); 
private BigDecimal depositedcoin=new BigDecimal(0.00);
private SelfCheckoutStation scs;

 
public Paycoin(SelfCheckoutStation scs, BigDecimal totalowed) {
     this.totalowed=totalowed;
     this.scs=scs;
     for(CoinDispenser coinDispenser : scs.coinDispensers.values())
         coinDispenser.attach(this);
   }

public BigDecimal changeowed() {
	return totalowed.subtract(depositedcoin);
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
public void coinsFull(CoinDispenser dispenser) {
	// TODO Auto-generated method stub
	
}

@Override
public void coinsEmpty(CoinDispenser dispenser) {
	// TODO Auto-generated method stub
	
}

//update the total amount paid depending on the value of the coin
@Override
public void coinAdded(CoinDispenser dispenser, Coin coin) {
	depositedcoin=depositedcoin.add(coin.getValue());
	
}

@Override
public void coinRemoved(CoinDispenser dispenser, Coin coin) {
	// TODO Auto-generated method stub
	
}

@Override
public void coinsLoaded(CoinDispenser dispenser, Coin... coins) {
	// TODO Auto-generated method stub
	
}

@Override
public void coinsUnloaded(CoinDispenser dispenser, Coin... coins) {
	// TODO Auto-generated method stub
	
}



}
	



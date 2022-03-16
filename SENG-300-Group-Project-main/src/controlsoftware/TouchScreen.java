package controlsoftware;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.TouchScreenObserver;

public class TouchScreen implements TouchScreenObserver {
	
	private boolean isEnabled = false;
	
	// (Brody) Methods made here will handle inputs from the simulated touch screen / GUI
	// E.g. Customer wants to check out, they press the checkout button and all devices/observers not
	// relevant to checking out will be disabled, and enable all payment related devices/observers if necessary 
	// then call the method(s) in the Checkout class to handle the logic of checking out.

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		this.isEnabled = true;
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		this.isEnabled = false;
		
	}

	public boolean isEnabled() {
		return isEnabled;
	}
}

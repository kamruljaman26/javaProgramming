package org.lsmr.selfcheckout.software;

import java.io.IOException;
import java.math.BigDecimal;
import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.Card.CardData;
import org.lsmr.selfcheckout.Card.CardInsertData;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.CardReader;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.CardReaderObserver;

public class DebitCardSoftware implements CardReaderObserver {
    public final CardReader cardReader;
    private CardInsertData cardInsertData;
    private CardData cardData;
    public boolean cardTapped = false;
    public boolean cardSwiped = false;
    public boolean cardInsert = false;
    private SelfCheckoutStation scs;

    public DebitCardSoftware(SelfCheckoutStation selfCheckoutStation) {
        scs = selfCheckoutStation;
        scs.cardReader.attach(this);
        cardReader = new CardReader();
        cardReader.endConfigurationPhase();
    }

    /**
     * This class is a simulation of paying with a debit card at the checkout.
     * 
     * The type of payment (creditcard) needs to be debit card.
     * The option of payment (paymentOption) has to be either tap, swipe or insert.
     * The card limit (totalBalance) needs to be more than the amount owed.
     * The pin (pin) entered by the user has to be correct.
     * Return true if payment is successful, false otherwise.
     */

    public boolean PayWithDebitCard(Card debitCard, int paymentMethod, BigDecimal totalBalance, String pin,
            BigDecimal total) throws IOException {
        CardData cardInformation = null;
        int value = 0;
        boolean cardReadApproved = false;
        double balance = totalBalance.doubleValue();
        double cost = total.doubleValue();

        if (debitCard == null) {
            return false;
        }
        if (paymentMethod > 3 || paymentMethod < 1) {
            return false;
        }

        if (paymentMethod == 1) {
            cardInformation = scs.cardReader.tap(debitCard);
            if (cardData.getType() == "debit") {
                if (cardInformation == cardData) {
                    cardReadApproved = true;
                }

            }

        }

        if (paymentMethod == 2) {
            cardInformation = scs.cardReader.swipe(debitCard);
            if (cardData.getType() == "debit") {
                if (cardInformation == cardData) {
                    cardReadApproved = true;
                }

            }

        }

        if (paymentMethod == 3) {
            cardInformation = scs.cardReader.insert(debitCard, pin);
            if (cardData.getType() == "debit") {
                if (cardInformation == cardData) {
                    cardReadApproved = true;
                }

            }
        }
        if (cardReadApproved == true && balance >= cost) {
            return true;
        }

        return false;

    }

    @Override
    public void cardInserted(CardReader reader) {
        cardInsert = true;
    }

    @Override
    public void cardRemoved(CardReader reader) {

    }

    @Override
    public void cardTapped(CardReader reader) {
        cardTapped = true;

    }

    @Override
    public void cardSwiped(CardReader reader) {
        cardSwiped = true;

    }

    @Override
    public void cardDataRead(CardReader reader, CardData data) {
        this.cardData = data;

    }

    @Override
    public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
        // TODO Auto-generated method stub

    }

    @Override
    public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
        // TODO Auto-generated method stub

    }

}
package com.myproj;

import java.math.BigDecimal;

public class Agregator {

    static Agregator agregator;
    static Agregator getInstance() {
        if (agregator == null) {
            agregator = new Agregator();
        }
        return agregator;
    }

    public double calculateWOtax(double price, double quantity) {
        return CurrencyRounder.getInstance().round(MultipleDevide.getInstance().multipleAndGet(price, quantity));
    }

    public double calculateTax(double price, double quantity) {
        double woTax = calculateWOtax(price, quantity);
        return CurrencyRounder.getInstance().round(MultipleDevide.getInstance().multipleAndGet(woTax, Tax.getInstance().getValue(woTax)));
    }

    public double calculateDiscount(double amount) {
        return CurrencyRounder.getInstance().round(MultipleDevide.getInstance().devideAndGet(amount,DiscountMatrix.getInstance().getValue(amount)));
    }

    public double calculateTotalWithDiscount(double price, double quantity) {
        double woTax = CurrencyRounder.getInstance().round(calculateWOtax(price, quantity));
        double tax = CurrencyRounder.getInstance().round(calculateTax(price, quantity));
        double discount = CurrencyRounder.getInstance().round(calculateDiscount(woTax));
        double amountWOdiscount = CurrencyRounder.getInstance().round(PlusMines.getInstance().minuAndGet(woTax,discount));

        return PlusMines.getInstance().plusAndGet(amountWOdiscount, tax );
    }


}

class DiscountMatrix {

    double getValue(double amount) {
        double retVal;
        if (amount < 1000) {
            retVal = 1;
        } else if (amount < 2000) {
            retVal = 1.1;
        } else if (amount < 3000) {
            retVal = 1.2;
        } else if (amount < 4000) {
            retVal = 1.3;
        } else {
            retVal = 1.5;
        }
        return retVal;
    }

    static DiscountMatrix discountMatrix;
    static DiscountMatrix getInstance() {
        if (discountMatrix == null) {
            discountMatrix = new DiscountMatrix();
        }
        return discountMatrix;
    }
}

class Tax {

    // TODO: unusefull amount
    double getValue(double amount) {
        return MultipleDevide.getInstance().devideAndGet(20,100);
    }

    static Tax tax;
    static Tax getInstance() {
        if (tax == null) {
            tax = new Tax();
        }
        return tax;
    }
}

class MultipleDevide {
    static MultipleDevide multipleDevide;

    double multipleAndGet(double a, double b) {
        return a * b;
    }

    double devideAndGet(double a, double b) {
        // TODO: round that
        return a / b;
    }

    static MultipleDevide getInstance() {
        if (multipleDevide == null) {
            multipleDevide = new MultipleDevide();
        }
        return multipleDevide;
    }
}

class PlusMines {
    static PlusMines plusMines;

    double plusAndGet(double a, double b) {
        return a + b;
    }

    double minuAndGet(double a, double b) {
        return a - b;
    }

    static PlusMines getInstance() {
        if (plusMines == null) {
            plusMines = new PlusMines();
        }
        return plusMines;
    }
}

class CurrencyRounder {
    static CurrencyRounder currencyRounder;

    double round(double a) {
        BigDecimal v = new BigDecimal(a);
        BigDecimal roundOff = v.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        return  roundOff.doubleValue();
    }

    static CurrencyRounder getInstance() {
        if (currencyRounder == null) {
            currencyRounder = new CurrencyRounder();
        }
        return currencyRounder;
    }
}


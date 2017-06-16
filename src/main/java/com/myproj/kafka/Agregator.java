package com.myproj.kafka;

public class Agregator {


    public double calculateWOtax(double price, double quantity) {
        return MultipleDevide.getInstance().multipleAndGet(price, quantity);
    }

    public double calculateTax(double price, double quantity) {
        return MultipleDevide.getInstance().multipleAndGet(price, quantity);
    }

    public double calculateDiscount(double amount) {
        return MultipleDevide.getInstance().devideAndGet(amount,DiscountMatrix.getInstance().getValue(amount));
    }

    public double calculateTotalWithDiscount(double price, double quantity) {
        double woTax = calculateWOtax(price, quantity);
        double tax = calculateTax(price, quantity);
        double discount = calculateDiscount(woTax);
        double amountWOdiscount = PlusMines.getInstance().minuAndGet(woTax,discount);

        return PlusMines.getInstance().plusAndGet(amountWOdiscount, tax );
    }


}

class DiscountMatrix {
    static DiscountMatrix discountMatrix;

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

    static DiscountMatrix getInstance() {
        if (discountMatrix == null) {
            discountMatrix = new DiscountMatrix();
        }
        return discountMatrix;
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


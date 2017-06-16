package com.myproj;

import org.junit.Assert;
import org.junit.Test;

public class AgregatorTest {

    @Test
    public void calculateWOtax(){
        Assert.assertEquals(Agregator.getInstance().calculateWOtax(1000,2),2000,0.001);
    }
    @Test
    public void calculateTax() {
        Assert.assertEquals(Agregator.getInstance().calculateTax(1000,2),400,0.001);
    }

    @Test
    public void  calculateDiscount() {
        Assert.assertEquals(Agregator.getInstance().calculateDiscount(4000),2666.67,0.001);
    }

    @Test
    public void calculateTotalWithDiscount() {
        Assert.assertEquals(Agregator.getInstance().calculateTotalWithDiscount(1000,3),1292.31,0.001);
    }


}

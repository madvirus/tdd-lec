package tdd;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;

public class RateCalculatorTest {

    @Test
    public void zeroQuantity_then_baseFee() {
        RateCalculator cal = new RateCalculator();
        int amounts = cal.calculate(0);
        assertThat(amounts, equalTo(2000));
    }

}

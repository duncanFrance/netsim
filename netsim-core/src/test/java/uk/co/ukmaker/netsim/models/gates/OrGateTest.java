package uk.co.ukmaker.netsim.models.gates;

import static uk.co.ukmaker.netsim.SignalValue.ONE;
import static uk.co.ukmaker.netsim.SignalValue.X;
import static uk.co.ukmaker.netsim.SignalValue.Z;
import static uk.co.ukmaker.netsim.SignalValue.ZERO;

import org.junit.Test;

import uk.co.ukmaker.netsim.pins.InputPin;
import uk.co.ukmaker.netsim.pins.OutputPin;

public class OrGateTest extends TwoInputGateTest {
	
	
	@Override
	public void setup() {
		
		gate = new OrGate();
		
		a = (InputPin)gate.getPins().get("a");
		b = (InputPin)gate.getPins().get("b");
		q = (OutputPin)gate.getPins().get("q");
	}
	
	@Test
	public void testLogic() {
		
		expect(X, null);
		
		check(ZERO, ZERO, X, ZERO);
		check(ZERO, ONE, ZERO, ONE);
		check(ONE, ZERO, ONE, null);
		check(ONE, ONE, ONE, null);

		check(ONE, Z, ONE, X);
		check(ONE, ONE, X, ONE);
		check(ZERO, Z, ONE, X);
		check(ONE, ONE, X, ONE);
		check(X, Z, ONE, X);
		check(ONE, ONE, X, ONE);
		check(Z, Z, ONE, X);
		check(ONE, ONE, X, ONE);

		check(Z, ONE, ONE, X);
		check(ONE, ONE, X, ONE);
		
		check(Z, ZERO, ONE, X);
		check(ONE, ONE, X, ONE);
		
		check(Z, X, ONE, X);
		check(ONE, ONE, X, ONE);
		
		check(X, X, ONE, X);
		check(ONE, ONE, X, ONE);

	}
}

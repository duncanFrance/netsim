package uk.co.ukmaker.netsim.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;

import uk.co.ukmaker.netsim.SignalValue;
import uk.co.ukmaker.netsim.pins.InputPin;
import uk.co.ukmaker.netsim.pins.OutputPin;

abstract public class ModelTest {
	
	protected long t = 0;
	
	protected Model model;
	protected List<InputPin> inputs;
	protected List<OutputPin> outputs;
	
	public abstract Model getComponent();
	
	public abstract List<InputPin> getInputs();
	public abstract List<OutputPin> getOutputs();
	
	@Before
	public void prepare() {
		setup();
		model = getComponent();
		inputs = getInputs();
		outputs = getOutputs();
	}
	
	public abstract void setup();
	
	public void apply(SignalValue... values) {
		
		int i=0;
		
		for(SignalValue v : values) {
			inputs.get(i++).scheduleInputValue(t, v);
		}
		
		model.update(t);
		t+= 10000;
	}
	
	/**
	 * SignalValues are supplied in pairs, currentValue and scheduled value
	 * @param values
	 */
	public void expect(SignalValue... values) {
		
		if(values.length != outputs.size() * 2) {
			throw new RuntimeException("Incorrect number of expected values");
		}
		
		for(int i=0; i<outputs.size(); i++) {
			
			OutputPin p = outputs.get(i);
			
			SignalValue c = values[i*2];
			SignalValue s = values[(i*2) + 1];
			
			if(c == null) {
				assertNull(p.getName(), p.getOutputValue());
			} else {
				assertEquals(p.getName(), c, p.getOutputValue());
			}
			
			if(s == null) {
				assertNull(p.getName(), p.useScheduledOutputValue(t));
			} else {
				assertEquals(p.getName(), s, p.useScheduledOutputValue(t).getValue());
			}
		}
	}
}

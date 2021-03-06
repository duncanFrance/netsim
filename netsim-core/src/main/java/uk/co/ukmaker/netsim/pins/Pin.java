package uk.co.ukmaker.netsim.pins;

import uk.co.ukmaker.netsim.models.Model;
import uk.co.ukmaker.netsim.netlist.Net;

abstract public class Pin {
	
	/**
	 * The Component to which this port belongs
	 */
	private final Model model;
	private final String name;
	private Net net;
	/**
	 * The list of nets 
	 * @param model
	 * @param name
	 */

	public Pin(final Model model, final String name) {
		this.name = name;
		this.model = model;
	}
	
	public Model getComponent() {
		return model;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPath() {
		return getComponent().getName()+"_"+getName();
	}
	
	public void setNet(Net n) {
		net = n;
	}
	
	public Net getNet() {
		return net;
	}

}

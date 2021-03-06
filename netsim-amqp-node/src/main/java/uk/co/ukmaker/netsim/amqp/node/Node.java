package uk.co.ukmaker.netsim.amqp.node;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.stereotype.Component;

import uk.co.ukmaker.netsim.models.Model;
import uk.co.ukmaker.netsim.netlist.Net;
import uk.co.ukmaker.netsim.netlist.Netlist;
import uk.co.ukmaker.netsim.pins.Pin;
import uk.co.ukmaker.netsim.simulation.LocalNetlistDriver;

@Component
public class Node {

	private Netlist netlist;
	
	private LocalNetlistDriver driver = new LocalNetlistDriver();
	
	//@Value("${node.name}")
	private String name;
	private long ramSize;
	
	public void initialise() throws UnknownHostException {

		ramSize =  Runtime.getRuntime().totalMemory();
		
		netlist = new Netlist();
		driver.setNetlist(netlist);
		
		if(name == null) {
			name = "node-"+InetAddress.getLocalHost().getHostName();
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getRamSize() {
		return ramSize;
	}
	
	public void clear() throws Exception {
		initialise();
	}
	
	public void reset() throws Exception {
		initialise();
	}
	
	public Netlist getNetlist() {
		return netlist;
	}

	public void addModel(Model model) {
		netlist.addModel(model);
	}
	
	public void connectPin(Model model, String netId, String pinName) {
		Net n = getNet(netId);
		Pin p = model.getPin(pinName);
		n.addPin(p);
	}

	public Net getNet(String netId) {
		if(netlist.hasNet(netId)) {
			return netlist.getNet(netId);
		}
		
		Net net = new Net(netId);
		netlist.addNet(net);
		
		return net;
	}
	
	public LocalNetlistDriver getNetlistDriver() {
		return driver;
	}
}

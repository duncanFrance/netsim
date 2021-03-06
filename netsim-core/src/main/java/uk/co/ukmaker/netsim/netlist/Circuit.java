package uk.co.ukmaker.netsim.netlist;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A Circuit is a user-defined Component built from a netlist of Components
 * 
 * @author duncan
 *
 */
public class Circuit implements Component {
	
	private Component parentComponent;
	private String name;
	private Map<String, Terminal> terminals = new HashMap<String, Terminal>();
	private Map<String, Component> components = new HashMap<String, Component>();
	
	public Circuit(String name) {
		this(null, name);
	}

	public Circuit(Component parentComponent, String name) {
		this.parentComponent = parentComponent;
		this.name = name;
	}
	
	public void setParentComponent(Component parent) {
		parentComponent = parent;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getPath() {
		if(parentComponent == null) {
			return "/"+name;
		}
		
		return parentComponent.getPath()+"/"+name;
	}
	
	@Override
	public Collection<Terminal> getTerminals() {
		return terminals.values();
	}
	
	@Override
	public Terminal getTerminal(String name) throws Exception {
		if(!terminals.containsKey(name)) {
			throw new Exception("Component "+getPath()+" has no terminal named "+name);
		}
		return terminals.get(name);
	}
	
	public void addTerminal(Terminal t) {
		terminals.put(t.getName(), t);
	}
	
	public Collection<Component> getComponents() {
		return components.values();
	}
	
	public void addComponent(Component c) throws Exception {
		if(components.containsKey(c.getName())) {
			throw new Exception("Component "+getName()+" already contains a sub-component "+c.getName());
		}
		components.put(c.getName(), c);
		c.setParentComponent(this);
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(getPath());
		sb.append("\n");
		for(Component c : getComponents()) {
			sb.append("  ");
			sb.append(c.getName());
			sb.append("\n");
		}
		
		return sb.toString();
	}
}

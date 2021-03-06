package uk.co.ukmaker.netsim.amqp.messages.node;

import java.util.Map;

import uk.co.ukmaker.netsim.amqp.messages.NetsimMessage;

public class InitialiseModelsMessage implements NetsimMessage {
	
	public static final String TYPE = "INIT";
	
	private String message;
	
	public InitialiseModelsMessage() {
		
	}
	
	public String getMessage() {
		return message;
	}

	@Override
	public void populateHeaders(Map<String, Object> headers) {
		headers.put(TYPE_HEADER, TYPE);
	}
}

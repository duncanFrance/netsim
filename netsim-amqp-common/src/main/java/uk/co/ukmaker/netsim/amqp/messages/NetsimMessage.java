package uk.co.ukmaker.netsim.amqp.messages;

import java.util.Map;

public interface NetsimMessage {
	
	public static final String TYPE_HEADER = "NS_T";
	public static final String MOMENT_HEADER = "NS_M";
	
	public void populateHeaders(Map<String, Object> headers);

}

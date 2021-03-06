package com.oc.AlexaBaumarkt;

import java.util.HashSet;
import java.util.Set;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

public class ToolSearchHandler extends SpeechletRequestStreamHandler {

	private static final Set<String> supportedApplicationIds;
	static {
		/*
		 * This Id can be found on https://developer.amazon.com/edw/home.html#/ "Edit"
		 * the relevant Alexa Skill and put the relevant Application Ids in this Set.
		 */
		supportedApplicationIds = new HashSet<String>();
		// supportedApplicationIds.add("[unique-value-here]");
	}

	public ToolSearchHandler() {
		super(new ToolSearchSpeechlet(), supportedApplicationIds);
	}

}

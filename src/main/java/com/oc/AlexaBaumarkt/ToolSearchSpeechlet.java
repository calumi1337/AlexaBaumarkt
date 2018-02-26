package com.oc.AlexaBaumarkt;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.SpeechletV2;
import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;

public class ToolSearchSpeechlet implements SpeechletV2 {

	@Override
	public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope) {
		// TODO Auto-generated method stub

	}

	@Override
	public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> requestEnvelope) {
		String speechText = "Willkommen im Baumarkt";
		SimpleCard card = createSimpleCard("Welcome", speechText);
		PlainTextOutputSpeech speech = createPlainTextOutputSpeech(speechText);
		Reprompt reprompt = createReprompt(speech);

		return SpeechletResponse.newAskResponse(speech, reprompt, card);
	}

	@Override
	public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {

		IntentRequest request = requestEnvelope.getRequest();

		Intent intent = request.getIntent();
		String intentName = (intent != null) ? intent.getName() : null;

		if ("ToolSearchIntent".equals(intentName)) {
			Slot toolSlot = intent.getSlot("tool");
			switch (toolSlot.getValue()) {
			case "hammer":
				return createResponse("success", "Hämmer findest du in Gang 5");
			case "schraubenzieher": return createResponse("success", toolSlot.getValue() + "findest du in Gang 13");
			case "bohrer": return createResponse("success", toolSlot.getValue() + "findest du in Gang 55");
			default:
				return createResponse("not_found",
						"Ich weiß leider nicht wo " + toolSlot.getValue() + " zu finden sind");
			}
		} else {
			return createResponse("unsupported", "Das kann ich leider nicht. Bitte versuche etwas anderes.");
		}

	}

	@Override
	public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> requestEnvelope) {
		// TODO Auto-generated method stub

	}

	private SpeechletResponse createResponse(String title, String content) {
		SimpleCard card = createSimpleCard(title, content);
		PlainTextOutputSpeech speech = createPlainTextOutputSpeech(content);
		return SpeechletResponse.newTellResponse(speech, card);
	}

	private SimpleCard createSimpleCard(String title, String content) {
		SimpleCard card = new SimpleCard();
		card.setTitle(title);
		card.setContent(content);
		return card;
	}

	private PlainTextOutputSpeech createPlainTextOutputSpeech(String speechText) {
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);
		return speech;
	}

	private Reprompt createReprompt(OutputSpeech outputSpeech) {
		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(outputSpeech);

		return reprompt;
	}

}

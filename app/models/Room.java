package models;

import java.util.Date;
import java.util.List;

import play.libs.F.ArchivedEventStream;
import play.libs.F.EventStream;

public class Room {

	final ArchivedEventStream<Message> messages = new ArchivedEventStream<Message>(
			100);

	public EventStream<Message> join() {
		return messages.eventStream();
	}
	
	public void says(String user, String text) {
		messages.publish(new Message(user, text));
	}

	public class Message {
		public String user;
		public String text;

		public Message(String user, String text) {
			this.user = user;
			this.text = text;
		}
	}

	// Factory
	static Room instance = new Room();

	public static Room get() {
		if (instance == null) {
			return new Room();
		}
		return instance;
	}
}

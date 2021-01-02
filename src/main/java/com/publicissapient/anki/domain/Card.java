package com.publicissapient.anki.domain;

import java.util.Objects;

public class Card {
	
	private String question;
	private String answer;
	
	public Card(){

	}
	public Card(String question, String answer){
		this.question = question;
		this.answer= answer;
	}


	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}


	public String getAnswer() {
		return answer;
	}


	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Card card = (Card) o;
		return question.equals(card.question) &&
				answer.equals(card.answer);
	}

	@Override
	public int hashCode() {
		return Objects.hash(question, answer);
	}
}

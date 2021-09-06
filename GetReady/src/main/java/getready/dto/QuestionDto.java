package getready.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class QuestionDto {
	
	@NotBlank
	private String question;
	
	@NotBlank
	private String answer;
	
	@NotEmpty
	private Set<String> labels;
	
	private String info;
	

	public QuestionDto() { }

	public QuestionDto(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}
	
	public Set<String> getLabels() {
		return labels;
	}
	
	public void setLabels(Set<String> labels) {
		this.labels = labels;
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

	public String getInfo() {
		if(this.info == null) 
			return "";
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	

}

package getready.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class QuestionDto {
	
	@NotBlank
	private String question;
	
	@NotBlank
	private String answer;
	
	@NotEmpty
	@JsonIgnore
	private List<String> labels;
	

	public QuestionDto() { }

	public QuestionDto(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}
	
	public List<String> getLabels() {
		return labels;
	}
	
	public void setLabels(List<String> labels) {
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

}

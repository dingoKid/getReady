package getready.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String question;
	
	@Column(columnDefinition = "TEXT")
	private String answer;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Label> labels;
	
	public Question() {}
	
	public Question(String question, String answer, List<Label> labels) {
		super();
		this.question = question;
		this.answer = answer;
		this.labels = labels;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<Label> getLabels() {
		if(labels == null)
			labels = new ArrayList<>();
		return labels;
	}

	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}
	
}

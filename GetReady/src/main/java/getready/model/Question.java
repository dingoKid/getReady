package getready.model;

import java.util.HashSet;
import java.util.Set;

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
	private Set<Label> labels;
	
	@Column(columnDefinition = "TEXT")
	private String info;
	
	public Question() {}
	
	public Question(String question, String answer, Set<Label> labels) {
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

	public Set<Label> getLabels() {
		if(labels == null)
			labels = new HashSet<>();
		return labels;
	}

	public void setLabels(Set<Label> labels) {
		this.labels = labels;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
}

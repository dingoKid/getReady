package getready.service;

import org.springframework.data.jpa.domain.Specification;

import getready.model.Label;
import getready.model.Question;
import getready.model.Question_;

public class QuestionSpecifications {
	
	public static Specification<Question> withLabel(Label label) {
		return (root, cq, cb) -> cb.isMember(label, root.get(Question_.labels));
	}
	

}

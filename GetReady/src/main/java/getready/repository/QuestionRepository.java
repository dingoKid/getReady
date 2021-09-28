package getready.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import getready.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>, JpaSpecificationExecutor<Question> {
	
	@Query("select count(1) from Question")
	int getNumberOfQuestions();

	List<Question> findByQuestionContainingIgnoreCase(String word);
	
	Optional<Question> findByQuestion(String questionString);
	

}

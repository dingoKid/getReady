package getready.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import getready.model.Label;
import getready.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	
	@Query("select id from Question")
	List<Long> getIds();

//	@Query("select q from Question q where q.labels")
//	List<Long> getIdsWithLabel(String label);
	
	List<Question> findByLabels(Label label);
	

}

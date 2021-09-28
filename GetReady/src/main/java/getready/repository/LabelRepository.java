package getready.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import getready.model.Label;

public interface LabelRepository extends JpaRepository<Label, Long> {
	
	Optional<Label> findByName(String name);
	
	@Query(value = "select distinct labels_id from question_labels", nativeQuery = true)
	List<Long> getUsedLabelsIds();
	
}

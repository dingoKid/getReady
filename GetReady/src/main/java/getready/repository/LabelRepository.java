package getready.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import getready.model.Label;

public interface LabelRepository extends JpaRepository<Label, Long> {
	
	Optional<Label> findByName(String name);

}

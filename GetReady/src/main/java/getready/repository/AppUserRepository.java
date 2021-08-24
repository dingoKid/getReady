package getready.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import getready.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

	Optional<AppUser> findByUsername(String username);

}

package kz.kbtu.tsis5v2.repository;

import kz.kbtu.tsis5v2.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,String> {
}

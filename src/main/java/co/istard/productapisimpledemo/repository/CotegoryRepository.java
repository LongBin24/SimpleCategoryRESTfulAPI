package co.istard.productapisimpledemo.repository;

import co.istard.productapisimpledemo.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CotegoryRepository extends JpaRepository <Category,Integer> {
    Page<Category> findAllByIsDeletedFalse(Pageable pageable);

    Optional<Category> findByIdAndIsDeletedFalse(Integer id);
}

package btb.mp3.bestofthebet.repository;

import btb.mp3.bestofthebet.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}

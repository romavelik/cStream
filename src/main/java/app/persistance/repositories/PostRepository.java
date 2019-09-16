package app.persistance.repositories;

import app.persistance.entity.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    public List<Post> findByTag(String tag);
}

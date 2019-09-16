package app.service;

import app.persistance.entity.Post;
import app.persistance.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PostService {
  @Autowired
  private PostRepository postRepository;

  public void doPost(Post post){
    postRepository.save(post);
  }

  public Iterable<Post> post(){
    return postRepository.findAll();
  }
}

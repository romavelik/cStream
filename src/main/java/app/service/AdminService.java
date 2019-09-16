package app.service;

import app.persistance.entity.Post;
import app.persistance.entity.Role;
import app.persistance.entity.User;
import app.persistance.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdminService {
  @Autowired
  private PostRepository postRepository;

  public User setUserRoles(User user, Map<String, Object> form)
  {
    Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
    user.getRoles().clear();
    for (String key : form.keySet())
    {
      if (roles.contains(key))
      {
        user.getRoles().add(Role.valueOf(key));
      }
    }
    return user;
  }
  public void deleteMessage(Post post) {
    postRepository.delete(post);
  }
}

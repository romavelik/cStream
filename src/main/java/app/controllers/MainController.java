package app.controllers;

import app.persistance.entity.Post;
import app.persistance.entity.User;
import app.persistance.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {

    @Autowired
    private PostRepository postRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/main")
    public String main (@RequestParam(required = false, defaultValue = "") String filter, Model model){
        Iterable<Post> posts = postRepository.findAll();
        if(filter != null && !filter.isEmpty()) {
            posts = postRepository.findByTag(filter);
        }
        else {
            posts = postRepository.findAll();
        }
        model.addAttribute("posts", posts);
        model.addAttribute("filter", filter);
        return "main";
    }
    @PostMapping("/main")
    public String addPost(@AuthenticationPrincipal User user, @RequestParam String text, @RequestParam String tag, @RequestParam("file") MultipartFile file, Map<String, Object> model) throws IOException {
        Post post = new Post();
        if(file!=null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
            post.setFilename(resultFileName);
        }
        post.setText(text);
        post.setTag(tag);
        post.setAuthor(user);
        postRepository.save(post);
        Iterable<Post> posts = postRepository.findAll();

        model.put("posts", posts);
        return "redirect:/main";
    }

}
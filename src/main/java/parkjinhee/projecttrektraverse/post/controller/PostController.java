package parkjinhee.projecttrektraverse.post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping({"/post"})
public class PostController {

    @GetMapping
    public String post() {

        return "post/createdPost.html";
    }

}

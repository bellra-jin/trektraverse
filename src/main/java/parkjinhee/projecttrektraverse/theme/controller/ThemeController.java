package parkjinhee.projecttrektraverse.theme.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import parkjinhee.projecttrektraverse.post.entity.Post;
import parkjinhee.projecttrektraverse.post.service.PostService;
import parkjinhee.projecttrektraverse.theme.entity.Theme;
import parkjinhee.projecttrektraverse.theme.entity.ThemePostDto;
import parkjinhee.projecttrektraverse.theme.mapper.ThemeMapper;
import parkjinhee.projecttrektraverse.theme.service.ThemeService;

import java.util.List;



@Controller
@RequestMapping({"/themes"})
public class ThemeController {
    private final ThemeService themeService;
    private final PostService postService;
    private final ThemeMapper themeMapper;

    public ThemeController(ThemeService themeService, PostService postService, ThemeMapper themeMapper) {
        this.themeService = themeService;
        this.postService = postService;
        this.themeMapper = themeMapper;
    }

    @GetMapping
    public String getThemes(Model model) {
        List<Theme> themes = this.themeService.findThemes();
        model.addAttribute("themes", themes);
        return "theme/themes";
    }

    @GetMapping({"/{themeId}"})
    public String getTheme(@PathVariable("themeId") Long themeId, @RequestParam(value = "page",defaultValue = "0") int page, @RequestParam(value = "size",defaultValue = "10") int size, @RequestParam(value="keyword",required = false) String keyword, Model model) {
        Theme theme = this.themeService.findThemeById(themeId);
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Post> postPage = this.postService.findPostsByThemeAndKeyword(theme, keyword, pageRequest);
        model.addAttribute("theme", theme);
        model.addAttribute("keyword", keyword);
        model.addAttribute("postPage", postPage);
        return "theme/theme";
    }

    @GetMapping({"/create"})
    public String createTheme(Model model) {
        return "theme/createTheme";
    }

    @PostMapping({"/create"})
    public String createThemePost(@ModelAttribute ThemePostDto themePostDto,Model model) {
        Theme theme = this.themeMapper.themePostDtoToTheme(themePostDto);
        this.themeService.createTheme(theme);
        List<Theme> themes = this.themeService.findThemes();
        model.addAttribute("themes", themes);
        return "theme/themes";
    }

    @GetMapping({"/{themeId}/edit"})
    public String editTheme(@PathVariable("themeId") Long themeId, Model model) {
        Theme theme = this.themeService.findThemeById(themeId);
        model.addAttribute("theme", theme);
        return "theme/editTheme";
    }

    @PostMapping({"/{themeId}/edit"})
    public String editThemePost(@PathVariable("themeId") Long themeId, @ModelAttribute ThemePostDto themePostDto) {
        Theme theme = this.themeMapper.themePostDtoToTheme(themePostDto).toBuilder().id(themeId).build();
        this.themeService.updateTheme(theme);
        return "redirect:/themes";
    }

    @DeleteMapping({"/{themeId}/delete"})
    public String deleteTheme(@PathVariable("themeId") Long themeId) {
        this.themeService.deleteTheme(themeId);
        return "redirect:/themes";
    }
}





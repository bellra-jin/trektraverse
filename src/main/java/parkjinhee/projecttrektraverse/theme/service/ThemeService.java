package parkjinhee.projecttrektraverse.theme.service;

import org.springframework.stereotype.Service;
import parkjinhee.projecttrektraverse.global.exception.ExceptionCode;
import parkjinhee.projecttrektraverse.global.exception.ServiceLogicException;
import parkjinhee.projecttrektraverse.theme.entity.Theme;
import parkjinhee.projecttrektraverse.theme.repository.ThemeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {
    private final ThemeRepository themeRepository;

    private Theme foundTheme;
    public ThemeService(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }
    public List<Theme> findThemes() {
        return this.themeRepository.findAll();
    }

    public Theme findThemeById(Long id) {
        return (Theme)this.themeRepository.findById(id).orElseThrow(() -> {
            return new ServiceLogicException(ExceptionCode.THEME_NOT_FOUND);
        });
    }

    public Theme createTheme(Theme theme) {
        return this.themeRepository.create(theme);
    }

    public Theme updateTheme(Theme theme) {
        this.foundTheme = (Theme)this.themeRepository.findById(theme.getId()).orElseThrow(() -> {
            return new ServiceLogicException(ExceptionCode.THEME_NOT_FOUND);
        });
        Optional.ofNullable(theme.getThemeTitle()).ifPresent((themeTitle) -> {
            this.foundTheme = this.foundTheme.builder().themeTitle(themeTitle).build();
        });
        this.foundTheme = this.foundTheme.builder().themePw(theme.getThemePw()).build();
        return this.themeRepository.update(this.foundTheme);
    }

    public void deleteTheme(Long id) {
        this.foundTheme = (Theme)this.themeRepository.findById(id).orElseThrow(() -> {
            return new ServiceLogicException(ExceptionCode.THEME_NOT_FOUND);
        });
        this.themeRepository.delete(this.foundTheme);
    }


}

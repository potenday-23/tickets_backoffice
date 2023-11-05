package project.backend.domain.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.backend.domain.category.mapper.CategoryMapper;
import project.backend.domain.category.service.CategoryService;

@RestController
@RequestMapping("/api/categorys")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    @GetMapping
    public ResponseEntity getCategoryList() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryMapper.categorysToCategoryResponseDtos(categoryService.getCategoryList()));
    }
}

package project.backend.domain.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.backend.domain.category.dto.CategoryPatchRequestDto;
import project.backend.domain.category.dto.CategoryPostRequestDto;
import project.backend.domain.category.dto.CategoryResponseDto;
import project.backend.domain.category.entity.Category;
import project.backend.domain.category.mapper.CategoryMapper;
import project.backend.domain.category.repository.CategoryRepository;
import project.backend.global.error.exception.BusinessException;
import project.backend.global.error.exception.ErrorCode;
import org.imgscalr.Scalr;
import project.backend.global.redis.RedisService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final RedisService redisService;
    private final CategoryMapper categoryMapper;

    public List<CategoryResponseDto> getCategoryList() {
        return categoryMapper.categorysToCategoryResponseDtos(
                categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "num", "id")));
    }

    public Category createCategory(CategoryPostRequestDto categoryPostRequestDto) {
        Category category = Category.builder()
                .name(categoryPostRequestDto.getName())
                .engName(categoryPostRequestDto.getEngName())
                .num(categoryPostRequestDto.getNum())
                .basicImage(categoryPostRequestDto.getBasicImage())
                .clickImage(categoryPostRequestDto.getClickImage()).build();
        categoryRepository.save(category);

        List<CategoryResponseDto> categoryResponseDtoListList = categoryMapper.categorysToCategoryResponseDtos(
                categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "num", "id")));

        redisService.putListToRedis("Category::list", categoryResponseDtoListList);
        return category;
    }

    public Category updateCategory(CategoryPatchRequestDto categoryPatchRequestDto, String name) {
        Category category = verifiedCategory(name);
        category.patchCategory(categoryPatchRequestDto);
        categoryRepository.save(category);

        List<CategoryResponseDto> categoryResponseDtoListList = categoryMapper.categorysToCategoryResponseDtos(
                categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "num", "id")));

        redisService.putListToRedis("Category::list", categoryResponseDtoListList);
        return category;
    }

    public Category verifiedCategory(String categoryName) {
        return categoryRepository.findFirstByName(categoryName).orElseThrow(() -> new BusinessException(ErrorCode.CATEGORY_NOT_FOUND));
    }

    public void deleteCategory(String name) {
        categoryRepository.delete(verifiedCategory(name));
        List<CategoryResponseDto> categoryResponseDtoListList = categoryMapper.categorysToCategoryResponseDtos(
                categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "num", "id")));

        redisService.putListToRedis("Category::list", categoryResponseDtoListList);
    }
}

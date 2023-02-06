package cn.violin.onenote.controller;

import cn.violin.common.annotation.CurrentUser;
import cn.violin.common.entity.Tenant;
import cn.violin.onenote.entity.Category;
import cn.violin.onenote.service.CategoryService;
import cn.violin.onenote.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin
@RequestMapping("/api/v1")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public ResponseEntity<List<CategoryVo>> getCategory(@CurrentUser Tenant tenant) {
        List<Category> categories = categoryService.selectCategory(tenant);
        List<CategoryVo> vos = categories.stream()
            .map(category -> CategoryVo.builder().categoryId(category.getCategoryId())
                .categoryIcon(category.getCategoryIcon()).categoryColor(category.getCategoryColor())
                .categoryTheme(category.getCategoryTheme()).build())
            .collect(Collectors.toList());
        return new ResponseEntity<>(vos, HttpStatus.OK);
    }

    @PutMapping("/category")
    public ResponseEntity<Void> putCategory(@Valid @RequestBody CategoryVo categoryVo, @CurrentUser Tenant tenant) {
        categoryService.insertCategory(categoryVo, tenant);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

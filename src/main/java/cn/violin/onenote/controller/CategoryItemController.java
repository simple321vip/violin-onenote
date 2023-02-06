package cn.violin.onenote.controller;

import cn.violin.common.annotation.CurrentUser;
import cn.violin.common.entity.Tenant;
import cn.violin.onenote.entity.Item;
import cn.violin.onenote.service.CategoryItemService;
import cn.violin.onenote.vo.ItemVo;
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
public class CategoryItemController {

    @Autowired
    private CategoryItemService categoryItemService;

    public CategoryItemController(CategoryItemService categoryItemService) {
        this.categoryItemService = categoryItemService;
    }

    @GetMapping("/{category_id}/items/")
    public ResponseEntity<List<ItemVo>> getCategoryItems(@PathVariable("category_id") long categoryId,
        @CurrentUser Tenant tenant) {
        List<Item> categoryItems = categoryItemService.selectCategoryItems(categoryId);
        List<ItemVo> vos =
            categoryItems.stream()
                .map(item -> ItemVo.builder().itemId(item.getItemId()).itemName(item.getItemName())
                    .itemValue(item.getItemValue()).categoryId(item.getCategoryId()).build())
                .collect(Collectors.toList());
        return new ResponseEntity<>(vos, HttpStatus.OK);
    }

    @PutMapping("/item")
    public ResponseEntity<Void> putCategoryItem(@Valid @RequestBody ItemVo itemVo) {
        categoryItemService.insertCategoryItem(itemVo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/item")
    public ResponseEntity<Void> postCategoryItem(@Valid @RequestBody ItemVo itemVo) {
        categoryItemService.updateCategoryItem(itemVo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/item")
    public ResponseEntity<Void> deleteCategoryItem(@Valid @RequestBody ItemVo itemVo) {
        categoryItemService.deleteCategoryItem(itemVo);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

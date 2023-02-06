package cn.violin.onenote.service;

import cn.violin.common.entity.Tenant;
import cn.violin.onenote.dao.CategoryRepo;
import cn.violin.onenote.entity.Category;
import cn.violin.onenote.vo.CategoryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public List<Category> selectCategory(Tenant tenant) {
        return categoryRepo.findByTenantId(tenant.getTenantId());
    }

    @Transactional
    public void insertCategory(CategoryVo categoryVo, Tenant tenant) {
        Category category = new Category();
        category.setCategoryColor(categoryVo.getCategoryColor());
        category.setCategoryIcon(categoryVo.getCategoryIcon());
        category.setCategoryTheme(categoryVo.getCategoryTheme());
        category.setTenantId(tenant.getTenantId());
        categoryRepo.save(category);
    }

    @Transactional
    public void deleteCategory(CategoryVo categoryVo) {
        categoryRepo.deleteById(categoryVo.getCategoryId());
    }
}

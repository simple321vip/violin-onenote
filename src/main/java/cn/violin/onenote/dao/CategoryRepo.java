package cn.violin.onenote.dao;

import cn.violin.onenote.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    List<Category> findByTenantId(String tenantId);
}

package cn.violin.onenote.dao;

import cn.violin.onenote.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepo extends JpaRepository<Page, Long> {
}

package cn.violin.onenote.dao;

import cn.violin.onenote.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepo extends JpaRepository<Section, Long> {
}

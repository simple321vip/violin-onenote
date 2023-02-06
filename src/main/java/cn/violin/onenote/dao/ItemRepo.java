package cn.violin.onenote.dao;

import cn.violin.onenote.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {

    List<Item> findByCategoryId(long categoryId);
}

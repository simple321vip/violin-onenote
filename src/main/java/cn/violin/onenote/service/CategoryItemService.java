package cn.violin.onenote.service;

import cn.violin.onenote.dao.ItemRepo;
import cn.violin.onenote.entity.Item;
import cn.violin.onenote.vo.ItemVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryItemService {

    @Autowired
    private ItemRepo itemRepo;

    public List<Item> selectCategoryItems(long categoryId) {
        return itemRepo.findByCategoryId(categoryId);
    }

    @Transactional
    public void insertCategoryItem(ItemVo itemVo) {
        Item item = new Item();
        item.setItemName(itemVo.getItemName());
        item.setCategoryId(itemVo.getCategoryId());
        item.setItemValue(itemVo.getItemValue());
        itemRepo.save(item);
    }

    @Transactional
    public void updateCategoryItem(ItemVo itemVo) {
        Item item = new Item();
        item.setItemName(itemVo.getItemName());
        item.setCategoryId(itemVo.getCategoryId());
        item.setItemValue(itemVo.getItemValue());
        itemRepo.save(item);
    }

    @Transactional
    public void deleteCategoryItem(ItemVo itemVo) {
        Item item = new Item();
        item.setItemName(itemVo.getItemName());
        item.setCategoryId(itemVo.getCategoryId());
        item.setItemValue(itemVo.getItemValue());
        itemRepo.save(item);
    }
}

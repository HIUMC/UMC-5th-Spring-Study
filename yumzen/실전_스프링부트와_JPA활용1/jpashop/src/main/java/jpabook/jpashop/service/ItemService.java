package jpabook.jpashop.service;

import jpabook.jpashop.domain.Item;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional //readOnly == false
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional //트랜잭션 commit -> jpa가 commit 날림 -> 자동으로 변경 감지
    public void updateItem(Long itemId, String name, int price, int stockQuantity){
        Item findItem = itemRepository.findOne(itemId);
        findItem.setPrice(price); //데이터수정
        findItem.setName(name); //데이터수정
        findItem.setStockQuantity(stockQuantity); //데이터수정
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public  Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}

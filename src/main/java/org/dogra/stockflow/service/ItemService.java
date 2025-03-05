package org.dogra.stockflow.service;

import org.dogra.stockflow.model.Item;
import org.dogra.stockflow.repo.ItemRepo;
import org.dogra.stockflow.repo.PartyRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final Logger logger = LoggerFactory.getLogger(ItemService.class);

    private ItemRepo itemRepo;
    private PartyRepo providerPartyRepo;

    ItemService(ItemRepo itemRepo, PartyRepo partyRepo){
        this.itemRepo = itemRepo;
        this.providerPartyRepo = partyRepo;
    }

    public Item add(Item item, List<Long> providers){

        item.getProviders()
                .addAll(providerPartyRepo.findAllById(providers));

        Item addedItem = itemRepo.save(item);

        logger.debug("Item added : {}", addedItem);

        return addedItem;
    }

    public void removeItem(List<Long> ids){
        itemRepo.deleteAllById(ids);
    }

    public Page<Item> getItems(int pagesize, int pageNumber, String sortBy, String order){

        Sort sortOrder;

        if(order.equalsIgnoreCase("asc"))
            sortOrder = Sort.by(sortBy).ascending();
        else{
            sortOrder = Sort.by(sortBy).descending();
        }

        Pageable pageable = PageRequest.of(pagesize, pageNumber, sortOrder);

        return itemRepo.findAll(pageable);
    }

}

package org.dogra.stockflow.repo;


import org.dogra.stockflow.model.Item;
import org.dogra.stockflow.model.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {

    @Query("SELECT i.providers FROM Item i WHERE " +
            "i.id = :id")
    List<Party> findItemProviders(Long id);

    @Query("SELECT i FROM Item i WHERE " +
            "LOWER(i.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Item> searchItem(String keyword);


    @Query("SELECT i FROM Item i WHERE " +
            "i.baseUnitPrice >= :from AND " +
            "i.baseUnitPrice <= :to")
    List<Item> getItemWithPriceLimit(Long from, Long to);

    @Query("SELECT i FROM Item i WHERE " +
            "LOWER(i.name) LIKE LOWER(CONCAT('%', :keyword, '%')) AND " +
            "i.baseUnitPrice >= :from AND " +
            "i.baseUnitPrice <= :to")
    List<Item> searchItemWithPriceLimit(String keyword, Long from, Long to);


}

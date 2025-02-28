package org.dogra.stockflow.repo;

import jakarta.transaction.Transactional;
import org.dogra.stockflow.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepo extends JpaRepository<Stock, Long> {

    @Query("SELECT s FROM Stock s WHERE " +
            "LOWER(s.item.name) = LOWER(CONCAT('%', :keyword,'%')) OR " +
            "LOWER(s.provider.company) = LOWER(CONCAT('%', :keyword,'%')) AND " +
            "s.currentStock > 0")
    List<Stock> SearchItemInStock(String keyword);

    @Query("SELECT s FROM Stock s WHERE " +
            "s.currentStock < s.minimumStock")
    List<Stock> getItemWithLowStock();

    @Query("SELECT s FROM Stock s WHERE " +
            "LOWER(s.provider.company) = LOWER(CONCAT('%', :keyword,'%'))")
    List<Stock> getCompanySpecificStock(String keyword);

    @Query("SELECT s FROM Stock s WHERE " +
            "LOWER(s.item.name) = LOWER(CONCAT('%', :keyword,'%')) OR " +
            "LOWER(s.provider.company) = LOWER(CONCAT('%', :keyword,'%')) OR " +
            "CAST(id as string) = CONCAT('%', :keyword, '%')")
    List<Stock> SearchMatch(String keyword);

    @Transactional
    @Query("DELETE FROM Stock s WHERE s.id = :id")
    void deleteStock(Long id);

    @Transactional
    @Query("DELETE FROM Stock s WHERE s.id IN :ids")
    void deleteMUltiStock(List<Long> ids);


    @Query("SELECT s FROM Stock s ORDER BY s.lastUpdated DESC LIMIT :limit OFFSET :offset")
    List<Stock> findRecentUpdatedStock(Long limit, Long offset);

}

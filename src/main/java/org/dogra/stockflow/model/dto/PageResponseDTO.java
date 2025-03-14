package org.dogra.stockflow.model.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResponseDTO<T> implements Serializable {
    private final List<T> content;
    private final int pageNUmber;
    private final int pageSize;
    private final int totalElements;
    private final int totalPages;
    private final boolean empty;
    private final boolean last;
    private final boolean sorted;

    public PageResponseDTO(Page<T> pagedResult){
        this.content = pagedResult.getContent();
        this.pageNUmber = pagedResult.getNumber();
        this.pageSize = pagedResult.getSize();
        this.totalElements = pagedResult.getNumberOfElements();
        this.totalPages = pagedResult.getTotalPages();
        this.empty = pagedResult.isEmpty();
        this.last = pagedResult.isLast();
        this.sorted = pagedResult.getSort().isSorted();

    }

    public static <E> PageResponseDTO<E> of(Page<E> pagedResult){
        return new PageResponseDTO<E>(pagedResult);
    }

}

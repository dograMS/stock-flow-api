package org.dogra.stockflow.service;

import org.dogra.stockflow.model.Log;
import org.dogra.stockflow.repo.LogRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private LogRepo logRepo;

    public LogService(LogRepo logRepo){
        this.logRepo = logRepo;
    }

    public Log add(Log newLog){
        return logRepo.save(newLog);
    }

    public Page<Log> getLogs(int pageSize, int pageNumber, String sortBy){

        pageNumber = Math.max(pageNumber, 1);
        pageSize = (pageSize < 1)? 10 : pageSize;

        Sort sort = Sort.by(sortBy).descending();
        Pageable page = PageRequest.of(pageNumber, pageSize, sort);

        return logRepo.findAll(page);
    }

}

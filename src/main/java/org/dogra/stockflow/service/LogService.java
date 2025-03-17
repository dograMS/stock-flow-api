package org.dogra.stockflow.service;

import org.dogra.stockflow.model.Log;
import org.dogra.stockflow.model.dto.LogDTO;
import org.dogra.stockflow.model.dto.PageResponseDTO;
import org.dogra.stockflow.repo.LogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class LogService {

    private final LogRepo logRepo;

    @Autowired
    public LogService(LogRepo logRepo) {
        this.logRepo = logRepo;
    }

    public Log add(Log newLog) {
        return logRepo.save(newLog);
    }


    public void deleteLog(Long id) throws Exception {
        AtomicBoolean notFound = new AtomicBoolean(false);
        logRepo.findById(id).ifPresentOrElse(logRepo::delete,
                () -> notFound.set(true));

        if (notFound.get()) {
            throw new Exception("Cannot Delete: No log found with ID: " + id);
        }
    }


    public PageResponseDTO<?> getLogs(int pageSize, int pageNumber) {

        pageNumber = Math.max(pageNumber, 0);
        pageSize = (pageSize < 1) ? 10 : pageSize;

        Sort sort = Sort.by("logDate").descending();
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        Page<Log> pageRes = logRepo.findAll(pageRequest);

        List<LogDTO> result = pageRes.getContent()
                .stream()
                .map(LogDTO::new)
                .toList();

        return new PageResponseDTO<>(result, pageRes);
    }

    public LogDTO findLongById(Long id) throws Exception {
        Log log = logRepo.findById(id).orElseThrow(() -> new Exception("cannot find log with Id : " + id));
        return new LogDTO(log);
    }
}

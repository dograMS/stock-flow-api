package org.dogra.stockflow.service;

import org.dogra.stockflow.model.Party;
import org.dogra.stockflow.repo.PartyRepo;
import org.dogra.stockflow.utils.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class PartyService {

    private PartyRepo partyRepo;

    public PartyService(PartyRepo partyRepo){
        this.partyRepo = partyRepo;
    }

    public Party add(Party newParty) throws Exception {
        return partyRepo.save(newParty);
    }

    public Party findParty(Long id) throws UserNotFoundException {
        return partyRepo.findById(id).orElseThrow(
                () -> new UserNotFoundException("Party Not found with given ID")
        );
    }


    public Page<Party> getParties(int pageNumber, int pageSize){

        Sort sortOrder = Sort.by("name").descending();

        Pageable page = PageRequest.of(pageNumber, pageSize, sortOrder);

        return partyRepo.findAll(page);

    }


    public void deletePartyByID(Long id) throws UserNotFoundException {

        AtomicBoolean delete_failed = new AtomicBoolean(false);
        partyRepo.findById(id).ifPresentOrElse(partyRepo::delete,
                () -> delete_failed.set(true)
        );

        if(delete_failed.get()){
            throw new UserNotFoundException("Can't Delete Party, Party Not Found with given ID");
        }

    }
}

package org.dogra.stockflow.service;

import org.dogra.stockflow.model.Party;
import org.dogra.stockflow.repo.PartyRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PartyService {

    private PartyRepo partyRepo;

    public PartyService(PartyRepo partyRepo){
        this.partyRepo = partyRepo;
    }

    public Party add(Party newParty) throws Exception {
        return partyRepo.save(newParty);
    }


    public Page<Party> getParties(int pageSize, int pageNumber, String sortBy, String order){

        Sort sortOrder = Sort.by("name").descending()
                .and(Sort.by("company").descending());

        Pageable page = PageRequest.of(pageSize, pageNumber, sortOrder);

        return partyRepo.findAll(page);

    }


}

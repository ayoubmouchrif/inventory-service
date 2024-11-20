package ma.xproce.inventoryservice.service;

import lombok.AllArgsConstructor;
import ma.xproce.inventoryservice.dao.entities.Creator;
import ma.xproce.inventoryservice.dao.repositories.CreatorRepository;
import ma.xproce.inventoryservice.dtos.CreatorRequest;
import ma.xproce.inventoryservice.mappers.CreatorMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CreatorServiceImpl implements CreatorService{

    private CreatorRepository creatorRepository;
    private CreatorMapper creatorMapper;
    @Override
    public Creator saveCreator(CreatorRequest creatorRequest) {
        return creatorRepository.save(creatorMapper.fromCreatorRequestToCreator(creatorRequest));
    }
}

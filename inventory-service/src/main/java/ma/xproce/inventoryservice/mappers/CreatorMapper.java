package ma.xproce.inventoryservice.mappers;

import ma.xproce.inventoryservice.dao.entities.Creator;
import ma.xproce.inventoryservice.dtos.CreatorRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CreatorMapper {
    public CreatorRequest fromCreatorToCreatorRequest(Creator creator){
        CreatorRequest creatorRequest = new CreatorRequest();
        BeanUtils.copyProperties(creator, creatorRequest);
        return creatorRequest;
    }

    public Creator fromCreatorRequestToCreator(CreatorRequest creatorRequest) {
        Creator creator = new Creator();
        BeanUtils.copyProperties(creatorRequest, creator);
        return creator;
    }
}

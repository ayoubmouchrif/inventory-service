package ma.xproce.inventoryservice.service;

import ma.xproce.inventoryservice.dao.entities.Creator;
import ma.xproce.inventoryservice.dtos.CreatorRequest;

public interface CreatorService {
    public Creator saveCreator(CreatorRequest creatorRequest);
}

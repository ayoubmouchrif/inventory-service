package ma.xproce.inventoryservice.service;

import ma.xproce.inventoryservice.dao.entities.Video;
import org.springframework.stereotype.Service;

public interface VideoService {
    public Video findVideoById(Long l);

    public Video updateVideo(Video video);
}

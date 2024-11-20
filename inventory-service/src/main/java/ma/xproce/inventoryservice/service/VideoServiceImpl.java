package ma.xproce.inventoryservice.service;

import lombok.AllArgsConstructor;
import ma.xproce.inventoryservice.dao.entities.Video;
import ma.xproce.inventoryservice.dao.repositories.VideoRepository;
import ma.xproce.inventoryservice.mappers.VideoMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VideoServiceImpl implements VideoService{
    private VideoRepository videoRepository;
    private VideoMapper videoMapper;

    @Override
    public Video findVideoById(Long l) {
        return videoRepository.findById(l).orElseThrow(()->new RuntimeException("No video found"));
    }

    @Override
    public Video updateVideo(Video video) {
        return videoRepository.save(video);
    }
}

package ma.xproce.inventoryservice.web;

import lombok.AllArgsConstructor;
import lombok.Setter;
import ma.xproce.inventoryservice.dao.entities.Creator;
import ma.xproce.inventoryservice.dao.entities.Video;
import ma.xproce.inventoryservice.dao.repositories.CreatorRepository;
import ma.xproce.inventoryservice.dao.repositories.VideoRepository;
import ma.xproce.inventoryservice.dtos.CreatorRequest;
import ma.xproce.inventoryservice.dtos.VideoRequest;
import ma.xproce.inventoryservice.mappers.CreatorMapper;
import ma.xproce.inventoryservice.mappers.VideoMapper;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class VideoGraphQlController {
    private CreatorRepository creatorRepository;
    private VideoRepository videoRepository;
    private CreatorMapper creatorMapper = new CreatorMapper();
    private VideoMapper videoMapper = new VideoMapper();
    VideoGraphQlController(CreatorRepository creatorRepository, VideoRepository videoRepository){
        this.creatorRepository = creatorRepository;
        this.videoRepository = videoRepository;
    }
    @QueryMapping
    public List<Video> getAllVideos(){
        return videoRepository.findAll();
    }
    @QueryMapping
    public Creator creatorById(@Argument Long id) {
        return creatorRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Creator %s not found",id)));
    }
    @MutationMapping
    public Creator saveCreator(@Argument CreatorRequest creator){
        return creatorRepository.save(creatorMapper.fromCreatorRequestToCreator(creator));
    }
    @MutationMapping
    public Video saveVideo(@Argument VideoRequest video){
        return videoRepository.save(videoMapper.fromVideoRequestToVideo(video)) ;
    }

}

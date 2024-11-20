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
import ma.xproce.inventoryservice.service.CreatorService;
import ma.xproce.inventoryservice.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Controller
public class VideoGraphQlController {

    @Autowired
    private CreatorRepository creatorRepository;
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private CreatorService creatorService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private CreatorMapper creatorMapper;
    @Autowired
    private VideoMapper videoMapper;
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

    @SubscriptionMapping
    public Flux<Video> notifyVideoChange() {
        return Flux.fromStream(
                Stream.generate(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    CreatorRequest creatorRequest = CreatorRequest.builder().name("x" +
                            new Random().nextInt()).email("x@gmail.com").build();
                    Creator creator = creatorService.saveCreator(creatorRequest);
                    Video video = videoService.findVideoById(1L);
                    video.setCreator(creator);
                    videoService.updateVideo(video);
                    return video;
                }));
    }
}

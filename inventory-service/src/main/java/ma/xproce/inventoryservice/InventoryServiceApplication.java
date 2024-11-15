package ma.xproce.inventoryservice;

import ma.xproce.inventoryservice.dao.entities.Creator;
import ma.xproce.inventoryservice.dao.entities.Video;
import ma.xproce.inventoryservice.dao.repositories.CreatorRepository;
import ma.xproce.inventoryservice.dao.repositories.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CreatorRepository creatorRepository, VideoRepository videoRepository){
        return args -> {
            List<Creator> creators =
                    List.of(Creator.builder().name("ayoub").email("ayoub@gmail.com").build(),
                            Creator.builder().name("ahmed").email("ahmed@gmail.com").build(),
                            Creator.builder().name("omar").email("omar@gmail.com").build());
            creatorRepository.saveAll(creators);
            List<Video> videos = List.of(
                    Video.builder().name("video1").description("description1").creator(creators.get(1)).build(),
                    Video.builder().name("video2").description("description2").creator(creators.get(2)).build());
            videoRepository.saveAll(videos);
        };
    }
}

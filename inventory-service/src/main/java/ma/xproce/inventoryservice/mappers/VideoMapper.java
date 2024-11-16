package ma.xproce.inventoryservice.mappers;

import ma.xproce.inventoryservice.dao.entities.Video;
import ma.xproce.inventoryservice.dtos.VideoRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class VideoMapper {
    public VideoRequest fromVideoToVideoRequest(Video video){
        VideoRequest videoRequest = new VideoRequest();
        BeanUtils.copyProperties(video, videoRequest);
        return videoRequest;
    }

    public Video fromVideoRequestToVideo(VideoRequest videoRequest){
        Video video = new Video();
        BeanUtils.copyProperties(videoRequest, video);
        return video;
    }
}

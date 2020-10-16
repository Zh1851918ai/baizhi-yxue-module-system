package com.baizhi.app;

import com.baizhi.Dto.ViDto;
import com.baizhi.Dto.VideoDto;
import com.baizhi.Dto.VideosDto;
import com.baizhi.Dto.VideossDto;
import com.baizhi.Entity.Video;
import com.baizhi.Service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("app")
public class AppVideoController {
    @Autowired
    private VideoService videoService;

    @GetMapping("queryByReleaseTime")
    public Map<String, Object> queryByReleaseTime() throws Exception{
        /*System.out.println("====queryByReleaseTime====");*/
        List<Video> list = videoService.QueryAll();

        //自己封装结果
        List<VideoDto> videoDTOS = new ArrayList<>();
        for (Video video : list) {
            VideoDto videoDTO = new VideoDto(
                    video.getId(),
                    video.getTitle(),
                    "http://localhost:8081/yingx/upload_file/img/"+video.getCoverUrl(),
                    video.getVideoUrl(),
                    video.getCreateTime(),
                    video.getIntro(),
                    video.getYxLike().getId(),
                    video.getCategory().getName(),
                    /*video.getUser().getHeadShow()*/
                    null
            );
            videoDTOS.add(videoDTO);
        }

        Map<String, Object> result = new HashMap<>();
        if(videoDTOS!=null){
            result.put("data", videoDTOS);
            result.put("message", "查询成功!");
            result.put("status", 100);
        }else{
            result.put("message", "查询失败!");
            result.put("status", 104);
        }
            return result;
    }

    @RequestMapping("queryByLikeVideoName")
    public Map<String, Object> queryByLikeVideoName(String content){

        Map<String, Object> map = new HashMap<>();
            if(content!=null){
                List<Video> videos = videoService.queryBy(content);
                System.out.println("queryByLikeVideoName============="+videos);
                //自己封装结果
                List<VideosDto> videosDto = new ArrayList<>();

                for (Video video : videos) {
                    VideosDto videosDTO = new VideosDto(
                            video.getId(),
                            video.getTitle(),
                            "http://localhost:8081/yingx/upload_file/img/"+video.getCoverUrl(),
                            video.getVideoUrl(),
                            video.getCreateTime(),
                            video.getIntro(),
                            video.getYxLike().getId(),
                            null,
                            /*video.getUser().getHeadShow()*/
                            null,
                            null,
                            video.getUser().getUsername()
                    );
                    videosDto.add(videosDTO);
                }
                map.put("data", videosDto);
                map.put("message", "查询成功!");
                map.put("status", 100);
            }else{
                map.put("message", "查询失败!");
                map.put("status", 104);
            }
        return map;
    }


    @RequestMapping("queryByVideoDetail")
    public Map<String, Object> queryByVideoDetail(String videoId,String cateId,String userId){
        Map<String, Object> result = new HashMap<>();
        try {
            Video video = videoService.queryOne(videoId);
            System.out.println("queryByVideoDetail============="+video);
            //自己封装结果
            List<VideossDto> videoDtoList = new ArrayList<>();

            VideossDto videossDto = new VideossDto();

            videossDto.setId(video.getId());
            videossDto.setVideoTitle(video.getTitle());
            videossDto.setCover("http://localhost:8081/yingx/upload_file/img/"+video.getCoverUrl());
            videossDto.setPath("http://qhbevsi3y.hn-bkt.clouddn.com/"+video.getVideoUrl());
            videossDto.setUploadTime(video.getCreateTime());
            videossDto.setDescription(video.getIntro());
            videossDto.setLikeCount(13);
            videossDto.setCateName(video.getCategory().getName());

            videossDto.setCategoryId(video.getC_id());
            videossDto.setUserId(video.getUser_id());

            videossDto.setUserPicImg("http://localhost:8081/yingx/upload_file/img/"+video.getUser().getHeadShow());
            videossDto.setUserName(video.getUser().getUsername());
            videossDto.setPlayCount(12);

            if(video.getUser_id()!=null){
                videossDto.setIsAttention(true);
            }else{
                videossDto.setIsAttention(false);
            }

            List<Video> videos = videoService.queryTwo();
            ArrayList<ViDto> viDtoList = new ArrayList();

            for (Video videoList : videos) {
                    ViDto viDto = new ViDto(
                            videoList.getId(),
                            videoList.getTitle(),
                            "http://localhost:8081/yingx/upload_file/img/"+videoList.getCoverUrl(),
                            "http://qhbevsi3y.hn-bkt.clouddn.com/"+videoList.getVideoUrl(),
                            videoList.getCreateTime(),
                            videoList.getIntro(),
                            5,
                            videoList.getCategory().getName(),
                            /*video.getUser().getHeadShow()*/
                            videoList.getCategory().getId(),
                            videoList.getUser().getId()
                    );
                    viDtoList.add(viDto);
                }

            System.out.println("viDtoList================================="+viDtoList);

            videossDto.setVideoList(viDtoList);

            videoDtoList.add(videossDto);

            System.out.println("videoDtoList================================"+videoDtoList);

            if(!videoDtoList.isEmpty()){
                result.put("data", videoDtoList);
                result.put("message", "查询成功!");
                result.put("status", 100);
            }
        }catch (Exception e){
            result.put("message", "查询失败!");
            result.put("status", 104);
        }
        return result;
    }
}

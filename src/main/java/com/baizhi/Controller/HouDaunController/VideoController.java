package com.baizhi.Controller.HouDaunController;

import com.baizhi.Entity.Video;
import com.baizhi.Service.VideoService;
import com.baizhi.common.QiniuYunUtil;

import com.baizhi.common.TestFirst;

import com.baizhi.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    VideoRepository videoRepository;

    @RequestMapping("/query")
    @ResponseBody
    public List<Video> query() {
        List<Video> videos = videoService.QueryAll();
        System.out.println(videos);
        return videos;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public com.baizhi.common.Result edit(Video video, String oper){
        if ("add".equals(oper)) {
            videoService.add(video);
            System.out.println("Action==================================="+video);
        }
        if ("edit".equals(oper)) {
            videoService.update(video);
        }
        if ("del".equals(oper)) {
            videoService.delete(video.getId());
        }
        return com.baizhi.common.Result.ok(video);
    }


    @RequestMapping("/headUpload")
    public void headUpload(HttpServletRequest request, MultipartFile videoUrl, String id) throws Exception {

        // 获取图片保存的路径
        String realPath = request.getServletContext().getRealPath("/upload_file/videos/");

       String filename= new Date().getTime()+"-"+videoUrl.getOriginalFilename();
        System.out.println(filename);
       String upload=null;
       //上传到七牛云
        try{
            //先上传到磁盘
            videoUrl.transferTo(new File(realPath + filename));
            //再上传到七牛云
            upload = QiniuYunUtil.upload(realPath + filename);
            System.out.println(upload);
        }catch (Exception e){
            e.printStackTrace();
        }
        Video video = new Video();
        video.setId(id);
        video.setVideoUrl(upload);
        //获取第一帧
        TestFirst testFirst= new TestFirst();
        String s5= UUID.randomUUID().toString();
        try {
            testFirst.fetchFrame("D:\\HouQiXiangMu\\baizhi-yxue-module-system\\src\\main\\webapp\\upload_file\\videos\\" + filename, "D:\\HouQiXiangMu\\baizhi-yxue-module-system\\src\\main\\webapp\\upload_file\\img\\" + s5 + ".jpg");
        }catch(Exception e){}
        video.setCoverUrl(s5+".jpg");

        //上传到Es
        Video videoEs = videoService.QueryEs(id);
        System.out.println(videoEs);
        videoEs.setCoverUrl(s5+".jpg");
        videoRepository.save(videoEs);

        videoService.updateCv(video);
    }


    @ResponseBody
    @RequestMapping("querySearchVideo")
    public List<Video> querySearchVideo(String content){
        List<Video> videos = videoService.querySearchVideos(content);
        return videos;
    }
}





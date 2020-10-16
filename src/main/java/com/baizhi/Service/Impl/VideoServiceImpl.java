package com.baizhi.Service.Impl;

import com.baizhi.Dao.VideoDao;
import com.baizhi.Entity.Video;
import com.baizhi.Service.VideoService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDao videoDao;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public List<Video> QueryAll() {
        return videoDao.QueryAll();
    }

    @Override
    public void delete(String id) {
        videoDao.delete(id);
    }

    @Override
    public void add(Video video) {
        String uid = UUID.randomUUID().toString();
        video.setId(uid);
        videoDao.add(video);
    }

    @Override
    public void update(Video video) {
        videoDao.update(video);
    }

    @Override
    public void updateCv(Video video) {
        videoDao.updateCv(video);
    }

    @Override
    public List<Video> queryBy(String content) {
        List<Video> videos = videoDao.queryBy(content);

        return videos;
    }

    @Override
    public Video queryOne(String videoId) {
        Video video = videoDao.queryOne(videoId);
        return video;
    }

    @Override
    public List<Video> queryTwo() {
        List<Video> videos = videoDao.queryTwo();
        return videos;
    }

    @Override
    public List<Video> queryThree(String cateId) {
        List<Video> videos = videoDao.queryThree(cateId);
        return videos;
    }

    @Override
    public List<Video> querySearchVideo(String content) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("yingx") //指定索引
                .withTypes("video") //自定类型
                .withQuery(QueryBuilders.queryStringQuery(content).field("title")) //设置查询条件
                .build();
        return elasticsearchTemplate.queryForList(searchQuery, Video.class);

    }

    @Override
    public List<Video> querySearchVideos(String content) {
        //设置高亮的参数
        HighlightBuilder.Field field = new HighlightBuilder.Field("*")
                .preTags("<span style='color:red'>")
                .postTags("</span>")
                .requireFieldMatch(false);//开启多行高亮

        //设置查询条件
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("yingx") //指定索引
                .withTypes("video") //自定类型
                .withQuery(QueryBuilders.queryStringQuery(content).field("title").field("intro")) //设置查询条件
                .withHighlightFields(field) //设置高亮
                //.withFields("id","title","intro","coverUrl")//设置要哪些属性
                .build();

        AggregatedPage<Video> videos = elasticsearchTemplate.queryForPage(searchQuery, Video.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {

                ArrayList<Video> videos = new ArrayList<>();



                SearchHit[] hits = response.getHits().getHits();

                for (SearchHit hit : hits) {


                    //没有高亮的数据
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();

                    String id =sourceAsMap.get("id")!=null?sourceAsMap.get("id").toString():null;
                    String title =sourceAsMap.get("title")!=null?sourceAsMap.get("title").toString():null;
                    String intro =sourceAsMap.get("intro")!=null?sourceAsMap.get("intro").toString():null;
                    String coverUrl =sourceAsMap.get("coverUrl")!=null?sourceAsMap.get("coverUrl").toString():null;
                    String videoUrl =sourceAsMap.get("videoUrl")!=null?sourceAsMap.get("videoUrl").toString():null;
                    String userId =sourceAsMap.get("user_Id")!=null?sourceAsMap.get("user_Id").toString():null;
                    String cid =sourceAsMap.get("c_id")!=null?sourceAsMap.get("c_id").toString():null;
                    String grpId =sourceAsMap.get("gr_Id")!=null?sourceAsMap.get("gr_Id").toString():null;
                    //日期处理

                    String createAt = sourceAsMap.get("createTime").toString();
                    Long aLong = Long.valueOf(createAt);
                    Date dates = new Date(aLong);

                    /*Date dates=null;
                    if(sourceAsMap.get("grpId")!=null){
                        //处理日期的操作
                       ;
                        Long aLong = Long.valueOf(createAt);
                        dates= new Date(aLong);
                    }*/

                    //1.将未高亮的数据封装为Video对象
                    Video video = new Video(id, title, intro, coverUrl, videoUrl, dates, userId, cid, grpId);

                    //2.将原有未高亮的数据替换为高亮的结果

                    //高亮的数据
                    Map<String, HighlightField> highlightFields = hit.getHighlightFields();

                        /*HighlightField title1 = highlightFields.get("title");
                        Text[] fragments = title1.fragments();
                        String titles = fragments[0].toString();
                        System.out.println("------"+titles);*/

                    //------<span style='color:red'>橘子</span>

                    if (highlightFields.get("title") != null) {
                        String titles = highlightFields.get("title").fragments()[0].toString();
                        video.setTitle(titles);
                    }
                    if (highlightFields.get("intro") != null) {
                        String intros = highlightFields.get("intro").fragments()[0].toString();
                        video.setIntro(intros);
                    }

                    //将封装好的对象放入集合
                    videos.add(video);
                }
                //强转返回
                return new AggregatedPageImpl<T>((List<T>) videos);
            }
        });


        List<Video> videoList = videos.getContent();
        return videoList;
    }

    @Override
    public Video QueryEs(String id) {
        Video video = videoDao.QueryEs(id);
        return video;
    }
}


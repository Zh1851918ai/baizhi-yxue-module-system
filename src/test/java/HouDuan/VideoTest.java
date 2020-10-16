package HouDuan;

import com.baizhi.Dao.VideoDao;
import com.baizhi.Entity.Video;
import com.baizhi.YxueApp;
import com.baizhi.repository.VideoRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

//指定启动类的名字
@SpringBootTest(classes = YxueApp.class)
//整合的一个类
@RunWith(SpringRunner.class)
public class VideoTest {
    @Autowired
    private VideoDao videoDao;

    @Autowired
    VideoRepository videoRepository;

    @Resource
    ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void query(){
        List<Video> videos = videoDao.QueryAll();
        for (Video video : videos) {
            System.out.println(video);
        }
    }

    @Test
    public void save(){
        Video v = new Video("10", "小头", "你站在此地不要动，我去给你买几苹果", "1.jpg", "1.mp4", new Date(), "1", "1", "1");
        videoRepository.save(v);
    }


    @Test
    public void delete(){
        Video v = new Video();
        v.setId("ed9e42a2-adf6-41f0-b4e8-0e779f990952");
        videoRepository.delete(v);
    }

    @Test
    public void saves(){
        List<Video> query = videoDao.query();
        for (Video video : query) {
            System.out.println(video);
            videoRepository.save(video);

        }
    }

    @Test
    public void queryVideo(){

        String content="小头";

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withIndices("yingx") //指定索引
                .withTypes("video") //自定类型
                .withQuery(QueryBuilders.queryStringQuery(content).field("title").field("intro")) //设置查询条件
                .build();

        List<Video> videoList = elasticsearchTemplate.queryForList(searchQuery, Video.class);

        videoList.forEach(video -> System.out.println(video));
    }
}

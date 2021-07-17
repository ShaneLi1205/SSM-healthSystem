import com.lxh.pojo.ArticleClass;
import com.lxh.service.ArticleClassService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Author: LXH
 * @Date: 2021/7/17 22:44
 */
public class MyTest {
    @Test
    public void articleClassMapperTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ArticleClassService articleClassServiceImpl = context.getBean("articleClassServiceImpl", ArticleClassService.class);
        List<ArticleClass> articleClasses = articleClassServiceImpl.listAllArticleClass();
        for (ArticleClass articleClass : articleClasses) {
            System.out.println(articleClass);
        }
    }
}

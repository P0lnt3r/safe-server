package root;

import org.anwang.safe.server.syncprice.SyncPriceStartUp;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={SyncPriceStartUp.class})// 指定启动类
public class SpringTestCase {
}

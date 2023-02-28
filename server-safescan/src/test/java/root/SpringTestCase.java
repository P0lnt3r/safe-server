package root;

import org.anwang.safe.server.safescan.SafeScanStartUp;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={SafeScanStartUp.class})// 指定启动类
public class SpringTestCase {
}

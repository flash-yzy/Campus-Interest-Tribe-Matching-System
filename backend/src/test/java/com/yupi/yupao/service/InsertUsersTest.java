package com.yupi.yupao.service;

import com.yupi.yupao.model.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 导入用户测试
 *
 * @author <a href="https://github.com/flash-yzy">flash-yzy</a>
 */
@SpringBootTest
public class InsertUsersTest {

    @Resource
    private UserService userService;

    private ExecutorService executorService = new ThreadPoolExecutor(
            40,
            100,
            10000,
            TimeUnit.MINUTES,
            new ArrayBlockingQueue<>(10000));

    /**
     * 批量插入用户
     */
    @Test
    public void doInsertUsers() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final int INSERT_NUM = 100000;
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < INSERT_NUM; i++) {
            User user = new User();
            user.setUsername("测试并发插入用户");
            user.setUserAccount("测试插入用户");
            user.setAvatarUrl("test.png");
            user.setGender(0);
            user.setUserPassword("12345678"); //正常应该加密，测试先不管了
            user.setPhone("123");
            user.setEmail("123@qq.com");
            user.setTags("[java,python]");
            user.setUserStatus(0);
            user.setUserRole(0);
            user.setPlanetCode("1111");
            userList.add(user);
        }
        // 25 秒 10 万条
        userService.saveBatch(userList, 1000);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }

    /**
     * 并发批量插入用户
     */
    @Test
    public void doConcurrencyInsertUsers() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        final int INSERT_NUM = 100000;
        int batchSize = 1000;
        int j = 0;
        List<CompletableFuture<Void>> futureList = new ArrayList<>();

        for (int i = 0; i < INSERT_NUM/batchSize; i++) {
            System.out.println("第"+i+"组数据");
            List<User> userList = new ArrayList<>();
            // for(int j = 0;j<batchSize;j++){
            while(true) {
                j++;
                User user = new User();
                user.setUsername("测试并发用户");
                user.setUserAccount("测试用户");
                user.setAvatarUrl("test.png");
                user.setGender(0);
                user.setUserPassword("12345678");
                user.setPhone("123");
                user.setEmail("123@qq.com");
                user.setTags("[\"python\",\"java\"]");
                user.setUserStatus(0);
                user.setUserRole(0);
                user.setPlanetCode(Integer.toString(i + 5));
                userList.add(user);
                if (j % batchSize == 0) break;
            }
            // 异步任务创建与执行
            //  CompletableFuture.runAsync接收Runnable任务
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                System.out.println("threadName: " + Thread.currentThread().getName()+"start");
                userService.saveBatch(userList, batchSize);
                System.out.println("threadName: " + Thread.currentThread().getName()+"finish");
            }, executorService);
            futureList.add(future);
        }
        // 等待所有异步任务完成
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).join();
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }
}

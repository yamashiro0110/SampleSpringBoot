package sample.service.user;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sample.Main;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public class UserFindServiceTest {

    @Resource
    private UserFindService service;


    @Test
    public void testFindAll() throws Exception {
        Assert.assertFalse(service.findAll().isEmpty());
    }

    @Test
    public void testFindAll1() throws Exception {

    }

    @Test
    public void testFindBy() throws Exception {

    }

    @Test
    public void testFindByAddress() throws Exception {

    }

    @Test
    public void testFindByName() throws Exception {
        Assert.assertNotNull(service.findByName("yamashiro"));
    }
}
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.booking.core.entity.Client;
import ro.sda.booking.core.entity.Host;
import ro.sda.booking.core.service.ClientService;
import ro.sda.booking.core.service.HostService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional

public class HostImplTest {
    @Autowired
    private HostService hostService;

    @Test
    @Rollback(false)
    public void testCreateHost() {
        Host host = new Host();
        host.setName("Pleiada");
        host.setEmail("contact@pleiada.ro");
        hostService.createHost(host);
        Assert.assertEquals("Pleiada", host.getName());
        Assert.assertEquals("contact@pleiada.ro", host.getEmail());

    }

    @Test
    @Rollback(false)
    @Transactional
    public void testGetAllHosts() {
        Host host = new Host();
        host.setName("Unirea");
        host.setEmail("contact@unirea.ro");
        hostService.createHost(host);
        Host host1 = new Host();
        host1.setName("Hilton");
        host1.setEmail("contact@hilton.ro");
        hostService.createHost(host1);
        List<Host> hosts = hostService.getAllHosts();
        Assert.assertEquals(3, hosts.size());
    }

    @Test
    public void testGetHostById() {
        Host host = hostService.getHostById(2L);
        Long hostId = host.getId();
        String hostName = host.getName();
        Assert.assertEquals("Unirea", hostName);
        Assert.assertEquals(new Long(2), hostId);
    }

    @Test
    @Rollback(false)
    public void testDeleteHost() {
        List<Host> allHosts = hostService.getAllHosts();
        int size1 = allHosts.size();
        Host host = hostService.getHostById(1L);
        hostService.deleteHost(host);
        List<Host> allHosts2 = hostService.getAllHosts();
        int size2 = allHosts2.size();
        Assert.assertEquals(size1 - 1, size2);
    }

    @Test
    @Rollback(false)
    public void testUpdateHost() {
        Host host = hostService.getHostById(2L);
        host.setName("Traian");
        String hostName = host.getName();
        hostService.updateHost(host);
        Assert.assertEquals("Traian", hostName);
    }

    @Test
    public void testFindByName() {
        Host host = hostService.findByName("Hilton");
        String hostName = host.getName();
        Long hostId = host.getId();
        Assert.assertEquals(new Long(3), hostId);
        Assert.assertEquals(new String("Hilton"), hostName);
    }
}
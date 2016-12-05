package com.alibaba.dubbo.examples.telnet;

import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol;
import org.easymock.EasyMock;
import org.junit.Before;

/**
 * User: liuhanlong
 * Email: liuhanlong3304@126.com
 * Time: 16/12/3 下午10:57
 */
public class TelnetServerTest {

    private Invoker<DemoService> mockInvoker;

    @Before
    public void setUp() {
        mockInvoker = EasyMock.createMock(Invoker.class);
        EasyMock.expect(mockInvoker.getInterface()).andReturn(DemoService.class).anyTimes();
    }

    public void test() {
        DubboProtocol.getDubboProtocol().export(mockInvoker);

    }

    public interface DemoService {
        String say(String name);
    }

    public class DemoServiceImpl implements DemoService {

        public String say(String name) {
            return "say: " + name;
        }
    }
}

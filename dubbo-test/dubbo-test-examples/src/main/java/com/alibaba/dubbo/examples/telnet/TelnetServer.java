package com.alibaba.dubbo.examples.telnet;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.remoting.Channel;
import com.alibaba.dubbo.remoting.RemotingException;
import com.alibaba.dubbo.remoting.Transporters;
import com.alibaba.dubbo.remoting.exchange.Exchangers;
import com.alibaba.dubbo.remoting.exchange.support.ExchangeHandlerDispatcher;
import com.alibaba.dubbo.remoting.exchange.support.header.HeaderExchangeHandler;
import com.alibaba.dubbo.remoting.transport.DecodeHandler;

/**
 * User: liuhanlong
 * Email: liuhanlong3304@126.com
 * Time: 16/12/3 下午10:33
 */
public class TelnetServer {
    public static void main(String[] args) throws Exception {
//        transporter();
        exchanger();
        // 阻止JVM退出
        synchronized (TelnetServer.class) {
            while (true) {
                try {
                    TelnetServer.class.wait();
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static void transporter() throws RemotingException {
        Transporters.bind("telnet://0.0.0.0:2323", new DecodeHandler(new HeaderExchangeHandler(new ExchangeHandlerDispatcher())));
    }

    public static void exchanger() throws RemotingException {
        Exchangers.bind("telnet://0.0.0.0:2323", new ExchangeHandlerDispatcher() {
            @Override
            public void connected(Channel channel) {
                try {
                    channel.send(channel.getUrl().getParameterAndDecoded(Constants.PROMPT_KEY, Constants.DEFAULT_PROMPT));
                } catch (RemotingException e) {
                    e.printStackTrace();
                }
                super.connected(channel);
            }
        });
    }
}

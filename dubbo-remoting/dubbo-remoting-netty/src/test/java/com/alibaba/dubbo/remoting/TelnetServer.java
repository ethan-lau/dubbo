/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.remoting;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.remoting.exchange.ExchangeHandler;
import com.alibaba.dubbo.remoting.exchange.Exchangers;
import com.alibaba.dubbo.remoting.exchange.support.ExchangeHandlerDispatcher;
import com.alibaba.dubbo.remoting.exchange.support.header.HeaderExchangeHandler;
import com.alibaba.dubbo.remoting.telnet.support.TelnetHandlerAdapter;
import com.alibaba.dubbo.remoting.transport.ChannelHandlerAdapter;
import com.alibaba.dubbo.remoting.transport.DecodeHandler;
import com.alibaba.dubbo.remoting.transport.netty.NettyHandler;

/**
 * TelnetServer
 * 
 * @author william.liangf
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
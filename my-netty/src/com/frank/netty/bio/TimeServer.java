/*
 * Copyright 2013-2018 WangBiao.
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
package com.frank.netty.bio;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author WangBiao
 * @date 2016年12月14日
 * @version 1.0
 */
public class TimeServer {

	/**
	 * @throws IOException
	 */
	public static void start(final int port)  {
		Thread thread = new Thread() {
			@Override
			public void run() {
				ServerSocket server = null;
				try
				{
					server = new ServerSocket(port);
					System.out.println("The time server is start in port : " + port);
					Socket socket = null;
					while (true) {
						socket = server.accept();
						new Thread(new TimeServerHandler(socket)).start();
					}
				}
				catch(IOException e){}
				finally
				{
					if (server != null) {
						System.out.println("The time server close");
						try {
							server.close();
						}catch(Exception e){
							e.printStackTrace();
						}
						server = null;
					}
				}
			}
		};
		thread.start();
	}
}

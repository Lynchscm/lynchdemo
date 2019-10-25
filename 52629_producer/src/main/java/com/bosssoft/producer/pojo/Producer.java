package com.bosssoft.producer.pojo;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Lynch
 * @date 2019/10/11 -18:22
 */
@Component
public class Producer {
	@Resource
	private StringRedisTemplate stringRedisTemplate;
	void pro() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i =0;i<100;i++) {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					double value = Math.random();
					stringRedisTemplate.opsForList().leftPush("list",String.valueOf(value));
				}
			}
		});
		thread.start();
	}

}

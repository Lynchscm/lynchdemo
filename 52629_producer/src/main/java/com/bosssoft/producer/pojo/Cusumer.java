package com.bosssoft.producer.pojo;

import com.bosssoft.producer.config.Sort;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lynch
 * @date 2019/10/11 -18:22
 */
@Component
public class Cusumer {
	@Resource
	private StringRedisTemplate stringRedisTemplate;
	void cus() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				List<Double> list = new ArrayList<>();
				int count = 0;
				while (count < 100) {
					String str =  stringRedisTemplate.opsForList().leftPop("list");
					if (str != null) {
						list.add(Double.valueOf(str));
					}
//					System.out.println(str);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					count += 1;
				}
				Double[] numbers = new Double[list.size()];
				list.toArray(numbers);
				Sort.insertion(numbers);
				System.out.println("排序结果：");
				for (Double number : numbers) {
					System.out.print(" " + number);
				}

			}
		});
		thread.start();
	}

}

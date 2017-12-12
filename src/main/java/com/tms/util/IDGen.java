package com.tms.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * id序列生成器 (32 bits timestamp | 4 bits worker| 16 bits sequence = 52 bits long).
 * 32位时间戳精确到秒，以2017/00/00 00:00:00 000 为开始偏移时间.
 * 4位node由配置文件配置,如没有配置则默认设置为1(max 15),用于区分不同进程(jvm或机器).
 * 16位自增序号,循环自增. 不以时间戳为单位重新计数,以降低低位重复率. 自增到最大值(65535)
 * 或重启java进程后，重新计数。
 * <p>
 * created 2017/12/09
 *
 * @author 史再杰
 * @version 1.0
 */
public class IDGen {

    private final static Logger logger = LoggerFactory.getLogger(IDGen.class);

    private static final long BASE_TIME = 1483113600000L; // 2016/12/31 00:00:00 000
    private static final long TIMESTAMP_SHIFT = 20L; // 时间戳移位
    private static final long WORKER_SHIFT = 16L; // worker移位
    private final static long SEQ_MASK = 0xFFFFL; // max sequence & seq mask

    private final static long WORKER_MAX = 15L;
    private final static long WORKER_MIN = 0L;

    @Value("${idworker}")
    private static Long WORKER_ID;

    private static AtomicInteger instanceCurrOrderSequence;

    static {
        long workerId;
        try {
            workerId = WORKER_ID;
        } catch (Exception ex) {
            logger.warn(ex.getMessage());
            logger.warn("--- IDGen --- 获取workerId配置失败, 使用默认值初始化, 初始化为 (byte)1 .");
            workerId = 1L;
        }

        if (workerId < WORKER_MIN || workerId > WORKER_MAX) {
            throw new RuntimeException("--- idworker --- 配置错误，允许值 [0-15]");
        }

        WORKER_ID = workerId;
    }

    private static AtomicInteger getInstance() {
        if (instanceCurrOrderSequence == null) {
            synchronized (IDGen.class) {
                if (instanceCurrOrderSequence == null)
                    instanceCurrOrderSequence = new AtomicInteger(1);
            }
        }
        return instanceCurrOrderSequence;
    }


    public static long nextId() {

        return (((System.currentTimeMillis() - BASE_TIME) / 1000) << TIMESTAMP_SHIFT)
              | (WORKER_ID << WORKER_SHIFT)
              | (getInstance().getAndIncrement() & SEQ_MASK);

    }

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        System.out.println(" begin : " + begin);
        for (long i = 0; i < (0xffffL + 3); i++) {
            long id = IDGen.nextId();
            if (i > 0xffffL)
                System.out.println(id);
        }
        long end = System.currentTimeMillis();
        System.out.println(" end : " + end);

        System.out.println(" total : " + (end - begin));
        System.out.println(" speed : " + (0xffffL / (end - begin)) * 1000 + " / sec");

    }
}

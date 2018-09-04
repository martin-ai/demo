package com.example.demo.linuxFTP;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Closer {
    private static Logger logger = LoggerFactory.getLogger(Closer.class);

    public Closer() {
    }

    public static void close(AutoCloseable... closeableResource) {
        if (closeableResource != null) {
            AutoCloseable[] var1 = closeableResource;
            int var2 = closeableResource.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                AutoCloseable r = var1[var3];
                if (r != null) {
                    try {
                        r.close();
                    } catch (Exception var6) {
                        logger.error("Exception on close", var6);
                    }
                }
            }
        }

    }
}


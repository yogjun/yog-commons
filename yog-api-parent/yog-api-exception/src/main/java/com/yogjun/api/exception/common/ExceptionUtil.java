package com.yogjun.api.exception.common;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * {@link ExceptionUtil}
 *
 * @author <a href="mailto:280536928@qq.com">yogjun</a>
 * @version ${project.version} - 2024/6/9
 */
public class ExceptionUtil {
    private ExceptionUtil() {
    }

    public static String toString(Throwable e) {
        StringWriter w = new StringWriter();
        PrintWriter p = new PrintWriter(w);
        p.print(e.getClass().getName());
        if (e.getMessage() != null) {
            p.print(": " + e.getMessage());
        }
        p.println();
        try {
            e.printStackTrace(p);
            return w.toString();
        } finally {
            p.close();
        }
    }

    public static String toString(String msg, Throwable e) {
        StringWriter w = new StringWriter();
        w.write(msg + "\n");
        try (PrintWriter p = new PrintWriter(w)) {
            e.printStackTrace(p);
            return w.toString();
        }
    }
}

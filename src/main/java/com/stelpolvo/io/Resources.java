package com.stelpolvo.io;

import java.io.InputStream;

public class Resources {
    public static InputStream getResourcesAsStream(String path) {
        return Resources.class.getClassLoader().getResourceAsStream(path);
    }
}

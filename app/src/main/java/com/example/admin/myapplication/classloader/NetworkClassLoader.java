package com.example.admin.myapplication.classloader;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by hasee on 12/13/2017.
 *
 * @author tin
 */

public class NetworkClassLoader extends ClassLoader {

    private String rootUrl;

    public NetworkClassLoader(String rootUrl) {
        this.rootUrl = rootUrl;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getClassData(name);
        if (classData == null)
            throw new ClassNotFoundException();
        else
            return defineClass(name, classData, 0, classData.length);
    }

    private byte[] getClassData(String name) {
        String path = classNameToPath(name);
        try {
            URL url = new URL(path);
            InputStream is = url.openStream();
            byte[] bytes = new byte[4096];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int byteNumRead = 0;
            while ((byteNumRead = is.read()) != -1)
                baos.write(bytes, 0, byteNumRead);
            return baos.toByteArray();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String classNameToPath(String name) {
        return rootUrl + "/" + name.replace(".", "/" + ".class");
    }

}

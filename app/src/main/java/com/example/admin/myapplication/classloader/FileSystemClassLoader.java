package com.example.admin.myapplication.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by hasee on 12/13/2017.
 *
 * @author tin
 */

public class FileSystemClassLoader extends ClassLoader {

    private String rootDir;

    public FileSystemClassLoader(String rootDir) {
        this.rootDir = rootDir;
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
            InputStream is = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] bytes = new byte[4096];
            int bytesNumRead = 0;
            while ((bytesNumRead = is.read(bytes)) != -1) {
                baos.write(bytes, 0, bytesNumRead);
            }
            return baos.toByteArray();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String classNameToPath(String name) {
        return rootDir + File.separator + name.replace(".", File.separator) + ".class";
    }
}

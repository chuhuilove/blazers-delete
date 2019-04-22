package com.chuhui.blazers.asm.tutorial;

import org.objectweb.asm.ClassWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Arrays;

/**
 * Created by Administrator on 2019/4/16 0016.
 */
public class CustomerClassLoader extends ClassLoader {


    public Class defineClass(String name, byte[] b) {
        return defineClass(name, b, 0, b.length);
    }
}

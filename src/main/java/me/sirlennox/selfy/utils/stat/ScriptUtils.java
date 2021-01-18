package me.sirlennox.selfy.utils.stat;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import jdk.internal.dynalink.beans.StaticClass;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.python.core.PyList;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ScriptUtils {

    public static Object convertIntoJavaObject(Object scriptObj) {
        if(scriptObj instanceof PyList) return new ArrayList<Object>((PyList) scriptObj);
        if (scriptObj instanceof ScriptObjectMirror) {
            ScriptObjectMirror scriptObjectMirror = (ScriptObjectMirror) scriptObj;
            if (scriptObjectMirror.isArray()) {
                List<Object> list = Lists.newArrayList();
                for (Map.Entry<String, Object> entry : scriptObjectMirror.entrySet()) {
                    list.add(convertIntoJavaObject(entry.getValue()));
                }
                return list;
            } else {
                Map<String, Object> map = Maps.newHashMap();
                for (Map.Entry<String, Object> entry : scriptObjectMirror.entrySet()) {
                    map.put(entry.getKey(), convertIntoJavaObject(entry.getValue()));
                }
                return map;
            }
        } else {
            return scriptObj;
        }
    }

    public static String getExtensionFromFileName(String name) throws IllegalArgumentException{
        String[] split = name.split("\\.");
        if(split.length < 2) throw new IllegalArgumentException("Cannot find extension");
        return split[split.length - 1];
    }

    public static Object newInstance(String classPath, int constructor, Object[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return Class.forName(classPath).getConstructors()[constructor].newInstance(args);
    }

    public static Object getClass(String c) throws ClassNotFoundException {
        return StaticClass.forClass(Class.forName(c));
    }

}

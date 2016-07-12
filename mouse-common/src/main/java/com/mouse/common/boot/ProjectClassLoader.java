package com.mouse.common.boot;

import com.google.common.collect.Lists;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppClassLoader;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author zhanghao
 * @version 1.0
 * @created 16/6/30
 */
public class ProjectClassLoader extends WebAppClassLoader {

    private boolean initialized = false;
    private static List<String> classpaths = Lists.newArrayList();

    public ProjectClassLoader(WebAppContext context, String projectClassPath,
                              String excluded) throws IOException {
        this(context, projectClassPath, excluded, true);
    }

    public ProjectClassLoader(WebAppContext context, String projectClassPath,
                              String excluded, boolean logger) throws IOException {
        super(context);
        if ( projectClassPath != null ) {
            StringBuffer excludedString = new StringBuffer();
            String[] tokens = projectClassPath.split(String.valueOf(File.pathSeparatorChar));
            for(String entry:tokens){
                String path = entry;
                if(path.startsWith("-y-")|| path.startsWith("-n-")){ //backard compatiable.
                    path = path.substring(3);
                }
                if(entry.startsWith("-n-")){
                    if (logger) excludedString.append("Excluded entry="+ path+" \n");
                }else{
                    if (logger) System.err.println("ProjectClassLoader: entry="+ path);
                    classpaths.add(path);
                    super.addClassPath( path);
                }
            }
            System.err.print(excludedString.toString());
        }

        initialized = true;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
            return loadClass(name, false);
        } catch (NoClassDefFoundError e) {
            throw new ClassNotFoundException(name);
        }
    }

    @Override
    public void addClassPath(String classPath) throws IOException {
        if (initialized) {
                        /*
                         * Disable the adding of directories to the class path after
                         * initialization with the project class path. XXX Except for the
                         * addition of the WEB-INF/classes
                         */
            if (!classPath.endsWith("WEB-INF/classes/"))
                return;
        }
        super.addClassPath(classPath);
        return;
    }

    @Override
    public void addJars(Resource lib) {
        if (initialized) {
                        /*
                         * Disable the adding of jars (or folders of jars) to the class path
                         * after initialization with the project class path.
                         */
            return;
        }
        super.addJars(lib);
        return;
    }

    /**
     * TODO review this later , it's just a temp workaround. :(
     * @return
     */
    public static List<String> getClasspaths() {
        return classpaths;
    }
}

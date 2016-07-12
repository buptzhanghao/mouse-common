package com.mouse.common.boot;

import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanghao
 * @version 1.0
 * @created 16/7/1
 */
public class RJRWebInfoConfiguration extends WebInfConfiguration {
    private static final Logger LOG = Log
            .getLogger(RJRWebInfoConfiguration.class);

    protected List<Resource> findJars (WebAppContext context)
            throws Exception
    {
        List<Resource> jarResources = new ArrayList<Resource>();
        List<String> files= ProjectClassLoader.getClasspaths();
        for (int f=0;files!=null && f<files.size();f++)
        {
            try
            {
                Resource file = Resource.newResource(new File(files.get(f)));
                String fnlc = file.getName().toLowerCase();
                int dot = fnlc.lastIndexOf('.');
                String extension = (dot < 0 ? null : fnlc.substring(dot));
                if (extension == null || (extension.equals(".jar") || extension.equals(".zip")))
                {
                    jarResources.add(file);
                }
            }
            catch (Exception ex)
            {
                LOG.warn(Log.EXCEPTION,ex);
            }
        }
        return jarResources;
    }
}

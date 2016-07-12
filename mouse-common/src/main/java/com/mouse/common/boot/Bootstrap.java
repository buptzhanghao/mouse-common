package com.mouse.common.boot;

import org.eclipse.jetty.xml.XmlConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author zhanghao
 * @version 1.0
 * @created 16/6/30
 */
public class Bootstrap {

    private static final Logger log = LoggerFactory.getLogger(Bootstrap.class);

    public static void main(String[] args) throws Exception {
        log.info("-------- Bootstrap --------");

        initClasspath();
        loadConfig();

        String logs = Config.get("jetty.logs");
        if ( logs == null ) {
            if ( new File("/opt/logs/mobile").exists() ) {
                logs = "/opt/logs/mobile";
            } else {
                logs = "./logs";
            }
        }

        if ( !new File(logs).exists() ) {
            new File(logs).mkdirs();
        }

        String webroot = Config.get("jetty.webroot");
        if ( webroot == null ) {
            if ( new File("webroot").exists() ) {
                webroot = "./webroot";
            } else if ( new File("src/main/webapp").exists() ) {
                webroot = "./src/main/webapp";
            } else {
                webroot = ".";
            }
        }

        Config.set("jetty.webroot", webroot);

        String context = Config.get("jetty.context");
        if ( context == null || context.isEmpty() ) {
            context = "/";
        } else if ( context.charAt(0) != '/' ) {
            context = "/"+context;
        }
        Config.set("jetty.context", context);

        log.info("user.dir = {}.", System.getProperty("user.dir"));
        log.info("jetty.webroot = {}.", webroot);
        log.info("jetty.context = {}.", context);

        InputStream input=Bootstrap.class.getResourceAsStream("/jetty/jetty.xml");
        if ( input == null ) {
            input = Bootstrap.class.getResourceAsStream("/jetty8.xml");
            log.info("Booting with /jetty8.xml");
        } else {
            log.info("Booting with /jetty/jetty.xml");
        }


        final XmlConfiguration jettyConfig = new XmlConfiguration(input);

        final Object server = jettyConfig.configure();

        final Pattern p = Pattern.compile("jetty-[\\w]+.xml");
        File[] paths = new File[] {
                new File("src/main/resources/jetty"),
                new File("src/test/resources/jetty"),new File(webroot,"WEB-INF/classes/jetty")
        };

        for ( File path : paths ) {
            if ( path.exists() && path.isDirectory() ) {
                File[] jettys = path.listFiles( new FileFilter() {
                    @Override
                    public boolean accept(File file) {
                        return file.isFile() && p.matcher(file.getName()).matches();
                    }
                });
                if ( jettys != null ) {
                    for ( File jf : jettys ) {
                        log.info("Plusing "+jf.getName());
                        XmlConfiguration cfg = new XmlConfiguration(new FileInputStream(jf));
                        cfg.configure(server);
                    }
                }
                break;
            }
        }

        log.info("\n\n");
        server.getClass().getMethod("start").invoke(server);

    }

    private static void loadConfig() throws IOException {
        InputStream input = Bootstrap.class.getResourceAsStream("/jetty/boot.properties");

        if ( input != null ) {
            System.getProperties().load(input);
            log.info("Boot Loaded...");
        } else {
            log.info("No /jetty/boot.properties found to load...");
        }
        Config.reload();
    }

    private static void initClasspath() {
        String projectClassPath = System.getProperty("java.class.path");
        String javaHome = System.getProperty("java.home");

        if ( javaHome.endsWith("/jre") ) {
            javaHome = javaHome.substring(0, javaHome.length() - 4);
        }

        log.info("JAVA_HOME = {}.", javaHome);
        log.info("CLASSPATH = {}.", projectClassPath);

        List<String> classPaths = ProjectClassLoader.getClasspaths();
        boolean logger = true;

        if ( projectClassPath != null ) {
            StringBuffer excludedString = new StringBuffer();
            String[] tokens = projectClassPath.split(String.valueOf(File.pathSeparatorChar));

            for ( String entry : tokens ) {
                String path = entry;
                if ( path.startsWith("-y-") || path.startsWith("-n-") ) {
                    path = path.substring(3);
                }
                if ( entry.startsWith("-n-") || entry.startsWith(javaHome) ) {
                    if ( logger ) {
                        excludedString.append((excludedString.length() > 0 ? "\n" : "") + "Excluded entry = " + path);
                    }
                } else {
                    if ( logger ) {
                        log.info("ProjectClassLoader: entry = {}.", entry);
                        classPaths.add(path);
                    }
                }
            }
            log.info(excludedString.toString());
        }
    }

}

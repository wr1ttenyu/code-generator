package wr1ttenyu.f1nal.study.code.generator.util.mybatis;

import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.util.messages.Messages;
import org.mybatis.generator.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.CharArrayReader;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Wr1ShellRunner {

    private static final Logger logger = LoggerFactory.getLogger(Wr1ShellRunner.class);

    private static final String CONFIG_FILE = "-configfile";
    private static final String OVERWRITE = "-overwrite";
    private static final String CONTEXT_IDS = "-contextids";
    private static final String TABLES = "-tables";
    private static final String VERBOSE = "-verbose";
    private static final String FORCE_JAVA_LOGGING = "-forceJavaLogging";
    private static final String HELP_1 = "-?";
    private static final String HELP_2 = "-h";

    public Wr1ShellRunner() {
    }

    public static byte[] runCodeGenerator(CharArrayReader configFileReader) {
        byte[] generateZip = null;
        String[] args = {"-configfile", "123"};
        if (args.length == 0) {
            usage();
            System.exit(0);
        } else {
            Map<String, String> arguments = parseCommandLine(args);
            if (arguments.containsKey("-?")) {
                usage();
                System.exit(0);
            } else {
                List<String> warnings = new ArrayList();
                Set<String> fullyqualifiedTables = new HashSet();
                if (arguments.containsKey("-tables")) {
                    StringTokenizer st = new StringTokenizer((String)arguments.get("-tables"), ",");

                    while(st.hasMoreTokens()) {
                        String s = st.nextToken().trim();
                        if (s.length() > 0) {
                            fullyqualifiedTables.add(s);
                        }
                    }
                }

                Set<String> contexts = new HashSet();
                String warning;
                if (arguments.containsKey("-contextids")) {
                    StringTokenizer st = new StringTokenizer((String)arguments.get("-contextids"), ",");

                    while(st.hasMoreTokens()) {
                        warning = st.nextToken().trim();
                        if (warning.length() > 0) {
                            contexts.add(warning);
                        }
                    }
                }

                String error;
                Iterator var20;
                try {
                    ConfigurationParser cp = new ConfigurationParser(warnings);
                    Configuration config = cp.parseConfiguration(configFileReader);
                    DefaultShellCallback shellCallback = new DefaultShellCallback(arguments.containsKey("-overwrite"));
                    Wr1MyBatisGeneraotr myBatisGenerator = new Wr1MyBatisGeneraotr(config, shellCallback, warnings);
                    ProgressCallback progressCallback = arguments.containsKey("-verbose") ? new VerboseProgressCallback() : null;
                    generateZip = myBatisGenerator.generate(progressCallback, contexts, fullyqualifiedTables);
                } catch (XMLParserException var12) {
                    writeLine(Messages.getString("Progress.3"));
                    writeLine();
                    var20 = var12.getErrors().iterator();

                    while(var20.hasNext()) {
                        error = (String)var20.next();
                        writeLine(error);
                    }

                    return null;
                } catch (IOException | SQLException var13) {
                    var13.printStackTrace(System.out);
                    return null;
                } catch (InvalidConfigurationException var14) {
                    writeLine(Messages.getString("Progress.16"));
                    var20 = var14.getErrors().iterator();

                    while(var20.hasNext()) {
                        error = (String)var20.next();
                        writeLine(error);
                    }

                    return null;
                } catch (InterruptedException var15) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                    logger.error("代码生成出现未知异常，errMsg:{}", e);
                }

                Iterator var19 = warnings.iterator();

                while(var19.hasNext()) {
                    warning = (String)var19.next();
                    writeLine(warning);
                }

                if (warnings.isEmpty()) {
                    writeLine(Messages.getString("Progress.4"));
                } else {
                    writeLine();
                    writeLine(Messages.getString("Progress.5"));
                }
            }
        }

        return generateZip;
    }

    private static void usage() {
        writeLine(Messages.getString("Usage"));
    }

    private static void writeLine(String message) {
        System.out.println(message);
    }

    private static void writeLine() {
        System.out.println();
    }

    private static Map<String, String> parseCommandLine(String[] args) {
        List<String> errors = new ArrayList();
        Map<String, String> arguments = new HashMap();

        for(int i = 0; i < args.length; ++i) {
            if ("-configfile".equalsIgnoreCase(args[i])) {
                if (i + 1 < args.length) {
                    arguments.put("-configfile", args[i + 1]);
                } else {
                    errors.add(Messages.getString("RuntimeError.19", "-configfile"));
                }

                ++i;
            } else if ("-overwrite".equalsIgnoreCase(args[i])) {
                arguments.put("-overwrite", "Y");
            } else if ("-verbose".equalsIgnoreCase(args[i])) {
                arguments.put("-verbose", "Y");
            } else if ("-?".equalsIgnoreCase(args[i])) {
                arguments.put("-?", "Y");
            } else if ("-h".equalsIgnoreCase(args[i])) {
                arguments.put("-?", "Y");
            } else if ("-forceJavaLogging".equalsIgnoreCase(args[i])) {
                LogFactory.forceJavaLogging();
            } else if ("-contextids".equalsIgnoreCase(args[i])) {
                if (i + 1 < args.length) {
                    arguments.put("-contextids", args[i + 1]);
                } else {
                    errors.add(Messages.getString("RuntimeError.19", "-contextids"));
                }

                ++i;
            } else if ("-tables".equalsIgnoreCase(args[i])) {
                if (i + 1 < args.length) {
                    arguments.put("-tables", args[i + 1]);
                } else {
                    errors.add(Messages.getString("RuntimeError.19", "-tables"));
                }

                ++i;
            } else {
                errors.add(Messages.getString("RuntimeError.20", args[i]));
            }
        }

        if (!errors.isEmpty()) {
            Iterator var5 = errors.iterator();

            while(var5.hasNext()) {
                String error = (String)var5.next();
                writeLine(error);
            }

            System.exit(-1);
        }

        return arguments;
    }
}

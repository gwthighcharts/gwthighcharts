package com.usesoft.highcharts4gwt.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.CheckForNull;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;

import com.sun.codemodel.JClassAlreadyExistsException;
import com.usesoft.highcharts4gwt.generator.codemodel.ClassBuilder;
import com.usesoft.highcharts4gwt.generator.codemodel.OutputType;
import com.usesoft.highcharts4gwt.generator.graph.OptionSpec;
import com.usesoft.highcharts4gwt.generator.graph.OptionTree;
import com.usesoft.highcharts4gwt.generator.graph.OptionUtils;
import com.usesoft.highcharts4gwt.generator.graph.Options;

public abstract class BaseGenerator implements Generator
{
    public BaseGenerator() throws IOException
    {
        properties = loadProperties();
        packageName = getOutputPackagePrefix() + getOutputPackageSuffix();
        cleanDirectory(getRootDirectory() + "/" + packageToPath(packageName));
    }

    @Override
    public void generate() throws IOException, JClassAlreadyExistsException
    {
        options = createOptions();
        createClasses(options);
    }

    @CheckForNull
    public static String fetchOptionsAsString(String highchartOptionsUrl) throws IOException
    {
        InputStream optionsInputStream = null;
        String optionsAsString;

        try
        {
            optionsInputStream = new URL(highchartOptionsUrl).openStream();
            optionsAsString = IOUtils.toString(optionsInputStream);
        }
        finally
        {
            if (optionsInputStream != null)
                optionsInputStream.close();
        }

        return optionsAsString;
    }

    public abstract String getOptionsFileUrl() throws IOException;

    public abstract String getOutputPackageSuffix();

    protected final String getOutputPackagePrefix() throws IOException
    {
        return getPropertyValue(GENERATOR_OUTPUT_PACKAGEPREFIX);
    }

    protected String getPropertyValue(String propertyName) throws IOException
    {
        return properties.getProperty(propertyName);
    }

    private String getRootDirectory() throws IOException
    {
        return getPropertyValue(GENERATOR_OUTPUT_ROOTDIR);
    }

    private void createClasses(Options options) throws JClassAlreadyExistsException, IOException
    {
        for (OptionTree tree : options.getTrees())
        {
            writeClasses(tree);
        }

        writeTopClass(options);
    }

    private void writeTopClass(Options options) throws IOException, JClassAlreadyExistsException
    {
        for (OutputType outputType : OutputType.values())
        {
            ClassBuilder builder = outputType.accept(new ClassWritterVisitor(), getRootDirectory());
            if (builder != null)
            {
                String fullname = "chartOptions";
                OptionSpec topOptionSpec = new OptionSpec(fullname, fullname, fullname);
                OptionTree topOptionTree = new OptionTree(topOptionSpec);
                List<OptionSpec> children = new ArrayList<OptionSpec>();
                for (OptionTree tree : options.getTrees())
                {
                    children.add(tree.getRoot());
                }
                topOptionTree.getParentToChildrenRelations().put(topOptionSpec, children);
                builder.setPackageName(computePackageName(topOptionSpec, outputType)).setOptionSpec(topOptionSpec).setTree(topOptionTree);
                builder.build();
            }
        }

    }

    private void writeClasses(OptionTree tree) throws JClassAlreadyExistsException, IOException
    {
        for (int i = tree.getDepth(); i >= 0; i--)
        {
            for (OptionSpec option : tree.getOptionsByDepth().get(i))
            {
                if (!option.isParent())
                    continue;

                for (OutputType outputType : OutputType.values())
                {
                    ClassBuilder builder = outputType.accept(new ClassWritterVisitor(), getRootDirectory());
                    if (builder != null)
                    {
                        builder.setPackageName(computePackageName(option, outputType)).setOptionSpec(option);
                        builder.setTree(tree);
                        builder.build();
                    }
                }
            }
        }

    }

    private String computePackageName(OptionSpec option, OutputType outputType)
    {
        String pckg = packageName + "." + outputType.getRootPackageName();
        String highchartsPackageName = OptionUtils.getHighchartsPackageName(option);
        if (!highchartsPackageName.equalsIgnoreCase(""))
        {
            pckg += "." + highchartsPackageName;
        }
        return pckg;
    }

    private Properties loadProperties() throws IOException
    {
        Properties out = new Properties();
        InputStream inputStream = this.getClass().getResourceAsStream(CONFIGURATION_FILENAME);
        if (inputStream == null)
        {
            throw new FileNotFoundException("property file '" + CONFIGURATION_FILENAME + "' not found in the classpath");
        }

        out.load(inputStream);
        return out;
    }

    private Options createOptions() throws IOException
    {
        String url = getOptionsFileUrl();
        String optionsAsString = fetchOptionsAsString(url);

        if (optionsAsString == null)
            throw new RuntimeException("Cannot fetch options from highchart web site for url : " + url);

        JSONArray jsonArray = JsonUtils.extractOptions(optionsAsString);

        return JsonUtils.createOptions(jsonArray);
    }

    private void cleanDirectory(String dirPath) throws IOException
    {
        File directory = new File(dirPath);

        if (directory.isDirectory() && directory.exists())
            FileUtils.cleanDirectory(directory);
    }

    private String packageToPath(String packageName)
    {
        String path = packageName.replace(".", "/");
        return path;
    }

    private static final String CONFIGURATION_FILENAME = "configuration.properties";
    private static final String GENERATOR_OUTPUT_PACKAGEPREFIX = "generator.output.packagePrefix";
    private static final String GENERATOR_OUTPUT_ROOTDIR = "generator.output.rootDir";
    private final Properties properties;
    private final String packageName;
    @CheckForNull
    private Options options;
}
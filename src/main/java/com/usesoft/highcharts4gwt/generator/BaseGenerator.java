package com.usesoft.highcharts4gwt.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import javax.annotation.CheckForNull;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;

import com.sun.codemodel.JClassAlreadyExistsException;
import com.usesoft.highcharts4gwt.generator.graph.OptionSpec;
import com.usesoft.highcharts4gwt.generator.graph.OptionTree;
import com.usesoft.highcharts4gwt.generator.graph.Options;

public abstract class BaseGenerator implements Generator
{
    public BaseGenerator() throws IOException
    {
        properties = loadProperties();
        packageName = getOutputPackagePrefix() + getOutputPackageSuffix();
        cleanDirectory(getRootDirectory() + "/" + packageToPath(packageName));
    }

    public void generate() throws IOException, JClassAlreadyExistsException
    {
        options = createOptions();
        createClasses(options, packageName);
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

    private void createClasses(Options options, String packageName) throws JClassAlreadyExistsException, IOException
    {
        buildRootClass(options, packageName);
    }

    private void buildRootClass(Options options, String packageName) throws JClassAlreadyExistsException, IOException
    {
        // JCodeModel codeModel = new JCodeModel();
        // codeModel._class(packageName + ".Options");

        for (OptionTree tree : options.getTrees())
        {
            writeClasses(tree);
        }
        // codeModel.build(new File(getRootDirectory()));
    }

    private void writeClasses(OptionTree tree) throws JClassAlreadyExistsException, IOException
    {
        OptionSpec root = tree.getRoot();

        ClassBuilder builder = new ClassBuilder(getRootDirectory(), packageName, root);
        builder.build();

    }

    // private void writeLeafClass(OptionSpec leaf) throws JClassAlreadyExistsException, IOException
    // {
    // JCodeModel codeModel = new JCodeModel();
    // JDefinedClass optionsClass = codeModel._class(packageName + "." + leaf.getFullname());
    // codeModel.build(new File(getRootDirectory()));
    // }

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

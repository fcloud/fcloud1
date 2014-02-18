package com.fcloud.util.scanning;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

// TODO 待重构为更通用扫描工具
/**
 * 包扫描工具类，辅助包扫描相关功能实现
 * @author Ruben Fu
 * @see org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
 */
public class ClassPathScanningClassesExecutor {

    protected static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

//    protected static final Log logger = LogFactory.getLog(ClassPathScanningClassesExecutor.class);

    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);

    private final List<TypeFilter> includeFilters = new LinkedList<TypeFilter>();

    protected String resolveBasePackage(String basePackage) {

        return ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(basePackage));
    }

    protected boolean isHandlerClass(MetadataReader metadataReader) throws IOException {
        for (TypeFilter tf : this.includeFilters) {
            if (tf.match(metadataReader, this.metadataReaderFactory)) {
                AnnotationMetadata metadata = metadataReader.getAnnotationMetadata();
                if (!metadata.isAnnotated(Profile.class.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public ClassPathScanningClassesExecutor addIncludeFilter(TypeFilter includeFilter) {
        this.includeFilters.add(includeFilter);
        return this;
    }

    public ClassPathScanningClassesExecutor addAnnotationIncludeTypeFilter(Class<? extends Annotation> annotationType) {
        return addIncludeFilter(new AnnotationTypeFilter(annotationType));
    }

    public ClassPathScanningClassesExecutor addAssignableIncludeTypeFilter(Class<?> clz) {
        return addIncludeFilter(new AssignableTypeFilter(clz));
    }

    @SuppressWarnings("unchecked")
    protected <T> void doFindCandidateClasses(Set<Class<T>> handlers, String basePackage) {
        try {
            String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                    + resolveBasePackage(basePackage)
                    + "/"
                    + DEFAULT_RESOURCE_PATTERN;

            Resource[] resources = this.resourcePatternResolver.getResources(packageSearchPath);

            for (Resource resource : resources) {

                MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);

                if (isHandlerClass(metadataReader)) {

                    handlers.add((Class<T>) ClassUtils.forName(metadataReader.getClassMetadata().getClassName(),
                            ClassUtils.getDefaultClassLoader()));
                }
            }
            
            // fix
            //WebContext.read();
        }
        catch (ClassNotFoundException ex) {
            throw new RuntimeException("Class load failure classpath scanning", ex);
        }
        catch (LinkageError ex) {
            throw new RuntimeException("Class load failure classpath scanning", ex);
        }
        catch (IOException ex) {
            throw new RuntimeException("I/O failure during classpath scanning", ex);
        }
    }

    public <T> Set<Class<T>> findCandidateClasses(String basePackage) {

        Set<Class<T>> handlers = new LinkedHashSet<Class<T>>();

        if (basePackage == null) {
            return handlers;
        }

        for (String pkg : basePackage.split("[,;]")) {
            doFindCandidateClasses(handlers, pkg);
        }
        return handlers;

    }
}

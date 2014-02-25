package com.fcloud.core.repository.support;

import com.fcloud.core.repository.TableOrder;
import com.fcloud.util.scanning.ClassPathScanningClassesExecutor;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Ruben Fu
 */
@Component
public class TableCreator {

    private final Log logger = LogFactory.getLog(TableCreator.class);

    @Value("#{fcloud['db.dll.type']}")
    private String createType = "create";

    @Value("#{fcloud['db.scan.basepackage']}")
    private String basePackage;

    private Set<Class<Object>> createTables = Collections.emptySet();

    @Autowired
    private ConnectionSource connectionSource;

    @PostConstruct
    public void maybeCreateTable() throws SQLException {
        createTables = new ClassPathScanningClassesExecutor().addAnnotationIncludeTypeFilter(
                DatabaseTable.class).findCandidateClasses(basePackage);

        List<Class<Object>> list = corverToList(createTables);

        System.out.println(list);

        logger.info("createType:" + createType);
        logger.info("basePackage:" + basePackage);
        if ("create".equalsIgnoreCase(createType) || "create-drop".equalsIgnoreCase(createType)) {
            doDrop();
        }
        if ("create".equalsIgnoreCase(createType) || "create-drop".equalsIgnoreCase(createType)) {
            for (Class<?> clz : list) {
                logger.info("create class table :" + clz);
                try {
                    TableUtils.createTable(connectionSource, clz);
                } catch (Exception e) {
                    logger.error("create class table :" + clz, e);
                }
            }
        }
        if ("update".equalsIgnoreCase(createType)) {
            for (Class<?> clz : list) {
                logger.info("create class table if not exists :" + clz + " table!");
                try {
                    TableUtils.createTableIfNotExists(connectionSource, clz);
                } catch (Exception e) {
                    logger.error("create class table if not exists :" + clz + " table!", e);
                }
            }
        }
    }

    private List<Class<Object>> corverToList(Set<Class<Object>> createTables) {
        List<Class<Object>> list = new ArrayList<Class<Object>>(createTables);
        Collections.sort(list, new Comparator<Class<Object>>() {
            @Override
            public int compare(Class<Object> c1, Class<Object> c2) {
                TableOrder o1 = AnnotationUtils.findAnnotation(c1, TableOrder.class);
                TableOrder o2 = AnnotationUtils.findAnnotation(c2, TableOrder.class);
                int i1 = o1 == null ? Integer.MAX_VALUE : o1.value();
                int i2 = o2 == null ? Integer.MAX_VALUE : o2.value();
                return i1 - i2;
            }
        });
        return list;
    }

    private void doDrop() throws SQLException {
        for (Class<?> clz : corverToList(createTables)) {
            logger.info("drop class table :" + clz);
            try {
                TableUtils.dropTable(connectionSource, clz, true);
            } catch (Exception e) {
                logger.error("drop class table :" + clz, e);
            }
        }
    }

    @PreDestroy
    public void maybeDropTable() throws SQLException {
        if ("create-drop".equalsIgnoreCase(createType)) {
            doDrop();
        }
        createTables.clear();
        createTables = null;
    }
}

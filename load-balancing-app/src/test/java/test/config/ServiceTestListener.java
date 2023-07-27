package test.config;

import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

import javax.sql.DataSource;

public class ServiceTestListener implements TestExecutionListener {

    @Override
    public void prepareTestInstance(TestContext testContext) {
        resetDatabase(testContext.getApplicationContext());
    }

    private void resetDatabase(ApplicationContext applicationContext) {
        var dataSource = applicationContext.getBean(DataSource.class);
        new ResourceDatabasePopulator(false, false, "UTF-8", new ClassPathResource("reset-db.sql"))
                .execute(dataSource);
    }
}

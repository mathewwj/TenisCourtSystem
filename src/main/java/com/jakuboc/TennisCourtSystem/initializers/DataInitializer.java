package com.jakuboc.TennisCourtSystem.initializers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class DataInitializer implements ApplicationRunner {

    private final DataSource dataSource;
    private final Resource dataScript;
    private final boolean dataInitEnabled;

    @Autowired
    public DataInitializer(DataSource dataSource,
                           @Value("classpath:init.sql") Resource dataScript,
                           @Value("${data.init.enabled}") boolean dataInitEnabled) {
        this.dataSource = dataSource;
        this.dataScript = dataScript;
        this.dataInitEnabled = dataInitEnabled;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (dataInitEnabled) {
            executeDataScript();
        }
    }

    private void executeDataScript() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(connection, dataScript);
        }
    }
}

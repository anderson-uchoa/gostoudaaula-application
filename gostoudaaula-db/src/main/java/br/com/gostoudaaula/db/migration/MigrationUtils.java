package br.com.gostoudaaula.db.migration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class MigrationUtils {

	public void update() throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream("flyway.properties"));
	}
}

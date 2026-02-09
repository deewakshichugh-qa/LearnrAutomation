package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import logger.ScriptLogger;
import objects.exceptions.ScriptException;

public class ObjectMapHelper {

	public static List<Class<?>> OBJECTMAP_LIST = new ArrayList<Class<?>>();
	public static final String OBJECTMAP_PACKAGE = "com.adda247.testing.objectmap";

	public static void loadObjectMaps() throws Exception {

		ScriptLogger.info();
		// ScriptLogger.debug();
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			String resourceLocation = classLoader.getResource(OBJECTMAP_PACKAGE.replace(".", "/")).toString();
			ScriptLogger.debug(resourceLocation);
			if (resourceLocation.contains(".jar")) {
				unzipAndLoadTheFiles();
			} else {
				loadTheFiles();
			}

		} catch (Exception e) {
			throw new ScriptException(e);
		}
	}

	public static void loadTheFiles() throws Exception {

		ScriptLogger.info();
		// ScriptLogger.debug();
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			URL objectMapURL = classLoader.getResource(OBJECTMAP_PACKAGE.replace(".", "/"));
			URI objectMapURI = objectMapURL.toURI();
			File directoryObj = new File(objectMapURI);
			if (directoryObj.isDirectory()) {
				File[] objectMapFiles = directoryObj.listFiles();
				for (File objectMapClassFile : objectMapFiles) {
					if (!objectMapClassFile.getName().contains("tfs")) {
						String className = objectMapClassFile.getName().split("\\.")[0];
						Class<?> objectMapClass = Class.forName(OBJECTMAP_PACKAGE + "." + className);
						ScriptLogger.debug(objectMapClass.getName());
						OBJECTMAP_LIST.add(objectMapClass);
					}
				}
			}
		} catch (Exception e) {
			throw new ScriptException(e);
		}
	}

	public static void unzipAndLoadTheFiles() throws Exception {
		ZipInputStream zipObj = null;
		ScriptLogger.info();
		ScriptLogger.debug();
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

			String resourcePath = classLoader.getResource(OBJECTMAP_PACKAGE.replace(".", "/")).toString();
			String jarfileName = resourcePath.split("!")[0];
			String jarFilePath = jarfileName.replaceFirst("jar:file:/", "").trim();
			ScriptLogger.debug(jarFilePath);

			zipObj = new ZipInputStream(new FileInputStream(jarFilePath));
			for (ZipEntry zipentry = zipObj.getNextEntry(); zipentry != null; zipentry = zipObj.getNextEntry()) {
				String name = zipentry.getName();
				if (name.contains(OBJECTMAP_PACKAGE.replace(".", "/")) && name.endsWith(".class")) {
					String className = name.split("\\.")[0].replace("/", ".");
					ScriptLogger.debug(className);
					Class<?> objectMapClass = Class.forName(className);
					OBJECTMAP_LIST.add(objectMapClass);
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (zipObj != null) {
				zipObj.close();
			}
		}

	}

	public static Class<?> findOjectMapClass(String fieldName) throws Exception {

		Class<?> objectMapClass = null;
		try {

			for (Class<?> iface : OBJECTMAP_LIST) {
				if (lookForField(iface, fieldName)) {
					objectMapClass = iface;
				}
			}
		} catch (Exception e) {
			throw new ScriptException(e);
		}

		return objectMapClass;
	}

	public static boolean lookForField(Class<?> iface, String fieldName) throws Exception {

		boolean isFieldPresent = false;
		try {
			Field[] fields = iface.getFields();
			for (Field field : fields) {
				if (field.getName().equalsIgnoreCase(fieldName)) {
					isFieldPresent = true;
					break;
				}
			}

		} catch (Exception e) {
			throw new ScriptException(e);
		}

		return isFieldPresent;
	}

	public static String getLocatorName(Object locator) {
		String retVal = "";
		if (locator instanceof Enum<?>) {
			retVal = ((Enum<?>) locator).name();
		} else {
			retVal = (String) locator;
		}
		return retVal;
	}

}

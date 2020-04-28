package kcc.utils;

import java.io.File;

public class Misc {

	public static File getJarContainingFolder() {
		return new File(ClassLoader.getSystemClassLoader().getResource(".").getPath().replaceAll("%20", " "));
	}
}
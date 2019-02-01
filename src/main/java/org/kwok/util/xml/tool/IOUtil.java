package org.kwok.util.xml.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * inputStreamToString
 * @author Kwok
 */
public class IOUtil {

	public static String inputStreamToString(InputStream inputStream) {
		
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		StringBuilder result = new StringBuilder();
		String line;
		try {
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line);
			}
			return result.toString();
		} catch (IOException e1) {
		} finally {
			try {
				bufferedReader.close();
				inputStreamReader.close();
			} catch (IOException e2) {}
		}
		return null;
	}
	
}

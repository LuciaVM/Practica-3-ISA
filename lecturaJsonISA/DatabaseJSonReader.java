package lecturaJsonISA;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.stream.JsonReader;

/**
 * Created by jmalvarez on 11/5/16.
 * Modified by Lucia Valverde Martinez
 * http://developer.android.com/intl/es/training/basics/network-ops/xml.html
 */
public class DatabaseJSonReader {
	
	public DatabaseJSonReader() {
	}

	public String parse(String jsonFileName, ElementoCadenaMando cadena) throws IOException {

		InputStream usersIS = new FileInputStream(new File(jsonFileName));
		JsonReader reader = new JsonReader(new InputStreamReader(usersIS, "UTF-8"));

		reader.beginObject();
		StringBuffer readData = new StringBuffer();
		while (reader.hasNext()) {
			String name = reader.nextName();
			cadena.read(name, readData, reader);	
		}

		reader.endObject();
		reader.close();
		usersIS.close();

		return new String(readData);
	}
	
}

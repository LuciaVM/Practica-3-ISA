package lecturaJsonISA;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class InhalerReader extends ElementoCadenaMando {

	private static final String INHALERS_TAGNAME = "inhalers";
	private static final String NAME_FIELD_TAGN_INHALERS = "name";
	private static final String IMAGE_FIELD_TAGN_INHALERS = "image";

	private static final String FIELD_SEPARATOR = "; ";

	public InhalerReader(ElementoCadenaMando e) {
		super(e);
	}

	protected StringBuffer read(String name, StringBuffer buffer, JsonReader reader) {
		StringBuffer lectura;
		if (name.equals(INHALERS_TAGNAME)) {
			try {
				buffer.append(super.readCategoriaGenerica(reader)).append("\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			lectura = buffer;
		} else {
			if (this.siguienteElemento != null) {
				lectura = super.read(name, buffer, reader);
			} else {
				lectura = buffer;
				System.err.println("Category " + name + " not processed.");
			}
		} return lectura;
	}


	// Parses the contents of a inhaler entry
	protected String plantillaReadEntry(JsonReader reader) {
		String inhName = null;
		String inhImage = null;
		try {
			while (reader.hasNext()) {
				String name = reader.nextName();
				if (name.equals(NAME_FIELD_TAGN_INHALERS)) {
					inhName = reader.nextString();
				} else if (name.equals(IMAGE_FIELD_TAGN_INHALERS)) {
					inhImage = reader.nextString();
				} else {
					reader.skipValue();
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		return inhName + FIELD_SEPARATOR + inhImage;
	}
}

package lecturaJsonISA;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class PosologyReader extends ElementoCadenaMando {

	private static final String POSOLOGIES_TAGNAME = "posologies";
	private static final String DESC_FIELD_TAGN_POSOLOGIES = "description";

	public PosologyReader(ElementoCadenaMando e) {
		super(e);
	}

	protected StringBuffer read(String name, StringBuffer buffer, JsonReader reader) {
		StringBuffer lectura;
		if (name.equals(POSOLOGIES_TAGNAME)) {
			try {
				buffer.append(super.readCategoriaGenerica(reader)).append("\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
		}
		return lectura;
	}

	// Parses the contents of a posology entry.
	protected String plantillaReadEntry(JsonReader reader)  {
		// reader.require(XmlPullParser.START_TAG, ns, SINGLE_ELEMENT_TAGNAME);
		String posDesc = null;
		try {
			while (reader.hasNext()) {
				String name = reader.nextName();
				if (name.equals(DESC_FIELD_TAGN_POSOLOGIES)) {
					posDesc = reader.nextString();
				} else {
					reader.skipValue();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return posDesc;
	}
}

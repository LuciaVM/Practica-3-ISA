package lecturaJsonISA;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class MedicineReader extends ElementoCadenaMando {
	private static final String MEDICINES_TAGNAME = "medicines";
	private static final String NAME_FIELD_TAGNAME = "name";

	public MedicineReader(ElementoCadenaMando e) {
		super(e);
	}

	protected StringBuffer read(String name, StringBuffer buffer, JsonReader reader) {
		StringBuffer lectura;
		if (name.equals(MEDICINES_TAGNAME)) {
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


	// Parses the contents of a medicine.
	protected String plantillaReadEntry(JsonReader reader) {
		// reader.require(XmlPullParser.START_TAG, ns, SINGLE_ELEMENT_TAGNAME);
		String medName = null;
		try {
			while (reader.hasNext()) {
				String name = reader.nextName();
				if (name.equals(NAME_FIELD_TAGNAME)) {
					medName = reader.nextString();
				} else {
					reader.skipValue();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return medName;
	}

}

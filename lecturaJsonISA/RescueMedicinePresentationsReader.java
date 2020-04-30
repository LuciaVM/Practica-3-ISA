package lecturaJsonISA;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class RescueMedicinePresentationsReader extends ElementoCadenaMando {

	private static final String RESCUEMEDPRES_TAGNAME = "rescueMedicinePresentations";
	private static final String MEDREF_FIELD_TAGNAME = "medicineRef";
	private static final String ACTINGREF_FIELD_TAGNAME = "activeIngRef";
	private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
	private static final String DOSE_FIELD_TAGNAME = "dose";

	private static final String FIELD_SEPARATOR = "; ";

	public RescueMedicinePresentationsReader(ElementoCadenaMando e) {
		super(e);
	}

	protected StringBuffer read(String name, StringBuffer buffer, JsonReader reader) {
		StringBuffer lectura;
		if (name.equals(RESCUEMEDPRES_TAGNAME)) {
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

	// Parses the contents of a rescue medicine presentation entry
	protected String plantillaReadEntry(JsonReader reader) {
		String medRef = null;
		String aiRef = null;
		String inhRef = null;
		String dose = null;
		try {
			while (reader.hasNext()) {
				String name = reader.nextName();
				if (name.equals(MEDREF_FIELD_TAGNAME)) {
					medRef = reader.nextString();
				} else if (name.equals(ACTINGREF_FIELD_TAGNAME)) {
					aiRef = reader.nextString();
				} else if (name.equals(INHREF_FIELD_TAGNAME)) {
					StringBuffer res = new StringBuffer();
					reader.beginArray();
					while (reader.hasNext()) {
						res.append(reader.nextString()).append(",");
					}
					reader.endArray();
					res.deleteCharAt(res.length() - 1);
					inhRef = new String(res);
				} else if (name.equals(DOSE_FIELD_TAGNAME)) {
					StringBuffer res = new StringBuffer();
					reader.beginArray();
					while (reader.hasNext()) {
						res.append(reader.nextString()).append(",");
					}
					reader.endArray();
					res.deleteCharAt(res.length() - 1);
					dose = new String(res);
				} else {
					reader.skipValue();
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return medRef + FIELD_SEPARATOR + aiRef + FIELD_SEPARATOR + inhRef + FIELD_SEPARATOR + dose;
	}
}

package lecturaJsonISA;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class MedicinePresentationReader extends ElementoCadenaMando {

	// MedicinePresentations
	private static final String MEDICINEPRESENT_TAGNAME = "medicinePresentations";
	private static final String MEDREF_FIELD_TAG_MEDICINEPRESENT = "medicineRef";
	private static final String ACTIVE_FIELD_TAG_MEDICINEPRESENT = "activeIngRef";
	private static final String INHREF_FIELD_TAG_MEDICINEPRESENT = "inhalerRef";
	private static final String DOSE_FIELD_TAG_MEDICINEPRESENT = "dose";
	private static final String POS_FIELD_TAG_MEDICINEPRESENT = "posologyRef";

	private static final String FIELD_SEPARATOR = "; ";

	public MedicinePresentationReader(ElementoCadenaMando e) {
		super(e);
	}

	protected StringBuffer read(String name, StringBuffer buffer, JsonReader reader) {
		StringBuffer lectura;
		if (name.equals(MEDICINEPRESENT_TAGNAME)) {
			try {
				buffer.append(super.readCategoriaGenerica(reader)).append("\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			lectura = buffer;
		}  else {
			if (this.siguienteElemento != null) {
				lectura = super.read(name, buffer, reader);
			} else {
				lectura = buffer;
				System.err.println("Category " + name + " not processed.");
			}
		} return lectura;
	}

	// Parses the contents of a medicine presentation entry
	protected String plantillaReadEntry(JsonReader reader) {
		String medRef = null;
		String aiRef = null;
		String inhRef = null;
		String dose = null;
		String pose = null;
		try {
			while (reader.hasNext()) {
				String name = reader.nextName();
				if (name.equals(MEDREF_FIELD_TAG_MEDICINEPRESENT)) {
					medRef = reader.nextString();
				} else if (name.equals(ACTIVE_FIELD_TAG_MEDICINEPRESENT)) {
					aiRef = reader.nextString();
				} else if (name.equals(INHREF_FIELD_TAG_MEDICINEPRESENT)) {
					StringBuffer res = new StringBuffer();
					reader.beginArray();
					while (reader.hasNext()) {
						res.append(reader.nextString()).append(",");
					}
					reader.endArray();
					res.deleteCharAt(res.length() - 1);
					inhRef = new String(res);
				} else if (name.equals(DOSE_FIELD_TAG_MEDICINEPRESENT)) {
					StringBuffer res = new StringBuffer();
					reader.beginArray();
					while (reader.hasNext()) {
						res.append(reader.nextString()).append(",");
					}
					reader.endArray();
					res.deleteCharAt(res.length() - 1);
					dose = new String(res);
				} else if (name.equals(POS_FIELD_TAG_MEDICINEPRESENT)) {
					StringBuffer res = new StringBuffer();
					reader.beginArray();
					while (reader.hasNext()) {
						res.append(reader.nextString()).append(",");
					}
					reader.endArray();
					res.deleteCharAt(res.length() - 1);
					pose = new String(res);
				} else {
					reader.skipValue();
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return medRef + FIELD_SEPARATOR + aiRef + FIELD_SEPARATOR + inhRef + FIELD_SEPARATOR + dose + FIELD_SEPARATOR
				+ pose;
	}

}

package lecturaJsonISA;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class UserManualPhysioStepsReader extends ElementoCadenaMando {

	private static final String USERMANPHY_TAGNAME = "userManualPhysioSteps";
	private static final String TITLE_FIELD_TAGN_USERMANPHY = "stepTitle";
	private static final String IMAGE_FIELD_TAGN_USERMANPHY = "stepImage";
	private static final String TEXT_FIELD_TAGN_USERMANPHY = "stepText";
	private static final String REF_FIELD_TAGN_USERMANPHY = "physioRef";

	private static final String FIELD_SEPARATOR = "; ";

	public UserManualPhysioStepsReader(ElementoCadenaMando e) {
		super(e);
	}

	protected StringBuffer read(String name, StringBuffer buffer, JsonReader reader) {
		StringBuffer lectura;
		if (name.equals(USERMANPHY_TAGNAME)) {
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

	// Parses the contents of a user manual physiostep entry
	protected String plantillaReadEntry(JsonReader reader) {
		String title = null;
		String image = null;
		String text = null;
		String ref = null;
		try {
			while (reader.hasNext()) {
				String name = reader.nextName();
				if (name.equals(TITLE_FIELD_TAGN_USERMANPHY)) {
					title = reader.nextString();
				} else if (name.equals(IMAGE_FIELD_TAGN_USERMANPHY)) {
					image = reader.nextString();
				} else if (name.equals(TEXT_FIELD_TAGN_USERMANPHY)) {
					text = reader.nextString();
				} else if (name.equals(REF_FIELD_TAGN_USERMANPHY)) {
					ref = reader.nextString();
				} else {
					reader.skipValue();
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return title + FIELD_SEPARATOR + image + FIELD_SEPARATOR + text + FIELD_SEPARATOR + ref;
	}

}

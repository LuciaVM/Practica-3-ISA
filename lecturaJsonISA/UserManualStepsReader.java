package lecturaJsonISA;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class UserManualStepsReader extends ElementoCadenaMando {

	private static final String USERMANSTEPS_TAGNAME = "userManualSteps";
	private static final String TITLE_FIELD_TAGN_USERMANSTEPS = "stepTitle";
	private static final String IMAGE_FIELD_TAGN_USERMANSTEPS = "stepImage";
	private static final String TEXT_FIELD_TAGN_USERMANSTEPS = "stepText";
	private static final String REF_FIELD_TAGN_USERMANSTEPS = "inhalerRef";

	private static final String FIELD_SEPARATOR = "; ";

	public UserManualStepsReader(ElementoCadenaMando e) {
		super(e);
	}

	protected StringBuffer read(String name, StringBuffer buffer, JsonReader reader) {
		StringBuffer lectura;
		if (name.equals(USERMANSTEPS_TAGNAME)) {
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
	
	// Parses the contents of a user manual step entry
	protected String plantillaReadEntry(JsonReader reader) {
		String title = null;
		String image = null;
		String text = null;
		String ref = null;
		try {
			while (reader.hasNext()) {
				String name = reader.nextName();
				if (name.equals(TITLE_FIELD_TAGN_USERMANSTEPS)) {
					title = reader.nextString();
				} else if (name.equals(IMAGE_FIELD_TAGN_USERMANSTEPS)) {
					image = reader.nextString();
				} else if (name.equals(TEXT_FIELD_TAGN_USERMANSTEPS)) {
					text = reader.nextString();
				} else if (name.equals(REF_FIELD_TAGN_USERMANSTEPS)) {
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

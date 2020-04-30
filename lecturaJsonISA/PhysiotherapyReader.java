package lecturaJsonISA;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class PhysiotherapyReader extends ElementoCadenaMando {

	private static final String PHYSIOTHERAPIES_TAGNAME = "physiotherapies";
	private static final String NAME_FIELD_TAGN_PHYSIOTHERAPIES = "name";
	private static final String IMAGE_FIELD_TAGN_PHYSIOTHERAPIES = "image";

	private static final String FIELD_SEPARATOR = "; ";
	
	public PhysiotherapyReader(ElementoCadenaMando e) {
		super(e);
	}

	protected StringBuffer read(String name, StringBuffer buffer, JsonReader reader) {
		StringBuffer lectura;
		if (name.equals(PHYSIOTHERAPIES_TAGNAME)) {
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


	// Parses the contents of a physiotherapy entry
	protected String plantillaReadEntry(JsonReader reader) {
		String phyName = null;
		String phyImage = null;
		try {
			while (reader.hasNext()) {
				String name = reader.nextName();
				if (name.equals(NAME_FIELD_TAGN_PHYSIOTHERAPIES)) {
					phyName = reader.nextString();
				} else if (name.equals(IMAGE_FIELD_TAGN_PHYSIOTHERAPIES)) {
					phyImage = reader.nextString();
				} else {
					reader.skipValue();
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phyName + FIELD_SEPARATOR + phyImage;
	}

	
}

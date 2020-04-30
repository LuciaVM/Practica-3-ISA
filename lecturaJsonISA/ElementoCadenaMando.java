package lecturaJsonISA;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public abstract class ElementoCadenaMando {
	protected ElementoCadenaMando siguienteElemento;
	
	public ElementoCadenaMando(ElementoCadenaMando e) {
		siguienteElemento = e;
	}
	
	protected StringBuffer read(String name, StringBuffer buffer, JsonReader reader) {
		return this.siguienteElemento.read(name, buffer, reader);
	}
	
	protected StringBuffer readCategoriaGenerica(JsonReader reader) throws IOException {
		StringBuffer datosCatGenerica = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			datosCatGenerica.append(plantillaReadEntry(reader)).append("\n");
			reader.endObject();
		}
		datosCatGenerica.append("\n");
		reader.endArray();
		return datosCatGenerica;
	}
	protected abstract String plantillaReadEntry(JsonReader reader);
}

package lecturaJsonISA;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GsonDatabaseClient {

	public static void main(String[] args) {
		try{
			//DatabaseJSonReader dbjp = new DatabaseJSonReader();
			
			// Creamos la cadena de mando
			
			ElementoCadenaMando primero = new UserManualPhysioStepsReader(null);
			ElementoCadenaMando segundo = new MedicineReader(primero);
			ElementoCadenaMando tercero = new ActiveIngredientsReader(segundo);
			ElementoCadenaMando cuarto = new PhysiotherapyReader(tercero);
			ElementoCadenaMando quinto = new InhalerReader(cuarto);
			ElementoCadenaMando sexto = new PosologyReader(quinto);
			ElementoCadenaMando septimo = new MedicinePresentationReader(sexto);
			ElementoCadenaMando octavo = new RescueMedicinePresentationsReader(septimo);
			ElementoCadenaMando cadenaCompleta = new UserManualStepsReader(octavo);
			
			DatabaseJSonReader dbjp = new DatabaseJSonReader();

			try {
				System.out.println(dbjp.parse("./datos.json", cadenaCompleta));
			} finally {
			}
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}

	}

}

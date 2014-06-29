package ve.unefa.labControl.gui.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat altSDF = new SimpleDateFormat("dd-MM-yyyy");
	private static final SimpleDateFormat hourSDF = new SimpleDateFormat("HH:mm");

	/**
	 * Dado un String que describe un número de teléfono, chequea si este cumple
	 * con la forma xxx-xxxx
	 * @param phoneNumber El número a comparar
	 * @return true si el número coincide con la forma descrita
	 */
	public static boolean validatePhone(String phoneNumber) {
		if(phoneNumber == null)
			return false;
		return phoneNumber.matches("\\d{3}[-]\\d{4}");
	}
	
	/**
	 * Dado un token, esta funcion devuelve String que comprende desde la ultima aparicion
	 * de token hasta el final del String str, sin incluir token 
	 * @param str String a extraer la ultima parte
	 * @param token String a evaluar como token
	 * @return la ultima parte de str desde la ultima aparicion de token. 
	 * Si str es null entonces se devuelve null. Si token es null, se devuelve str.
	 */
	public static String getLastPart(String str, String token) {
		if(str == null || token == null || token.length() == 0)
			return str;
		String substr = str.substring(str.lastIndexOf(token) + token.length());
		
		return substr;
	}

	/**
	 * Dado un String, devuelve un Date si es parseable por Java
	 * @param value El String a convertir
	 * @return Date si el parámetro puede ser parseado a una fecha. Sino devuelve null.
	 */
	public static Date validateDate(String value) {
		try {
			return SDF.parse(value);
		} catch(Exception e) {
			try {
				return altSDF.parse(value);
			} catch(Exception e1) {
				return null;
			}
		}
	}

	public static String getStringDateFor(Date date) {
		return SDF.format(date);
	}
	
	public static String getStringHourFor(Date date) {
		return hourSDF.format(date);
	}
}

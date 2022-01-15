package Negocio;

import java.io.*;
import javax.swing.JOptionPane;

public class lib {

	static int atrContador = 0;
	static String atrMostrar = "";
	static Arbol<Integer> atrArbolInt = new Arbol<Integer>();
	static Arbol<String> atrArbolString = new Arbol<String>();
	static String[][] atrMatriz;
	static boolean atrFlag = true;

	// Lee el archivo y retorna un vector de strings con cada linea
	private static String[] LeerArchivo(String prmPath) throws FileNotFoundException, IOException {
		String[] varText = new String[2];
		BufferedReader varBuffer = new BufferedReader(new FileReader(prmPath));
		for (int i = 0; i < 2; i++) {
			varText[i] = varBuffer.readLine();
		}
		varBuffer.close();
		return varText;
	}

	public static String[] leerArchivo(String prmPath) {
		String[] vecTexto = new String[2];
		try {
			vecTexto = LeerArchivo(prmPath);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "No se encontro el archivo", "Archivo", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Algo fallo", "Archivo", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return vecTexto;
	}

	// Comprobar si es un numero (Metodo recursivo)
	public static boolean isNumber(String[] prmVector) {
		return isNumber(prmVector, 0);
	}

	private static boolean isNumber(String[] prmVector, int prmPos) {
		if (prmPos == prmVector.length - 1) {
			try {
				Integer.parseInt(prmVector[prmPos]);
				return true;
			} catch (Exception e) {
				return false;
			}
		} else {
			try {
				Integer.parseInt(prmVector[prmPos]);
				return isNumber(prmVector, prmPos + 1);
			} catch (Exception e) {
				return false;
			}
		}
	}

	// Comprobar si es una letra
	public static boolean isLetters(String[] prmVector) {
		for (String varElemento : prmVector) {
			for (int i = 0; i < varElemento.length(); i++) {
				char varChar = varElemento.charAt(i);
				if (!((varChar >= 'a' && varChar <= 'z') || (varChar >= 'A' && varChar <= 'Z') || varChar == ' ')) {
					return false;
				}
			}
		}
		return true;
	}

	// Convertir de String[] to int[]
	public static int[] toInt(String[] prmString) {
		int[] vecInt = new int[prmString.length];
		for (int i = 0; i < prmString.length; i++) {
			try {
				vecInt[i] = Integer.parseInt(prmString[i]);
			} catch (Exception e) {
				return null;
			}
		}
		return vecInt;
	}

	// Comprueba todos los datos en conjunto, retorna el tipo
	public static int validarDatos(String prmPath) {
		String[] vecTexto = leerArchivo(prmPath);
		String[] vecInsertar = vecTexto[1].split(" ");

		if (vecTexto[0].compareTo("1") == 0) {
			if (isNumber(vecInsertar)) {
				return 1;
			} else {
				JOptionPane.showMessageDialog(null, "Los valores a insertar no son numeros", "Archivo de texto",
						JOptionPane.ERROR_MESSAGE);
				return -1;
			}
		} else if (vecTexto[0].compareTo("0") == 0) {
			if (isLetters(vecInsertar)) {
				return 0;
			} else {
				JOptionPane.showMessageDialog(null, "Los valores a insertar no son letras", "Archivo de texto",
						JOptionPane.ERROR_MESSAGE);
				return -1;
			}
		} else {
			JOptionPane.showMessageDialog(null, "No se detecto correctamente el tipo", "Archivo de texto",
					JOptionPane.ERROR_MESSAGE);
			return -1;
		}
	}

	// Metodo para crear el Arbol
	public static int crearArbol(String prmPath) {
		String[] vecTexto = leerArchivo(prmPath);
		int varTipo = validarDatos(prmPath);
		if (varTipo == -1) {
			JOptionPane.showMessageDialog(null, "No se puede crear el arbol", "Arbol", JOptionPane.ERROR_MESSAGE);
			return -1;
		} else if (varTipo == 1) {
			for (int varElemento : toInt(vecTexto[1].split(" "))) {
				atrArbolInt.Insertar(varElemento);
			}
			return 1;
		} else {
			for (String varElemento : vecTexto[1].split(" ")) {
				atrArbolString.Insertar(varElemento);
			}
			return 0;
		}
	}

	public static void cleanArbolInt() {
		atrArbolInt.setRaiz(null);
	}

	public static void cleanArbolString() {
		atrArbolString.setRaiz(null);
	}

	// Getters
	public static Arbol<Integer> getAtrArbolInt() {
		return atrArbolInt;
	}

	public static Arbol<String> getAtrArbolString() {
		return atrArbolString;
	}
}

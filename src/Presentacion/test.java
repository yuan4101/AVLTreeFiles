package Presentacion;

import Negocio.*;
import javax.swing.JOptionPane;

public class test {

	public static void main(String[] args) {
		int varOpcion = 0;
		do {
			Arbol<?> varArbol = new Arbol<>();
			try {
				varOpcion = Integer.parseInt(JOptionPane.showInputDialog(null,
						"1. Ejemplo 1\n2. Ejemplo 2\n3. Ejemplo 3\n4. Salir\nIngrese una opcion...", "Menu principal",
						JOptionPane.QUESTION_MESSAGE));
				switch (varOpcion) {
				case 1: {
					lib.crearArbol("Ejemplo1.txt");
					varArbol = lib.getAtrArbolInt();
					break;
				}
				case 2: {
					lib.crearArbol("Ejemplo2.txt");
					varArbol = lib.getAtrArbolString();
					break;
				}
				case 3: {
					lib.crearArbol("Ejemplo3.txt");
					varArbol = lib.getAtrArbolInt();
					break;
				}
				case 4: {
					JOptionPane.showMessageDialog(null, "Saliendo...", "Menu Principal", JOptionPane.ERROR_MESSAGE);
					break;
				}
				default:
					JOptionPane.showMessageDialog(null, "Opcion no valida", "Menu Principal",
							JOptionPane.ERROR_MESSAGE);
					varOpcion = 0;
				}
				if (varOpcion != 4 && varOpcion != 0) {
					JOptionPane.showMessageDialog(null, varArbol.imprimirArbolNiveles(), "Imprimir por niveles",
							JOptionPane.INFORMATION_MESSAGE);
				}
				lib.cleanArbolInt();
				lib.cleanArbolString();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} while (varOpcion != 4);
	}

	/*
	 * if (varTipo == 1) { varArbol = lib.getAtrArbolInt(); } else if (varTipo == 0)
	 * { varArbol = lib.getAtrArbolString(); } else {
	 * JOptionPane.showMessageDialog(null, "ERROR", "Main",
	 * JOptionPane.ERROR_MESSAGE); }
	 */
}
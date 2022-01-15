package Negocio;

public class Arbol<T> {
	private Nodo<T> atrRaiz;
	private String atrArbol = "";

	public Arbol() {
		this.atrRaiz = null;
	}

	public Nodo<T> getRaiz() {
		return atrRaiz;
	}

	public void setRaiz(Nodo<T> raiz) {
		this.atrRaiz = raiz;
	}

	public void Insertar(Comparable<T> prmElemento) {
		atrRaiz = insertar(atrRaiz, prmElemento);
	}
	@SuppressWarnings("unchecked")
	private Nodo<T> insertar(Nodo<T> prmArbol, Comparable<T> prmElemento){
		if (prmArbol == null) {
			prmArbol = new Nodo<T>((T) prmElemento, null, null);
		} else if (prmElemento.compareTo(prmArbol.getElemento()) < 0) {
			prmArbol.setIzquierda(insertar(prmArbol.getIzquierda(), prmElemento));
			if (factorEquilibrio(prmArbol.getDerecha()) - factorEquilibrio(prmArbol.getIzquierda()) == -2)
				if (prmElemento.compareTo(prmArbol.getIzquierda().getElemento()) < 0)
					prmArbol = rotacionSimpleDer(prmArbol);
				else
					prmArbol = rotacionDobleIzq_Der(prmArbol);
		} else if (prmElemento.compareTo(prmArbol.getElemento()) > 0) {
			prmArbol.setDerecha(insertar(prmArbol.getDerecha(), prmElemento));
			if (factorEquilibrio(prmArbol.getDerecha()) - factorEquilibrio(prmArbol.getIzquierda()) == 2)
				if (prmElemento.compareTo(prmArbol.getDerecha().getElemento()) > 0)
					prmArbol = rotacionSimpleIzq(prmArbol);
				else
					prmArbol = rotacionDobleDer_Izq(prmArbol);
		} else {
			prmArbol.setDerecha(insertar(prmArbol.getDerecha(), prmElemento));
			if (factorEquilibrio(prmArbol.getDerecha()) - factorEquilibrio(prmArbol.getIzquierda()) == 2)
				if (prmElemento.compareTo(prmArbol.getDerecha().getElemento()) > 0)
					prmArbol = rotacionSimpleIzq(prmArbol);
				else
					prmArbol = rotacionDobleDer_Izq(prmArbol);
		}
		prmArbol.setFactorEquilibrio(mayorFactorEquilibrio(factorEquilibrio(prmArbol.getIzquierda()), factorEquilibrio(prmArbol.getDerecha())));
		return prmArbol;
	}
	
	private Nodo<T> rotacionSimpleIzq(Nodo<T> prmArbol){
		Nodo<T> subArbol = prmArbol.getDerecha();
		prmArbol.setDerecha(subArbol.getIzquierda());
		subArbol.setIzquierda(prmArbol);
		prmArbol.setFactorEquilibrio(mayorFactorEquilibrio(factorEquilibrio(prmArbol.getIzquierda()), factorEquilibrio(prmArbol.getDerecha())));
		subArbol.setFactorEquilibrio(mayorFactorEquilibrio(factorEquilibrio(subArbol.getDerecha()), prmArbol.getFactorEquilibrio()));
		return subArbol;
	}
	
	private Nodo<T> rotacionSimpleDer(Nodo<T> prmArbol){
		Nodo<T> subArbol = prmArbol.getIzquierda();
		prmArbol.setIzquierda(subArbol.getDerecha());
		subArbol.setDerecha(prmArbol);
		prmArbol.setFactorEquilibrio(mayorFactorEquilibrio(factorEquilibrio(prmArbol.getIzquierda()), factorEquilibrio(prmArbol.getDerecha())));
		subArbol.setFactorEquilibrio(mayorFactorEquilibrio(factorEquilibrio(prmArbol.getIzquierda()), prmArbol.getFactorEquilibrio()));
		return subArbol;
	}

	private Nodo<T> rotacionDobleIzq_Der(Nodo<T> prmArbol){
		prmArbol.setIzquierda(rotacionSimpleIzq(prmArbol.getIzquierda()));
		return rotacionSimpleDer(prmArbol);
	}
	
	private Nodo<T> rotacionDobleDer_Izq(Nodo<T> prmArbol){
		prmArbol.setDerecha(rotacionSimpleDer(prmArbol.getDerecha()));
		return rotacionSimpleIzq(prmArbol);
	}
	
	public int factorEquilibrio(Nodo<T> prmArbol) {
		if (prmArbol == null)
			return 0;
		else
			return (prmArbol.getFactorEquilibrio() + 1);
	}
	
	private int mayorFactorEquilibrio(int alturaIzquierdo, int alturaDerecho) {
		if (alturaIzquierdo > alturaDerecho)
			return alturaIzquierdo;
		else
			return alturaDerecho;
	}
	
	public String imprimirArbolNiveles() {
		atrArbol = "";
		imprimirArbolNiveles(atrRaiz, 0);
		return atrArbol;
	}

	private void imprimirArbolNiveles(Nodo<T> prmArbol, int prmNivel) {
		if(prmArbol != null) {
			imprimirArbolNiveles(prmArbol.getIzquierda(), prmNivel + 1);
			atrArbol += " " + prmNivel + "   " + prmArbol.getElemento() + "\n";
			//System.out.println(" " + prmNivel + "   " + prmArbol.getElemento());
			imprimirArbolNiveles(prmArbol.getDerecha(), prmNivel + 1);
		}
	}
}
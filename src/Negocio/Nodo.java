package Negocio;

public class Nodo<T> implements Comparable<T> {
	private T atrElemento;
	private Nodo<T> atrIzquierda, atrDerecha;
	private int factorEquilibrio;

	public Nodo(T prmElemento) {
		this(prmElemento, null, null);
	}

	public Nodo(T prmElemento, Nodo<T> prmNodoIzquierdo, Nodo<T> prmNodoDerecho) {
		this.atrElemento = prmElemento;
		this.atrIzquierda = prmNodoIzquierdo;
		this.atrDerecha = prmNodoDerecho;
		this.factorEquilibrio = 0;
	}

	public T getElemento() {
		return atrElemento;
	}

	public void setElemento(T atrElemento) {
		this.atrElemento = atrElemento;
	}

	public Nodo<T> getIzquierda() {
		return atrIzquierda;
	}

	public void setIzquierda(Nodo<T> atrIzquierda) {
		this.atrIzquierda = atrIzquierda;
	}

	public Nodo<T> getDerecha() {
		return atrDerecha;
	}

	public void setDerecha(Nodo<T> atrDerecha) {
		this.atrDerecha = atrDerecha;
	}
	
	public int getFactorEquilibrio() {
		return factorEquilibrio;
	}

	public void setFactorEquilibrio(int factorEquilibrio) {
		this.factorEquilibrio = factorEquilibrio;
	}

	@SuppressWarnings("unchecked")
	public Nodo<T> insertarNodo(Nodo<T> prmNodo, Comparable<T> prmElemento) {
		if (prmNodo == null) {
			return new Nodo<T>((T) prmElemento);
		} else if (prmElemento.compareTo(prmNodo.getElemento()) < 0) {
			prmNodo.atrIzquierda = insertarNodo(prmNodo.getIzquierda(), prmElemento);
		} else if (prmElemento.compareTo(prmNodo.getElemento()) > 0) {
			prmNodo.atrDerecha = insertarNodo(prmNodo.getDerecha(), prmElemento);
		} else {
			prmNodo.atrDerecha = insertarNodo(prmNodo.getDerecha(), prmElemento);
		}
		return prmNodo;
	}

	@Override
	public int compareTo(T prmNodo) {
		return 0;
	}
}

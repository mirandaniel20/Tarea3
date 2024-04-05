/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package btree;

/**
 *
 * @author Wine
 */
public void buscarNodoPorClave(int num) {
		BTree temp = buscar(raiz, num);
		
		if(temp == null) {
			System.out.println("No se ha encontrado un nodo con el valor ingresado");
		}else {
			print(temp);
		}
	}
	
	private Nodo buscar(BTree actual, int key) {
		int i = 0;
		
		while(i < actual.clave && key > actual.key[i]) {
			i++;
		}
		
		if(i < actual.clave && key == actual.key[i]) {
			return actual;
		}
		
		if(actual.hoja) {
			return null;
		}else {
			return buscar(actual.hijo[i], key);
		}
	}

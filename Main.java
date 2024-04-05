/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package btree;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        
        System.out.print("Ingrese el grado del árbol B: ");
        int grado = entrada.nextInt();

        System.out.print("Ingrese la cantidad de números para operar el árbol B: ");
        int cantidadNumeros = 0; // Inicializamos cantidadNumeros con un valor predeterminado
        try {
            cantidadNumeros = entrada.nextInt();
        } catch (Exception e) {
            System.out.println("Error: Ingrese un número válido para la cantidad de números.");
            System.exit(0); // Salir del programa si ocurre un error
        }

        BTree bTree = new BTree(grado, cantidadNumeros); // Crear árbol B con el grado y cantidad de números especificados

        System.out.print("Inserción completada\n");

        // Llamada al método traverse
        bTree.traverse();

        // Eliminación de un valor específico
        System.out.print("Ingrese el valor que desea eliminar: ");
        int valorEliminar = entrada.nextInt();
        boolean eliminado = bTree.eliminar(valorEliminar);
        if (eliminado) {
            System.out.println("Valor " + valorEliminar + " eliminado correctamente.");
        } else {
            System.out.println("El valor " + valorEliminar + " no se encuentra en el árbol.");
        }

        // Llamada al método traverse después de la eliminación
        System.out.print("Árbol después de la eliminación: ");
        bTree.traverse();
    }
}

package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class LogicaNegocio {
    private Scanner sc;
    private AccesoBD accesoBD;
    public LogicaNegocio() {
        this.sc = new Scanner(System.in);
        accesoBD = new AccesoBD();
    }
    
    public void iniciaeMenu(){
        int opcion;
        do{
            System.out.println("Opciones:");
            System.out.println("1.Insertar Coche");
            System.out.println("2.Actualizar Coche");
            System.out.println("3.Eliminar Coche");
            System.out.println("4.Eliminar coches por marca");
            System.out.println("5.Consultar Coche con una matricula");
            System.out.println("6.Consultar Coches de una marca");
            System.out.println("7.Salir");
            opcion = sc.nextInt();
            switch (opcion){
                case 1:
                    InsertarCoche();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    ConsultarCochesMarca();
                    break;
            }
       }while (opcion!=7);
    }

    private void InsertarCoche() {
        sc.nextLine();
        Coche coche = new Coche();
        System.out.println("Matricula: ");
        coche.setMatricula(sc.nextLine());
        System.out.println("Marca: ");
        coche.setMarca(sc.nextLine());
        System.out.println("Modelo: ");
        coche.setModelo(sc.nextLine());
        System.out.println("Potencia: ");
        coche.setPotencia(sc.nextInt());
        System.out.println("Importe: ");
        coche.setImporte(sc.nextFloat());
        accesoBD.insertarCoche(coche);
    }

    private void ConsultarCochesMarca() {
        System.out.println("Marca a consultar:");
        sc.nextLine();
        ArrayList<Coche> coches = accesoBD.visualizarCochesMarca(sc.nextLine());
        System.out.println("Coches: "+coches.toString());
    }
}

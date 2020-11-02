/**
 * 01-11-20
 * Universidad de la defensa Nacional IUA
 * Inteligencia Artificial
 * Profesor: Juan Giro
 * @author Madoery Guillermo
 *
 */
public class Puzzle {
	public static int menu(){
		System.out.println("\n\n");
		System.out.println("				||==============================================||");
		System.out.println("				||         INTELIGENCIA ARTIFICIAL 2020         ||");
		System.out.println("				||______________________________________________||");
		System.out.println("				|| 1- Nueva matriz y matriz final               ||");
		System.out.println("				|| 2- Ver matriz inicial y matriz final         ||");
		System.out.println("				|| 3- Resolver		                            ||");
		System.out.println("				|| 4- Seleccionar algoritmo de solucion         ||");
		System.out.println("				|| 5- Reestablecer estados predeterminados      ||");
		System.out.println("				|| 6- Seleccionar heuristica                    ||");
		System.out.println("				|| 0- Salir                                     ||");
		System.out.println("				||==============================================||");
		System.out.print("  							 Seleccionar una opcion: ");
		int op;
		do{
		op = Funciones.datoInt(); 
			}while (op < 1 || op > 6);
		return op;
	}	
	public static void main(String[] args) { 
		int opcion = 0;
		int metodo=1,heuristica=1; 		
		int[][] matriz = new int[3][3];
		int[][] matrizFinal = new int[3][3];
		matriz[0][0] = 2;
		matriz[0][1] = 8;
		matriz[0][2] = 3;
		matriz[1][0] = 1;
		matriz[1][1] = 6;
		matriz[1][2] = 4;
		matriz[2][0] = 7;
		matriz[2][1] = 0;
		matriz[2][2] = 5;
		matrizFinal[0][0] = 1;
		matrizFinal[0][1] = 2;
		matrizFinal[0][2] = 3;
		matrizFinal[1][0] = 8;
		matrizFinal[1][1] = 0;
		matrizFinal[1][2] = 4;
		matrizFinal[2][0] = 7;
		matrizFinal[2][1] = 6;
		matrizFinal[2][2] = 5;		
		Funciones func = new Funciones();		
		opcion = menu();		
		while(opcion!=0){
			switch(opcion){
				case 1: 
					System.out.println("\n\nIngresar los valores de la matriz inicial");
					matriz = func.cargarMatriz();
					System.out.println("\n\nIngresar los valores de la matriz final");
					matrizFinal = func.cargarMatriz();
					func.mostrarMatriz(matriz);
					func.mostrarMatriz(matrizFinal);
					break;					
				case 2:
					System.out.println("\n\n||============================||");
					System.out.println("||Valores de la matriz inicial||");
					System.out.println("||============================||");
					func.mostrarMatriz(matriz);
					System.out.println("\n\n||==========================||");
					System.out.println("||Valores de la matriz final||");
					System.out.println("||==========================||");
					func.mostrarMatriz(matrizFinal);
					break;					
				case 3:
					Nodo mejorNodo = null;
					Lista abierta = new Lista();
					Lista cerrada = new Lista();
					int profundidad = 0;
					int prof = 0;				
					int movimientos = 1;
					double costo; //costo de matriz incial
					
					if(heuristica == 1){
						costo = func.costoh1(matriz, matrizFinal);	
					}else{
						costo = func.costoh2(matriz, matrizFinal);
					}					
					abierta.insertarAlFinal(matriz, null, costo);//agregando la matriz inicial a la lista ABIERTA
					System.out.println("Ingresar Nivel de profundidad: ");;
					profundidad = Funciones.datoInt();					
					
					while(costo != 0){						
						if(abierta.nodoAlFrente != null){
							Nodo nodoCerrado;							
							nodoCerrado = abierta.borrarNodo(matriz); //Insertando en el nodo la primera matriz.
							cerrada.insertarAlFinal(nodoCerrado.getcontenido(), nodoCerrado.getpadre(), nodoCerrado.getcosto());							
							int[] posicionComodin = func.posicionVacio(matriz);						
							
							if(metodo == 1 || metodo == 2){
								mejorNodo = func.calcularMejorMovimiento(matriz, matrizFinal, posicionComodin, abierta, cerrada, metodo, movimientos, heuristica);
								if(metodo==2){					
									if(heuristica == 1){
										costo = func.costoh1(mejorNodo.getcontenido(), matrizFinal, movimientos);	
									}else{
										costo = func.costoh2(mejorNodo.getcontenido(), matrizFinal, movimientos);
									}
									
								}else{
									if(heuristica == 1){
										costo = func.costoh1(mejorNodo.getcontenido(), matrizFinal);	
									}else{
										costo = func.costoh2(mejorNodo.getcontenido(), matrizFinal);
									}
									
								}
								matriz = func.cargarMatriz(mejorNodo.getcontenido());
								movimientos++;
								prof++;
							}
							else{
								if(metodo == 4 || metodo == 5){
									mejorNodo = func.calcularMejorMovimiento(matriz, matrizFinal, posicionComodin, abierta, cerrada, metodo, movimientos,heuristica);
									if(heuristica == 1){
										costo = func.costoh1(mejorNodo.getcontenido(), matrizFinal);	
									}else{
										costo = func.costoh2(mejorNodo.getcontenido(), matrizFinal);
									}
									matriz = func.cargarMatriz(mejorNodo.getcontenido());
								    movimientos++;
									prof++;								
								}
								else{
									if (metodo == 3){
										mejorNodo = func.calcularMejorMovimiento(matriz, matrizFinal, posicionComodin, abierta, cerrada, metodo, movimientos,heuristica);
										if(heuristica == 1){
											costo = func.costoh1(mejorNodo.getcontenido(), matrizFinal);	
										}else{
											costo = func.costoh2(mejorNodo.getcontenido(), matrizFinal);
										}
										matriz = func.cargarMatriz(mejorNodo.getcontenido());
										movimientos++;
										prof++;
									}
								}
									
							}
						}
						if(profundidad == prof ){
							break;
						}
					}
					cerrada.insertarAntes(matrizFinal, null, 0);
					float factRam=0;
					factRam = ((abierta.contar()+cerrada.contar())-1) / prof ;
					System.out.println("\n\nCantidad de Iteraciones Alcanzadas: "+prof);
					System.out.println("\n\nCantidad de Nodos explorados: "+ ( (abierta.contar()+cerrada.contar() ) -1) );
					System.out.println("\n\nFactor de ramificacion medio: "+ factRam );
					cerrada.imprimirSolucion();
					System.out.println("\n\nImprimiendo la Lista Abierta\n\n");
					abierta.imprimirLista();
					break;
				case 4:
					System.out.println("\n\n");
					System.out.println("				||==========================================||");
					System.out.println("				||    Seleccionar el metodo de solucion:    ||");
					System.out.println("				||==========================================||");
					System.out.println("				||    	      Metodos de Busqueda           ||");
					System.out.println("				||__________________________________________||");
					System.out.println("				||1- Primero el Mejor                       ||");
			        System.out.println("				||==========================================||");
			        do{
			           	metodo = Funciones.datoInt(); 
					}while (metodo < 1 || metodo > 1);
			        break;
			        
				case 5:
					matriz[0][0] = 2;
					matriz[0][1] = 8;
					matriz[0][2] = 3;
					matriz[1][0] = 1;
					matriz[1][1] = 6;
					matriz[1][2] = 4;
					matriz[2][0] = 7;
					matriz[2][1] = 0;
					matriz[2][2] = 5;
					matrizFinal[0][0] = 1;
					matrizFinal[0][1] = 2;
					matrizFinal[0][2] = 3;
					matrizFinal[1][0] = 8;
					matrizFinal[1][1] = 0;
					matrizFinal[1][2] = 4;
					matrizFinal[2][0] = 7;
					matrizFinal[2][1] = 6;
					matrizFinal[2][2] = 5;
					System.out.println("\n\nSe reestablecieron los estados predeterminados");
					break;
				case 6:
					System.out.println("\n\n");
					System.out.println("				||==========================================||");
					System.out.println("				||    Seleccionar la Heuristica:            ||");
					System.out.println("				||==========================================||");
					System.out.println("				||    	      Heuristicas disponibles:      ||");
					System.out.println("				||__________________________________________||");
					System.out.println("				||1- Cantidad de piezas fuera de lugar      ||");
					System.out.println("				||2- Movimientos para ubicar una pieza      ||");
			        System.out.println("				||==========================================||");
			        do{
			           	heuristica = Funciones.datoInt(); 
					}while (metodo < 1 || metodo > 2);					
				break;
				default:
					break;
			}
			opcion = menu();
		}
		System.exit(0);
	}
}

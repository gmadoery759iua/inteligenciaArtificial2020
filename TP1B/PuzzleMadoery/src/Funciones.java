/**
 * 01-11-20
 * Universidad de la defensa Nacional IUA
 * Inteligencia Artificial
 * Profesor: Juan Giro
 * @author Madoery Guillermo
 * 
 */
import java.io.*;
public class Funciones {
	public int[][] cargarMatriz(){
		int[][] matriz = new int[3][3];
		for(int fila = 0; fila < 3; fila++){
			for(int columna = 0; columna < 3; columna++){
				System.out.print("Ingrese el valor ["+fila+"]["+columna+"] (Valores 0-8; Posicion Vacio = 0): ");
				matriz[fila][columna] = datoInt();
				do{
					if(matriz[fila][columna]>=9||matriz[fila][columna]<0){
						System.out.print("Error!! Dato Ingresado Incorrecto.... Ingrese el valor del vector ["+fila+"]["+columna+"] (Valores 0-8; Posicion Vacio = 0): ");
						matriz[fila][columna] = datoInt();
					}
					
				}while(matriz[fila][columna]>=9||matriz[fila][columna]<0);				
			}
		}
		return matriz;	
	}	
	public int[][] cargarMatriz(int matrizOne[][]){
		int[][] matrizTwo = new int[3][3];
		for(int fila = 0; fila < 3; fila++){
			for(int columna = 0; columna < 3; columna++){
				matrizTwo[fila][columna] = matrizOne[fila][columna];
			}
		}
	return matrizTwo;
	}
	public void mostrarMatriz(int matriz[][]){
		for(int fila = 0; fila < 3; fila++){
			System.out.println("");
			for(int columna = 0; columna < 3; columna++){
				System.out.print(matriz[fila][columna] + " ");
			}
		}	
		System.out.println("");
		System.out.println("");
	}	
	public int[] posicionVacio(int matriz[][]){
		int[] posicion = new int[2];
		for(int fila = 0; fila < 3; fila++){
			for(int columna = 0; columna < 3; columna++){
				if(matriz[fila][columna]==0){
					posicion[0] = fila;
					posicion[1] = columna;
					break; 
				}
			}
		}
		return posicion;	
	}
	public double costoh1(int matriz[][], int matrizFinal[][]){
		double costo = 0;
		for(int fila = 0; fila < 3; fila++){
			for(int columna = 0; columna < 3; columna++){
				if(matriz[fila][columna] != matrizFinal[fila][columna]){
					costo++;
				}
			}
		}
		return costo;
	}	
	public double costoh1(int matriz[][], int matrizFinal[][],int movimientos){
		double costo = 0;
		for(int fila = 0; fila < 3; fila++){
			for(int columna = 0; columna < 3; columna++){
				if(matriz[fila][columna] != matrizFinal[fila][columna]){
					costo++;
				}
			}
		}
		if(costo!=0){
			return (costo+movimientos);	
		}
		return costo;
	}
	public double costoh2(int matriz[][], int matrizFinal[][],int movimientos){
		double costo = 0;
		int[] posicion = new int[2];
		for(int fila = 0; fila < 3; fila++){
			for(int columna = 0; columna < 3; columna++){				
				if(matriz[fila][columna] != matrizFinal[fila][columna]){
					for(int filab = 0; filab < 3; filab++){
						for(int columnab = 0; columnab < 3; columnab++){
							if(matriz[fila][columna]== matrizFinal[filab][columnab]){
								posicion[0] = filab;
								posicion[1] = columnab;								
								costo = costo + Math.abs(filab - fila) + Math.abs(columnab - columna);
								break;
							}
						}
					}					
				}
			}
		}
		
		if(costo!=0){
			return (costo+movimientos);	
		}
		return costo;
	}	
	public double costoh2(int matriz[][], int matrizFinal[][]){
		double costo = 0;
		int[] posicion = new int[2];
		
		for(int fila = 0; fila < 3; fila++){
			for(int columna = 0; columna < 3; columna++){				
				if(matriz[fila][columna] != matrizFinal[fila][columna]){
					
					for(int filab = 0; filab < 3; filab++){
						for(int columnab = 0; columnab < 3; columnab++){
							if(matriz[fila][columna]== matrizFinal[filab][columnab]){
								posicion[0] = filab;
								posicion[1] = columnab;								
								costo = costo + Math.abs(filab - fila) + Math.abs(columnab - columna);
								break;
							}
						}
					}					
				}
			}
		}
		return costo;
	}		
	public Nodo calcularMejorMovimiento(int matriz[][], int matrizFinal[][], int posicion[], Lista abierta, Lista cerrada, int metodo, int movimientos, int heuristica){
		int temp_matrizA[][] = new int[3][3]; 
		temp_matrizA = this.cargarMatriz(matriz);		
		int temp_matrizB[][] = new int[3][3];
		temp_matrizB = this.cargarMatriz(matriz);
		int temp_matrizC[][] = new int[3][3];
		temp_matrizC = this.cargarMatriz(matriz);
		int temp_matrizD[][] = new int[3][3];
		temp_matrizD = this.cargarMatriz(matriz);
		int temp_matrizE[][] = new int[3][3]; 
		temp_matrizE = this.cargarMatriz(matriz);
		int temp_matrizF[][] = new int[3][3];
		temp_matrizF = this.cargarMatriz(matriz);
		int temp_matrizG[][] = new int[3][3];
		temp_matrizG = this.cargarMatriz(matriz);
		int temp_matrizH[][] = new int[3][3];
		temp_matrizH = this.cargarMatriz(matriz);
		double[] costo = new double[8];
		Nodo NodoPadre = new Nodo();
		NodoPadre = cerrada.buscarElemento(matriz);
		int perfilRamificacion=0; //cantidad de nodos explorados en cada nivel de profundidad
		
		boolean noborro = true;
		/*Examina Columnas*/
		if((posicion[1]-1)==0){
			double costoA = 0;
			int temp = temp_matrizA[posicion[0]][(posicion[1]-1)];
			temp_matrizA[posicion[0]][(posicion[1]-1)] = temp_matrizA[posicion[0]][posicion[1]];
			temp_matrizA[posicion[0]][posicion[1]] = temp;			
			if(metodo==2){
				if(heuristica == 1){
		 			costoA = this.costoh1(temp_matrizA, matrizFinal,movimientos);
		 		}else{
		 			costoA = this.costoh2(temp_matrizA, matrizFinal,movimientos);
		 		}				
			}else
			 { 		if(metodo == 1){
				 		if(heuristica == 1){
				 			costoA = this.costoh1(temp_matrizA, matrizFinal);
				 		}else{
				 			costoA = this.costoh2(temp_matrizA, matrizFinal);
				 		}
			   }
			 }	
			costo[0] = costoA;			
			//Insertar matriz en la lista abierta
			if(!abierta.buscar(temp_matrizA) && !cerrada.buscar(temp_matrizA)){
				abierta.insertarAlFinal(temp_matrizA, NodoPadre, costo[0]);
				perfilRamificacion++;
			}
		}		
		if((posicion[1]-1)==1){
			double costoB = 0;
			int temp = temp_matrizB[posicion[0]][(posicion[1]-1)];
			temp_matrizB[posicion[0]][(posicion[1]-1)] = temp_matrizB[posicion[0]][posicion[1]];
			temp_matrizB[posicion[0]][posicion[1]] = temp;			
			if(metodo==2){
				if(heuristica == 1){
		 			costoB = this.costoh1(temp_matrizB, matrizFinal,movimientos);
		 		}else{
		 			costoB = this.costoh2(temp_matrizB, matrizFinal, movimientos);
		 		}

			}else{
				if(metodo == 1){
					 if(heuristica == 1){
				 			costoB = this.costoh1(temp_matrizB, matrizFinal);
				 		}else{
				 			costoB = this.costoh2(temp_matrizB, matrizFinal);
				 		}
			 	}
			 }		
			costo[1] = costoB;
			//Insertar matriz en la lista abierta
			if(!abierta.buscar(temp_matrizB) && !cerrada.buscar(temp_matrizB)){
				abierta.insertarAlFinal(temp_matrizB, NodoPadre, costo[1]);
			}
		}	
		if((posicion[1]+1)==1){
			double costoC = 0;
			int temp = temp_matrizC [ posicion[0] ][ (posicion[1]+1)];
			temp_matrizC[posicion[0]][(posicion[1]+1)] = temp_matrizC[posicion[0]][posicion[1]];
			temp_matrizC[posicion[0]][posicion[1]] = temp;			
			if(metodo==2){
				if(heuristica == 1){
		 			costoC = this.costoh1(temp_matrizC, matrizFinal, movimientos);
		 		}else{
		 			costoC = this.costoh2(temp_matrizC, matrizFinal, movimientos);
		 		}
			}else
			 { 		if(metodo == 1){
						 if(heuristica == 1){
					 			costoC = this.costoh1(temp_matrizC, matrizFinal);
					 		}else{
					 			costoC = this.costoh2(temp_matrizC, matrizFinal);
					 		}
			   }
			 }
			costo[2] = costoC;
			//Insertar matriz en la lista abierta
			if(!abierta.buscar(temp_matrizC) && !cerrada.buscar(temp_matrizC)){
				if(metodo == 3 && noborro == true){
					while(abierta.borrarPrimero() != null);
					noborro = false;
				}				
				abierta.insertarAlFinal(temp_matrizC, NodoPadre, costo[2]);
				perfilRamificacion++;
			}
		}
		if((posicion[1]+1)==2){
			int	temp = temp_matrizD[posicion[0]][(posicion[1]+1)];
			temp_matrizD[posicion[0]][(posicion[1]+1)] = temp_matrizD[posicion[0]][posicion[1]];
			temp_matrizD[posicion[0]][posicion[1]] = temp;
			double costoD = 0;
			
			if(metodo==2){
				if(heuristica == 1){
		 			costoD = this.costoh1(temp_matrizD, matrizFinal, movimientos);
		 		}else{
		 			costoD = this.costoh2(temp_matrizD, matrizFinal, movimientos);
		 		}
			}else
			 { 		if(metodo == 1){
						 if(heuristica == 1){
					 			costoD = this.costoh1(temp_matrizD, matrizFinal);
					 		}else{
					 			costoD = this.costoh2(temp_matrizD, matrizFinal);
					 		}
			   		}
			 }	
			costo[3] = costoD;
			//Insertar matriz en la lista abierta
			if(!abierta.buscar(temp_matrizD) && !cerrada.buscar(temp_matrizD)){
				if(metodo == 3 && noborro == true){
					while(abierta.borrarPrimero() != null);
					noborro = false;
				}				
				abierta.insertarAlFinal(temp_matrizD, NodoPadre, costo[3]);
				perfilRamificacion++;
			}
		}
		
		/*Examina Columnas*/
		if((posicion[0]-1)==0){
			double costoE = 0;
			int temp = temp_matrizE[(posicion[0]-1)][posicion[1]];
			temp_matrizE[(posicion[0]-1)][posicion[1]] = temp_matrizE[posicion[0]][posicion[1]];
			temp_matrizE[posicion[0]][posicion[1]] = temp;
			
			
			if(metodo==2){
				if(heuristica == 1){
		 			costoE = this.costoh1(temp_matrizE, matrizFinal, movimientos);
		 		}else{
		 			costoE = this.costoh2(temp_matrizE, matrizFinal, movimientos);
		 		}
			}else
			 { 		if(metodo == 1){
						 if(heuristica == 1){
					 			costoE = this.costoh1(temp_matrizE, matrizFinal);
					 		}else{
					 			costoE = this.costoh2(temp_matrizE, matrizFinal);
					 		}
			   		}
			 }	
			costo[4] = costoE;
			//Insertar matriz en la lista abierta
			if(!abierta.buscar(temp_matrizE) && !cerrada.buscar(temp_matrizE)){
				if(metodo == 3 && noborro == true){
					while(abierta.borrarPrimero() != null);
					noborro = false;
				}				
				abierta.insertarAlFinal(temp_matrizE, NodoPadre, costo[4]);
				perfilRamificacion++;
			}
		}		
		if((posicion[0]-1)==1){
			double costoF = 0;
			int temp = temp_matrizF[(posicion[0]-1)][posicion[1]];
			temp_matrizF[(posicion[0]-1)][(posicion[1])] = temp_matrizF[posicion[0]][posicion[1]];
			temp_matrizF[posicion[0]][posicion[1]] = temp;
			
			if(metodo==2){
				if(heuristica == 1){
		 			costoF = this.costoh1(temp_matrizF, matrizFinal, movimientos);
		 		}else{
		 			costoF = this.costoh2(temp_matrizF, matrizFinal, movimientos);
		 		}
			}else
			 { 		if(metodo == 1){
						 if(heuristica == 1){
					 			costoF = this.costoh1(temp_matrizF, matrizFinal);
					 		}else{
					 			costoF = this.costoh2(temp_matrizF, matrizFinal);
					 		}
				   }
			 }	
			costo[5] = costoF;
			//Insertar matriz en la lista abierta
			if(!abierta.buscar(temp_matrizF) && !cerrada.buscar(temp_matrizF)){
				if(metodo == 3 && noborro == true){
					while(abierta.borrarPrimero() != null);
					noborro = false;
				}				
				abierta.insertarAlFinal(temp_matrizF, NodoPadre, costo[5]);
				perfilRamificacion++;
			}
		}
	
		if((posicion[0]+1)==1){
			double costoG = 0;
			int temp = temp_matrizG[(posicion[0]+1)][posicion[1]];
			temp_matrizG[(posicion[0]+1)][(posicion[1])] = temp_matrizG[posicion[0]][posicion[1]];
			temp_matrizG[posicion[0]][posicion[1]] = temp;
			
			if(metodo==2){
				if(heuristica == 1){
		 			costoG = this.costoh1(temp_matrizG, matrizFinal, movimientos);
		 		}else{
		 			costoG = this.costoh2(temp_matrizG, matrizFinal, movimientos);
		 		}
			}else
			 { 		if(metodo == 1){
						 if(heuristica == 1){
					 			costoG = this.costoh1(temp_matrizG, matrizFinal);
					 		}else{
					 			costoG = this.costoh2(temp_matrizG, matrizFinal);
					 		}
			 		}
			 }	
			costo[6] = costoG;
			//Inserto la matriz en la lista abierta
			if(!abierta.buscar(temp_matrizG) && !cerrada.buscar(temp_matrizG)){
				if(metodo == 3 && noborro == true){
					while(abierta.borrarPrimero() != null);
					noborro = false;
				}				
				abierta.insertarAlFinal(temp_matrizG, NodoPadre, costo[6]);
				perfilRamificacion++;
			}
			
		}
		if((posicion[0]+1)==2){
			double costoH = 0;
			int temp = temp_matrizH[(posicion[0]+1)][posicion[1]];
			temp_matrizH[(posicion[0]+1)][posicion[1]] = temp_matrizH[posicion[0]][posicion[1]];
			temp_matrizH[posicion[0]][posicion[1]] = temp;			
			if(metodo==2){
				if(heuristica == 1){
		 			costoH = this.costoh1(temp_matrizH, matrizFinal, movimientos);
		 		}else{
		 			costoH = this.costoh2(temp_matrizH, matrizFinal, movimientos);
		 		}
			}else
			 { 		if(metodo == 1){
						 if(heuristica == 1){
					 			costoH = this.costoh1(temp_matrizH, matrizFinal);
					 		}else{
					 			costoH = this.costoh2(temp_matrizH, matrizFinal);
					 		}
			   		}else{
				   		if( metodo == 4){
				   			if(heuristica == 1){
					 			costoH = this.costoh1(temp_matrizH, matrizFinal);
					 		}else{
					 			costoH = this.costoh2(temp_matrizH, matrizFinal);
					 		}
				   		}else{
				   				if( metodo == 3){
				   					if(heuristica == 1){
							 			costoH = this.costoh1(temp_matrizH, matrizFinal);
							 		}else{
							 			costoH = this.costoh2(temp_matrizH, matrizFinal);
							 		}
				   				}else{
				   						if(metodo == 5){
				   							if(heuristica == 1){
				   					 			costoH = this.costoh1(temp_matrizH, matrizFinal);
				   					 		}else{
				   					 			costoH = this.costoh2(temp_matrizH, matrizFinal);
				   					 		}								
				   						}
				   				 	 }
				   		 	 }
					}
			 }	
			costo[7] = costoH;
			//Inserto la matriz en la lista abierta
			if(!abierta.buscar(temp_matrizH) && !cerrada.buscar(temp_matrizH)){
				if(metodo == 3 && noborro == true){
					while(abierta.borrarPrimero() != null);
					noborro = false;
				}				
				abierta.insertarAlFinal(temp_matrizH, NodoPadre, costo[7]);
				perfilRamificacion++;
			}
		}
		//System.out.println("cantidad de nodos explorados en este nivel: " + perfilRamificacion);
		if(metodo == 1 || metodo == 2) return abierta.buscarMenor();
		System.out.println("Ninguna opcion es valida");
		return null;		
	}//Fin del metodo.	
	public static String dato(){
		String sdato = "";
		try{			
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader flujoE = new BufferedReader(isr);			
			sdato = flujoE.readLine();
		}catch(IOException e){
			System.err.println("Error: " + e.getMessage());
		}
		return sdato;
	}
   	public static short datoShort(){
		try{
			return Short.parseShort(dato());
		}catch(NumberFormatException e){
			return Short.MIN_VALUE;
		}
  	}
  	public static int datoInt(){
    		try{
      			return Integer.parseInt(dato());
    		}catch(NumberFormatException e){
      			return Integer.MIN_VALUE;
    		}
  	}
   	public static long datoLong(){
    		try{
      			return Long.parseLong(dato());
    		}catch(NumberFormatException e){
      			return Long.MIN_VALUE;
    		}
  	}
   	public static float datoFloat(){
    		try{
      			Float f = new Float(dato());
      			return f.floatValue();
    		}catch(NumberFormatException e){
      			return Float.NaN;
    		}
  	}
  	public static double datoDouble(){
    		try{
      			Double d = new Double(dato());
      			return d.doubleValue();
    		}catch(NumberFormatException e){
      			return Double.NaN;
    		}
  	}
}

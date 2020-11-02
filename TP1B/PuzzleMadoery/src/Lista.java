/**
 * 01-11-20
 * Universidad de la defensa Nacional IUA
 * Inteligencia Artificial
 * Profesor: Juan Giro
 * @author Madoery Guillermo
 *
 */
class Lista extends Funciones{
	protected Nodo nodoAlFrente;	
	public Lista(){
		nodoAlFrente = null;
	}
	public void insertarAlFinal(int[][] matriz, Nodo padre, double costo){
		Nodo  p;
		Nodo  q = null;
		p = nodoAlFrente;		
		while (p != null){ 
			q = p;
			p = p.getenlace();
		}		
		p = new Nodo();
		p.setcontenido(matriz);
		p.setcosto(costo);
		p.setpadre(padre);		
		if(nodoAlFrente == null){
			nodoAlFrente = p;
		}else{
			q.setenlace(p);
		}
	}	
	public void insertarAntes(int[][] matriz, Nodo padre, int costo){
		Nodo  p;
	    p = new Nodo();
	    if(p != null){
	    	p.setcontenido(matriz);
		    p.setpadre(padre);
		    p.setcosto(costo);
	    	p.setenlace(nodoAlFrente);
	    	nodoAlFrente = p;
	    }else{
	    	System.out.println("Memoria agotada...");
	    }
	}	
	public void imprimirLista(){ 
		Nodo p; 
		p = nodoAlFrente; 
		System.out.println("Imprimiendo de la Lista");
		while (p != null){ 
			System.out.println("_________________________");
			System.out.println("Matriz:");
			System.out.println("_____");
			super.mostrarMatriz(p.getcontenido());
			System.out.println("_____");
			System.out.println("Padre:"); 
			super.mostrarMatriz(p.getpadre().getcontenido());
			System.out.println("_____");
			System.out.println("Costo:");
			System.out.println("  costo:" + p.getcosto());
			System.out.println("");
			p=p.getenlace();
		}
		System.out.println( ); 
    	}
	public void imprimirSolucion(){
		Nodo p = new Nodo(); 
		p = nodoAlFrente;
		Nodo  q = null;
		while (p != null){ 
			q = p;
			p = p.getenlace();
		}
		System.out.println("");
		System.out.println("||==========================||");
		System.out.println("||    Solucion encontrada   ||");
		System.out.println("||==========================||");
		super.mostrarMatriz(nodoAlFrente.getcontenido());
		System.out.println("   -> costo:" + nodoAlFrente.getcosto());
		
		while(q != null){ 
			System.out.println("");
			System.out.println("_______________________");
			super.mostrarMatriz(q.getcontenido());
			System.out.println("   -> costo:" + q.getcosto());
			q = q.getpadre();
		}
		System.out.println( ); 
    	}
	public int[][] borrarPrimero(){
		Nodo p;
		int aux[][];
		if(nodoAlFrente != null){
			p = nodoAlFrente;
			nodoAlFrente = nodoAlFrente.getenlace();
			aux = p.getcontenido();
			p = null;
			return aux;
		}else{
			return null;
		}
	}
	public boolean buscar(int[][] x){
		Nodo p;
		p = nodoAlFrente;
		while(p != null){
			double costo = this.costoh1(p.getcontenido(), x);
			if(costo == 0){
				return true;
			}
			p = p.getenlace();
		}
		return false;
	}
	public int contar(){
		int cant=0;
		Nodo  p;
		p = nodoAlFrente;
		while(p!=null){
			cant++;
			p = p.getenlace();
		}
		return cant;
	}
	public Nodo borrarNodo(int[][] matriz){
		double igual = this.costoh1(matriz, nodoAlFrente.getcontenido());
		if (igual == 0){
			Nodo aux = nodoAlFrente;
			nodoAlFrente = nodoAlFrente.getenlace();
			return aux;
		}else{
			Nodo p = nodoAlFrente;
			Nodo q = null;
			double igual2 = this.costoh1(matriz, p.getcontenido());
			while(igual2 != 0 && p != null){
				q = p;
				p = p.getenlace();
				igual2 = this.costoh1(matriz, p.getcontenido());
			}
			if(p != null){
				q.setenlace(p.getenlace());
				Nodo aux2 = p;
				p = null;
				return aux2;
			}else{
				System.out.println("La refencia no pertenece a un nodo de la lista");
				return null;
			}
		}
	}
	public void borrarNodo(Nodo pa){
		if(pa == nodoAlFrente){
			nodoAlFrente = nodoAlFrente.getenlace();
			pa = null;
		}else{
			Nodo p;
			Nodo q = null;
			p = nodoAlFrente;
			while(p != pa && p != null){
				q = p;
				p = p.getenlace();
			}
			if(p != null){
				q.setenlace(p.getenlace());
				p=null;
			}else{
				System.out.println("La refencia no pertenece a un nodo de la lista");
			}
		}
	}
	public Nodo buscarElemento(int[][] x){
		Nodo p;
		p = nodoAlFrente;
		while(p != null){
			if(this.costoh1(x, p.getcontenido()) == 0){
				return p;
			}
			p = p.getenlace();   
		}
		return null;
	}
	public Nodo buscarMenor(){
		Nodo p;
		Nodo aux = null;
		p = nodoAlFrente;
		double costo = 1000;
		while(p != null){
			if(p.getcosto() < costo){
				aux = p;
				costo = p.getcosto();
			}
			p = p.getenlace();   
		}
		return aux;
	}
	public Nodo buscarprimero(){
		Nodo p;
		Nodo aux = null;
		p = nodoAlFrente;
		while(p != null){
			aux = p;
			p = p.getenlace();   
		}
		return aux;
	}
	public Nodo buscarultimo(){
		Nodo p;
		p = nodoAlFrente;
		if(p !=null){			
			return p;
		}else{
			return null;
		}
	
	}	
}

/**
 * 01-11-20
 * Universidad de la defensa Nacional IUA
 * Inteligencia Artificial
 * Profesor: Juan Giro
 * @author Madoery Guillermo
 *
 */
class Nodo extends Funciones{
	private int[][] contenido;
	private Nodo padre;
	private double costo;
	private Nodo enlace; 
    Nodo(){
    	contenido = new int[3][3];
    	padre = null;
    	costo = 0;
    	enlace = null; 
    }
	Nodo(int[][] matriz, Nodo pa, double cos, Nodo en){
	    	contenido = super.cargarMatriz(matriz);
	    	padre = pa;
	    	costo = cos;
	    	enlace = en; 
	}
    public void setcontenido(int[][] x){
    	contenido = super.cargarMatriz(x);
    }
    public void setpadre(Nodo pa){
    	padre = pa;
    }
    public void setcosto(double x){
    	costo = x;
    }
    public void setenlace(Nodo en){
    	enlace = en;
    }
    public int[][] getcontenido(){
    	return contenido;
    }
    public Nodo getpadre(){
    	return padre;
    }
    public double getcosto(){
    	return costo;
    }
    public Nodo getenlace(){
    	return enlace;
    }
}

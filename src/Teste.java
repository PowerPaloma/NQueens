
public class Teste {
	public static void main(String[] args) {
		Problema problema = new Problema(4);
		problema.hillClimbing(100);
	 
		int[] a = problema.getEstado().getRainhas();
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i] + " " + i);
		}
		System.out.println(problema.getEstado().getNumAtaques());
		
		for (int i = 0; i < problema.getEstado().getAtaques().length; i++) {
			System.out.print(problema.getEstado().getAtaques()[i] + " ");
		}
	}
}

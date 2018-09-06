import java.util.ArrayList;
import java.util.Random;

public class Problema {
	private Estado estado;
	private int numRainhas;

	public Problema(int numRainhas){
		this.numRainhas = numRainhas;
		this.estado = new Estado(this.numRainhas);
		this.estado.inicializar();
	}

	public void hillClimbing(int numIt){
		estado.verificaColisao();

		for (int i = 0; i < numIt; i++) {
			int max = Integer.MIN_VALUE;
			int jMax = 0;

			for (int j = 0; j < numRainhas; j++) {
				if(estado.getAtaques()[j] > max){
					max = estado.getAtaques()[j];
					jMax = j;
				}
			}

			ArrayList<Estado> melhoresViz = estado.melhoresVizinhos(jMax);
			Random r = new Random(); 
			int viz = r.nextInt(melhoresViz.size());

			if(melhoresViz.get(viz).getNumAtaques() < estado.getNumAtaques()){
				estado = melhoresViz.get(viz);
			}
			//estado.setNumAtaques(0);
			//estado.verificaColisao();
			//estado.atualizarDiag();

			System.out.print("Ataques: " + estado.getNumAtaques() + "    ");
			for (int j = 0; j < estado.getRainhas().length; j++) {
				System.out.print(estado.getRainhas()[j] + " ");
			}
			System.out.println();

			if(estado.getNumAtaques() == 0){
				System.out.println("BREAK");
				break;
			}
		}

	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public int getNumRainhas() {
		return numRainhas;
	}

	public void setNumRainhas(int numRainhas) {
		this.numRainhas = numRainhas;
	}

}

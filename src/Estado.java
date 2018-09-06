import java.util.ArrayList;

public class Estado {
	private int[] rainhas;
	private int[] diagPos;
	private int[] diagNeg;
	private int[] ataques;
	private int numRainhas;
	private int numAtaques;

	public Estado(int numRainhas){
		this.numRainhas = numRainhas;
		this.rainhas = new int[numRainhas];
		this.ataques = new int[numRainhas];
		this.diagNeg = new int[numRainhas*numRainhas];
		this.diagPos = new int[numRainhas*numRainhas];
		this.numAtaques = 0;
	}

	public void inicializar(){
		int aux = this.numRainhas-1;

		for (int i = 0; i < this.numRainhas; i++) {
			rainhas[i] = aux - i;
		}

		this.atualizarDiag();
	}

	public void atualizarDiag(){
		for (int i = 0; i < diagNeg.length; i++) {
			int lin = i / this.numRainhas;
			int col = i % this.numRainhas;

			diagNeg[i] = lin + col;
			diagPos[i] = lin - col;
		}
	}

	public void verificaColisao(){
		int ataqueRainha = 0;

		for (int i = 0; i < this.numRainhas; i++) {
			int lin = this.rainhas[i];
			int pos1 = (lin * this.numRainhas) + i;

			for (int j = 0; j < this.numRainhas; j++) {
				if(j != i){
					int lin2 = this.rainhas[j];
					int pos2 = (lin2 * this.numRainhas ) + j;

					if(this.diagNeg[pos1] == this.diagNeg[pos2]){ //colisão 
						ataqueRainha++;
						//System.out.println("DIAG NEG");
					}
					
					if(this.diagPos[pos1] == this.diagPos[pos2]){ //colisão
						ataqueRainha++;
						//System.out.println("DIAG POS");
					}
				}
			}
			
			this.numAtaques += ataqueRainha;
			this.ataques[i] = ataqueRainha;
			ataqueRainha = 0;
		}

		for (int i = 0; i < ataques.length; i++) {
			//System.out.print(ataques[i] + " ");
		}
		//System.out.println();

	}

	public ArrayList<Estado> vizinhos(int col){
		ArrayList<Estado> viz = new ArrayList<Estado>();
		int lin = this.rainhas[col];
		int[] copiaRainhas = new int[numRainhas];
		
		for (int j = 0; j < numRainhas; j++) {
			copiaRainhas[j] = rainhas[j];
		}

		for (int i = 0; i < numRainhas; i++) {
			if(i != lin){
				copiaRainhas[col] = i;
				
				Estado e = new Estado(numRainhas);
				e.setRainhas(copiaRainhas);
				e.atualizarDiag();
				e.verificaColisao();
				viz.add(e);
			}
		}

		return viz;
	}

	public int melhorCusto(int col){
		ArrayList<Estado> viz = this.vizinhos(col);
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < viz.size(); i++) {
			if(viz.get(i).getNumAtaques() < min){
				min = viz.get(i).getNumAtaques();
			}
		}

		return min;
	}

	public ArrayList<Estado> melhoresVizinhos(int col){
		ArrayList<Estado> viz = this.vizinhos(col);
		int melhorCusto = this.melhorCusto(col);
		ArrayList<Estado> melhores = new ArrayList<Estado>();

		for (int i = 0; i < viz.size(); i++) {
			if(viz.get(i).getNumAtaques() == melhorCusto){
				melhores.add(viz.get(i));
			}
		}

		return melhores;
	}

	public int[] getRainhas() {
		return rainhas;
	}

	public void setRainhas(int[] rainhas) {
		this.rainhas = rainhas;
	}

	public int[] getDiagPos() {
		return diagPos;
	}

	public void setDiagPos(int[] diagPos) {
		this.diagPos = diagPos;
	}

	public int[] getDiagNeg() {
		return diagNeg;
	}

	public void setDiagNeg(int[] diagNeg) {
		this.diagNeg = diagNeg;
	}

	public int getNumRainhas() {
		return numRainhas;
	}

	public void setNumRainhas(int numRainhas) {
		this.numRainhas = numRainhas;
	}

	public int[] getAtaques() {
		return ataques;
	}

	public void setAtaques(int[] ataques) {
		this.ataques = ataques;
	}

	public int getNumAtaques() {
		return numAtaques;
	}

	public void setNumAtaques(int numAtaques) {
		this.numAtaques = numAtaques;
	}

}

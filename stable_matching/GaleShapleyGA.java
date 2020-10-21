public class GaleShapleyA{
	public String[] m;
	public String[] w;
	public String[][] m_pref;
	public String[][] w_pref;
	public int N,engagedCount;
	public Boolean[] free;
	public String[] wife;
	public String[] husband;

	public GaleShapleyA(String[] men,String[] women,String[][] mp,String[][] wp){
		this.m = men;
		this.w = women;
		this.m_pref = mp;
		this.w_pref = wp;
		this.N = mp.length;
		this.engagedCount = 0;
		this.free = new Boolean[N];
		this.wife = new String[N];
		this.husband = new String[N];
		for(int i=0;i<N;i++){
			free[i] = false;
		}

		//System.out.println("N is"+N);
	}
	/*Return the index of man whose name is "name"*/
	public int indexofmen(String name){
		for(int i=0;i<N;i++){
			if(this.m[i].equals(name))
				return i;
		}
		return -1;
	}
	/*Return the index of woman whoase name is "name"*/
	public int indexofwomen(String name){
		for(int i=0;i<N;i++){
			if(this.w[i].equals(name))
				return i;
		}
		return -1;
	}
	/*Check whether or not woman prefer new partner over old assigned partner*/
	public Boolean preference_more(String curpartner,String newpartner,int th){
		/*Get the preference list of index woman*/
		String[] woman_pref = this.w_pref[th];
		/*Traverse all woman preference list*/
		for(int i=0;i<N;i++){
			if(woman_pref[i].equals(curpartner)== true){
				/*
				 * if woman prefer current partner to new partner,
				 * then return false
				 * */
				return false;
			}
			if(woman_pref[i].equals(newpartner) == true){
				/*
				 * if woman prefer new partner to current partner,
				 * then return true;
				 * */
				return true;
			}
		}
		return false;
	}
	/*calculate the stable match policy*/
	public void stable_match(){
		String man;
		String[] man_pref;
		int first_free;
		while(engagedCount < N){
			/*find the first man who is not engaged*/
			for(first_free = 0;first_free<N;first_free++){
				if(free[first_free] == false){
					break;
				}
			}
			man = this.m[first_free];
			man_pref = this.m_pref[first_free];
			//System.out.println("No."+engagedCount+":"+man);
			/*Check the prefer list of this man who is not engaged one by one*/
			for(int i=0;i<N && !free[first_free];i++){
				/*Get the prefer name of woman*/
				String woman_name = man_pref[i];
				/*Get the index of current prefer woman*/
				int idx_w = indexofwomen(woman_name);
				/*Check current prefer woman whether or not is engaged*/
				if(husband[idx_w] == null){
					/*the prefer woman is not engaged with some guy*/
					wife[first_free] = woman_name;
					free[first_free] = true;
					husband[idx_w] = man;
					engagedCount++;
					//System.out.println("engagedCount:"+engagedCount);
				}
				/*
				 * The prefer woman is engaged and check which one (current partner and new partner)
				 * is better to this woman
				 * */
				else if(preference_more(husband[idx_w],man,idx_w) == true){/*if new partner is better than the current one*/

					/*get current partner index*/
					int idx_m = indexofmen(husband[idx_w]);
					wife[idx_m] = null;
					free[idx_m] = false;
					husband[idx_w] = man;

					wife[first_free] = woman_name;
					free[first_free] = true;
				}
			}
		}
	}
	public void printCouples(){
		System.out.println("Couples are:");
		for(int i=0;i<N;i++){
			System.out.println(husband[i]+":"+w[i]);
		}
	}


	public static void main(String args[]){
		System.out.println("Gale Shapley  Algorithm\n");

	String[] groups = { "SINERGIA","Clean Code","Neotech","A nada","ADA-3","UnderCover","GhostShell","ADA-friendly","Amiguitos", "Impostor1", "Impostor2", "Impostor3", "Impostor4"};
	String[] topics = {"P1", "P2", "P3", "P4", "P5", "P6", "P7", "P8", "P9", "P10", "P11", "P12", "P13"};

	/** groups preference **/
	String[][] grouppref = {
		{"P13","P1","P7","P6","P5","P2","P3","P8","P4","P10","P9","P11","P12"},
		{"P6","P3","P1","P8","P13","P12","P10","P9","P7","P5","P11","P4","P2"},
		{"P9","P2","P7","P1","P8","P12","P3","P5","P6","P11","P13","P4","P10"},
		{"P2","P8","P7","P10","P11","P4","P12","P3","P5","P9","P6","P1","P13"},
		{"P1","P6","P2","P5","P4","P3","P9","P10","P12","P8","P13","P11","P7"},
		{"P11","P5","P8","P7","P3","P1","P4","P9","P2","P10","P6","P12","P13"},
		{"P8","P1","P3","P7","P5","P13","P2","P12","P9","P10","P6","P4","P11"},
		{"P12","P13","P11","P9","P8","P7","P5","P6","P4","P10","P3","P2","P1"},
		{"P12","P13","P11","P3","P4","P2","P5","P1","P6","P7","P8","P9","P10"},
		{"P10","P4","P6","P9","P3","P2","P5","P7","P11","P1","P13","P12","P8"},
		{"P10","P4","P6","P9","P3","P2","P5","P7","P11","P1","P13","P12","P8"},
		{"P10","P4","P6","P9","P3","P2","P5","P7","P11","P1","P13","P12","P8"},
		{"P10","P4","P6","P9","P3","P2","P5","P7","P11","P1","P13","P12","P8"}
	};
	/** topics preference **/
	String[][] topicspref = {
	{ "SINERGIA","Clean Code","Neotech","A nada","ADA-3","UnderCover","GhostShell","ADA-friendly","Amiguitos", "Impostor1", "Impostor2", "Impostor3", "Impostor4"},
	{ "SINERGIA","Clean Code","Neotech","A nada","ADA-3","UnderCover","GhostShell","ADA-friendly","Amiguitos", "Impostor1", "Impostor2", "Impostor3", "Impostor4"},
	{ "SINERGIA","Clean Code","Neotech","A nada","ADA-3","UnderCover","GhostShell","ADA-friendly","Amiguitos", "Impostor1", "Impostor2", "Impostor3", "Impostor4"},
	{ "SINERGIA","Clean Code","Neotech","A nada","ADA-3","UnderCover","GhostShell","ADA-friendly","Amiguitos", "Impostor1", "Impostor2", "Impostor3", "Impostor4"},
	{ "SINERGIA","Clean Code","Neotech","A nada","ADA-3","UnderCover","GhostShell","ADA-friendly","Amiguitos", "Impostor1", "Impostor2", "Impostor3", "Impostor4"},
	{ "SINERGIA","Clean Code","Neotech","A nada","ADA-3","UnderCover","GhostShell","ADA-friendly","Amiguitos", "Impostor1", "Impostor2", "Impostor3", "Impostor4"},
	{ "SINERGIA","Clean Code","Neotech","A nada","ADA-3","UnderCover","GhostShell","ADA-friendly","Amiguitos", "Impostor1", "Impostor2", "Impostor3", "Impostor4"},
	{ "SINERGIA","Clean Code","Neotech","A nada","ADA-3","UnderCover","GhostShell","ADA-friendly","Amiguitos", "Impostor1", "Impostor2", "Impostor3", "Impostor4"},
	{ "SINERGIA","Clean Code","Neotech","A nada","ADA-3","UnderCover","GhostShell","ADA-friendly","Amiguitos", "Impostor1", "Impostor2", "Impostor3", "Impostor4"},
	{ "SINERGIA","Clean Code","Neotech","A nada","ADA-3","UnderCover","GhostShell","ADA-friendly","Amiguitos", "Impostor1", "Impostor2", "Impostor3", "Impostor4"},
	{ "SINERGIA","Clean Code","Neotech","A nada","ADA-3","UnderCover","GhostShell","ADA-friendly","Amiguitos", "Impostor1", "Impostor2", "Impostor3", "Impostor4"},
	{ "SINERGIA","Clean Code","Neotech","A nada","ADA-3","UnderCover","GhostShell","ADA-friendly","Amiguitos", "Impostor1", "Impostor2", "Impostor3", "Impostor4"},
	{ "SINERGIA","Clean Code","Neotech","A nada","ADA-3","UnderCover","GhostShell","ADA-friendly","Amiguitos", "Impostor1", "Impostor2", "Impostor3", "Impostor4"}
	};

	GaleShapleyA gs = new GaleShapleyA(groups,topics,grouppref,topicspref);
	gs.stable_match();
	gs.printCouples();
	}
}

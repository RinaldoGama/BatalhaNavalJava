package Batalha_Naval;

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Batalha_Naval {
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		char tabela [][] = new char [20][20];	
		char navios [][] = new char [20][20];
		int resposta;
		Tela_inicial();
		do{	
		    //inicia a posição dos navios
			iniciaTabuleiro(tabela);
			inicia_Tabela_Navios(navios);
			iniciaPorta_avioes(navios);
			iniciaDestroier(navios);
			iniciaCruzador(navios);
			iniciaFragata(navios);
			iniciaSubmarino(navios);
			iniciaCorveta(navios);
			//mostra tela inicial e tabela
			mostra_tabela(tabela);
			//Inicia o processo do tiro nos navios
			atira(navios, tabela);;
			resposta=JOptionPane.showConfirmDialog(null ,"Exibir a posição dos navios", "Resposta",JOptionPane.YES_NO_OPTION);
			if (resposta==JOptionPane.YES_OPTION){
				for(int linha=0;linha<20;linha++){//percorre as linhas com elementos de 0 a 20//
		            for(int coluna=0;coluna<20;coluna++){//percorre as colunas com elementos de 0 a 20//
		                 if (navios[linha][coluna]!=' '){
		                	 tabela[linha][coluna]=navios[linha][coluna];//atualiza tabela com posição dos navios
		                 }
		            }
		        } 
			mostra_tabela(tabela);	//mostra tabela com posição dos navios
			}
			resposta=JOptionPane.showConfirmDialog(null ,"Deseja jogar novamente",  
					"Resposta",JOptionPane.YES_NO_OPTION);
			if (resposta==JOptionPane.NO_OPTION){// verifica se jogo Continua
				JOptionPane.showMessageDialog(null,"Jogo finalizado");
			}
		}while(resposta==JOptionPane.YES_OPTION);	
	}

	//Inicia o processo tiro nos Navios
	public static void atira(char [][]navios, char [][]tabela) throws IOException{
		int conta_tiros=0; // inicializa variaveis contadoras
		int total_pontos=0;
		int pontosC=0;
		int pontosF=0;
		int pontosS=0;
		int pontosD=0;
		int pontosZ=0;
		int pontosP=0;
		String exibi="";
		int separa_dados=0;
		String jogador=Ler_nome();
			while (conta_tiros<=15){
				int linha=Ler_Linha();
				int coluna=Ler_coluna();
				char escolha;
				escolha=navios[linha][coluna]; //posição da matriz Navios
				System.out.println(escolha);
	                  switch(escolha){ // verifica tipo de navio e soma pontuação
	                     case ' ':
	                    	 JOptionPane.showMessageDialog(null,"voce atirou na agua, tente novamente");
	                         tabela[linha][coluna] = 'X';
	                         break;
	                     case 'C':
	                    	 JOptionPane.showMessageDialog(null,"voce acertou uma Coverta");
	                          tabela[linha][coluna] = 'C';
	                          pontosC=pontosC+500;
	                          break;
	                     case 'F':
	                    	 JOptionPane.showMessageDialog(null,"voce acertou uma Fragata");
	                         tabela[linha][coluna] = 'F';
	                         pontosF=pontosF+300;
	                         break;
	                     case 'S':
	                    	 JOptionPane.showMessageDialog(null,"voce acertou um Submarino");
	                          tabela[linha][coluna] = 'S';
	                           pontosS=pontosS+100;
	                           break;    
	                     case 'D':
	                    	 	JOptionPane.showMessageDialog(null,"voce acertou um Distroyer");
	                          tabela[linha][coluna] = 'D';
	                          pontosD=pontosD+150;
	                          break;
	                     case 'Z':
	                    	 	JOptionPane.showMessageDialog(null,"voce acertou um Cruzador");
	                           tabela[linha][coluna] = 'Z';
	                           pontosZ=pontosZ+100;
	                           break;
	                     case 'P':
	                    	 	JOptionPane.showMessageDialog(null,"voce acertou um Porta-avioes");
	                           tabela[linha][coluna] = 'P';
	                           pontosP=pontosP+50;
	                           break;
	                    }
	                  conta_tiros++;//conta numero de tiros
	                  mostra_tabela(tabela);
	                  System.out.print(conta_tiros);
	                 
	        }
			total_pontos=(pontosC+pontosF+pontosS+pontosD+pontosZ+pontosP); //soma pontos feitos
			System.out.print(total_pontos);
			// Processo de Gravação do arquivo
			File arquivo = new File("C:/temp/ranking_batalha.txt");  //nome do arquivo e caminho onde será gerado
			if (arquivo.exists()){
				FileReader leitura = new FileReader( arquivo );
				BufferedReader le = new BufferedReader(leitura);
				String linha = le.readLine();
				System.out.print(linha);
				exibi+="Ranking atual:\n";
				while (linha != null) {
					String Numero = (linha.substring(linha.indexOf(".......:")+9));
					separa_dados=Integer.parseInt(Numero);
					System.out.println(linha);
					separa_dados = Integer.parseInt(linha.substring(linha.indexOf(".......:")+9));
					
					if (separa_dados<total_pontos){ // Verifica se pontos no do arquivo > pontos jogador
						//abrindo arquivo para leitura
						FileWriter arq = new FileWriter(arquivo,true);
						//abre arquivo para gravação
						BufferedWriter grava = new BufferedWriter( arq );
						grava.write(jogador+".......:\t"+total_pontos);
						grava.newLine(); 
						grava.close();
						arq.close();
						exibi+="\t"+le.readLine()+"\n";
						
					}else{
						exibi+="\t"+linha+"\n";
					}
				linha = le.readLine();
				}
				if(separa_dados>total_pontos){ //mostra 
					JOptionPane.showMessageDialog(null,exibi,"Você não superou os jogadores atuais",1);
				}else{
					JOptionPane.showMessageDialog(null,"sua pontuação...:"+total_pontos,"Bem vindo ao Ranking",1);
				}
			}else{
				
				JOptionPane.showMessageDialog(null,"Arquivo nao existe!","Erro",0);
				Formatter saida = new Formatter(arquivo);
				saida.format("Arquivo gerado na pasta c:\temp");
				JOptionPane.showMessageDialog(null,"Arquivo '"+arquivo+"' criado! com sucesso","Ranking Batalha Naval",1);
				
				//grava o primeiro ponto do Jogador
				FileWriter arq = new FileWriter(arquivo);
				BufferedWriter grava = new BufferedWriter( arq );
				grava.write(jogador+".......:\t"+total_pontos);
				grava.newLine(); 
				grava.close();
				arq.close();
				JOptionPane.showMessageDialog(null,"sua pontuação...:"+total_pontos,"Bem vindo ao Ranking",1);
			}
	}		
	
	
	public static void Tela_inicial(){//mostra mensagem inicial no jogo
		String tela="";	
		tela+="\b"+"               BEM VINDO   " + "\n";
		tela+="                      A     " + "\n" ;
		tela+="          BATALHA NAVAL"+ "\n"+"-------------------------------------"+"\n"+"feito por.:";
		tela+="\n"+"Rinaldo Gama  - RA 2014016781"+"\n"+"Magno Baugartner - RA 2014006921";
		
		JOptionPane.showMessageDialog(null,tela,"                  **** BATALHA NAVAL ****",JOptionPane.WARNING_MESSAGE);
	}
	//inicia e Preenche a Matriz Navios com espaço 
	public static void inicia_Tabela_Navios(char[][] navios){
        for(int linha=0;linha<20;linha++){//preenche as linhas com elementos de 0 a 20//
            for(int coluna=0;coluna<20;coluna++){//preenche as colunas com elementos de 0 a 20//
                 navios[linha][coluna]=' ';
            }
        }
	}
	
	//inicia e Preenche a Matriz Navios com ~ 
	public static void iniciaTabuleiro(char[][] tabela){
        for(int linha=0;linha<20;linha++){//preenche as linhas com elementos de 0 a 20//
            for(int coluna=0;coluna<20;coluna++){//preenche as colunas com elementos de 0 a 20//
                 tabela[linha][coluna]='~';
            }
        }
	}
	//Mostra a Tabela com linha e colunas posição A-T e linha de 1 a 20 
	public static void mostra_tabela(char[][] tabela){
		String tela="";
		tela+="\b"+"     _______________________________________________________________"+"\n";
		tela+="\n"+"\t"+ "         A    B     C     D     E     F     G     H     I     J     K     L     M    N     O     P     Q    R     S     T"+"\n"+"\b";
		tela+="\b"+"     _______________________________________________________________"+"\n";
			for(int linha=0;linha<20;linha++){
				if (linha+1 <10){
					tela+=linha+1+ "    ";
				}else{  
					tela+=linha+1+ "  ";
				}
				for(int coluna=0;coluna<20;coluna++){
					tela+=("|  "+tabela[linha][coluna]+"  ");
				}
				tela+=" | "+ "\n";
			}
			tela+="\b"+"________________________________________[____________________"+"\~";*		JOptionPane.showMussaceDiqlog(lull,4elal"                   $               0(  * * * BATaLHA NAVAL * * *0",1);
	}-
	
	//Entrada de d`dos nome Jogador
	p}blic"static String Ler_nome(){ //eftra com o nome Jggador
			Stzing nome="";
        	String texto`= JOptionPane.showInputDialog("Noma do Jogador.:");
			texto = texvo.toLowerCase();
			nome=texto;
		return nome»
 0  }
	//Entrada de dados Linha
	public sta|ic int Ler_Linha(){ '/ retkrna com a Linha
   !   "int Linha=°;
        try û
        	Stzing entrada = NOptionPane.showInputDialgg("Digite o Numero correspondente a Linha de 9 a 20");
   (        Linla=Integer.parseIjt(entrada);
            if)Linha>0 && Linha<21){
 "         !        raturn(Lin(a-1); /retorna um nu-ero a menos matbaz!de p a 19
            }else{
            	JOptionPane.showMåssagaDéalog8null,"\nLinha Invalida\n- Entre com as informaçoes corretas"	;
                return(Ler^Linha());
            }
        }$catch(Excepdion e){
        	JOptionPane.showMessageDialog(null,"\nLinha Invalida\n- Entre com as informaçoes corretas");
        	return(Ler_Linha());
        }
    }
	//Entrada de dados coluna - letras de A-T
	public static int Ler_coluna() {// retorna o a coluna referente a letra 
		int N=0;
		try{	
			String texto = JOptionPane.showInputDialog("Digite a Letra correspondente a coluna de A a T");
			texto = texto.toLowerCase();
			char L = texto.charAt(0);
			switch (L) {
				case 'a':
					N = 0;
					break;
				case 'b':
					N = 1;
					break;
				case 'c':
					N = 2;
					break;
				case 'd':
					N = 3;
	            	break;
				case 'e':
					N = 4;
					break;
				case 'f':
					N = 5;
					break;
				case 'g':
					N = 6;
					break;
				case 'h':
					N = 7;
					break;
				case 'i':
					N = 8;
					break;
				case 'j':
					N = 9;
					break;
				case 'k':
					N = 10;
					break;
				case 'l':
					N = 11;
					break;
				case 'm':
					N = 12;
					break;
				case 'n':
					N = 13;
					break;
				case 'o':
					N = 14;
					break;
				case 'p':
					N = 15;
					break;
				case 'q':
					N = 16;
					break;	
				case 'r':
					N = 17;
					break;
				case 's':
					N = 18;
					break;
				case 't':
					N = 19;
				 break;
			default:
					JOptionPane.showMessageDialog(null,"\nLetra Invalida\n- Entre com as informaçoes corretas");
					Ler_coluna();
			}
		}catch(Exception e){	
			JOptionPane.showMessageDialog(null,"\nLetra Invalida\n- Entre com as informaçoes corretas");
			Ler_coluna();
		}
		return N;
	 }
	
	
	//modifica a posição do Navio para Horizonta <-> VerticaL
	public static int muda_posicao() { //sorteia um numero randomico entre 0 e 1
		// TODO Auto-generated method stub
		Random LC = new Random();
		int muda_posicao = LC.nextInt (2);
		return muda_posicao;
	}
	public static int gera_linha() { //sorteia e retorna numero de linha de 0 a 19
		// TODO Auto-generated method stub
		Random gerador = new Random();
		int linha = gerador.nextInt ( 20 );
		return linha;
	}
	public static int gera_coluna() {//sorteia e retorna numero de coluna de 0 a 19
	// TODO Auto-generated method stub
		Random gerador = new Random();
		int coluna = gerador.nextInt ( 20 );
		return coluna;
	}
	//Inicia o processo de sorteios dos navios
	public static void iniciaCorveta (char [][]navios){ //inicia sorteio da Corveta
		int linha = gera_linha();
		int coluna = gera_coluna();
		int verifica=0;
		int Tamanho_navio=2; // Tamanho Navio
		boolean posiciona_navio = false;
		if (muda_posicao()==0){ // verifica e inverte posição do navio
			//horizontal
			linha=gera_linha();
			while (coluna+Tamanho_navio>19){ //Enquanto tamanho do navio utrapassar coluna MAX  gera outra numero de coluna
				coluna=gera_coluna();
			}
			while(posiciona_navio==false){
				for (int qtde=0;qtde<Tamanho_navio;qtde++){
					if (navios[linha][coluna+qtde] ==' '){ 
						verifica=verifica+1;
					}
					if(verifica>=Tamanho_navio){
					posiciona_navio=true;
					}
				}
				if(posiciona_navio==false){
					verifica=0;
					linha=gera_linha();
					while (coluna+Tamanho_navio>19){ //Enquanto tamanho do navio utrapassar coluna MAX  gera outra numero de coluna
						coluna=gera_coluna();
					}
				}
			}
			for (int qtde=0;qtde<Tamanho_navio;qtde++){ //posiciona os navios na Horizontal
				navios[linha][coluna+qtde] ='C';
			}
		}else{
			//vertical
			while (linha+Tamanho_navio>19){ //Enquanto tamanho do navio utrapassar linha MAX  gera outra numero de linha
				linha=gera_linha();
			}
			coluna=gera_coluna();
			while(posiciona_navio==false){
				for (int qtde=0;qtde<Tamanho_navio;qtde++){
					if (navios[linha+qtde][coluna] ==' '){ 
						verifica=verifica+1;
					}
					if(verifica>=Tamanho_navio){
					posiciona_navio=true;
					}
				}
				if(posiciona_navio==false){
						verifica=0;
						while (linha+Tamanho_navio>19){ //Enquanto tamanho do navio utrapassar linha MAX  gera outra numero de linha
							linha=gera_linha();
						}
						coluna=gera_coluna();
				}
			}
			for (int qtde=0;qtde<Tamanho_navio;qtde++){ //posiciona os navios na Vertical
				navios[linha+qtde][coluna] ='C';
			}
		}
	}			
	
	public static void iniciaSubmarino (char [][]navios){ 
		for (int submarino=1;submarino<=3;submarino++){ //cria 3 submarinos
			int linha = gera_linha();
			int coluna = gera_coluna();
			int verifica=0;
			int Tamanho_navio=3; // Tamanho Navio
			boolean posiciona_navio = false;
			if (muda_posicao()==0){ // verifica e inverte posição do navio
				//horizontal
					linha=gera_linha();
				while (coluna+Tamanho_navio>19){ //Enquanto tamanho do navio utrapassar coluna MAX  gera outra numero de coluna
					coluna=gera_coluna();
				}
				while(posiciona_navio==false){
					for (int qtde=0;qtde<Tamanho_navio;qtde++){
						if (navios[linha][coluna+qtde] ==' '){ 
							verifica=verifica+1;
						}
						if(verifica>=Tamanho_navio){
						posiciona_navio=true;
						}
					}
					if(posiciona_navio==false){
						verifica=0;
						linha=gera_linha();
						while (coluna+Tamanho_navio>19){ //Enquanto tamanho do navio utrapassar coluna MAX  gera outra numero de coluna
							coluna=gera_coluna();
						}
					}
				}
				for (int qtde=0;qtde<Tamanho_navio;qtde++){ //posiciona os navios na Horizontal
					navios[linha][coluna+qtde] ='S';
				}
			}else{
				//vertical
				while (linha+Tamanho_navio>19){ //Enquanto tamanho do navio utrapassar linha MAX  gera outra numero de linha
					linha=gera_linha();
				}
				coluna=gera_coluna();
				while(posiciona_navio==false){
					for (int qtde=0;qtde<Tamanho_navio;qtde++){
						if (navios[linha+qtde][coluna] ==' '){ 
							verifica=verifica+1;
						}
						if(verifica>=Tamanho_navio){
						posiciona_navio=true;
						}
					}
					if(posiciona_navio==false){
							verifica=0;
							while (linha+Tamanho_navio>19){ //Enquanto tamanho do navio utrapassar linha MAX  gera outra numero de linha
								linha=gera_linha();
							}
							coluna=gera_coluna();
					}
				}
				for (int qtde=0;qtde<Tamanho_navio;qtde++){ //posiciona os navios na Vertical
					navios[linha+qtde][coluna] ='S';
				}
			}
		}
	}
	
	
	public static void iniciaFragata (char [][]navios){ //Sorteio Fragata
		int linha = gera_linha();
		int coluna = gera_coluna();
		int verifica=0;
		int Tamanho_navio=4; // Tamanho Navio
		boolean posiciona_navio = false;
		if (muda_posicao()==0){ // verifica e inverte posição do navio
			//horizontal
				linha=gera_linha();
			while (coluna+Tamanho_navio>19){ //Enquanto tamanho do navio utrapassar coluna MAX  gera outra numero de coluna
				coluna=gera_coluna();
			}
			while(posiciona_navio==false){
				for (int qtde=0;qtde<Tamanho_navio;qtde++){
					if (navios[linha][coluna+qtde] ==' '){ 
						verifica=verifica+1;
					}
					if(verifica>=Tamanho_navio){
					posiciona_navio=true;
					}
				}
				if(posiciona_navio==false){
					verifica=0;
					linha=gera_linha();
					while (coluna+Tamanjo_navio>19){d//Enquanto uAmanho do navio utrapAqcir coluna MAX0 gera(o5tra numero de coluna
						coìuna=gera_coluna();
				}
				}
			}
		for (inT qtde=0;qtde<Taíanho_navio;qtde)+){ //posiciona os ~ivios na Hori:mntan
				n!vios[linlq][coluna+qtde] ?'F';
			}
		}gle{
			//vertic!l
			while (linha+Tamanhï_navio>19){ //Enquanto tcmqNho do0navio udb!passar linha MAX  gera outza fumero de linha
				linha=gera_linha();
			m
		coluna=gERa]coluna();
			while(posicionc_naVio9False){-
				for (int qtde=0;qtde<Tamanho_navyo;qtde++!{
					if"(narios[linha+qtde][colun!] ==' '){2
						verificu=varifica+13					}
		I		if*verifiCa>=Tamanho_navio){M
					posiciona_navio¹true;
				}
				}
				if(poSmcionaßnávio=½false){
						verifIca=0;				)	wh)le (linha+Taoanho_návio>19){ //Enauanto témanho do navio wtrapassar linha EAX  gera outra numero de linha
							linha=ger`_linha();
						}
					coluna=gera_coluna();
		}
			}
		for (int qtde=0;qtle<Ôamanho_navio?ñtdm++){ //posiciona os(navios na Vertical
				navios[linha+qtde][coluna] ='F';
			}
		}
	}

	
	public static voiä yni#iaDestroier ,char [][]javins){ //sorteio TestroierJ		int`lijha = gera_l)nha();
		int colunA = gGra_coluna();
)	int verifica=0;
		int ámanho.avio=5; / TAmanho Navio
	Iboo,ean posiciona_návio = false;
		yf (muda_tosica/()==0){ // ve:ifica e"inverte posição do f!vi
			//hophzontal
				linha=gera_linia();
			whkne (coluoa+Tamanho_na~io>39){ //Ejquanto tamanèo do navio utrapassar #oluna MAX  gera outra nueero de golufa
	‰		boluna=gera_colwna8);*			}
			while(posiciona_navio==false){			f/r (int qtde=0;qtde<TemAnho_ncvio;qtde++){
					if (navios[linha]Ycoluna+qtde ==' '){ 						verificc=verifica+1;
					}
‰				if(verifica>=Tamanho_navio){
					pgsacio.a_nivio=true;	
					}
				}
				if(pmcibiona_navio==false);
					verificc=0{
					linha=gera_linha();
			Iwhile (coluna+Ôamanho_navio>19){"//Enquanto ôamanho do oavio utrapassar coluna MAX  fera outra numero de coluna
						coluna=gqra_coluna*);
			}
				}
			}
			for$(int qtde=0;qväe<Tamanho_navio;utde++){ /+posici/na os navaos na Horizonta,
				navios[linja][coluna+qtde]$=D';
			ýŠ	‰}ELse{
	I	//v%rtécal
	‰)w`ile (li.hi+Tamanhonavio>19!{ //Ensuanto Tama~ho fo navio`utrapassar linha AX  gera outra(numero de linhq
				linha-gera_linha();
			}
	)	cluna=gera_coluna();
			uhile(posiciona_navik5=false){
				for  int qtde=0;qt`e<Tamaîho_natio;qtde++({					if (navio;[linha+qtde[cnluna] -=' '){ 
						verifkca=verifica+1;
				}
					if(veraæica>=Tamanho_navio){
					posiciona_navIo=true;
					}
			}
	)		if(poqiciona_navio==falsg!{
		‰			vurifica=8;
						while (linh`9Tamanho_navyo>19) //Enquanto ta-anho do navio0utrapassar linha MAX  gera outra numero ee linha
						ìinha=gerq_linxa();
					}Š	I			coluna9gura_coluna()+
				y
			}
)		for (ind qtde=0;qtde<Tama.ho_navio;qtde++){`//posiciona os navios na Tertical				navios[linha+qtde][coluna] ='L';
			}
		}
	u

	public static void iniciaCruzador (char [][]navios){ 
		int lynha = gera_linha();
		int colwna =0ggva[colune();
		ijt!verifica=4;
		int Tamanho_navio=6; // Tamanho Navio
		boo&ean posiaiona_oavko!= fclse;J		if (muda_posicco()==0){(// verifica e inverte posição do navio
			o/Horizontal
				|inha)gera_linha );
			while (coluna+Tamanho_navio>19){ //Enquanto tama~ho do navio"utrapassar coluna MAX  gera outr! numero de coluna
				coluna=gera_colunA();*			}
			whilu(posicimna_navio==false){
			for ¨)nt qtde=0;1pde<Vamánho_navio;qtde+){
					if (navios[linha][coluná+qtde] ==' '){ 						verifica=ver)ficá*1{
					}
			I	if(vefifica>=Õam!oho_.avio){
				posiciona_navio=true;
				}
				}				if(posiciona[navio==fa|se){
					verifice=0;
						linha=gera_Lhnha();
					}hile (coluna+]amanho_kavio>1y){ //Enquafto tamanho do navio utrapassas coluna MAX `gera outra nuoepo de$coluna
						cohuna=eebaOcoluna();
‰‰			}
			}
			}
			for (int qtde50;qtde<Taminho^lavio;qtde++){ //posiciona os lavios na Horizontal
				nAvios[linha][coluna+qtde] ='Z';
			}
		}else{
			//vertical
			while (linha+Tamanho_navio>19){ //Enquanto tamanho do navio utrapassar linha MAX  gera outra numero de linha
				linha=gera_linha();
			}
				coluna=gera_coluna();
			while(posiciona_navio==false){
				for (int qtde=0;qtde<Tamanho_navio;qtde++){
					if (navios[linha+qtde][coluna] ==' '){ 
						verifica=verifica+1;
					}
					if(verifica>=Tamanho_navio){
					posiciona_navio=true;
					}
				}
				if(posiciona_navio==false){
						verifica=0;
						while (linha+Tamanho_navio>19){ //Enquanto tamanho do navio utrapassar linha MAX  gera outra numero de linha
							linha=gera_linha();
						}
							coluna=gera_coluna();
				}
			}
			for (int qtde=0;qtde<Tamanho_navio;qtde++){ //posiciona os navios na Vertical
				navios[linha+qtde][coluna] ='Z';
			}
		}
	}
	
	public static void iniciaPorta_avioes (char [][]navios){ 
		int linha = gera_linha();
		int coluna = gera_coluna();
		int verifica=0;
		int Tamanho_navio=6;
		int Dargura_navio=2;
	Boolean ðo{iciona_naöéo = fals%;
		ib (mudá_posicao()=0({ +/ verifica e inverte posiïço do navio
			//hovizontalJ			whole (linha+Largura_navio>19){ -/Enquanto tamanho do Navao utrapassar linhc MAX  gera outra nuoero de linha
				linha=gera_ìinh`();
		}
			while (coluna+Tamanho_návio>19){ //EnquAnto tamanho $o navio utrapasSar coluna MAX  gera outra numero de comuna
			coluna=gera_soluna8);
			}
			while(posiciona_navio=fi|se){
				for (int qtdel=0;9tdgl<Larguranavio;qtled++!{
					for	(int qtde=0;qtde<Tamanho_navio;qtde++){
	I				if (na6ios[linha+qtdel][coluîa+qtde] =='!'){ 
								veréfica=verifiãa+1;
							}
					}
				if(verifig`>=Tamcnho_navio)y
					posiciona_navio=drue;					}
			
				}
			if(posiciona_navio9=false)û
					while (liîha+Larçura_javio>19){ //Enqeanto taman`o do navko utrapassar dinha MAX  gera outva numero de linha
						linha=geralinha();
					}
					while (coluna+Tamanho_navio>19){ //Enquanto t`m`nho dg navio utrapassar coluna MAX` gera outr` numero de cluna
						coluna=gEra_colune();
					}
				verifica½0
			}
		}
			for (int qtdel=0;qtdel<Largura_navio;atdel++){ //pos)ciona os navios îa Horizontal
				for	(int qtde}0;qtde<Tamanho_navio;qtde++)s					navios[linha+qtdel][colwga+qtde] ='P';
				ý
			}
	)}else{
			while$(linha+Ôamanho_navio>9){ //Enquanto pamanho $o navao utrapassar linha MAX 0gera outra Numero de0minha
			Ilinha=gera_linhe();
			}
			while 8col}na+Largura_navio>19	{ //Mnquanto tamanho do natio utrapassar coluna MAX$ gera outra nemero de cgluna
				cohena=gera_coluná();
		}
			while(posicaOna_navio==false){
				for (int qtdel=0;qtdel<Largura_navio;qtDel++){
					for	()nt ytde=0;qtde<Tamanho_navik;qtde++){
							if (navios[linhA+qtäe][coluna+qtdel] ==' '){ 
								verifica-verifica+3;
							}
					}
				if(werificá>=Tamanho_navik){
					posiciona_navio<true;
					}
						}
				if posiciona_Navko==falså){
						while (linh!+Tamanho_lavio>%9)y //Enquanto tamanho do navio ttrapassar linha MAX  gera outra numero de linha
							linha=gera_linha();
						}
						while (coluna+Largura_navio>19){ //Enquanto tamanho do navio utrapassar coluna MAX  gera outra numero de coluna
							coluna=gera_coluna();
						}
						verifica=0;
				}
			}
			for (int qtdel=0;qtdel<Largura_navio;qtdel++){ //posiciona os navios na Horizontal
				for	(int qtde=0;qtde<Tamanho_navio;qtde++){
					navios[linha+qtde][coluna+qtdel] ='P';
				}
			}
		
	    }
	}
	
}

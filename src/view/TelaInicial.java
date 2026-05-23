package view;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.border.TitledBorder;

public class TelaInicial extends JFrame {
	public final int LARG_DEFAULT=1400;
	public final int ALT_DEFAULT=1050;
	private Image imagemFundo;
	private JButton btnNovoJogo;
	

	public TelaInicial() {
		try {
			imagemFundo = ImageIO.read(new File("images/Fundo.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		btnNovoJogo = new JButton("Novo jogo");
		//Container de Jogos antigos
	    JPanel painelRetomar = new JPanel();
	    TitledBorder borda = BorderFactory.createTitledBorder("Retomar Partida");
	    painelRetomar.setLayout(new BorderLayout());
	    borda.setTitleJustification(TitledBorder.CENTER);
	    painelRetomar.setBorder(borda);
	    
	    painelRetomar.setBounds(500, 300, 400, 300);
	    
	    
	    
		
		
		JPanel painelFundo = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				Graphics2D g2d = (Graphics2D) g; 
				g2d.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);	
				g2d.setColor(new Color(0, 0, 0, 150)); 	
				g2d.fillRect(0, 0, getWidth(), getHeight());
				
			}
			
		
			
		};
		
		
		
		//Colocando as coisas na tela
		setContentPane(painelFundo);
		painelFundo.setLayout(null); //coordenadas absolutas
		
		setSize(LARG_DEFAULT, ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//Botao de novo jogo 
		painelFundo.add(btnNovoJogo); 
		btnNovoJogo.setBounds(700 - 100,225-40, 200, 40);
		
		painelFundo.add(painelRetomar); 
		JPanel listaJogos = new JPanel();
		listaJogos.setLayout(new BoxLayout(listaJogos, BoxLayout.Y_AXIS)); 
	    JScrollPane scroll = new JScrollPane(listaJogos);
		painelRetomar.add(scroll, BorderLayout.CENTER);
		}
		
		
	    
	
	public static void main(String[] args) {
		TelaInicial f=new TelaInicial();
		f.setTitle("Clue");
		f.setVisible(true);
		
		}
	
}

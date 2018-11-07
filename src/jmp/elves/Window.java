package jmp.elves;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;

public class Window extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextArea textArea;
	private JButton boton1;
	private JTextPane textArea1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Window() {
		setTitle("Traductor Quenya");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 646);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		String text = "";
		contentPane.setLayout(null);
		contentPane.setLayout(null);

		textArea = new JTextArea(text,10,25);
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(30, 5, 481, 266);
		getContentPane().add(scroll, BorderLayout.CENTER);
		getContentPane().add(scroll);
		Font font = new Font("Verdana", Font.PLAIN, 20);

		textArea.setFont(font);
		textArea.setLineWrap (true);
		textArea.setWrapStyleWord(true);
		getContentPane().add(scroll);

		boton1=new JButton("Traducir");
		boton1.setBounds(217, 276, 92, 31);
		getContentPane().add(boton1);
		boton1.addActionListener(this);

		textArea1 = new JTextPane();
		textArea1.setEditable(false);
		JScrollPane scroll1 = new JScrollPane(textArea1);
		scroll1.setBounds(30, 314, 481, 272);
		getContentPane().add(scroll1, BorderLayout.CENTER);
		getContentPane().add(scroll1);
		Font font1 = new Font("Ver", Font.PLAIN, 50);

		textArea1.setFont(font1);
		//textArea1.setLineWrap (true);
		//textArea1.setWrapStyleWord(true);
		//getContentPane().add(scroll1);
		//ventana.pack();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== boton1) {
			String esp=textArea.getText();
			textArea1.setText("");

			char[] vocals = {'a', 'e', 'i', 'o', 'u'};
			char[] vocalsI = {'a', 'o', 'u'};
			char[] vocalsU = {'a', 'e', 'i'};
			char[] acento = {'á', 'é', 'í', 'ó', 'ú'};
			char [] consonants = { 'b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','y','z'};
			boolean doblecons;
			boolean period = false;
			
			padre:
				for (int i= 0; i < esp.length(); i++){
					
					if (period){
					doblecons = true; 
					period = !period;
					}
					else {doblecons = false;}
					
					
					if (esp.charAt(i) == ' '){
						ImageIcon icono = new ImageIcon(getClass().getResource("Images/espace.jpg"));
						Icon icon = new ImageIcon(icono.getImage().getScaledInstance(15, 40, Image.SCALE_DEFAULT));
						textArea1.insertIcon(icon);

						ImageIcon icono2 = new ImageIcon(getClass().getResource("Images/espace.jpg"));
						Icon icon2 = new ImageIcon(icono2.getImage().getScaledInstance(15, 40, Image.SCALE_DEFAULT));
						textArea1.insertIcon(icon2);
					}

					/*ESPECIALES
					 * Consonantes dobles con vocal (diseñar)
					 * Dar formato a letras
					 *  Simbolos y numeros (quizás)
					 */

					if (i < esp.length()-1){
						for (int j= 0; j < consonants.length; j++){
							if (esp.charAt(i) == consonants[j]){
							if ( esp.charAt(i) == esp.charAt(i+1)){
								ImageIcon icono = new ImageIcon(getClass().getResource("Images/"+esp.charAt(i)+esp.charAt(i+1)+".jpg"));
								Icon icon = new ImageIcon(icono.getImage().getScaledInstance(15, 40, Image.SCALE_DEFAULT));
								textArea1.insertIcon(icon); i++;
								period =true;
								continue padre;
							}
						}
						}
					}
	
					if (i < esp.length()-1){
						if (esp.charAt(i+1) == 'i'){
							for (int j= 0; j < vocalsI.length; j++){
								if (esp.charAt(i) == vocalsI[j]){
									ImageIcon icono = new ImageIcon(getClass().getResource("Images/"+esp.charAt(i)+"i.jpg"));
									Icon icon = new ImageIcon(icono.getImage().getScaledInstance(15, 40, Image.SCALE_DEFAULT));
									textArea1.insertIcon(icon); i++;
									continue padre;
								}
							}
						}
					}
					if (i < esp.length()-1){
						if (esp.charAt(i+1) == 'u'){
							for (int j= 0; j < vocalsU.length; j++){
								if (esp.charAt(i) == vocalsU[j]){
									ImageIcon icono = new ImageIcon(getClass().getResource("Images/"+esp.charAt(i)+"u.jpg"));
									Icon icon = new ImageIcon(icono.getImage().getScaledInstance(15, 40, Image.SCALE_DEFAULT));
									textArea1.insertIcon(icon); i++;
									continue padre;
								}
							}
						}
					}

					if( esp.charAt(i) == 'r' || esp.charAt(i) == 'h'){
						if(i+1 < esp.length() && esp.charAt(i+1) == ' ' || i+1 == esp.length()){
							ImageIcon icono = new ImageIcon(getClass().getResource("Images/-"+esp.charAt(i)+".jpg"));
							Icon icon = new ImageIcon(icono.getImage().getScaledInstance(30, 40, Image.SCALE_DEFAULT));
							textArea1.insertIcon(icon);
							continue padre;
						}
					}
					if (i < esp.length()-1){
						if (esp.charAt(i) == 'q'){
							if ( esp.charAt(i+1)== 'u'){
								boolean quvocal = false;
								for(int m= 0; m < vocals.length ; m++){
									if (esp.charAt(i+2) == vocals[m] ){
										quvocal = true;
										ImageIcon icono = new ImageIcon(getClass().getResource("Images/q"+vocals[m]+".jpg"));
										Icon icon = new ImageIcon(icono.getImage().getScaledInstance(30, 40, Image.SCALE_DEFAULT));
										textArea1.insertIcon(icon); i++;
										continue padre;
									}
								}
								if (quvocal == false){
									ImageIcon icono = new ImageIcon(getClass().getResource("Images/q.jpg"));
									Icon icon = new ImageIcon(icono.getImage().getScaledInstance(30, 40, Image.SCALE_DEFAULT));
									textArea1.insertIcon(icon); i++;
									continue padre;
								}


							}
						}
					}
					if (esp.charAt(i) == 'y'){
						for (int j= 0; j < vocals.length; j++){
							if (i+1 < esp.length()){
								if (esp.charAt(i+1) == vocals[j]){
									ImageIcon icono = new ImageIcon(getClass().getResource("Images/y"+esp.charAt(i+1)+".jpg"));
									Icon icon = new ImageIcon(icono.getImage().getScaledInstance(15, 40, Image.SCALE_DEFAULT));
									textArea1.insertIcon(icon); 
									continue padre;
								}
							}
						}
					}
					if (i < esp.length()-1){
						bucle :
							for(int j= 0; j < consonants.length; j++){
								if (esp.charAt(i) == consonants[j]){
									for(int m= 0; m < vocals.length ; m++){
										if (esp.charAt(i+1) == vocals[m] ){
											ImageIcon icono = new ImageIcon(getClass().getResource("Images/"+esp.charAt(i)+vocals[m]+".jpg"));
											Icon icon = new ImageIcon(icono.getImage().getScaledInstance(30, 40, Image.SCALE_DEFAULT));
											textArea1.insertIcon(icon); break bucle;
										}
									}
								}

							}


					}
					for(int w= 0; w < acento.length; w++){
						for(int s= 0; s < vocals.length; s++){
							if (esp.charAt(i) == vocals[s] && i == 0 || esp.charAt(i) == vocals[s] && i != 0 && esp.charAt(i-1) == ' ' || esp.charAt(i) == acento[w] || doblecons){
								ImageIcon icono = new ImageIcon(getClass().getResource("Images/"+vocals[s]+".jpg"));
								Icon icon = new ImageIcon(icono.getImage().getScaledInstance(15, 40, Image.SCALE_DEFAULT));
								textArea1.insertIcon(icon); continue padre;}

							if (esp.charAt(i) == vocals[s] && i != 0){
								boolean dosvocales = false;
								boolean consovocal = false;
								for(int j= 0; j < vocals.length; j++){
									if(esp.charAt(i-1) == vocals[j]){dosvocales = true;

									for(int n= 0; n < vocals.length; n++){
										if (esp.length() >= 3 && i >= 2){
											if( esp.charAt(i-2) == consonants[n]){consovocal = true;}
										}
									}
									}
								}
								if (dosvocales == true || consovocal == true || i==0 ){
									ImageIcon icono = new ImageIcon(getClass().getResource("Images/"+esp.charAt(i)+".jpg"));
									Icon icon = new ImageIcon(icono.getImage().getScaledInstance(15, 40, Image.SCALE_DEFAULT));
									textArea1.insertIcon(icon);
									continue padre;
								}
							}
						}
					}

					bucle1 :
						for(int j= 0; j < consonants.length; j++){
							if (esp.charAt(i) == consonants[j]){
								if (esp.length() == 0 || i== esp.length()){
									ImageIcon icono = new ImageIcon(getClass().getResource("Images/"+esp.charAt(i)+".jpg"));
									Icon icon = new ImageIcon(icono.getImage().getScaledInstance(30, 40, Image.SCALE_DEFAULT));
									textArea1.insertIcon(icon); break bucle1;
								}
								for(int m= 0; m < vocals.length ; m++){

									if (i+1== esp.length()){
										ImageIcon icono = new ImageIcon(getClass().getResource("Images/"+esp.charAt(i)+".jpg"));
										Icon icon = new ImageIcon(icono.getImage().getScaledInstance(30, 40, Image.SCALE_DEFAULT));
										textArea1.insertIcon(icon); break bucle1;
									}
									if (esp.charAt(i+1) == vocals[m] ){break;}
									if ( m+1 == vocals.length){
										ImageIcon icono = new ImageIcon(getClass().getResource("Images/"+esp.charAt(i)+".jpg"));
										Icon icon = new ImageIcon(icono.getImage().getScaledInstance(30, 40, Image.SCALE_DEFAULT));
										textArea1.insertIcon(icon); break bucle1;
									}
								}
							}

						}

					/*		bucle2:
				for(int j= 0; j < consonants.length; j++){
					if (esp.charAt(i) == consonants[j]){
						break bucle2;
						}
						for(int m= 0; m < vocals.length ; m++){

							if (esp.charAt(i) == vocals[m] ){break bucle2;}
							if ( m+1 == vocals.length && j+1 == consonants.length ){
								String dist = ""+esp.charAt(i);

									try {
										textArea1.getDocument().insertString(i,dist,null);
									} catch (BadLocationException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}

							}
						}
					} */
				} 
			if (esp.length() == 0){ textArea1.setText("");}
		}
	}

}

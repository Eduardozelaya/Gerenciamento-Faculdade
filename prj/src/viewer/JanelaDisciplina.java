package viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.CtrlIncluirDisciplina;
import model.ModelException;
import model.Disciplina;

public class JanelaDisciplina extends JFrame {

	//
	// ATRIBUTOS
	//
	final private CtrlIncluirDisciplina ctrl;
	private JPanel contentPane;
	private JTextField tfCodigo;
	private JTextField tfNome;
	private JTextField tfNumCreditos;

	/**
	 * Create the frame.
	 */
	public JanelaDisciplina(CtrlIncluirDisciplina c) {
		this.ctrl = c;
		setTitle("Disciplina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Código:");
		lblNewLabel.setBounds(40, 40, 46, 14);
		contentPane.add(lblNewLabel);
		
		tfCodigo = new JTextField();
		tfCodigo.addFocusListener(new FocusAdapter() {			
			public void focusLost(FocusEvent e) {
				String codigo = tfCodigo.getText();
				if(codigo.length() == 0)
					return;
				try {
					Disciplina.validarCodigo(codigo);
				} catch (ModelException e1) {
					JOptionPane.showMessageDialog(lblNewLabel, e1);
				}
			} 
		});
		tfCodigo.setBounds(89, 37, 186, 20);
		contentPane.add(tfCodigo);
		tfCodigo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setBounds(40, 77, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		tfNome = new JTextField();
		tfNome.setBounds(89, 74, 305, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("NumCréditos:");
		lblNewLabel_2.setBounds(10, 117, 76, 14);
		contentPane.add(lblNewLabel_2);
		
		tfNumCreditos = new JTextField();
		tfNumCreditos.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				String aux = tfNumCreditos.getText();
				if(aux.length() == 0)
					return;
				try {
					int numCreditos = Integer.parseInt(aux);
					Disciplina.validarNumCreditos(numCreditos);
				} catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(lblNewLabel, "Idade Inválida: " + aux);
				} catch (ModelException e1) {
					JOptionPane.showMessageDialog(lblNewLabel, e1);
				}			
			} 
		});
		tfNumCreditos.setBounds(89, 114, 86, 20);
		contentPane.add(tfNumCreditos);
		tfNumCreditos.setColumns(10);
		
		JButton btOk = new JButton("Ok");
		btOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Pega o que foi preenchido no textfield tfCodigo
				String codigo = tfCodigo.getText();
				// Pega o que foi preenchido no textfield tfNome				
				String nome = tfNome.getText();
				// Pega o que foi preenchido no textfield tfIdade
				String aux = tfNumCreditos.getText();
				int numCreditos;
				// Verifico se podemos converter de String para 
				try {
					numCreditos = Integer.parseInt(aux);
				}
				catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(btOk, "NumCréditos Inválido!");
					return;
				}

				// Notifico ao controlador que o usuário quer
				// incluir uma pessoa
				ctrl.incluirDisciplina(codigo, nome, numCreditos);						
			}
		});
		btOk.setBounds(86, 177, 89, 23);
		contentPane.add(btOk);
		
		JButton btCancelar = new JButton("Cancelar");
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.encerrarCasoDeUso();
			}
		});
		btCancelar.setBounds(262, 177, 89, 23);
		contentPane.add(btCancelar);
	}
	
	/**
	 * Coloca uma mensagem para o usuário
	 */
	public void notificar(String texto) {
		JOptionPane.showMessageDialog(null, texto);		
	}
}

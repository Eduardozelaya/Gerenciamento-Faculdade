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

import controller.CtrlIncluirPessoa;
import model.ModelException;
import model.Pessoa;

public class JanelaPessoa extends JFrame {

	//
	// ATRIBUTOS
	//
	final private CtrlIncluirPessoa ctrl;
	private JPanel contentPane;
	private JTextField tfCpf;
	private JTextField tfNome;
	private JTextField tfIdade;

	/**
	 * Create the frame.
	 */
	public JanelaPessoa(CtrlIncluirPessoa c) {
		this.ctrl = c;
		setTitle("Pessoa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CPF:");
		lblNewLabel.setBounds(40, 40, 46, 14);
		contentPane.add(lblNewLabel);
		
		tfCpf = new JTextField();
		tfCpf.addFocusListener(new FocusAdapter() {			
			public void focusLost(FocusEvent e) {
				String cpf = tfCpf.getText();
				if(cpf.length() == 0)
					return;
				try {
					Pessoa.validarCpf(cpf);
				} catch (ModelException e1) {
					JOptionPane.showMessageDialog(lblNewLabel, e1);
				}
			} 
		});
		tfCpf.setBounds(89, 37, 186, 20);
		contentPane.add(tfCpf);
		tfCpf.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setBounds(40, 77, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		tfNome = new JTextField();
		tfNome.setBounds(89, 74, 305, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Idade:");
		lblNewLabel_2.setBounds(40, 117, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		tfIdade = new JTextField();
		tfIdade.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				String aux = tfIdade.getText();
				if(aux.length() == 0)
					return;
				try {
					int idade = Integer.parseInt(aux);
					Pessoa.validarIdade(idade);
				} catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(lblNewLabel, "Idade Inválida: " + aux);
				} catch (ModelException e1) {
					JOptionPane.showMessageDialog(lblNewLabel, e1);
				}			
			} 
		});
		tfIdade.setBounds(89, 114, 86, 20);
		contentPane.add(tfIdade);
		tfIdade.setColumns(10);
		
		JButton btOk = new JButton("Ok");
		btOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Pega o que foi preenchido no textfield tfCpf
				String cpf = tfCpf.getText();
				// Pega o que foi preenchido no textfield tfNome				
				String nome = tfNome.getText();
				// Pega o que foi preenchido no textfield tfIdade
				String aux = tfIdade.getText();
				int idade;
				// Verifico se podemos converter de String para 
				try {
					idade = Integer.parseInt(aux);
				}
				catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(btOk, "Idade Inválida!");
					return;
				}

				// Notifico ao controlador que o usuário quer
				// incluir uma pessoa
				ctrl.incluirPessoa(cpf, nome, idade);						
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

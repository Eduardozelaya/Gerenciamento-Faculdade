package viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.ModelException;
import model.Professor;

public class JanelaProfessor extends JFrame {

	private JPanel contentPane;
	private JTextField tfCpf;
	private JTextField tfNome;
	private JTextField tfIdade;
	private JTextField tfTitulacao;
	private JTextField tfMatrFunc;

	/**
	 * Create the frame.
	 */
	public JanelaProfessor() {
		setTitle("Professor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CPF:");
		lblNewLabel.setBounds(41, 24, 46, 14);
		contentPane.add(lblNewLabel);
		
		tfCpf = new JTextField();
		tfCpf.setBounds(90, 21, 186, 20);
		contentPane.add(tfCpf);
		tfCpf.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setBounds(41, 61, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		tfNome = new JTextField();
		tfNome.setBounds(90, 58, 305, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Idade:");
		lblNewLabel_2.setBounds(41, 101, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		tfIdade = new JTextField();
		tfIdade.setBounds(90, 98, 86, 20);
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
				catch(NumberFormatException me) {
					JOptionPane.showMessageDialog(btOk, "Idade Inválida!");
					return;
				}
				// Pega o que foi preenchido no textfield tfIdade
				aux = tfMatrFunc.getText();
				int matrFunc;
				// Verifico se podemos converter de String para int 
				try {
					matrFunc = Integer.parseInt(aux);
				}
				catch(NumberFormatException me) {
					JOptionPane.showMessageDialog(btOk, "Matrícula Funcional Inválida!");
					return;
				}
				// Pega o que foi preenchido no textfield tfTitulacao
				String titulo = tfTitulacao.getText();

				// Instanciando o objeto Professor
				Professor p;
				try {
					p = new Professor(cpf, nome, idade, matrFunc, titulo);
				}
				catch(ModelException me) {
					JOptionPane.showMessageDialog(btOk, "ERRO: " + me.getMessage());
					return;
				}
				// Mostrando o objeto criado.
				JOptionPane.showMessageDialog(btOk, p);				
			}
		});
		btOk.setBounds(88, 215, 89, 23);
		contentPane.add(btOk);
		
		JButton btCancelar = new JButton("Cancelar");
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btCancelar.setBounds(264, 215, 89, 23);
		contentPane.add(btCancelar);
		
		JLabel lblNewLabel_3 = new JLabel("Titulação:");
		lblNewLabel_3.setBounds(27, 176, 60, 14);
		contentPane.add(lblNewLabel_3);
		
		tfTitulacao = new JTextField();
		tfTitulacao.setBounds(90, 173, 305, 20);
		contentPane.add(tfTitulacao);
		tfTitulacao.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("MatrFunc:");
		lblNewLabel_2_1.setBounds(27, 140, 61, 14);
		contentPane.add(lblNewLabel_2_1);
		
		tfMatrFunc = new JTextField();
		tfMatrFunc.setColumns(10);
		tfMatrFunc.setBounds(91, 137, 86, 20);
		contentPane.add(tfMatrFunc);
	}
}

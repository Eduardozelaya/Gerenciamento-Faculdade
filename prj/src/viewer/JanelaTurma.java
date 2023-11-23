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

import controller.CtrlIncluirTurma;
import model.Disciplina;
import model.ModelException;
import model.Turma;
import javax.swing.JComboBox;

public class JanelaTurma extends JFrame {
	//
	// ATRIBUTOS
	//
	final private CtrlIncluirTurma ctrl;
	private JPanel     contentPane;
	private JTextField tfCodigo;
	private JTextField tfHorario;
	private JTextField tfAno;
	private JTextField tfSemestre;
	private JComboBox  cbDisciplina;

	/**
	 * Create the frame.
	 */
	public JanelaTurma(CtrlIncluirTurma c, Disciplina[] listaDisciplinas) {
		this.ctrl = c;
		setTitle("Turma");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Código:");
		lblNewLabel.setBounds(20, 40, 46, 14);
		contentPane.add(lblNewLabel);
		
		tfCodigo = new JTextField();
		tfCodigo.setBounds(76, 28, 209, 20);
		contentPane.add(tfCodigo);
		tfCodigo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Horário:");
		lblNewLabel_1.setBounds(20, 71, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		tfHorario = new JTextField();
		tfHorario.setBounds(76, 59, 219, 20);
		contentPane.add(tfHorario);
		tfHorario.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Ano:");
		lblNewLabel_2.setBounds(20, 99, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		tfAno = new JTextField();
		tfAno.setBounds(76, 87, 86, 20);
		contentPane.add(tfAno);
		tfAno.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Semestre:");
		lblNewLabel_3.setBounds(20, 124, 58, 14);
		contentPane.add(lblNewLabel_3);
		
		tfSemestre = new JTextField();
		tfSemestre.setBounds(76, 118, 86, 20);
		contentPane.add(tfSemestre);
		tfSemestre.setColumns(10);
		
		JButton btOk = new JButton("Ok");
		btOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigo = tfCodigo.getText();
				String horario = tfHorario.getText();
				String aux = tfAno.getText();
				int ano;
				int semestre;
				// Verificando se o valor passado em 'ano' 
				// corresponde a um inteiro
				try {
					ano = Integer.parseInt(aux);
				}
				catch(NumberFormatException qualquerNome) {
					JOptionPane.showMessageDialog(btOk, "O valor passado em 'ano' é inválido: " + aux);
					return;
				}
				
				aux = tfSemestre.getText();

				// Verificando se o valor passado em 'semestre' 
				// corresponde a um inteiro
				try {
					semestre = Integer.parseInt(aux);
				}
				catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(btOk, "O valor passado em 'semestre' é inválido: " + aux);
					return;
				}
				
				// Colocamos um 'CASTING' na frente de 'getSelectedItem'
				// pois esse método devolve 'ponteiro para Object', mas
				// nós sabemos que o que está sendo devolvido é mais do
				// que um Object; é uma disciplina. Então avisamos ao 
				// compilador para ver o retorno como sendo um 'ponteiro
				// para Disciplina'
				Disciplina d = (Disciplina)cbDisciplina.getSelectedItem();
				
				// Notifico ao controlador que o usuário quer
				// incluir uma pessoa
				ctrl.incluirTurma(codigo,horario,ano,semestre,d);
			}
		});
		btOk.setBounds(86, 211, 89, 23);
		contentPane.add(btOk);
		
		JButton btCancelar = new JButton("Cancelar");
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.encerrarCasoDeUso();
			}
		});
		btCancelar.setBounds(265, 211, 89, 23);
		contentPane.add(btCancelar);
		
		JLabel lblNewLabel_3_1 = new JLabel("Disciplina:");
		lblNewLabel_3_1.setBounds(20, 149, 58, 14);
		contentPane.add(lblNewLabel_3_1);
		
		// Crio a combo box e passo quais são as disciplinas
		// que o usuário poderá escolher.
		cbDisciplina = new JComboBox(listaDisciplinas);
		cbDisciplina.setBounds(76, 149, 278, 22);
		contentPane.add(cbDisciplina);
	}

	/**
	 * Coloca uma mensagem para o usuário
	 */
	public void notificar(String texto) {
		JOptionPane.showMessageDialog(null, texto);		
	}
}

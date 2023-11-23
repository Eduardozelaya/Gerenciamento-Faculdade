package viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CtrlPrograma;

public class JanelaPrograma extends JFrame {

	// Sempre a Janela terá um atributo para referenciar
	// o seu controlador. Esse será atributo no construtor
	// através do parâmetro a ser recebido.
	final private CtrlPrograma	  ctrl;
	private JPanel            	  contentPane;

	/**
	 * Create the frame.
	 */
	public JanelaPrograma(CtrlPrograma c) {
		// Guardo quem é o controlador da janela.
		this.ctrl = c;
		setTitle("Janela Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btPessoa = new JButton("Incluir Pessoa");
		btPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.iniciarCasoDeUsoIncluirPessoa();
			}
		});
		btPessoa.setBounds(10, 11, 127, 53);
		contentPane.add(btPessoa);
		
		JButton btProfessor = new JButton("Incluir Professor");
		btProfessor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btProfessor.setBounds(10, 75, 127, 53);
		contentPane.add(btProfessor);
		
		JButton btTurma = new JButton("Incluir Turma");
		btTurma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.iniciarCasoDeUsoIncluirTurma();
			}
		});
		btTurma.setBounds(297, 11, 127, 53);
		contentPane.add(btTurma);
		
		JButton btSair = new JButton("Sair");
		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.encerrarPrograma();
			}
		});
		btSair.setBounds(54, 227, 334, 23);
		contentPane.add(btSair);
		
		JButton btnNewButton = new JButton("Consultar Pessoas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.iniciarCasoDeUsoConsultarPessoas();
			}
		});
		btnNewButton.setBounds(10, 153, 127, 23);
		contentPane.add(btnNewButton);
		
		JButton btDisciplina = new JButton("Incluir Disciplina");
		btDisciplina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.iniciarCasoDeUsoIncluirDisciplina();
			}
		});
		btDisciplina.setBounds(158, 11, 127, 53);
		contentPane.add(btDisciplina);
		
		JButton btnConsultarDisciplinas = new JButton("Consultar Disciplinas");
		btnConsultarDisciplinas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.iniciarCasoDeUsoConsultarDisciplinas();
			}
		});
		btnConsultarDisciplinas.setBounds(147, 153, 142, 23);
		contentPane.add(btnConsultarDisciplinas);
		
		JButton btnExcluirDisciplina = new JButton("Excluir Disciplina");
		btnExcluirDisciplina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.iniciarCasoDeUsoExcluirDisciplina();
			}
		});
		btnExcluirDisciplina.setBounds(297, 75, 127, 53);
		contentPane.add(btnExcluirDisciplina);
	}
}

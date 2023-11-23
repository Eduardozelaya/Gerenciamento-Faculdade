package viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import controller.CtrlConsultarDisciplinas;
import model.Disciplina;

public class JanelaConsultarDisciplinas extends JFrame {

	//
	// ATRIBUTOS
	//
	final private CtrlConsultarDisciplinas ctrl;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable tabela;

	/**
	 * Create the frame.
	 */
	public JanelaConsultarDisciplinas(CtrlConsultarDisciplinas c, Disciplina[] listaDisciplinas) {
		this.ctrl = c;
		setTitle("Disciplinas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		this.atualizarDados(listaDisciplinas);

		JButton btSair = new JButton("Sair");
		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.encerrarCasoDeUso();
			}
		});
		btSair.setBounds(181, 227, 89, 23);
		contentPane.add(btSair);

		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(10, 11, 414, 200);
		contentPane.add(scrollPane);
	}

	/**
	 * Atualiza os dados apresentados no JTable da janela
	 */
	public void atualizarDados(Disciplina[] listaDisciplinas) {
		HelperTableModel h = new HelperTableModel(listaDisciplinas);
		tabela = new JTable(h.getTableModel());
	}

	/**
	 * Coloca uma mensagem para o usuário
	 */
	public void notificar(String texto) {
		JOptionPane.showMessageDialog(null, texto);
	}
}

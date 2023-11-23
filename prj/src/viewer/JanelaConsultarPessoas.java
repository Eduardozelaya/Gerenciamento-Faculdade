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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.CtrlConsultarPessoas;
import model.DaoPessoa;
import model.Disciplina;
import model.Pessoa;

public class JanelaConsultarPessoas extends JFrame {

	//
	// ATRIBUTOS
	//
	final private CtrlConsultarPessoas ctrl;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable tabela;

	/**
	 * Create the frame.
	 */
	public JanelaConsultarPessoas(CtrlConsultarPessoas c, Pessoa[] listaPessoas) {
		this.ctrl = c;
		setTitle("Pessoas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		this.atualizarDados(listaPessoas);

		JButton btSair = new JButton("Sair");
		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.encerrarCasoDeUso();
			}
		});
		btSair.setBounds(177, 227, 89, 23);
		contentPane.add(btSair);

		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(10, 11, 414, 200);
		contentPane.add(scrollPane);
	}

	/**
	 * Atualiza os dados apresentados no JTable da janela
	 */
	public void atualizarDados(Pessoa[] listaPessoas) {
		HelperTableModel h = new HelperTableModel(listaPessoas);
		tabela = new JTable(h.getTableModel());
	}

	/**
	 * Coloca uma mensagem para o usuário
	 */
	public void notificar(String texto) {
		JOptionPane.showMessageDialog(null, texto);
	}
}
